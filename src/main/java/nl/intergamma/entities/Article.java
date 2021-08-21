package nl.intergamma.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "ARTICLE")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String articleId;
    @Size(max = 100)
    private String name;
    @NotNull
    @Size(max = 250)
    private String description;

}
