package fr.epita.filrouge.domain.person;

import fr.epita.filrouge.domain.common.Person;

import java.time.LocalDate;

public class User extends Person {
    private String email;
    private String password;
    private Role role;

    //constructeur par défaut
    User() {

    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public static final class Builder {
        private Long id;
        private String lastname;
        private String firstname;
        private LocalDate birthdayDate;
        private String email;
        private String password;
        private Role role;

        private Builder() {
        }

        public static Builder anUser() {
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

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withRole(Role role) {
            this.role = role;
            return this;
        }

        public User build() {
            User user = new User();
            user.setId(id);
            user.setLastname(lastname);
            user.setFirstname(firstname);
            user.setBirthdayDate(birthdayDate);
            user.setEmail(email);
            user.setPassword(password);
            user.setRole(role);
            return user;
        }
    }

}
