package org.justme8code.ytdl4j.error;

public class Ydl4jError extends RuntimeException {
    public Ydl4jError(String message) {
        super(message);
    }
    public Ydl4jError(String message, Throwable cause) {
        super(message, cause);
    }
}
