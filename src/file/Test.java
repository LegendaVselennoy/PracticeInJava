package file;

import java.io.*;

public class Test {
    public static void main(String[] args) {
        try {
            FileWriter writer=new FileWriter("Text.txt");
            writer.write("Hello");
            writer.close();


            File myFile=new File("Text.txt");
            FileReader fileReader=new FileReader(myFile);

            BufferedReader reader=new BufferedReader(fileReader);
            String line;
            while ((line=reader.readLine())!=null){
                System.out.println(line);
            }
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
