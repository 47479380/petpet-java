package xmmt.dituon.share.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import xmmt.dituon.share.Function1;

import java.awt.image.BufferedImage;
import java.util.List;
@AllArgsConstructor
@Data
@ToString
@NoArgsConstructor
public class GifAvatarExtraDataProvider {
//        val fromAvatar: (() -> List<BufferedImage>)? = null,
//    val toAvatar: (() -> List<BufferedImage>)? = null,
//    val groupAvatar: (() -> List<BufferedImage>)? = null,
//    val botAvatar: (() -> List<BufferedImage>)? = null
     private Function1<List<BufferedImage>> fromAvatar;
    private Function1<List<BufferedImage>> toAvatar;
    private Function1<List<BufferedImage>> groupAvatar;
    private Function1<List<BufferedImage>> botAvatar;
}
