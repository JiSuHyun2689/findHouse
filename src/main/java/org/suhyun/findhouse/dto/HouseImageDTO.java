package org.suhyun.findhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.suhyun.findhouse.entity.House;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HouseImageDTO {

    private Long imageNum;

    private String imageName, path, uuid;

    public String getImageURL(){
        try{
            return URLEncoder.encode(path + "/" + uuid + "_" + imageName, "UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return "";
    }


}
