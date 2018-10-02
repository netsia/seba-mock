package org.onap.seba.common.util;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.onap.seba.model.PNF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import java.util.Map;

/**
 * Created by cemturker on 12.04.2018.
 */
public class AaiHeaderUtils extends CommonUtils {

    AaiHeaderUtils(){
        super();
    }

    public static Map<String, String> headers() {
        return new ImmutableMap.Builder<String, String>()
                .put(X_FROM_APP_ID,"SEBA")
                .put(X_TRANSACTION_ID,"100")
                .put(ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .put(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public static PNF convertToPnf(String body) {
        return new Gson().fromJson(body, PNF.class);
    }

    public static String convertPnfToString(PNF pnf) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create().toJson(pnf, PNF.class);
    }
}
