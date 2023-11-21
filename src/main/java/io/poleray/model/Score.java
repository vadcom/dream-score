package io.poleray.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * Score
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-11-17T13:56:19.196670531Z[GMT]")


public class Score   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("position")
  private Integer position = null;

  @JsonProperty("score")
  private Long score = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("date")
  private OffsetDateTime date = null;

  @JsonProperty("subscriotin")
  private String subscriotin = null;

  public Score id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(example = "256", description = "")
  
    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Score position(Integer position) {
    this.position = position;
    return this;
  }

  /**
   * Get position
   * @return position
   **/
  @Schema(example = "3", description = "")
  
    public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }

  public Score score(Long score) {
    this.score = score;
    return this;
  }

  /**
   * Get score
   * @return score
   **/
  @Schema(example = "10", description = "")
  
    public Long getScore() {
    return score;
  }

  public void setScore(Long score) {
    this.score = score;
  }

  public Score name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   **/
  @Schema(example = "Jon", description = "")
  
    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Score date(OffsetDateTime date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
   **/
  @Schema(description = "")
  
    @Valid
    public OffsetDateTime getDate() {
    return date;
  }

  public void setDate(OffsetDateTime date) {
    this.date = date;
  }

  public Score subscriotin(String subscriotin) {
    this.subscriotin = subscriotin;
    return this;
  }

  /**
   * Get subscriotin
   * @return subscriotin
   **/
  @Schema(example = "SDFRTSDERERFGKE", description = "")
  
    public String getSubscriotin() {
    return subscriotin;
  }

  public void setSubscriotin(String subscriotin) {
    this.subscriotin = subscriotin;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Score score = (Score) o;
    return Objects.equals(this.id, score.id) &&
        Objects.equals(this.position, score.position) &&
        Objects.equals(this.score, score.score) &&
        Objects.equals(this.name, score.name) &&
        Objects.equals(this.date, score.date) &&
        Objects.equals(this.subscriotin, score.subscriotin);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, position, score, name, date, subscriotin);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Score {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
    sb.append("    score: ").append(toIndentedString(score)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    subscriotin: ").append(toIndentedString(subscriotin)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}