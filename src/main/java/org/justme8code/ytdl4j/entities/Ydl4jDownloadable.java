package org.justme8code.ytdl4j.entities;

public interface Ydl4jDownloadable {
    String url();         // URL to download
    String outputPath();  // Output path for the downloaded file
    String fileName();
}
