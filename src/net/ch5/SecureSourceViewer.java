package net.ch5;

import java.io.*;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.URL;

public class SecureSourceViewer {
    public static void main(String[] args) {
        Authenticator.setDefault(new DialogAuthenticator());

        String[] line = {"https://www.oreilly.com"};

        for (int i = 0; i < line.length; i++) {

        try {
            URL u = new URL(line[i]);
            try (InputStream in = new BufferedInputStream(u.openStream())) {
                Reader r = new InputStreamReader(in);
                int c;
                while ((c = r.read()) != -1) {
                    System.out.print((char) c);
                }
            }
        } catch (MalformedURLException e) {
            System.err.println("Not URL");
        } catch (IOException e) {
            System.err.println(e);
        }

        // напечатайте пустую строку на отдельных страницах
        System.out.println();
    }
        // Поскольку мы использовали AWT, мы должны явно выйти.
        System.exit(0);
    }
}
