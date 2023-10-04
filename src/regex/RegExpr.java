package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpr {
    public static void main(String[] args) {
        Pattern pat;
        Matcher mat;
        boolean found;

        pat=Pattern.compile("Java");
        mat=pat.matcher("Java");
        found=mat.matches();

        System.out.println("Проверка на совпадение Java с Java");
        if (found){
            System.out.println("Yes");
        }else {
            System.out.println("No");
        }
        System.out.println();
        System.out.println("Проверка на совпадение Java с Java SE");
        mat=pat.matcher("Java SE");
        found=mat.matches();
        if (found){
            System.out.println("Yes");
        }else {
            System.out.println("No");
        }
    }
}
