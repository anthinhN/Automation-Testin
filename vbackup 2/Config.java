package dto.vServerr.han01.response.vbackup;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.vServerr.response.vbackup.VStorage;
import lombok.Data;

@Data
public class Config {
    @JsonProperty("vStorage")
    public VStorage vStorage;

}
