package mtt.webyte.model;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "T_USER")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractAuditEntity implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private String phone;

    private String address;

    private String city;

    private String state;

    private String zip;

    private String country;

    private String language;

    private String timeZone;

    private String status;

    private String role;

}
