package org.onap.seba.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by cemturker on 02.10.2018.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccessPod {
    private String pnfId;
    private String coreIp;
    private String corePort;
    private String ip;
    private String port;
    private String username;
    private String password;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccessPod{");
        sb.append("pnfId='").append(pnfId).append('\'');
        sb.append(", coreIp='").append(coreIp).append('\'');
        sb.append(", corePort='").append(corePort).append('\'');
        sb.append(", ip='").append(ip).append('\'');
        sb.append(", port='").append(port).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
