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

    @OneToMany(mappedBy = "department")
    private Set<Doctor> doctors = new HashSet<>();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Department{");
        sb.append("name='").append(departmentId).append('\'');
        sb.append(", description='").append(departmentName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

