package org.justme8code.ytdl4j;


import org.apache.commons.lang3.SystemUtils;
import org.justme8code.ytdl4j.config.FFmpegConfig;
import org.justme8code.ytdl4j.config.FFmpegConfigImpl;
import org.justme8code.ytdl4j.config.YdlBinaryConfig;
import org.justme8code.ytdl4j.config.YdlBinaryConfigImpl;
import org.justme8code.ytdl4j.entities.Ydl4jVideo;
import org.justme8code.ytdl4j.downloaders.VideoDownloadManager;
import org.justme8code.ytdl4j.entities.Ydl4jDownloadable;

public class Main {
    public static void main(String[] args) {

        try {
            // Set the output path and video URL
            String outputPath = SystemUtils.USER_HOME+"/videos/ytdl4j";

            // Create an instance of Ydl4jDownloadable (can be video or playlist)
            Ydl4jDownloadable yt = new Ydl4jVideo("https://www.youtube.com/watch?v=UuAF1DM9qv4", outputPath, "video1");

            // Create the VideoDownloadManager and start the download
            VideoDownloadManager videoManager = new VideoDownloadManager();
            videoManager.startDownload(yt);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}