package net.ch5;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class SourceViewer {
    public static void main(String[] args) {
//        if (args.length>0){
            InputStream in=null;
            try{
                URL u=new URL("https://ru.wikipedia.org/wiki/%D0%AF%D0%BD%D0%B4%D0%B5%D0%BA%D1%81");
//                URL u=new URL(args[0]);
                in=u.openStream();
                in=new BufferedInputStream(in);
                Reader r=new InputStreamReader(in);
                int c;
                while ((c=r.read())!=-1){
                    System.out.print((char) c);
                }
            }catch (MalformedURLException e){
                System.out.println("Not url");
            }catch (IOException e){
                System.out.println(e);
            }finally {
                if (in!=null){
                    try {
                        in.close();
                    }catch (IOException e){

                    }
                }
            }
//        }
    }
}
