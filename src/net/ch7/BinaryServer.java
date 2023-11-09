package net.ch7;

// Загрузка двоичного файла с веб-сайта и сохранение его на диск

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class BinaryServer {
    public static void main(String[] args) {
        try {
            URL root=new URL("");
            saveBinaryFile(root);
        }catch (MalformedURLException e){
            System.err.println("Not URL");
        }catch (IOException e){
            System.err.println(e);
        }
    }

    public static void saveBinaryFile(URL u) throws IOException{
        URLConnection uc = u.openConnection();
        String contentType=uc.getContentType();
        int contentLength=uc.getContentLength();
        if (contentType.startsWith("text/") || contentLength==-1){
            throw new IOException("Not binary file");
        }
        try(InputStream raw=uc.getInputStream()){
            InputStream in=new BufferedInputStream(raw);
            byte[]data=new byte[contentLength];
            int offset=0;
            while (offset<contentLength){
                int bytesRead=in.read(data,offset,data.length-offset);
                if (bytesRead==-1) break;
                offset+=bytesRead;
            }
            if (offset!=contentLength){
               throw new IOException("Only read "+offset
               +" bytes; Expected "+contentLength+" bytes");
            }
            String filename=u.getFile();
            filename=filename.substring(filename.lastIndexOf('/')+1);
            try(FileOutputStream fout=new FileOutputStream(filename)) {
                fout.write(data);
                fout.flush();
            }
        }
    }
}
