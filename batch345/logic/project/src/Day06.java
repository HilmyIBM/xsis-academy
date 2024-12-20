import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day06 {
    static void lembah(String input) {
        boolean lembahIn = false;
        int start = 0;
        int count = 0;

        input = input.toLowerCase(Locale.ROOT);

        for (int i = 0; i < input.length(); i++) {
            if (start < 0) lembahIn = true;
            if (start > 0) lembahIn = false;

            if (input.charAt(i) == 'd') start--;
            else start++;

            if (lembahIn & start == 0) count++;
        }

        System.out.println(count);
    }


    static void rotateString(String arr, int rot) {
        char[] res = new char[arr.length()];

        for (int i = 0, n; i < arr.length(); i++) {
            if (String.valueOf(arr.charAt(i)).matches("\\W")) {
                res[i] = arr.charAt(i);
                continue;
            }

            n = arr.charAt(i) + rot;
            if ((n >= 97 & n <= 122) | (n >= 65 & n <= 90))
                res[i] = (char) n;
            else  res[i] = (char) (n - 26);
        }

        System.out.println(Arrays.toString(res));
    }

    static void nilaiTertinggiX(String elemTinggi, String txt) {
        String[] el = elemTinggi.split(" ");
        int lowerBound = 97;;

        ArrayList<Integer> val = new ArrayList<>();

        for (char t : txt.toCharArray()) {
            val.add(Integer.valueOf(el[t-lowerBound]));
        }

        int max = val.stream().max(Integer::compareTo).orElse(0);
        int result = txt.length() * max;

        System.out.println(result);
    }

    static void vokalConsonan(String in) {
        ArrayList<Character> arrVokal = new ArrayList<>();
        ArrayList<Character> arrCons = new ArrayList<>();

        in = in.replaceAll("[^a-zA-Z]", "").toLowerCase(Locale.ROOT);

        for (int i = 0; i < in.length(); i++) {
            if (in.charAt(i) == 97 || in.charAt(i) == 105 || in.charAt(i) == 117 || in.charAt(i) == 101 || in.charAt(i) == 111)
                arrVokal.add(in.charAt(i));
            else
                arrCons.add(in.charAt(i));
        }

        Collections.sort(arrCons);
        Collections.sort(arrVokal);

        System.out.println("Vokal: " + arrVokal);
        System.out.println("Consonan: " + arrCons);
    }

    static void password(String pw) {
        ArrayList<ArrayList<String>> validations = new ArrayList<>();

        validations.add(new ArrayList<>() {{ add("[^a-zA-Z0-9]+"); add("Kurang symbol"); }});
        validations.add(new ArrayList<>() {{ add("[a-z]+"); add("Kurang huruf kecil"); }});
        validations.add(new ArrayList<>() {{ add("[A-Z]+"); add("Kurang huruf besar"); }});
        validations.add(new ArrayList<>() {{ add("[0-9]+"); add("Kurang angka"); }});

        System.out.println("Password: " + pw);

        boolean weakPassword = false;

        Pattern pattern;
        Matcher matcher;

        for (ArrayList<String> validation : validations) {
            pattern = Pattern.compile(validation.get(0));
            matcher = pattern.matcher(pw);

            if (!matcher.find()) {
                System.out.println("Password Weak & " + validation.get(1));
                weakPassword = true;
            }
        }

        if (pw.length() < 6) {
            System.out.println("Password Weak & Kurang dari 6 digit");
            weakPassword = true;
        }

        if (!weakPassword) System.out.println("Password Strong");
    }



    public static void main(String[] args) {
//        nilaiTertinggiX("1 3 1 4 6 2 1 1 3 5 2 3 1 1 1 1 5 2 3 1 3 5 4 3 2 5", "zia");
//
//        rotateString("middle-Zutz", 2);

        vokalConsonan("Simple_!Ca9090se");

//        password("Abc3!a");
//
//        lembah("UDUDDUUUDUDUDUUDUUDDDDDUDUDDDDUUDDUDDUUUUDUUDUDDDDUDUDUUUDDDUUUDUDDUUDDDUUDDUDDDUDUUDUUDUUDUDDDUUUUU");

    }
}
