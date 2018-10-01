package org.onap.seba.common.util;

public class CommonUtils {

    private CommonUtils(){}

    public static boolean checkParameters(Object ...parameters)
    {
        for (Object p : parameters)
            if (p == null)
                return false;

        return true;
    }
}
