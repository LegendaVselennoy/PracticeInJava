package input_output_files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.util.stream.Stream;

import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

// Тривиальный обход папки
public class PathVisitor extends SimpleFileVisitor<Path> {

    private final Path fileNameToSearch=null;
    private boolean fileFound;

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        if (exc!=null){
            throw exc;
        }
        System.out.println("Посещенный каталог: "+dir);
        return FileVisitResult.CONTINUE;
    }

//    Path path = Paths.get("D:/learning");
//    PathVisitor visitor = new PathVisitor();
//    Files.walkfileTree(path, visitor);

    // Поиск файла по имени
    private boolean search(Path file) throws IOException{
        Path fileName=file.getFileName();

        if (fileNameToSearch.equals(fileName)){
            System.out.println("Искомый файл найден: "+
                    fileNameToSearch+" в "+file.toRealPath().toString());
            return true;
        }
        return false;
    }


//    public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
//        fileFound = search((Path) file);
//        if (!fileFound) {
//            return FileVisitResult.CONTINUE;
//        } else {
//            return FileVisitResult.TERMINATE;
//        }
//    }

    // Удаление папки
     private static boolean delete(Path file) throws IOException{
        return Files.deleteIfExists(file);
     }

     // Копирование папки
     private final Path copyFrom=null;
     private final Path copyTo=null;

    private static void copySubTree(Path copyFrom, Path copyTo) throws IOException {
        Files.copy(copyFrom, copyTo, REPLACE_EXISTING, COPY_ATTRIBUTES);
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        Path newDir=copyTo.resolve(copyFrom.relativize((Path) dir));
        try {
            Files.copy((Path) dir, newDir, REPLACE_EXISTING, COPY_ATTRIBUTES);
        } catch (IOException e) {
            System.err.println("He получается создать "
                    + newDir + " [" + e + "]");
            return FileVisitResult.SKIP_SUBTREE;
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        try {
            copySubTree((Path) file, copyTo.resolve(
                    copyFrom.relativize((Path) file)));
        } catch (IOException e) {
            System.err.println("He получается скопировать "
                    + copyFrom + " [" + e + "]");
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        if (exc instanceof FileSystemLoopException) {
            System.err.println("Обнаружен цикл: " + (Path) file);
        } else {
            System.err.println("Произошла ошибка, не получается скопировать:"
                    + (Path) file + " [" + exc + "]");
        }
        return FileVisitResult.CONTINUE;
    }

    // скопируем папку D:/learning/packt В D:/е-courses
    Path copyFrom1 = Paths.get("D:/learning/packt");
    Path copyTo1 = Paths.get("D:/е-courses");

   // PathVisitor pathVisitor=new PathVisitor(copyFrom, copyTo));
    EnumSet opts=EnumSet.of(FileVisitOption.FOLLOW_LINKS);
    // Files.walkFileTree(copyFrom, opts, Integer.MAX_VALUE, pathVisitor);


    // пути из папки D:/learning
    Path directory = Paths.get("D:/learning");
//    Stream<Path> streamOfPath = Files.walk(directory, FileVisitOption.FOLLOW_LINKS);
//    streamOfPath.filter(e -> e.startsWith("D:/learning/books/cdi"))
//            .forEach(System.out::println);

    // размер папки в байтах
//    long folderSize = Files.walk(directory)
//            .filter(f -> f.toFile().isFile())
//            .mapToLong(f -> f.toFile().length())
//            .sum();
}
