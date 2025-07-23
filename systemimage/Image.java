package dto.vServerr.response.systemimage;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class Image {
    @JsonProperty("id")
    public String id;
    @JsonProperty("imageType")
    public String imageType;
    @JsonProperty("imageVersion")
    public String imageVersion;
    @JsonProperty("licence")
    public Boolean licence;
    @JsonProperty("flavorZoneIds")
    public List<String> flavorZoneIds;
    @JsonProperty("packageLimit")
    public PackageLimit packageLimit;
}
