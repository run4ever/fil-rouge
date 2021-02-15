package fr.epita.filrouge.domain.person;

public interface UserRepository {
    User findByEmail(String emailAddress);
    void save(User user);
}
