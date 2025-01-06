import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class cheatsheet {
    public static void main(String[] args) {
        
    }

    public static void listSheet(){
        List <String> cars = new ArrayList<String>();
        cars.add("Volvo");
        cars.add("BMW");
        cars.add("Mazda");
        String test = cars.get(0);//Volvo

        cars.set(1, "Pagani");
        cars.remove(2);
        cars.clear();
        cars.size();
        cars.add("Koenigsegg");
        Collections.sort(cars);

        String str1 = "abc";
        String str2 = "abcdef";
        str1.equals(str2);
        str1.compareTo(str2);
        str1.toUpperCase();
        str1.substring(0,2);
        str1.trim();
        str1.replaceAll("a", "");

        String parts[] = str1.split(",");

        String str = String.join(", ", "cars", "boats");

        char[] charArr = str.toCharArray();
        Arrays.sort(charArr);

        String[] arr = "1 2 3 4 5 6".split(" ");
        Integer[] intArr = new Integer[arr.length];

        for (int i = 0; i < intArr.length; i++){
            intArr[i] = Integer.parseInt(arr[i]);
        }

        double pi = 3.1415;
        String.format("%1s %2$.4f", "nilai pi adalah ", pi);

        
    }
}
