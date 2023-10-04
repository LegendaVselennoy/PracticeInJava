package file;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
//        int a[]={12,45,65,87};
//        String mas= Arrays.toString(a);
//        StringBuilder s=new StringBuilder(mas);
//
//        try{
//            FileWriter f=new FileWriter("Main.txt");
//            f.write(mas);
//            f.close();
//        }catch (IOException e){
//            e.printStackTrace();
//        }

        try{
            FileWriter writer=new FileWriter("foo.txt");
            writer.write("H foo");
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
