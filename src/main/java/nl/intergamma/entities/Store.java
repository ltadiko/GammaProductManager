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

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "STORE")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String storeId;
    private String city;
    private String postalCode;
    private String street;
    private String address_number;
    private String todayOpen;
    private String todayClose;
    private Boolean collectionPoint;
}
