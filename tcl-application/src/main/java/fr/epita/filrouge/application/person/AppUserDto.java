package fr.epita.filrouge.application.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.epita.filrouge.domain.entity.person.Role;

import java.time.LocalDate;

public class AppUserDto {
    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("birhdayDate")
    private LocalDate birthdayDate;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("role")
    private Role role;

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
        private String lastname;
        private String firstname;
        private LocalDate birthdayDate;
        private String email;
        private String password;
        private Role role;

        private Builder() {
        }

        public static Builder anAppUserDto() {
            return new Builder();
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

        public AppUserDto build() {
            AppUserDto appUserDto = new AppUserDto();
            appUserDto.setLastname(lastname);
            appUserDto.setFirstname(firstname);
            appUserDto.setBirthdayDate(birthdayDate);
            appUserDto.setEmail(email);
            appUserDto.setPassword(password);
            appUserDto.setRole(role);
            return appUserDto;
        }
    }
}
