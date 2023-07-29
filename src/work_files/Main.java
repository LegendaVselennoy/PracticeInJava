package work_files;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Path filePathR = Paths.get( "test.txt");
        Path filePathW=Paths.get("test1.txt");
        Test test=new Test();
//        test.writeFile(filePathR);

//        test.writeFile(filePathW);

        test.deleteFile1(filePathW);

    }
}
