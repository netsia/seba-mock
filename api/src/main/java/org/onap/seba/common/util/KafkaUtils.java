package org.onap.seba.common.util;

import org.onap.seba.model.AccessPod;
import org.onap.seba.model.PNF;

import java.util.List;

public class KafkaUtils {

    public static PNF validatePNF(List<PNF> pnfList, String corrId)
    {
        CommonUtils.checkParameters(pnfList,corrId);
        for (PNF pnf : pnfList) {
            if(pnf.getPnfId().equals(corrId))
                return pnf;
        }
        return null;
    }

    public static AccessPod convertPNFToAccessPod(PNF pnf, String port, String ip, String password, String corePort, String userName){
        if(!CommonUtils.checkParameters(pnf,port,ip,password,corePort,userName))
            return null;
        AccessPod accessPod = new AccessPod();
        accessPod.setPnfId(pnf.getPnfId());
        accessPod.setIp(pnf.getIpaddressV4Oam());
        accessPod.setPort(port);
        accessPod.setCoreIp(ip);
        accessPod.setUsername(userName);
        accessPod.setCorePort(corePort);
        accessPod.setPassword(password);
        return accessPod;
    }
}
