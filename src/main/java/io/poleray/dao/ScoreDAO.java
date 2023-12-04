package io.poleray.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.InsertOneOptions;
import io.poleray.model.Score;
import io.poleray.model.Section;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class ScoreDAO {

    public static final String DB_NAME = "dreamscore";
    public static final String SCORE_COLLECTION = "score";

    @Value("${spring.data.mongodb.uri}")
    String connectionString;

    MongoClient mongoClient;

    @PostConstruct
    void initDbConnection() {
        mongoClient = MongoClients.create(connectionString);
    }

    public List<Score> getScoreBySection(String app, String section, Integer position, Integer count) {
        Document query = getFilterQuery(app, section);
        FindIterable<Document> documents = mongoClient.getDatabase(DB_NAME).getCollection(SCORE_COLLECTION).find(query).sort(new Document("score", -1));
        documents.skip(position).limit(count);
        AtomicInteger aPos = new AtomicInteger(position);
        return documents.into(new ArrayList<>()).stream().limit(count).map(getMapper()).peek(score -> score.setPosition(aPos.incrementAndGet())).collect(Collectors.toList());
    }

    private static Function<Document, Score> getMapper() {
        return document -> {
            Score score = new Score();
            score.setId(document.get("_id").toString());
            score.setName(document.getString("name"));
            score.setScore(document.getLong("score"));
            score.setDate(document.getDate("date"));
            score.setSubscription(document.getString("subscription"));
            score.setSection(document.getString("section"));
            score.setApp(document.getString("app"));
            score.setPosition(document.getInteger("position"));
            score.setSelected(document.getBoolean("selected"));
            return score;
        };
    }

    private static Document getFilterQuery(String app, String section) {
        return Document.parse("{section: \"" + section + "\", app: \"" + app + "\"}");
    }

    public String addScore(String app, String section, Score score) {
        Document document = new Document();
        document.append("name", score.getName());
        document.append("score", score.getScore());
        document.append("date", score.getDate());
        document.append("subscription", score.getSubscription());
        document.append("position", score.getPosition());
        document.append("section", section);
        document.append("app", app);
        return mongoClient.getDatabase(DB_NAME)
                .getCollection(SCORE_COLLECTION)
                .insertOne(document, new InsertOneOptions().bypassDocumentValidation(true)).getInsertedId().asObjectId().getValue().toHexString();
    }

    /**
     * Get score around record with id.
     * Get upper and lower scores around record with id.
     * @param app
     * @param section
     * @param id
     * @param count
     */
    public List<Score> getScoreById(String app, String section, String id, Integer count) {
        Document query = getFilterQuery(app, section);
        FindIterable<Document> iterable = mongoClient.getDatabase(DB_NAME)
                .getCollection(SCORE_COLLECTION)
                .find(query)
                .sort(new Document("score", -1));
        Queue<Document> queue = new CircularFifoQueue<>(count*2+1);
        AtomicInteger limit = new AtomicInteger(-1);
        AtomicInteger position = new AtomicInteger(1);
        iterable.forEach(document -> {
            if (limit.get() <= count) {
                document.put("position", position.getAndIncrement());
                if (document.get("_id").toString().equals(id)) {
                    limit.set(0);
                    document.put("selected", true);
                }
                if (limit.get() >= 0) {
                    limit.getAndIncrement();
                }
                queue.add(document);
            }
        });
        return queue.stream().map(getMapper()).collect(Collectors.toList());
    }

    public List<Score> getUserScore(String app, String section, String user, String id, int count) {
        Document query = getFilterQuery(app, section);
        query.append("name", user);
        FindIterable<Document> iterable = mongoClient.getDatabase(DB_NAME)
                .getCollection(SCORE_COLLECTION)
                .find(query)
                .sort(new Document("date", -1));
        try (MongoCursor<Document> iterator = iterable.iterator()) {
            if (id!=null) {
                // Scip until we find the record with id
                while (iterator.hasNext()) {
                    Document document = iterator.next();
                    if (document.get("_id").toString().equals(id)) {
                        break;
                    }
                }
            }
            List<Score> scores = new ArrayList<>();
            while (iterator.hasNext()) {
                Document document = iterator.next();
                scores.add(getMapper().apply(document));
                if (count-- <= 0) {
                    break;
                }
            }
            return scores;
        }
    }

    public List<Section> getAppSections(String app) {
        Document query = new Document();
        query.append("app", app);
        FindIterable<Document> documents = mongoClient.getDatabase(DB_NAME).getCollection(SCORE_COLLECTION).find(query);
        Set<String> sections = new HashSet<>();
        documents.forEach(document -> {
            sections.add(document.getString("section"));
        });
        return sections.stream().map(section -> new Section().id(section).name(section)).collect(Collectors.toList());
    }
}
