import java.util.ArrayList;
import java.util.Arrays;

public class Catatan {
    public static void main(String[] args) {
        Integer[] array1 = {1,2,3};
        Integer[] array2 = {1,2,3,4};
        ArrayList<Integer> test1 = new ArrayList<>(Arrays.asList(array1));
        ArrayList<Integer> test2 = new ArrayList<>(Arrays.asList(array2));
        ArrayList<Integer> hasil = new ArrayList<>();
        String numStr1 = "";
        String numStr2 = "";
        for (int i = test1.size() -1 ; i >= 0; i--) {
            numStr1 += test1.get(i);
        }
        for (int i = test2.size() -1 ; i >= 0; i--) {
            numStr2 += test2.get(i);
        }

        // String result = Integer.toString(Integer.parseInt(numStr1) + Integer.parseInt(numStr2));
        // for (int i = 0; i < result.length(); i++) {
            //     hasil.add(Character.getNumericValue(result.charAt(i)));
            //     System.out.println(hasil);
            // }
            
        int result = (Integer.parseInt(numStr1) + Integer.parseInt(numStr2));
        int i = 0;
        while (result > 0) {
             
        }

        System.out.println(result);
        System.out.println(hasil);
    }
}
