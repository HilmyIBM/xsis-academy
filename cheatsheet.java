import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class cheatsheet {
    public static void main(String[] args) {
        // Call HackerRank1 test case
        HackerRank1 solution = new HackerRank1(); // Corrected class name
        System.out.println(Arrays.toString(solution.minOperations("110"))); // [1, 1, 3]
        System.out.println(Arrays.toString(solution.minOperations("001011"))); // [11, 8, 5, 4, 3, 4]
    }

    public static void listSheet() {
        List<String> cars = new ArrayList<>();
        cars.add("Volvo");
        cars.add("BMW");
        cars.add("Mazda");
        String test = cars.get(0); // Volvo

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
        str1.substring(0, 2);
        str1.trim();
        str1.replaceAll("a", "");

        String[] parts = str1.split(",");

        String str = String.join(", ", "cars", "boats");

        char[] charArr = str.toCharArray();
        Arrays.sort(charArr);

        String[] arr = "1 2 3 4 5 6".split(" ");
        Integer[] intArr = new Integer[arr.length];

        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = Integer.parseInt(arr[i]);
        }

        double pi = 3.1415;
        String.format("%1s %2$.4f", "nilai pi adalah ", pi);
    }

    // Make hackerRank1 a static inner class
    public static class HackerRank1 {
        public int[] minOperations(String boxes) {
            int n = boxes.length();
            int[] answer = new int[n];

            // First pass: Calculate operations from left to right
            int moves = 0;  // Accumulated moves
            int balls = 0;  // Number of balls encountered so far
            for (int i = 0; i < n; i++) {
                answer[i] += moves;  // Add moves so far to answer[i]
                if (boxes.charAt(i) == '1') {
                    balls++;  // Increment ball count for current box
                }
                moves += balls;  // Increment moves by number of balls encountered
            }

            // Second pass: Calculate operations from right to left
            moves = 0;
            balls = 0;
            for (int i = n - 1; i >= 0; i--) {
                answer[i] += moves;  // Add moves so far to answer[i]
                if (boxes.charAt(i) == '1') {
                    balls++;  // Increment ball count for current box
                }
                moves += balls;  // Increment moves by number of balls encountered
            }

            return answer;
        }
    }

    public static class HackerRank2 {
        public String convert(String s, int numRows) {
            if (numRows == 1 || s.length() <= numRows) {
                return s; // No zigzag needed
            }

            // Initialize a list to hold each row's string
            List<String> arrList = new ArrayList<>();
            for (int i = 0; i < numRows; i++) {
                arrList.add(""); // Initialize each row with an empty string
            }

            // Variables to track the current row and direction
            int currentRow = 0;
            boolean goingDown = false;

            // Traverse the string to populate the rows
            for (char c : s.toCharArray()) {
                // Add the current character to the corresponding row
                arrList.set(currentRow, arrList.get(currentRow) + c);

                // Change direction at the top or bottom row
                if (currentRow == 0 || currentRow == numRows - 1) {
                    goingDown = !goingDown;
                }

                // Move up or down the rows
                currentRow += goingDown ? 1 : -1;
            }

            // Combine all rows to form the final result
            StringBuilder ans = new StringBuilder();
            for (String row : arrList) {
                ans.append(row);
            }

            return ans.toString();
        }
    }

    public static class HackerRank3 {
        public int maxArea(int[] height) {
            int maxArea = 0;
            int left = 0;
            int right = height.length - 1;

            while (left < right) {
                maxArea = Math.max(maxArea, (right - left) * Math.min(height[left], height[right]));

                if (height[left] < height[right]) {
                    left++;
                } else {
                    right--;
                }
            }

            return maxArea;
        }
    }
}
