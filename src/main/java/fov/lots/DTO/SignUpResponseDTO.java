package fov.lots.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class SignUpResponseDTO {
    private String accessToken; // 2 ~ 14    
    private String refreshToken; // 14 ~ 90
}
