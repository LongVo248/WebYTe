package mtt.webyte.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_ACCOUNT")
public class Account extends AbstractAuditEntity implements Serializable {
    @Id
    @Column(name = "USERNAME", nullable = false)
    private String userName;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "T_ACCOUNT_ROLES",
            joinColumns = {@JoinColumn(name = "USERNAME")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
    private Set<Role> roles = new HashSet<>(0);

//    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "account")
//    @EqualsAndHashCode.Exclude
//    private List<GroupOfRoles> groupOfRoleses = new ArrayList<>();

    @OneToMany(mappedBy = "account")
    private Set<Question> questions = new HashSet<>(0);

    @OneToMany(mappedBy = "account")
    private Set<Answer> answers = new HashSet<>(0);

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account{");
        sb.append("userName='").append(userName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", roles=").append(roles);
        sb.append('}');
        return sb.toString();
    }
}
