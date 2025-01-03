import java.util.ArrayList;
import java.util.List;

public class AlgobashAnswers {
    public static void main(String[] args) {

    }

    public static String decodeBinaryCode(int n, String binaryCode) {
        if (n % 2 != 0) {
            throw new IllegalArgumentException("n must be divisible by 2.");
        }
        if (binaryCode.length() != n) {
            throw new IllegalArgumentException("Binary code length must match n.");
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i += 2) {
            String pair = binaryCode.substring(i, i + 2); // Extract substring
            if (pair.equals("00")) {
                ans.append("A");
            } else if (pair.equals("01")) {
                ans.append("B");
            } else if (pair.equals("10")) {
                ans.append("C");
            } else if (pair.equals("11")) {
                ans.append("D");
            }
        }
        return ans.toString();
    }

    public static List<String> removeStringsContainingW(List<String> inputList) {
        List<String> result = new ArrayList<>();
        for (String str : inputList) {
            if (!str.contains("w")) { // Check if the string does not contain 'w'
                result.add(str);
            }
        }
        return result;
    }

    public static int[] generatePattern(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Matrix size must be greater than 0.");
        }

        int size = n * n; // Total number of elements in the resulting array
        int[] result = new int[size];
        int index = 0;

        for (int i = 0; i < n; i++) { // Outer loop for rows
            for (int j = 0; j < n; j++) { // Inner loop for columns
                result[index++] = Math.abs(j - i); // Absolute difference between column and row
            }
        }

        return result;
    }

}
