package dto.vServerr.han01.request.ssh;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SSHImportRequest {
    public String name;
    public String pubKey;
}
