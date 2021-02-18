package fr.epita.filrouge.infrastructure.common;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "person")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class PersonJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String lastname;
    private String firstname;
    private LocalDate birthdayDate;

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
}
