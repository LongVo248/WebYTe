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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_DEPARTMENT")
public class Department extends AbstractAuditEntity {

    @Id
    @Column(name = "DEPARTMENT_ID", nullable = false)
    private String departmentId;

    @Column(name = "DEPARTMENT_NAME", nullable = false)
    private String departmentName;

    @ManyToMany
    @JoinTable(name = "T_DEPARTMENT_DOCTOR",
            joinColumns = @JoinColumn(name = "DEPARTMENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    private Set<User> users = new HashSet<>();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Department{");
        sb.append("name='").append(departmentId).append('\'');
        sb.append(", description='").append(departmentName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

