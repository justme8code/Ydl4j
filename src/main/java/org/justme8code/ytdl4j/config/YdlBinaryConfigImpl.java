package org.justme8code.ytdl4j.config;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.justme8code.ytdl4j.error.Ydl4jError;

import java.io.*;
import java.net.URI;

public class YdlBinaryConfigImpl implements YdlBinaryConfig{

    // urls for downloading yt-dlp binary for different platforms
    private static final String WINDOWS_URL = "https://github.com/yt-dlp/yt-dlp/releases/latest/download/yt-dlp.exe";
    private static final String LINUX_URL = "https://github.com/yt-dlp/yt-dlp/releases/latest/download/yt-dlp";
    private static final String MAC_URL = "https://github.com/yt-dlp/yt-dlp/releases/latest/download/yt-dlp";

    // location to save the binary file (default location)
    private static final String BINARY_DIR = SystemUtils.USER_HOME + "/ytdl4j/yt-dlp";
    private static final String BINARY_NAME = "yt-dlp";

    @Override
    //Method to check if binary exists
    public boolean isBinaryAvailable() {
        File binaryFile= new File(BINARY_DIR, BINARY_NAME);
        return binaryFile.exists();
    }

    // Helper method to get the download URL based on the OS
    private static String getPlatformUrl() {
        if (SystemUtils.IS_OS_WINDOWS) {
            return WINDOWS_URL;
        } else if (SystemUtils.IS_OS_LINUX) {
            return LINUX_URL;
        } else if (SystemUtils.IS_OS_MAC) {
            return MAC_URL;
        } else {
            return null;
        }
    }

    @Override
    // Method to download yt-dlp binary
    public void downloadYtdlBinary()  {
        String platformUrl = getPlatformUrl();
        if(platformUrl == null) {
//            throw new UnsupportedOperationException("Unsupported platform : " + SystemUtils.OS_NAME);
            throw new Ydl4jError("No platform url available "+ SystemUtils.OS_NAME);
        }

        // binary destination path
        File binaryFile= new File(BINARY_DIR, BINARY_NAME);

        // Download the binary
        System.out.println("Downloading yt-dlp binary.... ");

        try{
            FileUtils.copyURLToFile(new URI(platformUrl).toURL(), binaryFile);
        }catch(Exception e){
            throw new Ydl4jError("Error downloading yt-dlp binary", e);
        }

        // Make the binary executable (Linux/Mac only)
        if(SystemUtils.IS_OS_LINUX || SystemUtils.IS_OS_MAC) {
            binaryFile.setExecutable(true);
        }

        System.out.println("yt-dlp binary downloaded successfully at: " + binaryFile.getAbsolutePath());

    }




}
