package fr.epita.filrouge.infrastructure.person;


import fr.epita.filrouge.domain.person.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
}
