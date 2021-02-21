package fr.epita.filrouge.infrastructure.appuser;

import fr.epita.filrouge.infrastructure.domain.entity.person.Role;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class AppUserJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    private String lastname;
    private String firstname;
    private LocalDate birthdayDate;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public AppUserJpa() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public LocalDate getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(LocalDate birthdayDate) {
        this.birthdayDate = birthdayDate;
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

        public static Builder anAppUserJpa() {
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

        public AppUserJpa build() {
            AppUserJpa appUserJpa = new AppUserJpa();
            appUserJpa.setId(id);
            appUserJpa.setLastname(lastname);
            appUserJpa.setFirstname(firstname);
            appUserJpa.setBirthdayDate(birthdayDate);
            appUserJpa.setEmail(email);
            appUserJpa.setPassword(password);
            appUserJpa.setRole(role);
            return appUserJpa;
        }
    }
}
