package dto.vServerr.response.subnet;
import dto.vServerr.request.subnet.DataSubnet;
import lombok.Getter;
import java.util.ArrayList;

@Getter
public class GetSubnetListData {
    public ArrayList<DataSubnet> listData;
}
