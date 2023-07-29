package input_output_files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

// Потоковая передача содержимого файла
public class InFiles {

    public static void main(String[] args) {

        try(BufferedReader brStream=Files.newBufferedReader(
                Paths.get(FILE_PATH))) {
            brStream.lines().forEach(System.out::println);
        }catch (IOException e){
            e.printStackTrace();
        }




//        try (Stream<String> filesStream = Files.lines(
//                Paths.get(FILE_PATH), StandardCharsets.UTF_8)) {
//            filesStream.forEach(System.out::println);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    private static final String FILE_PATH="doubles.txt";



}
