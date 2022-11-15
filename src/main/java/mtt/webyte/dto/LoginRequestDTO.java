package mtt.webyte.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoginRequestDTO {
    private String username;
    private String password;
}
