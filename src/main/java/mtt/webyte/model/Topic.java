package mtt.webyte.model;

import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_TOPIC")
public class Topic extends AbstractAuditEntity implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "TOPIC_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long topicId;

    @Column(name = "TOPIC_NAME", nullable = false)
    private String topicName;

    @Column(name = "TOPIC_CONTENT", nullable = false)
    private String topicContent;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Question> questions = new HashSet<>();

    @Override
public String toString() {
        final StringBuilder sb = new StringBuilder("Topic{");
        sb.append("topicId='").append(topicId).append('\'');
        sb.append(", topicName='").append(topicName).append('\'');
        sb.append(", topicContent='").append(topicContent).append('\'');
        sb.append('}');
        return sb.toString();
    }
}