package input_output_files;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.stream.Stream;

public class DecisionFiles {

    // Создание пути относительно корня хранилища файлов
    // С:\learning\packt\JavaModernChallenge.pdf:

    Path path = Paths.get("/learning/packt/JavaModernChallenge.pdf");
    Path path1 = Paths.get("/learning", "/packt/JavaModernChallenge.pdf");
    Path path2 = Path.of("/learning/packt/JavaModernChallenge.pdf");
    Path path3 = Path.of("/learning", "packt/JavaModernChallenge.pdf");
    Path path4 = FileSystems.getDefault().getPath("/learning/packt", "JavaModernChallenge.pdf");
    Path path5 = FileSystems.getDefault().getPath("/learning/packt/JavaModernChallenge.pdf");
    Path path6 = Paths.get(URI.create("file:///learning/packt/JavaModernChallenge.pdf"));
    Path path7 = Path.of(URI.create("file:///learning/packt/JavaModernChallenge.pdf"));

    // Создание пути относительно текущей папки
    // C:\books\learning\packt\JavaModernChallenge.pdf:

    Path path8 = Paths.get("learning/packt/JavaModernChallenge.pdf");
    Path path9 = Paths.get("learning", "packt/JavaModernChallenge.pdf");
    Path Path10 = Path.of("learning/packt/JavaModernChallenge.pdf");
    Path path11 = Path.of("learning", "packt/JavaModernChallenge.pdf");
    Path path12 = FileSystems.getDefault().getPath("learning/packt", "JavaModernChallenge.pdf");
    Path path13 = FileSystems.getDefault().getPath("learning/packt/JavaModernChallenge.pdf");

    // Создание абсолютного пути
    // C:\learning\packt\JavaModernChallenge.pdf
    Path path14 = Paths.get("С:/learning/packt", "JavaModernChallenge.pdf");
    Path path15 = Paths.get("C:", "learning/packt", "JavaModernChallenge.pdf");
    Path path16 = Paths.get("C:", "learning", "packt", "JavaModernChallenge.pdf");
    Path path17 = Paths.get("C:/learning/packt/JavaModernChallenge.pdf");
    Path path18 = Paths.get(System.getProperty("user.home"), "downloads", "chess.exe");
    Path path19 = Path.of("C:", "learning/packt", "JavaModernChallenge.pdf");
    Path path20 = Path.of(System.getProperty("user.home"), "downloads", "chess.exe");
    Path path21 = Paths.get(URI.create("file:///C:/learning/packt/JavaModernChallenge.pdf"));
    Path path22 = Path.of(URI.create("file:///C:/learning/packt/JavaModernChallenge.pdf"));

    // Создание пути с помощью сокращений
    Path path23 = Paths.get("C:/learning/packt/chapters/../JavaModernChallenge.pdf").normalize();
    Path path24 = Paths.get("C:/learning/./packt/chapters/../JavaModernChallenge.pdf").normalize();
    Path path25 = FileSystems.getDefault().getPath("/learning/./packt", "JavaModernChallenge.pdf").normalize();
    Path path26 = Path.of("C:/learning/packt/chapters/../JavaModernChallenge.pdf").normalize();
    Path path27 = Path.of("C:/learning/./packt/chapters/../JavaModernChallenge.pdf").normalize();

    // В создании путей, которые на 100% совместимы с текущей операционной системой
    private static final String FILE_SEPARATOR = File.separator;
    private static final String FILE_SEPARATOR1 = FileSystems.getDefault().getSeparator();

    // относительно текущей рабочей папки
    Path path28 = Paths.get("learning", "packt", "JavaModernChallenge.pdf");
    Path path29 = Path.of("learning", "packt", "JavaModernChallenge.pdf");
    Path path30 = Paths.get(String.join(FILE_SEPARATOR, "learning", "packt", "JavaModernChallenge.pdf"));
    Path path31 = Path.of(String.join(FILE_SEPARATOR, "learning", "packt", "JavaModernChallenge.pdf"));

    // относительно корня хранилища файлов
    Path path32 = Paths.get(FILE_SEPARATOR + "learning", "packt", "JavaModernChallenge.pdf");
    Path path33 = Path.of(FILE_SEPARATOR + "learning", "packt", "JavaModernChallenge.pdf");

    // тоже самое для абсолютных путей
    Path path34 = Paths.get(File.listRoots()[0] + "learning", "packt", "JavaModernChallenge.pdf");
    Path path35 = Path.of(File.listRoots()[0] + "learning", "packt", "JavaModernChallenge.pdf");

    // Конвертирование путей к файлам
    String pathToString = path.toString();
    URI pathToURI = path.toUri();

    URI uri = URI.create("https://www.learning.com/packt/JavaModernChallenge.pdf");
    Path URIToPath = Paths.get(uri.getPath()).getFileName();

//    URL uri1 = new URL("https://www.learning.com/packt/JavaModernChallenge.pdf");
//    Path URLToPath = Paths.get(uri.getPath()).getFileName();

//    Path pathToAbsolutePath = path.toAbsolutePath();
//    Path realPath = path.toRealPath(LinkOption.NOFOLLOW_LINKS);

    File pathToFile = path.toFile();
    Path fileToPath = pathToFile.toPath();

    // Присоединение путей к именам файлов
    Path base = Paths.get("D:/learning/packt");
    Path path36 = base.resolve("JBossTools3.pdf");
    Path path37 = base.resolve("MasteringJSF22.pdf");

//    Path basePath = Paths.get("D:/learning/packt");
//    String[] books = {"Bookl.pdf", "Book2.pdf", "Book3.pdf"};
//    for(String book : books){
//        Path nextBook = basePath.resolve(book);
//        System.out.println(nextBook);
//    }

   // Конструирование пути между двумя местоположениями

    Path path38 = Paths.get("/learning/packt/2003/JBossTools3.pdf");
    Path path39 = Paths.get("/learning/packt/2019");
    Path path3ToPath4 = path3.relativize(path39);
    Path path4ToPath3 = path4.relativize(path38);

    // Сравнение путей к файлам
    boolean path1EqualsPath2 = path.equals(path2);

    // Пути, представляющие одинаковый файл/папку
//    boolean pathIsSameFilePath= Files.isSameFile(path1,path2);

    // Найти все файлы, заканчивающиеся расширением  .properties, и проследовать по символическим ссылкам
    Path startPath = Paths.get("D:/learning");
//    Stream<Path> resultAsStream = Files.find(
//            startPath,
//            Integer.MAX_VALUE,
//            (path, attr) -> path.toString().endsWith(".properties"),
//            FileVisitOption.FOLLOW_LINKS
//    ) ;
    // Найти все обычные файлы, имена которых начинаются с application
//       Stream<Path> resultAsStream = Files.find(
//        startPath,
//        Integer.MAX_VALUE,
//        (path, attr) -> attr.isRegularFile() &&
//          path.getFileName().toString().startsWith("application"));


     // Найти все каталоги, созданные после 16 марта 2019 года
//     Stream<Path> resultAsStream = Files.find(
//             startPath,
//             Integer.MAX_VALUE,
//             (path, attr) -> attr.isDirectory() &&
//              attr.creationTime().toInstant().isAfter(LocalDate.of(2019, 3, 16).atStartOfDay()
//            .toInstant(ZoneOffset.UTC)));
}
