package fov.lots.DTO;

import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

import fov.lots.DB.Entity.User;

@Getter
@Setter
@AllArgsConstructor
public class LoginRequestDTO {
    @Email
    private String email;

    @Pattern (regexp = "(?=.*[\\d])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}", message = "비밀번호는 영문과 숫자, 특수기호가 적어도 1개 이상씩 포함된 8 ~20자의 비밀번호여야 합니다.")
    private String password;
    
    public User to()
    {
        return User.builder().email(this.email).password(this.password).build();        
    }

    public LoginRequestDTO of(User user)
    {
        if (user == null)
            return null;

        return new LoginRequestDTO(user.GetId(), user.GetPassword());
    }
}
