package org.onap.seba.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by cemturker on 26.09.2018.
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class PNF {
    @SerializedName(value = "pnf-id", alternate = "correlationId")
    private String pnfId;
    @SerializedName("pnf-name")
    private String pnfName;
    @SerializedName("ipaddress-v4-oam")
    private String ipaddressV4Oam;
    @SerializedName("ipaddress-v6-oam")
    private String ipaddressV6Oam;
}
