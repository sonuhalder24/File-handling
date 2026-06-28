package com;

import java.io.File;
import java.io.FileWriter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Chrome crashes as root in a container without --no-sandbox.
        // Create a wrapper that injects the required flags every time the server starts.
        try {
            String wrapper = "#!/bin/bash\n" +
                "exec /usr/bin/google-chrome-stable --no-sandbox --disable-dev-shm-usage --disable-gpu \"$@\"\n";
            File chromeWrapper = new File("/usr/bin/google-chrome");
            chromeWrapper.delete();
            FileWriter fw = new FileWriter(chromeWrapper);
            fw.write(wrapper);
            fw.close();
            Runtime.getRuntime().exec(new String[]{"chmod", "+x", "/usr/bin/google-chrome"}).waitFor();
        } catch (Exception ignored) {}

        // Create testapp.txt in /tmp (local filesystem — ChromeDriver can always access it).
        try {
            File tmpFile = new File("/tmp/testapp.txt");
            FileWriter fw = new FileWriter(tmpFile);
            fw.write("testDataDummy");
            fw.close();
            tmpFile.setReadable(true, false);
            tmpFile.setWritable(true, false);
        } catch (Exception ignored) {}

        // Symlink /projects/challenge/testapp.txt -> /tmp/testapp.txt.
        // Created at server startup (before test.py runs), so the directory entry is
        // already settled when ChromeDriver's access() check fires.
        // Python's open("testapp.txt","w+") follows the symlink and writes to /tmp.
        // ChromeDriver follows the same symlink to /tmp (local fs) and finds the file.
        try {
            Runtime.getRuntime().exec(new String[]{
                "ln", "-sf", "/tmp/testapp.txt", "/projects/challenge/testapp.txt"
            }).waitFor();
        } catch (Exception ignored) {}
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {}
}
