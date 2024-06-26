package FoV.LoTs.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SummonerResponseDTO {
    private String puuid;
    private String gameName;
    private String tagLine;
}
//API로 받은 DTO를 프론트에게 전달해주는 DTO
