package org.onap.seba.aai.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by cemturker on 26.09.2018.
 */
@Setter
@Getter
public class PNF {
    @SerializedName("pnf-id")
    private String pnfId;
    @SerializedName("pnf-name")
    private String pnfName;
    @SerializedName("ipaddress-v4-oam")
    private String ipaddressV4Oam;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PNF{");
        sb.append("pnfName='").append(pnfName).append('\'');
        sb.append(", pnfId='").append(pnfId).append('\'');
        sb.append(", ipaddressV4Oam='").append(ipaddressV4Oam).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
