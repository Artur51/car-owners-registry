package data.registry.cars.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Table(
        name = "cars",
        indexes = {
                @Index(name = "index_cars_make", columnList = "make ASC"),
                @Index(name = "index_cars_make_desc", columnList = "make DESC"),
                @Index(name = "index_cars_model", columnList = "model ASC"),
                @Index(name = "index_cars_model_desc", columnList = "model DESC"),
                @Index(name = "index_cars_numberplate", columnList = "numberplate ASC"),
                @Index(name = "index_cars_numberplate_desc", columnList = "numberplate DESC")
        })
@Entity
@EqualsAndHashCode(exclude = "user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;

    @Column(nullable = false)
    String make;

    @Column(nullable = false)
    String model;

    @JsonProperty("numberplate")
    @Column(name = "numberplate", nullable = false, columnDefinition = "VARCHAR(200)")
    String numberPlate;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
