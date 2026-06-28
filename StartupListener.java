package com;

import java.io.File;
import java.io.FileWriter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            // Remove existing symlink at /usr/bin/google-chrome
            Runtime.getRuntime().exec(new String[]{"rm", "-f", "/usr/bin/google-chrome"}).waitFor();

            // Replace with wrapper that always adds --no-sandbox and other
            // flags required for Chrome to work in Docker/container environments.
            // Without --no-sandbox, Chrome's sandbox blocks file input access,
            // causing ChromeDriver send_keys to fail with "File not found".
            String wrapper = "#!/bin/bash\n" +
                "exec /usr/bin/google-chrome-stable --no-sandbox --disable-dev-shm-usage --disable-gpu \"$@\"\n";

            FileWriter fw = new FileWriter(new File("/usr/bin/google-chrome"));
            fw.write(wrapper);
            fw.close();

            Runtime.getRuntime().exec(new String[]{"chmod", "+x", "/usr/bin/google-chrome"}).waitFor();
        } catch (Exception ignored) {}
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {}
}
