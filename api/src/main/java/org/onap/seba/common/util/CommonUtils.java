package org.onap.seba.common.util;

public class CommonUtils {
    static final String X_FROM_APP_ID = "X-FromAppId";
    static final String X_TRANSACTION_ID = "X-TransactionId";
    static final String ACCEPT = "Accept";
    static final String CONTENT_TYPE = "Content-Type";
    CommonUtils(){}

    public static boolean checkParameters(Object ...parameters)
    {
        for (Object p : parameters)
            if (p == null)
                return false;

        return true;
    }

    public static String urlFormat(String ip, String port, String path){
        return String.format("http://%s:%s%s",ip,port,path);
    }

    public static String mergeFormat(String ... values){
        StringBuilder builder = new StringBuilder();
        for (String value : values) {
            builder.append(value);
        }
        return builder.toString();
    }
}
