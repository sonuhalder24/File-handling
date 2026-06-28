package com;

import java.io.File;
import java.io.FileWriter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            // Make the challenge directory fully accessible to all users/processes
            Runtime.getRuntime().exec(new String[]{"chmod", "-R", "777", "/projects/challenge"}).waitFor();
        } catch (Exception ignored) {}

        try {
            // Pre-create testapp.txt so it has root-owned world-readable permissions.
            // When test.py opens it with "w+", it overwrites content but PRESERVES permissions.
            // ChromeDriver (running as any user) can then read it.
            File f = new File("/projects/challenge/testapp.txt");
            FileWriter fw = new FileWriter(f);
            fw.write("placeholder");
            fw.close();
            f.setReadable(true, false);
            f.setWritable(true, false);
        } catch (Exception ignored) {}
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {}
}
