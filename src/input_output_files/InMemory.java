package input_output_files;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class InMemory {
    // Чтение текстовых файлов в память
    public static void main(String[] args) {

    }

    public static void inMemory(Path path){
        // либо использовать Files.newByteChannel ()
        try(FileChannel fileChannel=(FileChannel.open(path,
                EnumSet.of(StandardOpenOption.READ))) ){
            MappedByteBuffer mbBuffer=fileChannel.map(
                    FileChannel.MapMode.READ_ONLY,0,fileChannel.size());
            if (mbBuffer!=null){
                String bufferContent=
                        StandardCharsets.UTF_8.decode(mbBuffer).toString();
                System.out.println(bufferContent);
                mbBuffer.clear();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // Для огромных файлов рекомендуется обходить буфер с фиксированным размером
    private static final int MAP_SIZE=5242880; // 5 Мбайт в байтах

    public static void inMemory1(Path path){
        try(FileChannel fileChannel=(FileChannel.open(path,
                EnumSet.of(StandardOpenOption.READ))) ){
            int position=0;
            long length=fileChannel.size();

            while (position<length){
                long remaining=length-position;
                int bytesToMap=(int) Math.min(MAP_SIZE,remaining);

                MappedByteBuffer mbBuffer=fileChannel.map(
                        FileChannel.MapMode.READ_ONLY,position,bytesToMap);
                // сделать что-то с текущим буфером
                position+=bytesToMap;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
