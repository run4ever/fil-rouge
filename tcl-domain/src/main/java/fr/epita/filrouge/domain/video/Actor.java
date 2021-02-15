package fr.epita.filrouge.domain.video;

import fr.epita.filrouge.domain.common.Person;

import java.time.LocalDate;

public class Actor extends Person {

    private String pseudo;

    //constructeur par défaut
    Actor() {

    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }


    public static final class Builder {
        private String pseudo;
        private Long id;
        private String lastname;
        private String firstname;
        private LocalDate birthdayDate;

        private Builder() {
        }

        public static Builder anActor() {
            return new Builder();
        }

        public Builder withPseudo(String pseudo) {
            this.pseudo = pseudo;
            return this;
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

        public Actor build() {
            Actor actor = new Actor();
            actor.setPseudo(pseudo);
            actor.setId(id);
            actor.setLastname(lastname);
            actor.setFirstname(firstname);
            actor.setBirthdayDate(birthdayDate);
            return actor;
        }
    }
}
