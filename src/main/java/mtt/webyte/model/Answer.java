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
@Table(name = "T_ANSWER")
public class Answer extends AbstractAuditEntity implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ANSWER_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column(name = "ANSWER_CONTENT", nullable = false)
    private String answerContent;

    @ManyToOne(optional = false)
    @JoinColumn(name = "QUESTION_ID", nullable = false)
    private Question question;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account account;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Answer{");
        sb.append("answerId='").append(answerId).append('\'');
        sb.append(", answerContent='").append(answerContent).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
