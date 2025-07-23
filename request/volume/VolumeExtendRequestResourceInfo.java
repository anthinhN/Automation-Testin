package dto.vServerr.request.volume.""."".request.volume;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VolumeExtendRequestResourceInfo {
    public String newSize;
    public String newVolumeTypeId;
    public String volumeId;
}
