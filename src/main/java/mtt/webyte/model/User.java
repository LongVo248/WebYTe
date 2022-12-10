package mtt.webyte.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mtt.webyte.enums.RoleType;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_USER")
public class User extends AbstractAuditEntity implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "USER_LAST_NAME", nullable = false)
    private String userLName;

    @Column(name = "USER_FIRST_NAME", nullable = false)
    private String userFName;

    @Column(name = "USER_NAME", nullable = false)
    private String username;

    @Nationalized
    @Column(name = "PASSWORD", nullable = false)
    private String pwd;

    @Nationalized
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PHONE_NUMBER")
    private String phoneNum;

    @Nationalized
    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    @Nationalized
    @Column(name = "IMAGE")
    private String image;

    @Nationalized
    @Column(name = "DOCTOR_NAME")
    private String doctorName;

    @Column(name = "ROLE", nullable = false)
    private RoleType role;

    @ManyToMany(mappedBy = "users")
    private Set<Department> department = new java.util.LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Schedule> schedules = new java.util.LinkedHashSet<>();;
}
