package nl.novi.techiteasy.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class TelevisionWallBracket {
    @EmbeddedId
    private TelevisionWallBracketKey id;

    @ManyToOne
    @MapsId("televisionId")
    @JoinColumn(name = "television_id")
    private Television television;

    @ManyToOne
    @MapsId("wallBracketId")
    @JoinColumn(name = "wall_bracket_id")
    private WallBracket wallBracket;

    public TelevisionWallBracketKey getId() {
        return id;
    }

}
