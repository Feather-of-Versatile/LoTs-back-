package FoV.LoTs.Service;



import FoV.LoTs.DB.Entity.User;
import FoV.LoTs.DB.Repository.UserRepository;
import FoV.LoTs.DTO.LoginRequestDTO;
import FoV.LoTs.DTO.LoginResponseDTO;
import FoV.LoTs.DTO.SignUpRequestDTO;
import FoV.LoTs.DTO.SignUpResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public SignUpResponseDTO signUp(SignUpRequestDTO dto) {
        User user = userRepository.save(dto.to());

        SignUpResponseDTO responseDTO = new SignUpResponseDTO(user.GetId(), user.GetPassword());
        return responseDTO;
    }
    
    public LoginResponseDTO login(LoginRequestDTO dto) {
        try {
            User user = userRepository.findOneByEmailAndPassword(dto.getEmail(), dto.getPassword());
            LoginResponseDTO responseDTO = new LoginResponseDTO(user.GetId(), user.GetPassword());

            return responseDTO;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
        return null;
    }

}
