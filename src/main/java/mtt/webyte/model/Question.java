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
@Table(name = "T_QUESTION")
public class Question extends AbstractAuditEntity implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "QUESTION_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(name = "QUESTION_CONTENT", nullable = false)
    private String questionContent;

    @ManyToOne(optional = false)
    @JoinColumn(name = "TOPIC_ID", nullable = false)
    private Topic topic;

    @ManyToOne(optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Answer> answers = new HashSet<>();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Question{");
        sb.append("questionId='").append(questionId).append('\'');
        sb.append(", questionContent='").append(questionContent).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
