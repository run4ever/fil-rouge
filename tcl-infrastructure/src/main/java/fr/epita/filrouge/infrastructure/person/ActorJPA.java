package fr.epita.filrouge.infrastructure.person;

import fr.epita.filrouge.domain.video.Actor;
import fr.epita.filrouge.infrastructure.common.PersonJPA;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity(name = "actor")
public class ActorJPA extends PersonJPA {
    private String pseudo;

    public ActorJPA() {
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public ActorJPA(Actor actor) {
        super();
        this.pseudo = actor.getPseudo();
    }


    public static final class Builder {
        private Long id;
        private String lastname;
        private String firstname;
        private LocalDate birthdayDate;
        private String pseudo;

        private Builder() {
        }

        public static Builder anActorJPA() {
            return new Builder();
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public Builder withFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public Builder withBirthdayDate(LocalDate birthdayDate) {
            this.birthdayDate = birthdayDate;
            return this;
        }

        public Builder withPseudo(String pseudo) {
            this.pseudo = pseudo;
            return this;
        }

        public ActorJPA build() {
            ActorJPA actorJPA = new ActorJPA();
            actorJPA.setId(id);
            actorJPA.setLastname(lastname);
            actorJPA.setFirstname(firstname);
            actorJPA.setBirthdayDate(birthdayDate);
            actorJPA.setPseudo(pseudo);
            return actorJPA;
        }
    }
}
