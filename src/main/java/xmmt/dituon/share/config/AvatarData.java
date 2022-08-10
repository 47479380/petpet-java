package xmmt.dituon.share.config;

import com.google.gson.JsonArray;
import lombok.*;

import java.util.Collections;
import java.util.List;
@AllArgsConstructor
@Data
@ToString
@NoArgsConstructor
public class AvatarData {
//    val type: AvatarType,
//    val pos: JsonArray? = null,
//    val posType: AvatarPosType? = AvatarPosType.ZOOM,
//    val crop: JsonArray? = null,
//    val cropType: CropType? = CropType.NONE,
//    val style: List<Style>? = emptyList(),
//    val angle: Short? = 0,
//    val round: Boolean? = false,
//    val rotate: Boolean? = false,
//    val avatarOnTop: Boolean? = true,
//    val antialias: Boolean? = false
    private AvatarType type;
    private JsonArray pos;
    private AvatarPosType posType=AvatarPosType.ZOOM;
    private JsonArray crop;
    private List<Style> style= Collections.emptyList();
    private CropType cropType=CropType.NONE;
    private  Short angle=0;
    private Boolean round;
    private Boolean rotate;
    private  Boolean avatarOnTop=true;
    private  Boolean antialias;

}
