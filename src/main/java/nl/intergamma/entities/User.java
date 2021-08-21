package nl.intergamma.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User extends BaseEntity {

    private String username;
    private String password;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role> roles;
    private boolean active;

}
