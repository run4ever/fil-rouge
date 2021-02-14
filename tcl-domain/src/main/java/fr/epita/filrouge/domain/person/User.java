package fr.epita.filrouge.domain.person;

import java.time.LocalDate;

public class User extends Person {
    private EmailAddress email;
    private String password;
    private Role role;

    //constructeur par défaut
    User() {

    }
    public EmailAddress getEmail() {
        return email;
    }

    public void setEmail(EmailAddress email) {
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
        private EmailAddress email;
        private String password;
        private Role role;

        private Builder() {
        }

        public static Builder anUser() {
            return new Builder();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public Builder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public Builder birthdayDate(LocalDate birthdayDate) {
            this.birthdayDate = birthdayDate;
            return this;
        }

        public Builder email(EmailAddress email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder role(Role role) {
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
