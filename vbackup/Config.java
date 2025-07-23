package dto.vServerr.response.vbackup;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Config {
    @JsonProperty("vStorage")
    public VStorage vStorage;

}
