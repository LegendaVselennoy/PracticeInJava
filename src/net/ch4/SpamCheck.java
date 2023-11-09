package net.ch4;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SpamCheck {
    public static final String BLACKHOLE="sbl.spamhaus.org";

    public static void main(String[] args) {
        for (String arg : args) {
            if (isSpammer(arg)){
                System.out.println(arg+" является известным спамером.");
            }else {
                System.out.println(arg+" выглядит законным.");
            }
        }
    }

    private static boolean isSpammer(String arg){
        try {
            InetAddress address=InetAddress.getByName(arg);
            byte[] quad=address.getAddress();
            String query=BLACKHOLE;
            for (byte octet : quad) {
                int unsingnedByte=octet<0 ? octet+256:octet;
                query=unsingnedByte+"."+query;
            }
            InetAddress.getByName(query);
            return true;
        }catch (UnknownHostException e){
            return false;
        }
    }
}
