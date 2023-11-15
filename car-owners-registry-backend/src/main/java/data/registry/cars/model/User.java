package data.registry.cars.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Table(name = "users", indexes = {
        @Index(name = "index_users_name", columnList = "name ASC"),
        @Index(name = "index_users_name_desc", columnList = "name DESC")
})
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;

    @Column(nullable = false)
    String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    List<Car> cars;

    public void setCars(
            List<Car> value) {
        cars = value;
        if (value != null) {
            for (Car it : value) {
                it.setUser(this);
            }
        }
    }
}
