package FoV.LoTs.Service;

import FoV.LoTs.DTO.SummonerResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.net.URISyntaxException;

//입력받은 아이디로 소환사 정보 뽑아오기(지윤 계정으로 함**임시)
@Service
public class RiotApiService {
    private SummonerResponseDTO summonerResponseDTO;

    public SummonerResponseDTO byRiotId(String username,String tagline) {

        WebClient webClient=WebClient.builder().build();

        String urlReq = "https://asia.api.riotgames.com/riot/account/v1/accounts/by-riot-id/" +
                username + //닉네임
                "/" +
                tagline +  //태그
                "?api_key=RGAPI-3c267584-8c79-48cc-9c70-8633cd6bdc24"; //api 키




        //API요청
        webClient.get()
                .uri(urlReq)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    throw new RuntimeException("찾을 수 없는 아이디");
                })
                .bodyToMono(SummonerResponseDTO.class)
                .block();
        if(summonerResponseDTO==null)
            throw new RuntimeException();
        return summonerResponseDTO;
    }
}