package nl.novi.techiteasy.repositories;

import nl.novi.techiteasy.models.TelevisionWallBracket;
import nl.novi.techiteasy.models.TelevisionWallBracketKey;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TelevisionWallBracketRepository extends JpaRepository<TelevisionWallBracket, TelevisionWallBracketKey> {
    List<TelevisionWallBracket> findAllByTelevisionId(Long televisionId);
    List<TelevisionWallBracket> findAllByWallBracketId(Long wallBracketId);
}
