package org.justme8code.ytdl4j.downloaders;


import org.justme8code.ytdl4j.entities.Ydl4jDownloadable;
import org.justme8code.ytdl4j.executors.YdlCommandExecutor;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VideoDownloadManager {

    // Verify the downloadable file name
    public String verifyDownloadableFileName(Ydl4jDownloadable downloadable) {
        return downloadable.fileName() != null && !downloadable.fileName().isEmpty()
                ? downloadable.outputPath() + downloadable.fileName() + ".%(ext)s"
                : downloadable.outputPath() + "%(title)s.%(ext)s";
    }

    // Start a new download
    public void startDownload(Ydl4jDownloadable downloadable) {


            try {
                YdlCommandExecutor.executeCommandWithProgress(
                        List.of("-o", verifyDownloadableFileName(downloadable), downloadable.url())
                );
                System.out.println("Download completed for: " + downloadable.outputPath());
            } catch (IOException e) {
                System.err.println("Error during download: " + e.getMessage());
            }

    }

}