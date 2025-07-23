package dto.vServerr.han01.request.volume;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VolumeExtendRequest {
    public String resourceType;
    public String action;
    public VolumeExtendRequestResourceInfo resourceInfo;
}
