package org.justme8code.ytdl4j.entities;


public record Ydl4jVideo(String url, String outputPath, String fileName) implements Ydl4jDownloadable {}
