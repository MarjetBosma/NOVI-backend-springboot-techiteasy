package nl.novi.techiteasy.repositories;


import nl.novi.techiteasy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
