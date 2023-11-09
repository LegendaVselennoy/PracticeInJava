package net.ch7;

import net.ch5.QueryString;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FormPoster {
    private URL url;
    private QueryString query=new QueryString();

    public FormPoster(URL url){
        if (!url.getProtocol().toLowerCase().startsWith("http")){
            throw new IllegalArgumentException("Posting ");
        }
        this.url=url;
    }

    public void add(String name, String value) {
        query.add(name, value);
    }
    public URL getURL() {
        return this.url;
    }

    public InputStream post() throws IOException {
        URLConnection uc = url.openConnection();
        uc.setDoOutput(true);
        try (OutputStreamWriter out
                     = new OutputStreamWriter(uc.getOutputStream(), "UTF-8")) {
            out.write(query.toString());
            out.write("\r\n");
            out.flush();
        }
        return uc.getInputStream();
    }

    public static void main(String[] args) {
        URL url;
        try {
             url = new URL("");
            } catch (MalformedURLException ex) {
                System.err.println("Usage: java FormPoster url");
                return;
            }
            try {
                url = new URL(
                        "http://www.cafeaulait.org/books/jnp4/postquery.phtml");
            } catch (MalformedURLException ex) { // shouldn't happen
                System.err.println(ex);
                return;
            }

        FormPoster poster = new FormPoster(url);
        poster.add("name", "Elliotte Rusty Harold");
        poster.add("email", "elharo@ibiblio.org");
        try (InputStream in = poster.post()) {
            // Read the response
            Reader r = new InputStreamReader(in);
            int c;
            while((c = r.read()) != -1) {
                System.out.print((char) c);
            }
            System.out.println();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
