package input_output_files;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadJsonCsv {

    // Чтение/запись файла JSON как объекта
    Path pathArray = Paths.get("melons_array.json");
    Path pathMap = Paths.get("melons_map.json");
    Path pathRaw = Paths.get("melons_raw.json");

    // Прочитать melons array.json как массив Array объектов класса Melon:
//    Melon[] melonsArray = jsonb.fromJson(Files.newBufferedReader(
//                    pathArray, StandardCharsets.UTF_8), Melon[].class);

    // Прочитать melons_array. json как список List объектов Класса Melon
//    List<Melon> melonsList = jsonb.fromJson(Files.newBufferedReader(
//            pathArray, StandardCharsets.UTF_8), ArrayList.class);

    // Прочитать melons map.json как отображение Map объектов класса Melon
//     Map<String, Melon> melonsMap = jsonb.fromJson(Files.newBufferedReader(
//             pathMap, StandardCharsets.UTF_8), HashMap.class);


    // Прочитать melons_raw.json построчно В отображение Map
//    Map<String, String> stringMap = new HashMap<>();
//    try(BufferedReader br = Files.newBufferedReader(
//            pathRaw, StandardCharsets.UTF_8)){
//        String line;
//        while ((line = br.readLine()) != null) {
//            stringMap = jsonb.fromJson(line, HashMap.class);
//            System.out.println("Текущее отображение: " + stringMap);
//        }
//    }

    // Прочитать melons_raw.json построчно в объект Melon
//    try (BufferedReader br = Files.newBufferedReader(
//            pathRaw, StandardCharsets.UTF_8)) {
//        String line;
//        while ((line = br.readLine()) != null) {
//            Melon melon = jsonb.fromJson(line, Melon.class);
//            System.out.println("Текущая дыня: " + melon);
//        }
//    }

    // Записать объект в JSON-файл
//    Path path = Paths.get("melons_output.json");
//    jsonb.toJson(melonsMap, Files.newBufferedWriter(path,
//    StandardCharsets.UTF_8, StandardOpenOption.CREATE,
//    StandardOpenOption.WRITE));


    /**
     * Для десериализации мы используем метод ObjectMapper.readValue(), в то время как
     * ДЛЯ сериализации — метод ObjectMapper.writeValue () библиотеки Jackson.
     */
}
