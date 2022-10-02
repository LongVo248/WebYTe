package mtt.webyte.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_SICKNESS")
public class Sickness extends AbstractAuditEntity implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "SICKNESS_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sickId;

    @Column(name = "SICKNESS_NAME", nullable = false)
    private String sickName;
    @Column(name = "SICKNESS_DESCRIPTION", nullable = false)
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "typeSickId", nullable = false)
    private TypeSick typeSickId;

    @ManyToMany
    @JoinTable(name = "T_SICKNESS_SYMPTOM",
            joinColumns = @JoinColumn(name = "SICKNESS_ID"),
            inverseJoinColumns = @JoinColumn(name = "SYMPTOM_ID"))
    private Set<Symptom> symptoms = new java.util.LinkedHashSet<>();

    @ManyToMany(mappedBy = "sicknesses")
    private Set<HealthRecord> healthRecords = new java.util.LinkedHashSet<>();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Sickness{");
        sb.append("sickId=").append(sickId);
        sb.append(", sickName='").append(sickName).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", typeSickId=").append(typeSickId);
        sb.append(", symptoms=").append(symptoms);
        sb.append('}');
        return sb.toString();
    }
}
