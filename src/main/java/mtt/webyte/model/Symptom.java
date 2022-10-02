package mtt.webyte.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_SYMPTOM")
public class Symptom extends AbstractAuditEntity implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "SYMPTOM_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long symptomId;

    @Column(name = "SYMPTOM_NAME", nullable = false)
    private String symptomName;

    @ManyToMany(mappedBy = "symptoms")
    private Set<Sickness> sicknesses = new java.util.LinkedHashSet<>();


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Symptom{");
        sb.append("symptomId='").append(symptomId).append('\'');
        sb.append(", symptomName='").append(symptomName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
