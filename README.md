ytdl4j - YouTube Downloader Library for Java
============================================

ytdl4j is a simple Java wrapper for the yt-dlp tool that allows you to easily download videos and playlists from YouTube (and other websites) without requiring Python. The library offers methods to interact with yt-dlp, including downloading videos, extracting metadata, and more.

Table of Contents
-----------------

*   [Features](#features)

*   [Future Features](#future-features)

*   [Requirements](#requirements)

*   [Installation](#installation)

*   [Usage](#usage)

*   [Basic Example](#basic-example)

*   [Error Handling](#error-handling)

*   [FFmpeg Support](#ffmpeg-support)

*   [Contributing](#contributing)

*   [License](#license)


Features
--------

*   Download videos from YouTube only.

*   Download from other websites (not tested yet, but URL could be passed for testing for your self).

*   Auto-download yt-dlp for the current operating system platform.

*   Auto-download FFmpeg for Operating System (Linux & Windows only for now).


Future Features
---------------

*   Support for downloading playlists.

*   Option to download in different formats.

*   FFmpeg support for video/audio processing (with FFmpeg installation).

*   Provides progress reporting during download.

*   Easy-to-use Java interface for yt-dlp commands.


Requirements
------------

Before using ytdl4j, ensure that:

1.  The library automatically has functions to call, enabling you to download yt-dlp automatically.

2.  You have the yt-dlp binary installed. If not, download it from [yt-dlp's official GitHub page](https://github.com/yt-dlp/yt-dlp).

3.  Optionally, you can install FFmpeg for media processing. The library provides functionality to download and configure FFmpeg automatically.


Installation
------------

1.  Clone the repository or add it to your project using Maven or Gradle.


Usage
-----

### Basic Example

#### To download a single video from YouTube:

```java
package org.justme8code.ytdl4j;

import org.justme8code.ytdl4j.downloaders.VideoDownloadManager;
import org.justme8code.ytdl4j.entities.Ydl4jDownloadable;
import org.justme8code.ytdl4j.entities.Ydl4jVideo;

public class Main {
    public static void main(String[] args) {
        try {
            // Set the output path and video URL
            String outputPath = "/path/to/download/folder/";

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

```

#### To download Yt-dlp and FFmpeg using the library
```java
package org.justme8code.ytdl4j;


import org.justme8code.ytdl4j.config.FFmpegConfig;
import org.justme8code.ytdl4j.config.FFmpegConfigImpl;
import org.justme8code.ytdl4j.config.YdlBinaryConfig;
import org.justme8code.ytdl4j.config.YdlBinaryConfigImpl;
import org.justme8code.ytdl4j.entities.Ydl4jVideo;
import org.justme8code.ytdl4j.downloaders.VideoDownloadManager;
import org.justme8code.ytdl4j.entities.Ydl4jDownloadable;

public class Main {
    public static void main(String[] args) {

        YdlBinaryConfig ydlBinaryConfig = new YdlBinaryConfigImpl();
        ydlBinaryConfig.downloadYtdlBinary();

        FFmpegConfig fFmpegConfig = new FFmpegConfigImpl();
        fFmpegConfig.downloadFFmpeg();
        fFmpegConfig.extractFFmpeg();

    }
}
```

Contributing
------------

We welcome contributions to ytdl4j! Feel free to fork the repository and submit pull requests. If you encounter any issues or have ideas for improvements, please open an issue on GitHub.

Motivation
----------

While many tools exist for downloading videos from YouTube and other platforms, including Python-based libraries like yt-dlp, there is a gap when it comes to integrating this functionality smoothly into Java applications. As Java developers, we often face challenges when attempting to integrate external tools, especially when they require a Python environment or are limited by outdated Java wrappers. **ytdl4j** was created to solve this problem.

### Why this?

1.  **Simplicity**: yt-dlp is a powerful and feature-rich tool, but integrating it into Java projects typically requires additional steps or workarounds. **ytdl4j** simplifies this process, enabling Java developers to use the full power of yt-dlp without worrying about Python dependencies or complex setups.

2.  **Java-centric**: Many existing solutions either do not provide Java bindings or do so in a way that feels disjointed with the Java ecosystem. **ytdl4j** was created to provide a smooth, native experience for Java developers who need video downloading features integrated into their applications.

3.  **Java Wrappers of yt-dlp Have Been Lacking or Discontinued**: Other Java wrappers for yt-dlp have existed in the past, but many of them have either stopped being maintained or didn't evolve to keep up with the rapidly changing features of yt-dlp. This means they quickly become outdated, limiting their usefulness. **ytdl4j** was built to not only keep up with new features but also continue advancing, ensuring Java developers have access to the latest capabilities.

4.  **Extending Java's Capabilities**: This project is not just about downloading videos—it's about pushing Java's capabilities into more complex domains, like media processing, where tools like yt-dlp shine. By integrating with **ytdl4j**, Java developers can expand their apps to include video/audio handling, metadata extraction, and other media-processing tasks without leaving the Java ecosystem.

5.  **Filling the Gap**: While there are Python libraries and wrappers that do a great job, many Java developers are either unfamiliar with Python or prefer not to rely on it. With **ytdl4j**, we’re making these features more accessible for Java developers, filling an important gap in the ecosystem.

6.  **A Step Toward Bigger Projects**: This is just one small tool. But the goal of **ytdl4j** is bigger than simply creating a downloader. It’s about opening up new possibilities for Java in fields that are traditionally dominated by other languages. By improving accessibility to powerful tools like yt-dlp, we're opening the door to larger projects that combine video/audio processing with the power and flexibility of Java.


### What Are We Trying to Build Here?

With **ytdl4j**, the vision is to make Java more versatile in handling complex tasks, such as video and audio processing. By building a simple yet powerful interface to the yt-dlp tool, we are empowering Java developers to incorporate media downloading, metadata extraction, and processing seamlessly into their applications.

But it's more than just a library—it's a stepping stone towards a larger vision of enabling Java developers to build even more ambitious media-oriented projects. Whether you’re creating a media player, a video editor, or a custom downloader, **ytdl4j** is designed to support your needs while simplifying the process.

By contributing to **ytdl4j**, you are not just improving this tool—you are contributing to a broader movement to make Java a more powerful language for all kinds of applications.

**Let's make Java the go-to language for all kinds of exciting projects. Keep building. Keep contributing. Together, we'll keep making things better.**