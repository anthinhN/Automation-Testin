package dto.vServerr.han01.response.systemimage;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class PackageLimit {
    @JsonProperty("cpu")
    public int cpu;
    @JsonProperty("memory")
    public int memory;
    @JsonProperty("diskSize")
    public int diskSize;
}
