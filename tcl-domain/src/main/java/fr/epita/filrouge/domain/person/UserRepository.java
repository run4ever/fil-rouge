package fr.epita.filrouge.domain.person;

public interface UserRepository {
    User findByEmail(EmailAddress emailAddress);
    void save(User user);
}
