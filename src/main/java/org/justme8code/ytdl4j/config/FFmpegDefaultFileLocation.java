package org.justme8code.ytdl4j.config;

import org.apache.commons.lang3.SystemUtils;

public enum FFmpegDefaultFileLocation {
    MICROSOFT_FFMPEG("ffmpeg-download/ffmpeg-master-latest-win64-gpl.zip"),
    LINUX_FFMPEG(SystemUtils.USER_HOME + "/ytdl4j/ffmpeg-download/ffmpeg-master-latest-linuxarm64-gpl.tar.xz");

    private final String ffmpeg;

    FFmpegDefaultFileLocation(String s) {
        this.ffmpeg = s;
    }

    public static String getFFmpeg() {
        if (SystemUtils.IS_OS_WINDOWS) {
            return SystemUtils.USER_HOME + "/ytdl4j/ffmpeg-download/ffmpeg-master-latest-win64-gpl.zip";
        } else if (SystemUtils.IS_OS_LINUX) {
            return SystemUtils.USER_HOME + "/ytdl4j/ffmpeg-download/ffmpeg-master-latest-linuxarm64-gpl.tar.xz";
        } else {
            throw new RuntimeException("Unsupported OS");
        }
    }
}
