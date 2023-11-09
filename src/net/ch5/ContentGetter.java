package net.ch5;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ContentGetter {
    public static void main(String[] args) {
//        if (args.length>0){
            try {
                URL u=new URL("http://www.oreilly.com");
                Object o=u.getContent();
                System.out.println("I got a "+o.getClass().getName());
            }catch (MalformedURLException e){
                System.out.println("Not URL");
        }catch (IOException e){
                System.out.println(e);
            }
    }
}
