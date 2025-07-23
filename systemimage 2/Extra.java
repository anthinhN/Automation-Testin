package dto.vServerr.han01.response.systemimage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Extra {
    @Override
    public String toString() {
        return "Extra{}";
    }
}
