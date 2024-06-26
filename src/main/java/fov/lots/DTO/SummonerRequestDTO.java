package FoV.LoTs.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class SummonerRequestDTO {
    private String userID;
    private String tag;
}
//프론트한테 받아야하는 DTO
