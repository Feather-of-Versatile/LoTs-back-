package FoV.LoTs.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import FoV.LoTs.Service.UserService;
import FoV.LoTs.DTO.LoginRequestDTO;
import FoV.LoTs.DTO.LoginResponseDTO;
import FoV.LoTs.DTO.SignUpRequestDTO;
import FoV.LoTs.DTO.SignUpResponseDTO;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user/auth")
@Slf4j
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody SignUpRequestDTO dto) {
        try {
            SignUpResponseDTO authResponse = userService.signUp(dto);
            return ResponseEntity.ok(authResponse);
        } catch (Exception e) {
            return ResponseEntity.ofNullable(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO dto) {
        try {
            LoginResponseDTO authResponse = userService.login(dto);
            return ResponseEntity.ok(authResponse);
        } catch (Exception e) {
            return ResponseEntity.ofNullable(null);
        }
    }
}