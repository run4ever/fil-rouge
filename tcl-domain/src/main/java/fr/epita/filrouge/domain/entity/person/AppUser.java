package fr.epita.filrouge.domain.entity.person;

import java.time.LocalDate;

public class AppUser extends Person {
    private String email;
    private String password;
    private Role role;  // attention, on gère un seul Role ici !!!!

    //default constructor
    public AppUser() {
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
        private Role role;  // attention, on gère un seul Role ici !!!!

        private Builder() {
        }

        public static Builder anAppUser() {
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

        public AppUser build() {
            AppUser appUser = new AppUser();
            appUser.setId(id);
            appUser.setLastname(lastname);
            appUser.setFirstname(firstname);
            appUser.setBirthdayDate(birthdayDate);
            appUser.setEmail(email);
            appUser.setPassword(password);
            appUser.setRole(role);
            return appUser;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder ("Builder{");
            sb.append ("id=").append (id);
            sb.append (", lastname='").append (lastname).append ('\'');
            sb.append (", firstname='").append (firstname).append ('\'');
            sb.append (", birthdayDate=").append (birthdayDate);
            sb.append (", email='").append (email).append ('\'');
            sb.append (", password='").append (password).append ('\'');
            sb.append (", role=").append (role);
            sb.append ('}');
            return sb.toString ();
        }
    }


}
