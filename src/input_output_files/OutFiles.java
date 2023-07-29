package input_output_files;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public class OutFiles {
    // Запись текстовых файлов
    public static void main(String[] args) {
        Path textFile= Paths.get("simple.txt");
        List<String> linesToWrite= Arrays.asList("abc","def","ghi");
        try {
            Files.write(textFile,linesToWrite,StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,StandardOpenOption.WRITE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        writeFileText(textFile);
    }

    // Запись текстового файла посредством класса BufferedWriter

    // StandardOpenOption.CREATE - Создайте новый файл, если он не существует
    // StandardOpenOption.WRITE - Открыть для записи
    // StandardOpenOption.APPEND - Если файл открыт для доступа WRITE, то байты будут записаны в конец файла, а не в его начало
    // StandardOpenOption.CREATE_NEW - Создайте новый файл, если файл уже существует
    // StandardOpenOption.DELETE_ON_CLOSE - Удалить по закрытию
    // StandardOpenOption.DSYNC - Требует, чтобы каждое обновление содержимого файла было записано синхронно на базовое устройство хранения
    // StandardOpenOption.READ - Открыть для чтения
    // StandardOpenOption.SPARSE - Разреженный файл
    // StandardOpenOption.SYNC - Требует, чтобы каждое обновление содержимого файла или метаданных было записано синхронно на базовое устройство хранения
    // StandardOpenOption.TRUNCATE_EXISTING Если файл уже существует и он открыт для доступа WRITE, его длина сокращается до 0
    public static void writeFileText(Path path){
        try(BufferedWriter bw= Files.newBufferedWriter(
                path, StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                StandardOpenOption.WRITE)) {
             bw.write("Lorem ipsum dolor sit amet,...");
             bw.newLine();
             bw.write("sed do eiusmod tempor incididunt...");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // это может быть полезно для записи огромных текстовых файлов
    public static void writeFileText1(Path path){
        Path textFile = Paths.get("simple.txt");
        CharBuffer cb=CharBuffer.wrap("Lorem ipsum dolor sit amet,...");
        try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(
                textFile, EnumSet.of(StandardOpenOption.CREATE,
                        StandardOpenOption.READ, StandardOpenOption.WRITE))) {
            MappedByteBuffer mbBuffer = fileChannel
                    .map (FileChannel.MapMode.READ_WRITE, 0, cb.length());
            if (mbBuffer != null) {
                mbBuffer.put(StandardCharsets.UTF_8.encode(cb));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
