package mtt.webyte.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChangePasswordRequest extends AbstractDTO implements Serializable {
	private Long id;
	private String oldPassword;
	private String newPassword;
}
