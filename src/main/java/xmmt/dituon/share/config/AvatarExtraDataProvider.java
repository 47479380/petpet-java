package xmmt.dituon.share.config;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import xmmt.dituon.share.Function1;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
@Data
@ToString
@NoArgsConstructor
public class AvatarExtraDataProvider {
    //    val fromAvatar: (() -> BufferedImage)? = null,
//    val toAvatar: (() -> BufferedImage)? = null,
//    val groupAvatar: (() -> BufferedImage)? = null,
//    val botAvatar: (() -> BufferedImage)? = null
    private Function1<BufferedImage> fromAvatar;
    private Function1<BufferedImage> toAvatar;
    private Function1<BufferedImage> groupAvatar;
    private Function1<BufferedImage> botAvatar;


}
