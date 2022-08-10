package xmmt.dituon.share.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@AllArgsConstructor
@Data
@ToString
@NoArgsConstructor
public class TextExtraData {
//        val fromReplacement: String,
//    val toReplacement: String,
//    val groupReplacement: String,
//    val textList: List<String>
    private String fromReplacement;
    private String toReplacement;
    private String groupReplacement;
    private List<String> textList;

}
