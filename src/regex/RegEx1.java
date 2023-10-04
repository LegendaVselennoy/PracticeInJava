package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx1 {
    public static void main(String[] args) {
//        Pattern pat=Pattern.compile("e.+d");
//        Pattern pat=Pattern.compile("e.?+d");
        Pattern pat=Pattern.compile("e.+?d");
        Matcher mat=pat.matcher("extend cup end");
        while (mat.find()){
            System.out.println("Совпадение "+mat.group());
        }
    }
}
