package dto.vServerr.request.volume;

import dto.vServerr.han01.request.volume.VolumeExtendRequestResourceInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VolumeExtendRequest {
    public String resourceType;
    public String action;
    public VolumeExtendRequestResourceInfo resourceInfo;
}
