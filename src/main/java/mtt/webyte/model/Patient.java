package mtt.webyte.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "T_PATIENT")
public class Patient extends AbstractAuditEntity implements Serializable {

    @Id
    @Column(name = "PATIENT_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "BIRTH_DATE")
    private String birthDate;

    @Column(name = "ACCOUNT_USERNAME", nullable = false)
    private String accountUsername;
}
