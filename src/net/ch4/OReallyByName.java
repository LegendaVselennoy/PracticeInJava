package net.ch4;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class OReallyByName {
    public static void main(String[] args) {
        try{

//            InetAddress address=InetAddress.getByName("www.oreilly.com");
//            System.out.println(address);

       //     InetAddress address = InetAddress.getLocalHost(); //

//            InetAddress address = InetAddress.getByName("2.23.132.164");
//            System.out.println(address.getHostName());

             InetAddress[] addresses=InetAddress.getAllByName("www.oreilly.com");
            for (InetAddress address : addresses) {
                System.out.println(address);
            }
        }catch (UnknownHostException ex){
            System.out.println("Не удалось найти www.oreilly.com");
        }
    }
}
