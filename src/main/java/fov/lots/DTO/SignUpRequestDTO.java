package FoV.LoTs.DTO;

import FoV.LoTs.DB.Entity.User;
import lombok.Getter;


import jakarta.validation.constraints.*;

public class SignUpRequestDTO {
    @Email
    private String email;

    @Pattern (regexp = "(?=.*[\\d])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}", message = "비밀번호는 영문과 숫자, 특수기호가 적어도 1개 이상씩 포함된 8 ~20자의 비밀번호여야 합니다.")
    private String password;

    public SignUpRequestDTO(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    public User to()
    {
        return User.builder().email(this.email).password(this.password).build();
    }

    public SignUpRequestDTO of(User user)
    {
        if (user == null)
            return null;

        return new SignUpRequestDTO(user.GetId(), user.GetPassword());
    }
}
