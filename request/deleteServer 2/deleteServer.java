package dto.vServerr.han01.request.deleteServer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class deleteServer {
    public boolean deleteAllVolume;
}