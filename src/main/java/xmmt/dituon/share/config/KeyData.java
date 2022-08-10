package xmmt.dituon.share.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@AllArgsConstructor
@Data
@ToString
@NoArgsConstructor
public class KeyData {
//        val type: Type,
//    val avatar: List<AvatarData>,
//    val text: List<TextData>,
//    val background: BackgroundData? = null,
//    val alias: List<String>? = null,
//    val format: String? = "png", //未实装
//    val inRandomList: Boolean? = true,
//    val hidden: Boolean? = false
    private ImageType type;
    private List<AvatarData> avatar;
    private List<TextData> text;
    private BackgroundData background;
    private List<String> alias;
    private String format="png";
    private Boolean inRandomList=true;
    private Boolean hidden;
   static Gson gson ;
    static  {
         gson = new GsonBuilder().create();
    }
  public static KeyData  getData(String str){
        return gson.fromJson(str,KeyData.class);
    }


}

