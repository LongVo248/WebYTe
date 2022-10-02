package mtt.webyte.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ADMIN")
public class Admin extends AbstractAuditEntity implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADMIN_ID", nullable = false)
    private Long adminId;

    @Column(name = "ADMIN_NAME", nullable = false)
    private String adminName;

    @Column(name = "PHONE", nullable = false)
    private String phone;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name ="ADDRESS", nullable = false)
    private String address;

    @Column(name ="ACCOUNT_ID", nullable = false)
    private String accountId;

    @Column(name ="BIRTH_DATE", nullable = false)
    private String birthDate;

    @OneToMany(mappedBy = "adminId", cascade = CascadeType.ALL)
    private Set<News> newsList = new HashSet<>();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Admin{");
        sb.append("adminId=").append(adminId);
        sb.append(", adminName='").append(adminName).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", accountId='").append(accountId).append('\'');
        sb.append(", birthDate='").append(birthDate).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
