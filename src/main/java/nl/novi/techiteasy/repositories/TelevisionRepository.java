package nl.novi.techiteasy.repositories;

import nl.novi.techiteasy.models.Television;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TelevisionRepository extends JpaRepository<Television, Long> {
}
