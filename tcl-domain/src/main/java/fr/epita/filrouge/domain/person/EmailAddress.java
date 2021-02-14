package fr.epita.filrouge.domain.person;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class EmailAddress implements Serializable {
    private Long id;
    private String email;

    //constructeur par défaut utiliser par spring, avec visibilité package
    EmailAddress() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
