package net.ch7;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class LastModified {
    public static void main(String[] args) {
        try {
            URL u=new URL("http://www.ibiblio.org/xml");
            HttpURLConnection http=(HttpURLConnection) u.openConnection();
            http.setRequestMethod("HEAD");
            System.out.println(u+" modified "+new Date(http.getLastModified()));
        }catch (MalformedURLException e){
            System.err.println("Not URL");
        }catch (IOException e){
            System.err.println(e);
        }
        System.out.println();
    }
}
