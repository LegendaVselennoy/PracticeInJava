package input_output_files;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;


// Эффективное чтение/запись двоичных файлов
public class InOutFileBinary {
    public static void main(String[] args) {

        Path binaryFile = Paths.get("/input_output_files/Test.class");
        int fileSize = 0;
        try {
            fileSize = (int) Files.readAttributes(binaryFile, BasicFileAttributes.class). size();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        final byte[] buffer = new byte[fileSize];
        try (BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(binaryFile.toFile()))) {
            int i;
            while ((i = bis.read(buffer)) != -1) {
                System.out.print("\nReading ... " + i);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
     // Если файл является слишком крупным, чтобы поместиться в буфер размера файла, то предпочтительно читать его через
        // меньший буфер с фиксированным размером (например, 512 байт) и с помощью разновидности метода read().

        final byte[] buffer1 = new byte[fileSize];
        try (ByteArrayInputStream b = new ByteArrayInputStream(buffer)) {
            int i;
            while ((i = b.read(buffer)) != -1) {
                System.out.print("\nЧитается ... ");
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        // классы DataInputStream И DataOutputStream предусматривают удобные методы для чтения и записи некоторых типов данных
        Path dataFile = Paths.get("data.bin");
        try (DataInputStream dis = new DataInputStream(
                new BufferedInputStream(Files.newInputStream(dataFile)))) {
            while (dis.available() >0) {
                float nr = dis.readFloat ();
                System.out.println("Read: " + nr);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        // Эффективным способом записи двоичных файлов является использование класса BufferedOutputStream
        Path classFile = Paths.get("input_output_files/Test.class");
        try (BufferedOutputStream bos = new BufferedOutputStream(
                Files.newOutputStream(classFile, StandardOpenOption.CREATE,
                        StandardOpenOption.WRITE))) {
            bos.write(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Для записи огромных текстовых файлов
        try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(
                classFile, EnumSet.of(StandardOpenOption.CREATE,
                        StandardOpenOption.READ, StandardOpenOption.WRITE))) {
            MappedByteBuffer mbBuffer = fileChannel
                    .map(FileChannel.MapMode.READ_WRITE, 0, buffer.length);
            if (mbBuffer != null) {
                mbBuffer.put(buffer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //Запишем несколько чисел с плавающей точкой в файл
        Path floatFile = Paths.get("float.bin");
        try (DataOutputStream dis = new DataOutputStream (
                new BufferedOutputStream(Files.newOutputStream(floatFile)))) {
            dis.writeFloat(23.56f);
            dis.writeFloat(2.516f);
            dis.writeFloat(56.123f);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
