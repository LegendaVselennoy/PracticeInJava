package strings.numbers.math;


public class Test {
        public static void main(String[] args) {
                String[] line3 = {"Hellow", "World"};
                String result = String.join("+", "Hellow", "World");

                Decision decision = new Decision();
                decision.isPalindrome1("madam");
                decision.isAnagram("abcafd","abcfad");
                System.out.println(decision.isAnagram("abcafd","abcfad"));
                decision.longestCommonPrefix(line3);
        }
}
