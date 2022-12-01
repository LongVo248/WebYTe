package mtt.webyte.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_TYPE_SICK")
public class TypeSick extends AbstractAuditEntity implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TYPE_SICK_ID", nullable = false)
    private Long typeSickId;

    @Column(name = "TYPE_SICK_NAME", nullable = false)
    private String typeSickName;

    @OneToMany(mappedBy = "typeSick")
    private Set<Sickness> sicknesses = new java.util.LinkedHashSet<>();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TypeSick{");
        sb.append("typeSickId='").append(typeSickId).append('\'');
        sb.append(", typeSickName='").append(typeSickName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
