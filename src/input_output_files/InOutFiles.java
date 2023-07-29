package input_output_files;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InOutFiles {

    public static void main(String[] args) {
        Path chineseFile = Paths.get("Test.txt");
        InOutFiles.inRead(chineseFile);



    }

     // Эффективное чтение/запись текстовых файлов
    /**
     * Двоичные данные являются предметом работы двух абстрактных классов, InputStream и OutputStream
     * Для потоковой передачи файлов сырых двоичных данных FileInputStream и FileOutputStream,
     * читают/пишут 1 байт(8 бит) за один раз  (AudioInputStream - для аудиофайла)
     * не подходят для текстовых файлов.
     */


    public static void inRead(Path path){
        try(InputStream is=new FileInputStream(path.toString())){
            int i;
            while ((i=is.read())!=-1){
                System.out.print((char)i);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * InputStreamReader - класс является мостом из сырых байтовых потоков к символьным потокам и позволяет нам задавать набор символов:
     * китайский (UTF-16)
     */

    public static void inRead1(Path path){
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(path.toFile()),
                StandardCharsets.UTF_8)) {
            int i;
            while ((i = isr.read()) != -1) {
                System.out.print((char) i) ;
            }
        }catch (IOException e){
            e.printStackTrace();  // все равно медленно
        }
    }

    // Для текстовых файлов у нас есть выделенный класс FileReader (или соответственно Filewriter)., читает 2 или 4 байта.
    public  static void inRead2(Path path){
        try (FileReader isr = new FileReader(path.toFile(), StandardCharsets.UTF_8)) {
            int i;
            while ((i = isr.read()) != -1) {
                System.out.print((char) i) ;
            }
        }catch (IOException e){
            e.printStackTrace();  // все равно медленно
        }
    }

    /**
     * Главная идея заключается в буферизации данных перед обработкой, используется классами,
     * такими как BufferedInputStream, BufferedOutputStream для сырых двоичных потоков и BufferedReader и BufferedWriter
     * для символьных потоков
     */

    public static void inRead3(Path path){
        try(BufferedReader br=new BufferedReader(
                new FileReader(path.toFile(),StandardCharsets.UTF_8))){
            String line;
            // продолжать буферизировать и печатать
            while ((line=br.readLine())!=null){
                System.out.println(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void inRead4(Path path){
        try(BufferedReader br= Files.newBufferedReader(path,StandardCharsets.UTF_8)) {
            String line;
            while ((line= br.readLine())!=null){
                System.out.println(line);
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
