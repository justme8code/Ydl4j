package org.justme8code.ytdl4j.config;

import java.io.File;

public interface FFmpegConfig {
    boolean isFFmpegInstalled();
    void downloadFFmpeg();
    void extractFFmpeg();
}
