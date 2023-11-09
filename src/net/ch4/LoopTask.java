package net.ch4;

import java.net.InetAddress;
import java.util.concurrent.Callable;

public class LoopTask implements Callable<String>{
    private String line;

    public LoopTask(String line){
        this.line=line;
    }

    @Override
    public String call(){

        try{
            int index=line.indexOf(' ');
            String address=line.substring(0,index);
            String theRest=line.substring(index);
            String hostname= InetAddress.getByName(address).getHostName();
            return hostname+" "+theRest;
        }catch (Exception e){
            return line;
        }

    }
}
