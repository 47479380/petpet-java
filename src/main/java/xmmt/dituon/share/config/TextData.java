package xmmt.dituon.share.config;

import com.google.gson.JsonElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@AllArgsConstructor
@Data
@ToString
@NoArgsConstructor
public class TextData {
//     val text: String,
//    val pos: List<Int>? = null,
//    val color: JsonElement? = null,
//    val font: String? = null,
//    val size: Int? = null,
//    val align: TextAlign? = TextAlign.LEFT,
//    val wrap: TextWrap? = TextWrap.NONE,
//    val style: TextStyle? = TextStyle.PLAIN
    private String text;
    private List<Integer> pos;
    private JsonElement color;
    private  String font;
    private Integer size;
    private  TextAlign align=TextAlign.LEFT;
    private TextWrap wrap=TextWrap.NONE;
    private  TextStyle style;


    public  TextData(String text, List<Integer> pos, JsonElement color, String font, int fontSize) {
    }
}
