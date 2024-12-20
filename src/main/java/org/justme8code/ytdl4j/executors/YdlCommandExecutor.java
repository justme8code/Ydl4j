package org.justme8code.ytdl4j.executors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class YdlCommandExecutor {
    private static final String BINARY_PATH = System.getProperty("user.home") + "/yt-dlp/yt-dlp";

    // executes yt-dlp with arguments

    public static String executeCommand(String... commandArgs) throws IOException, InterruptedException {
        // Prepare full command, starting with the yt-dlp binary path

        String[] command = new String[commandArgs.length + 1];
        command[0] = BINARY_PATH;
        System.arraycopy(commandArgs, 0, command, 1, commandArgs.length);

        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream(true); // combine stdout and stderr
        Process p = pb.start();

        // capture the output of the command
        StringBuilder output = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        }

        // wait for the process to finish and get the exit code

        int exitCode = p.waitFor();
        if (exitCode != 0) {
            throw new IOException("yt-dlp command failed with exit code " + exitCode);
        }

        return output.toString();

    }

    //Method to download video using yt-dlp
    public static void downloadVideo(String videoUrl, String outputPath) throws IOException {
        try{
            String commandOutput = executeCommand("-o",outputPath,videoUrl);
            System.out.println("Download completed successfully:\n" + commandOutput);
        } catch (IOException | InterruptedException e) {
            System.err.println("Error downloading video: " + e.getMessage());
        }
    }

    // Method to extract metadata from a video
    public static void extractMetadata(String url) {
        try {
            String commandOutput = executeCommand("--get-title", "--get-duration", "--get-url", url);
            System.out.println("Video Metadata:\n" + commandOutput);
        } catch (IOException | InterruptedException e) {
            System.err.println("Error extracting metadata: " + e.getMessage());
        }
    }

    // Method to execute the yt-dlp command and show progress
    public static void executeCommandWithProgress(List<String> commandArgs) throws IOException {
        String[] command = new String[commandArgs.size() + 1];
        command[0] = BINARY_PATH; // Assuming yt-dlp binary is in PATH, otherwise provide full path
        for (int i = 0; i < commandArgs.size(); i++) {
            command[i + 1] = commandArgs.get(i);
        }

        // Create a process to run yt-dlp
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true); // Merge stdout and stderr

        // Start the process
        Process process = processBuilder.start();

        // Read the output in real-time
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            // Look for download progress lines and print them
            System.out.println(line);
        }

        try {
            // Wait for the process to finish
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.out.println("Error executing yt-dlp command");
            }
        } catch (InterruptedException e) {
            System.out.println("Process was interrupted");
        } finally {
            // Close the reader
            reader.close();
        }
    }
}
