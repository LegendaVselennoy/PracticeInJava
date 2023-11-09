package net.ch7;

// Выведите весь HTTP-заголовок целиком

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class AllHeaders {

    public static void main(String[] args) {
        try{
            URL u=new URL("http://www.oreilly.com");
            URLConnection uc=u.openConnection();
            for (int j = 1; ; j++) {
                String header=uc.getHeaderField(j);
                if (header==null)break;
                System.out.println(uc.getHeaderFieldKey(j)+": "+header);
            }
        }catch (MalformedURLException e){
            System.err.println("Not URL");
        }catch (IOException e){
            System.err.println(e);
        }
        System.out.println();
    }
}
