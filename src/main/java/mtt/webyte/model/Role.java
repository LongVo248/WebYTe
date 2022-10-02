package mtt.webyte.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_ROLE")
public class Role extends AbstractAuditEntity implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ROLE_ID", nullable = false)
    private Long roleId;

    @Column(name = "ROLE_NAME", nullable = false)
    private String roleName;

    @OneToMany(mappedBy = "role")
    private Set<Account> accounts = new HashSet<>();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Role{");
        sb.append("roleId=").append(roleId);
        sb.append(", roleName=").append(roleName);
        sb.append('}');
        return sb.toString();
    }
}
