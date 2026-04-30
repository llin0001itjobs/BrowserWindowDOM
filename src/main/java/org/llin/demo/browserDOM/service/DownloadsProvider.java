package org.llin.demo.browserDOM.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DownloadsProvider {

    private String downloadsPath;

    @PostConstruct
    public void init() {
        // This is the standard default Downloads folder on ALL major OSes
        String home = System.getProperty("user.home");
        downloadsPath = home + File.separator + "Downloads";
    }

    /**
     * Returns the default Downloads folder for the current user/device.
     * Automatically respects whatever the user has set as default
     * (including your E: drive change on Windows 11).
     */
    public String getDownloadsPath() {
        return downloadsPath;
    }
}