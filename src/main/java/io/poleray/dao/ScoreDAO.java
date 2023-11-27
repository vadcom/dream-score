package io.poleray.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.model.InsertOneOptions;
import io.poleray.model.Score;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class ScoreDAO {

    public static final String DREAMSCORE = "dreamscore";
    public static final String SCORE = "score";
    MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

    FindIterable<Document> getListOfScores(String section) {
        return mongoClient.getDatabase(DREAMSCORE).getCollection(section).find();
    }

    public List<Score> getScoreBySection(String app, String section, Integer position, Integer count) {
        Document query = getFilterQuery(app, section);
        FindIterable<Document> documents = mongoClient.getDatabase(DREAMSCORE).getCollection(SCORE).find(query).sort(new Document("score", 1));
        documents.skip(position).limit(count);
        AtomicInteger aPos = new AtomicInteger(position);
        return documents.into(new ArrayList<>()).stream().limit(count).map(document -> {
            Score score = new Score();
            score.setId(document.get("_id").toString());
            score.setName(document.getString("name"));
            score.setScore(document.getLong("score"));
            score.setDate(document.getDate("date"));
            score.setSubscription(document.getString("subscription"));
            score.setSection(section);
            score.setApp(app);
            score.setPosition(aPos.getAndIncrement());
            return score;
        }).collect(Collectors.toList());
    }

    private static Document getFilterQuery(String app, String section) {
        return Document.parse("{section: \"" + section + "\", app: \"" + app + "\"}");
    }

    public void addScore(String app, String section, Score score) {
        Document document = new Document();
        document.append("name", score.getName());
        document.append("score", score.getScore());
        document.append("date", score.getDate());
        document.append("subscription", score.getSubscription());
        document.append("position", score.getPosition());
        document.append("section", section);
        document.append("app", app);
        mongoClient.getDatabase(DREAMSCORE)
                .getCollection(SCORE)
                .insertOne(document, new InsertOneOptions().bypassDocumentValidation(true));
    }

}
