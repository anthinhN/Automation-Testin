package dto.vServerr.response.systemimage;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode
public class GetSystemImageResponse {
    public String errorMsg;
    public String errorCode;
    public Boolean success;
    public Extra extra;
    public List<Image> images;

}
