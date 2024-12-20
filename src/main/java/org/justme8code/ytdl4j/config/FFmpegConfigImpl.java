package org.justme8code.ytdl4j.config;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.xz.XZCompressorInputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.SystemUtils;
import org.justme8code.ytdl4j.error.Ydl4jError;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.attribute.FileTime;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FFmpegConfigImpl implements FFmpegConfig {
    @Override
    public boolean isFFmpegInstalled() {
        try {
            ProcessBuilder pb = new ProcessBuilder("ffmpeg", "-version");
            pb.redirectErrorStream(true);

            Process p = pb.start();
            return p.waitFor() == 0; // Use waitFor() to check exit value
        } catch (IOException | InterruptedException e) {
            return false;
        }
    }

    @Override
    public void downloadFFmpeg() {
        String ffmpegUrl;
        String fileName;
        if (SystemUtils.IS_OS_WINDOWS) {
            ffmpegUrl = "https://github.com/yt-dlp/FFmpeg-Builds/releases/latest/download/ffmpeg-master-latest-win64-gpl.zip";
            fileName = "ffmpeg-master-latest-win64-gpl.zip";
        } else if (SystemUtils.IS_OS_LINUX) {
            ffmpegUrl = "https://github.com/yt-dlp/FFmpeg-Builds/releases/download/latest/ffmpeg-master-latest-linuxarm64-gpl.tar.xz";
            fileName = "ffmpeg-master-latest-linuxarm64-gpl.tar.xz";
        } else {
            ffmpegUrl = "https://github.com/yt-dlp/FFmpeg-Builds/releases/latest/download/ffmpeg-master-latest-linux64-gpl.tar.xz";
            fileName = "ffmpeg-master-latest-linux64-gpl.tar.xz";
        }

        try {
            // Define the output directory
            Path outputPath = Paths.get(SystemUtils.USER_HOME, "ytdl4j", "ffmpeg-download");
            Files.createDirectories(outputPath);

            // Download the file and save it
            try (InputStream inputStream = new URI(ffmpegUrl).toURL().openStream()) {
                Files.copy(inputStream, outputPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("FFmpeg downloaded successfully at: " + outputPath.resolve(fileName).toAbsolutePath());
            }
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException("FFmpeg download failed", e);
        }
    }

    @Override
    public void extractFFmpeg() {
        Path archive = Paths.get(FFmpegDefaultFileLocation.getFFmpeg());
        Path outputDir = Paths.get(SystemUtils.USER_HOME, "ytdl4j", "ffmpeg-extracted");

        try {
            Files.createDirectories(outputDir); // Create output directory if it doesn't exist

            if (archive.toString().endsWith(".zip")) {
                try (ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(archive.toFile())))) {
                    processZipArchive(zipInputStream, outputDir);
                }
            } else if (archive.toString().endsWith(".tar.xz")) {
                try (TarArchiveInputStream tarInputStream = new TarArchiveInputStream(new XZCompressorInputStream(new FileInputStream(archive.toFile())))) {
                    processTarArchive(tarInputStream, outputDir);
                }
            } else {
                throw new Ydl4jError("Unsupported archive format: " + archive.toString());
            }
        } catch (IOException e) {
            throw new Ydl4jError("Failed to extract FFmpeg", e);
        }
    }

    private void processZipArchive(ZipInputStream zipInputStream, Path outputDir) throws IOException {
        ZipEntry entry;
        while ((entry = zipInputStream.getNextEntry()) != null) {
            File file = new File(outputDir.toFile(), entry.getName());
            if (entry.isDirectory()) {
                file.mkdirs();
            } else {
                file.getParentFile().mkdirs(); // Ensure parent directories exist
                try (OutputStream outputStream = new FileOutputStream(file)) {
                    IOUtils.copy(zipInputStream, outputStream);
                }
            }
        }
    }

    private void processTarArchive(TarArchiveInputStream tarInputStream, Path outputDir) throws IOException {
        ArchiveEntry entry;
        while ((entry = tarInputStream.getNextEntry()) != null) {
            File file = new File(outputDir.toFile(), entry.getName());
            if (entry.isDirectory()) {
                file.mkdirs();
            } else {
                file.getParentFile().mkdirs(); // Ensure parent directories exist
                try (OutputStream outputStream = new FileOutputStream(file)) {
                    IOUtils.copy(tarInputStream, outputStream);
                }
            }
        }
    }
}
