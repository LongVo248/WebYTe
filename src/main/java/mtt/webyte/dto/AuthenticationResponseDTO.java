package mtt.webyte.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponseDTO {
    private String username;
    private String token;
    private List<ERole> roleDTO;
}
