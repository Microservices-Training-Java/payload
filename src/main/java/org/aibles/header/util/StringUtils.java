package org.aibles.header.util;

import org.apache.logging.log4j.util.Strings;

public class StringUtils {

    public static String nullToEmpty(String params) {
        if (Strings.trimToNull(params) == null) {
            return "";
        }
        return params;
    }
}
