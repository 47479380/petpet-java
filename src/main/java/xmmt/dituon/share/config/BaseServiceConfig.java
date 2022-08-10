package xmmt.dituon.share.config;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Data
@ToString
@NoArgsConstructor
public class BaseServiceConfig implements Serializable {
    private Boolean antialias =true;
//    val gifMaxSize: List<Int> = emptyList(),
//    val gifEncoder: Encoder = Encoder.BUFFERED_STREAM
    private List<Integer> gifMaxSize= Collections.emptyList();
    private Encoder gifEncoder=Encoder.BUFFERED_STREAM;
}
