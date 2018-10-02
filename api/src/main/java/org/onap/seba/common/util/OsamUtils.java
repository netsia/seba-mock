package org.onap.seba.common.util;

import com.google.common.collect.ImmutableMap;
import org.springframework.http.MediaType;

import java.util.Map;

/**
 * Created by cemturker on 02.10.2018.
 */
public class OsamUtils extends CommonUtils {

    private static final String ACCESS_POD_PATH = "/accessPod";
    private static final String PNF_PATH = "/pnf/";

    OsamUtils(){}

    public static Map<String, String> headers() {
        return new ImmutableMap.Builder<String, String>()
                .put(ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .put(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public static String postAccessPodUrl(String ip, String port) {
        return CommonUtils.urlFormat(ip, port, ACCESS_POD_PATH);
    }

    public static String deleteAccessPod(String ip, String port, String pnfId) {
        return CommonUtils.urlFormat(ip, port, CommonUtils.mergeFormat(PNF_PATH,pnfId));
    }
}
