package fr.epita.filrouge.infrastructure.person;

import fr.epita.filrouge.domain.person.EmailAddress;
import fr.epita.filrouge.domain.person.User;
import fr.epita.filrouge.domain.person.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Override
    public User findByEmail(EmailAddress emailAddress) {

        return userJpaRepository.findByEmail(emailAddress.getEmail());
    }

    @Override
    public void save(User user) {
            userJpaRepository.save(user);
    }
}
