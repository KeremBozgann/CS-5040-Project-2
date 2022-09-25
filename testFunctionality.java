import java.util.*;

public class testFunctionality{
    public static void main(String[] args) {
        
        String str = "(+ (- 6 7) (* 234 455abA 256) (/ (/ 3) (*) (-2 3 1)))";
        System.out.println(str);
        ArrayList<String> substrings = new ArrayList<String>(
//            Arrays.asList(str.split("[-\\s+\\+\\*\\/()[\\p{L}&&[^\\p{Lu}]]\\p{Lu}]+"))
            Arrays.asList(
            str.split("((?=:|-|\\*|/|\\+|\\p{L}|\\s+|\\(|\\))|(?<=:|-|\\*|/|\\+|\\p{L}|\\s+|\\(|\\)))"))
            );
        substrings.removeAll(Arrays.asList("", " "));
        


        for (int i = 0; i<substrings.size(); i++)
            System.out.println(substrings.get(i));
    }
}