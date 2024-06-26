package FoV.LoTs.controller.common;

import FoV.LoTs.DTO.SummonerRequestDTO;
import FoV.LoTs.Service.RiotApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.URIException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor //생성자 자동생성
public class MainController {
    final private RiotApiService riotApiService;

    //매개변수가 프론트한테 받온거, 리턴문이 프론트한테 보내주는
    @PostMapping("/teamdiv")
    public ResponseEntity<?> byRiotId(@RequestBody SummonerRequestDTO dto) {
        try {
            return ResponseEntity.ok().body(riotApiService.byRiotId(dto.getUserID(),dto.getTag()));
        }catch (RuntimeException e){
            Map<String, Object> errmsg = new HashMap<>();
            errmsg.put("msg", e.getMessage());
            return ResponseEntity.badRequest().body(errmsg);
        }
    }
}