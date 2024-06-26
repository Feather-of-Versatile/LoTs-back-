package fov.lots.Service;

import fov.lots.DB.Repository.UserRepository;
import fov.lots.DTO.SignUpRequestDTO;
import fov.lots.DTO.SignUpResponseDTO;
import fov.lots.DTO.LoginRequestDTO;
import fov.lots.DTO.LoginResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import fov.lots.DB.Entity.User;

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
