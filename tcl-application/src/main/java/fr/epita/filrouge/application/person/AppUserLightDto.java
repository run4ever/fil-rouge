package fr.epita.filrouge.application.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.epita.filrouge.domain.entity.person.Role;

import javax.validation.constraints.Email;
import java.time.LocalDate;

public class AppUserLightDto {
    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("birhdayDate")
    private LocalDate birthdayDate;

    @JsonProperty("email")
    @Email
    private String email;

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
        private Role role;

        private Builder() {
        }

        public static Builder anAppUserLightDto() {
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

        public Builder withRole(Role role) {
            this.role = role;
            return this;
        }

        public AppUserLightDto build() {
            AppUserLightDto appUserLightDto = new AppUserLightDto();
            appUserLightDto.setLastname(lastname);
            appUserLightDto.setFirstname(firstname);
            appUserLightDto.setBirthdayDate(birthdayDate);
            appUserLightDto.setEmail(email);
            appUserLightDto.setRole(role);
            return appUserLightDto;
        }
    }
}
