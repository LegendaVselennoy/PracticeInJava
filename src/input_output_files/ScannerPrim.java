package input_output_files;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;

public class ScannerPrim {
    public static void main(String[] args) {
        try(Scanner scanner=new Scanner(
                Path.of("doubles.txt"), StandardCharsets.UTF_8)) {
            while (scanner.hasNextDouble()){
                double number=scanner.nextDouble();
                System.out.println(number);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
