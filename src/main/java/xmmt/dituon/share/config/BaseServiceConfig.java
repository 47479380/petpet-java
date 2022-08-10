package xmmt.dituon.share.config;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
@AllArgsConstructor
@Data
@ToString
@NoArgsConstructor
public class BaseServiceConfig implements Serializable {
    private Boolean antialias =true;
}
