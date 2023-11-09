package net.ch7;

// Загрузите веб-страницу с URL-соединением

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SourceViewer2 {
    public static void main(String[] args) {
        try{
            URL u=new URL("https://www.oreilly.com");
            URLConnection uc=u.openConnection();
            try(InputStream raw=uc.getInputStream()) {
                InputStream buffer=new BufferedInputStream(raw);
                // подключите входной поток к считывателю
                Reader reader=new InputStreamReader(buffer);
                int c;
                while ((c=reader.read())!=-1){
                    System.out.print((char)c);
                }
            }
        }catch (MalformedURLException e){
            System.err.println("Not URL");
        }catch (IOException e){
            System.err.println(e);
        }
    }
}
