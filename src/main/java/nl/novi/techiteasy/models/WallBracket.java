package nl.novi.techiteasy.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

@Entity
@Table(name = "wall-bracket")
public class WallBracket {
    @Id
    @GeneratedValue
    private Long id;
    private String size;
    private Boolean adjustable;
    private String name;
    private Double price;

    @ManyToMany(mappedBy = "wallBrackets")
    private Set<Television> televisions = new HashSet<>();

    public WallBracket(Long id, String size, Boolean adjustable, String name, Double price) {
        this.id = id;
        this.size = size;
        this.adjustable = adjustable;
        this.name = name;
        this.price = price;
    }

    public WallBracket() {

    }
}
