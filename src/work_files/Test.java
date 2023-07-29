package work_files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class Test {
    /**
     *
     Стандартные открытые опции
     Значение	Описание
     APPEND	Если файл открыт для доступа WRITE, то байты будут записаны в конец файла, а не в его начало.
     CREATE	Создайте новый файл, если он не существует.
     CREATE_NEW	Создайте новый файл, если файл уже существует.
     DELETE_ON_CLOSE	Удалить по закрытию.
     DSYNC	Требует, чтобы каждое обновление содержимого файла было записано синхронно на базовое устройство хранения.
     READ	Открыть для чтения.
     SPARSE	Разреженный файл.
     SYNC	Требует, чтобы каждое обновление содержимого файла или метаданных было записано синхронно на базовое устройство хранения.
     TRUNCATE_EXISTING	Если файл уже существует и он открыт для доступа WRITE, его длина сокращается до 0.
     WRITE	Открыть для записи.
     */

    // Чтение файла

    /**
     * Приведенный ниже код сначала считывает файл в байтовый массив, а затем создает строковый объект,
     * содержащий содержимое указанного файла
     * небольшого размера
     */

    public void readFile(Path path){
        if (Files.exists(path)){
            try {
                byte[]bytes=Files.readAllBytes(path);
                String text=new String(bytes, StandardCharsets.UTF_8);
                System.out.println(text);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void readFile1(Path path){
        if (Files.exists(path)){
            try {
                List<String>lines=Files.readAllLines(path,StandardCharsets.UTF_8);
                for (String line : lines) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Потоки чтения java.io
//    InputStream is = Files.newInputStream();
//    InputStreamReader isr = new InputStreamReader(is);
//    BufferedReader br = new BufferedReader(isr);
//
//    BufferedReader reader = Files.newBufferedReader(, StandardCharsets.UTF_8);

    // Запись в файл

    // байтами
    public void writeFile(Path path){
        byte[] bytes = new byte[] {0x48, 0x65, 0x6c, 0x6c, 0x6f, 0x20, 0x77, 0x6f, 0x72, 0x6c, 0x64, 0x21};

        try {
            Files.write(path, bytes);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }


    // Потоки записи

    /**
     * OutputStream os = Files.newOutputStream(filePath1);
     * OutputStreamWriter osw = new OutputStreamWriter(os);
     * BufferedWriter bw = new BufferedWriter(osw);
     * BufferedWriter writer = Files.newBufferedWriter(filePath2, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
     */

    // Копирование и перемещение файлов и каталогов

    /**
     * Стандартные параметры копирования
     * Значение	Описание
     * ATOMIC_MOVE	Переместите файл как элементарную операцию файловой системы.
     * COPY_ATTRIBUTES	Скопируйте атрибуты в новый файл.
     * REPLACE_EXISTING	Замените существующий файл, если он существует.
     */

    /**
     * copy(InputStream in, Path target, CopyOption... options)
     * Копирует все байты из входного потока в файл.
     * copy(Path source, OutputStream out)
     * Копирует все байты из файла в выходной поток.
     * copy(Path source, Path target, CopyOption... options)
     * Скопируйте файл в целевой файл.
     */

    public void copyFiles(Path source,Path target){
        try {
            Files.copy(source, target, StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Перемещение файлов и каталогов
    public void copyFiles2(Path source,Path target){
        try {
            Files.copy(source, target, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Удаление файлов и каталогов
    public void deleteFile(Path path){
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteFile1(Path path){
        try {
            Files.deleteIfExists(path);
            System.out.println("Удаленный файл "+path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
