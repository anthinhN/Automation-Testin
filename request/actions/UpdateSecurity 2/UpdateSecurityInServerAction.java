package dto.vServerr.han01.request.actions.UpdateSecurity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateSecurityInServerAction {
    @JsonProperty("securityGroup")
    ArrayList<String>  securityGroup;
}
