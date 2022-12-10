package mtt.webyte.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NewsDTO extends AbstractDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long newsId;
    private String title;
    private String content;
    private String img;
    private String author;
    private Date date;
    private String text;
    private Long userID; 
}
