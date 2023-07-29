package strings.numbers.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.counting;

public class Decision {

    private static final int EXTENDED_ASCII_CODES = 256;
    private static final String WHITESPACE = " ";
    private static final Pattern PATTERN = Pattern.compile(" +");

    // Подсчет повторяющихся символов
    public Map<Character, Integer> countDuplicateCharacters(String str) {
        Map<Character, Integer> result = new HashMap<>();
        // либо использовать for(char ch: str.toCharArray()) { ... }
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            result.compute(ch, (k, v) -> (v == null) ? 1 : ++v);
        }
        return result;
    }

    // Отыскание первого неповторяющегося символа
    public char firstNonRepeatedCharacter(String str) {
        int[] flags = new int[EXTENDED_ASCII_CODES];

        for (int i = 0; i < flags.length; i++) {
            flags[i] = -1;
        }

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (flags[ch] == -1) {
                flags[ch] = i;
            } else {
                flags[ch] = -2;
            }
        }
        int position = Integer.MAX_VALUE;

        for (int i = 0; i < EXTENDED_ASCII_CODES; i++) {
            if (flags[i] >= 0) {
                position = Math.min(position, flags[i]);
            }
        }
        return position == Integer.MAX_VALUE ?
                Character.MIN_VALUE : str.charAt(position);
    }

    public char firstNonRepeatedCharacter2(String str) {
        Map<Character, Integer> chars = new LinkedHashMap<>();
        // либо использовать for(char ch: str.toCharArray()) { ... }
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            chars.compute(ch, (k, v) -> (v == null) ? 1 : ++v);
        }
        for (Map.Entry<Character, Integer> entry : chars.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return Character.MIN_VALUE;
    }

    public static String firstNonRepeatedCharacter3(String str) {
        Map<Integer, Long> chs = str.codePoints()
                .mapToObj(cp -> cp)
                .collect(Collectors.groupingBy(Function.identity(),
                        LinkedHashMap::new, counting()));
        int cp = chs.entrySet().stream()
                .filter(e -> e.getValue() == 1L)
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(Integer.valueOf(Character.MIN_VALUE));
        return String.valueOf(Character.toChars(cp));
    }

    // Инвертирование букв и слова
    public String reverseWords(String str) {
        String[] words = str.split(WHITESPACE);
        StringBuilder reverseString = new StringBuilder();

        for (String word : words) {
            StringBuilder reverseWord = new StringBuilder();
            for (int i = word.length() - 1; i >= 0; i++) {
                reverseWord.append(word.charAt(i));
            }
            reverseString.append(reverseWord).append(WHITESPACE);
        }
        return reverseString.toString();
    }

    public static String reverseWords2(String str) {
        return PATTERN.splitAsStream(str)
                .map(w -> new StringBuilder(w).reverse())
                .collect(Collectors.joining(" "));
    }

    public String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    // Проверка, содержит ли строковое значение только цифры
    public boolean containsOnlyDigits(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean containsOnlyDigits1(String str) {
        return !str.chars()
                .anyMatch(n -> !Character.isDigit(n));
    }

    public boolean containsOnlyDigits2(String str) {
        return str.matches("[0-9]+");
    }

    // Подсчет гласных и согласных
    private static final Set<Character> allVowels
            = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

    public int countVowelsAndConsonants(String str) {
        str = str.toLowerCase();
        int vowels = 0;  // согласные
        int consonants = 0; // гласные
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (allVowels.contains(ch)) {
                vowels++;
            } else if ((ch >= 'a' && ch <= 'z')) {
                consonants++;
            }
        }
        return vowels;
    }

    // Подсчет появлений некоторого символа
    public int countOccurrencesOfACertainCharacter(String str, char ch) {
        return str.length() - str.replace(String.valueOf(ch), "").length();
    }

    public int countOccurrencesOfACertainCharacter1(String str, char ch) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }

    public long countOccurrencesOfACertainCharacter3(String str, char ch) {
        return str.chars()
                .filter(c -> c == ch)
                .count();
    }

    // Конвертирование строки в значение типа int, long, float или double, а также оболочек
    private static final String TO_INT = "453";
    int toInt = Integer.parseInt(TO_INT);
    Integer toInteger = Integer.valueOf(TO_INT); // обработать исключения

    // Удаление пробелов из строки
    public String removeWhitespaces(String str) {
        return str.replaceAll("\\s", "");
    }

    // Соединение нескольких строк с помощью разделителя
    public String joinByDelimiter(char delimiter, String... args) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        for (i = 0; i < args.length - 1; i++) {
            result.append(args[i]).append(delimiter);
        }
        result.append(args[i]);
        return result.toString();
    }

    public static String joinByDelimiter1(char delimiter, String... args) {
        return Arrays.stream(args, 0, args.length)
                .collect(Collectors.joining(String.valueOf(delimiter)));
    }

    // Генерирование всех перестановок
    private static void permuteAndPrint(String prefix, String str) {
        int n = str.length();

        if (n == 0) {
            System.out.print(prefix + " ");
        } else {
            for (int i = 0; i < n; i++) {
                permuteAndPrint(prefix + str.charAt(i),
                        str.substring(i + 1, n) + str.substring(0, i));
            }
        }
    }

    public void permuteAndPrint(String str) {
        permuteAndPrint("", str);
        System.out.println(str);
    }

    public Set<String> permuteAndStore1(String str) {
        return permuteAndStore1("", str);
    }

    private static Set<String> permuteAndStore1(String prefix, String str) {
        Set<String> permutations = new HashSet<>();
        int n = str.length();

        if (n == 0) {
            permutations.add(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                permutations.addAll(permuteAndStore1(prefix + str.charAt(i),
                        str.substring(i + 1, n) + str.substring(0, i)));
            }
        }
        return permutations;
    }

    private static void permuteAndPrintStream(String prefix, String str) {
        int n = str.length();
        if (n == 0) {
            System.out.print(prefix + " ");
        } else {
            IntStream.range(0, n)
                    .parallel()
                    .forEach(i -> permuteAndPrintStream(prefix + str.charAt(i),
                            str.substring(i + 1, n) + str.substring(0, i)));
        }
    }

    // Проверка, что строка является палиндромом
    public boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;

        while (right > left) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public boolean isPalindrome1(String str) {
        int n = str.length();

        for (int i = 0; i < n / 2; i++) {
            if (str.charAt(i) != str.charAt(n - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome2(String str) {
        return str.equals(new StringBuilder(str).reverse().toString());
    }

    // Удаление повторяющихся символов
    public String removeDuplicates(String str) {
        char[] chArray = str.toCharArray();
        StringBuilder sb = new StringBuilder();

        for (char ch : chArray) {
            if (sb.indexOf(String.valueOf(ch)) == -1) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static String removeDuplicates1(String str) {
        char[] chArray = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        Set<Character> chHashSet = new HashSet<>();
        for (char c : chArray) {
            if (chHashSet.add(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String removeDuplicates2(String str) {
        return Arrays.asList(str.split("")).stream()
                .distinct()
                .collect(Collectors.joining());
    }

    // Удаление заданного символа
    public static String removeCharacter(String str, char ch) {
        return str.replaceAll(Pattern.quote(String.valueOf(ch)), "");
    }


    public String removeCharacter1(String str, char ch) {
        StringBuilder sb = new StringBuilder();
        char[] chArray = str.toCharArray();

        for (char c : chArray) {
            if (c != ch) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // Отыскание символа с наибольшим числом появлений
    public Map<Character, Integer> maxOccurrenceCharacter(String str) {
        Map<Character, Integer> counter = new HashMap<>();
        char[] chStr = str.toCharArray();

        for (int i = 0; i < chStr.length; i++) {
            char currentCh = chStr[i];
            if (!Character.isWhitespace(currentCh)) { // игнорирование пробелов
                Integer noCh = counter.get(currentCh);
                if (noCh == null) {
                    counter.put(currentCh, 1);
                } else {
                    counter.put(currentCh, ++noCh);
                }
            }
        }
        int maxOccurrences = Collections.max(counter.values());
        char maxCharacter = Character.MIN_VALUE;

        for (Map.Entry<Character, Integer> entry : counter.entrySet()) {
            if (entry.getValue() == maxOccurrences) {
                maxCharacter = entry.getKey();
            }
        }
        return Map.of(maxCharacter, maxOccurrences);
    }

    // Проверка наличия подстроки в строке

    String text="Hellow World";
    String subtext="orl";
    boolean contains=text.contains(subtext);

    public boolean contains1(String text,String subtext){
        return text.indexOf(subtext)!=-1; // или lastIndexOf()
    }

    public static boolean contains(String text, String subtext) {
        return text.matches("(?i).*" + Pattern.quote(subtext) + ".*");
    }

    // Подсчет числа появлений подстроки в строке

    // (11 в 111 появляется 1 раз)
    public int countStringInString(String str,String toFind){
        int position=0;
        int count=0;
        int n=toFind.length();

        while ((position=str.indexOf(toFind,position))!=-1){
            position=position+n;
            count++;
        }
        return count;
    }

    public static int countStringInString1(String string, String toFind) {
        int result = string.split(Pattern.quote(toFind), -1).length - 1;
        return result < 0 ? 0 : result;
    }

    // (11 в 111 появляется 2 раз)
    public int countStringInString2(String s,String toFind){
        Pattern pattern=Pattern.compile(Pattern.quote(toFind));
        Matcher matcher=pattern.matcher(s);

        int position=0;
        int count=0;

        while (matcher.find(position)){
            position=matcher.start()+1;
            count++;
        }
        return count;
    }

    // Проверка, являются ли две строки анаграммами
     public boolean isAnagram(String str1,String str2){
        int[] chCounts=new int[EXTENDED_ASCII_CODES];
        char[] chStr1=str1.replaceAll("\\s","").toLowerCase().toCharArray();
        char[] chStr2=str2.replaceAll("\\s","").toLowerCase().toCharArray();

        if (chStr1.length!=chStr2.length){
            return false;
        }

         for (int i = 0; i < chStr1.length; i++) {
             chCounts[chStr1[i]]++;
             chCounts[chStr2[i]]--;
         }

         for (int i = 0; i < chCounts.length; i++) {
             if (chCounts[i]!=0){
                 return false;
             }
         }
         return true;
     }

     // Объявление многострочных строковых литералов
     private static final String LS = System.lineSeparator();

    String text1 = String.join(LS,
            "Моя школа, Иллинойская Академия,",
            "математики и естественных наук, показала мне, что,",
            "в этом мире все может быть и что никогда",
            "не слишком рано начинать мыслить масштабно.");

    // Конкатенирование одной и той же строки п раз
    String result="hello".repeat(5);

    String result1 = String.join("", Collections.nCopies(5, "TEXT"));


    // Удаление начальных и замыкающих пробелов
    String text2="\n \n\n hello \t \n \r";
    String trimmed=text.trim();
    String stripped=text2.strip();

    // Поиск наибольшего общего префикса
    public String longestCommonPrefix(String[] strs){
        if (strs.length==1){
            return strs[0];
        }

        int firstLen=strs[0].length();

        for (int prefixLen=0;prefixLen<firstLen;prefixLen++) {
            char ch=strs[0].charAt(prefixLen);
            for (int i = 1; i < strs.length; i++) {
                if (prefixLen>=strs[i].length()
                || strs[i].charAt(prefixLen)!=ch){
                    return strs[i].substring(0,prefixLen);
                }
            }
        }
        return strs[0];
    }

    // Применение отступа
//    String days="Saturday\n"+"Sunday\n";
//    days.indent(10);  отступ в 10 пробелов слева ,с JDK 12


    //  Поиск в больших файлах число появлений
    public int countOccurrences(Path path, String text, Charset ch)
            throws IOException {
        int count = 0;
        try (BufferedReader br = Files.newBufferedReader(path, ch)) {
            String line;
            while ((line = br.readLine()) != null) {
                count += countStringInString(line, text);
            }
        }
        return count;
    }
}
