package fr.epita.filrouge.infrastructure.person;

import fr.epita.filrouge.domain.person.AppUser;
import fr.epita.filrouge.domain.person.Role;
import fr.epita.filrouge.infrastructure.common.PersonJPA;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity(name = "appuser")
public class AppUserJPA extends PersonJPA {
    private String email;
    private String password;
    private Role role;  // attention, on gère un seul Role ici !!!!

    public AppUserJPA() {
    }

    public AppUserJPA(AppUser appUser) {
        super();
        this.email = appUser.getEmail();
        this.password = appUser.getPassword();
        this.role = appUser.getRole();
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

        public static Builder anAppUserJPA() {
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

        public AppUserJPA build() {
            AppUserJPA appUserJPA = new AppUserJPA();
            appUserJPA.setId(id);
            appUserJPA.setLastname(lastname);
            appUserJPA.setFirstname(firstname);
            appUserJPA.setBirthdayDate(birthdayDate);
            appUserJPA.setEmail(email);
            appUserJPA.setPassword(password);
            appUserJPA.setRole(role);
            return appUserJPA;
        }
    }
}
