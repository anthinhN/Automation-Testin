package dto.vServerr.request.floatingIp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Setter;

@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FloatingIpDisassociateRequest {
    public String networkInterfaceId;
}
