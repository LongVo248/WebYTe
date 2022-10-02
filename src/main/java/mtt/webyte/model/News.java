package mtt.webyte.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_NEWS")
public class News extends AbstractAuditEntity implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "NEWS_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsId;

    @Column(name = "NEWS_TITLE")
    private String title;

    @Column(name = "NEWS_CONTENT")
    private String content;

    @Column(name = "NEWS_IMAGE")
    private String img;

    @Column(name = "NEWS_AUTHOR")
    private String author;

    @Column(name = "NEWS_DATE")
    private Date date;

    @Column(name="NEWS_TEXT")
    private String text;

    @ManyToOne
    @JoinColumn(name="ADMIN_ID", nullable = false)
    private Admin adminId;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("News{");
        sb.append("newsId=").append(newsId);
        sb.append(", title='").append(title).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", img='").append(img).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append(", date=").append(date);
        sb.append(", text='").append(text).append('\'');
        sb.append(", adminId=").append(adminId);
        sb.append('}');
        return sb.toString();
    }
}
