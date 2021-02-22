package fr.epita.filrouge.infrastructure.person;


import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserJpaRepository extends JpaRepository<AppUserJpa,Long> {

    AppUserJpa findByEmail(String email);
}
