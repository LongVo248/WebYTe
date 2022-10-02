package mtt.webyte.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
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

    @ManyToOne
    @JoinColumn(name = "ROLE_ID", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "account")
    private Set<Question> questions = new HashSet<>(0);

    @OneToMany(mappedBy = "account")
    private Set<Answer> answers = new HashSet<>(0);

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account{");
        sb.append("username='").append(userName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", role=").append(role);
        sb.append('}');
        return sb.toString();
    }
}
