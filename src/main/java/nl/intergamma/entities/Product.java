package nl.intergamma.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Product {
    @Id
    private String productId;
    @Size(max = 100)
    private String name;

    @NotNull
    @Size(max = 250)
    private String description;
    @Column(name = "articleId")
    private String articleId;

    @Column(name = "storeId")
    private String storeId;

    public Product(String productId, @Size(max = 100) String name, @NotNull @Size(max = 250) String description, String articleId, String storeId) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.articleId = articleId;
        this.storeId = storeId;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "articleId", nullable = false, insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Article article;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "storeId", nullable = false, insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Store store;
}
