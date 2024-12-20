package org.justme8code.ytdl4j.util;

import org.apache.commons.lang3.SystemUtils;

public class VerifyOs {

    public static boolean isWindows() {
         return SystemUtils.IS_OS_WINDOWS;
    }

    public static boolean isMac() {
        return SystemUtils.IS_OS_MAC;
    }

    public static boolean isUnix() {
        return SystemUtils.IS_OS_UNIX;
    }
}
