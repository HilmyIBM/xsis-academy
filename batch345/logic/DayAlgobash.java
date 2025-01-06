public class DayAlgobash {
    public static void main(String[] args) {
        commonDifisor("5/25");
    }

    public static void commonDifisor (String data){
        String[] numberString = data.split("/");
        int pembilang = Integer.parseInt(numberString[0]);
        int penyebut = Integer.parseInt(numberString[1]);

        int n = (pembilang > penyebut ? penyebut : pembilang);

        for (int i = 1; i<n; i++){
            if (pembilang % i == 0 && penyebut % i == 0){
                pembilang /= i;
                penyebut /= i;
            }
        }
        StringBuilder result = new StringBuilder();
        if (pembilang % penyebut == 0){
            result.append(pembilang/penyebut);
        } else {    
            result.append(pembilang);
            result.append('/');
            result.append(penyebut);
        }

        System.out.println(result.toString());
    }

    public class ReverseStringCompare {
        public static void main(String[] args) {
            String original = "madam";
            char[] originalArray = original.toCharArray();
            char[] reversedArray = new char[originalArray.length];
    
            // Reverse the string into another array
            for (int i = 0; i < originalArray.length; i++) {
                reversedArray[i] = originalArray[originalArray.length - 1 - i];
            }
    
            // Print original and reversed arrays
            System.out.println("Original Array: ");
            printArray(originalArray);
    
            System.out.println("Reversed Array: ");
            printArray(reversedArray);
    
            // Compare the original and reversed arrays
            boolean areEqual = true;
            for (int i = 0; i < originalArray.length; i++) {
                if (originalArray[i] != reversedArray[i]) {
                    areEqual = false;
                    break;
                }
            }
    
            if (areEqual) {
                System.out.println("The original and reversed arrays are identical.");
            } else {
                System.out.println("The original and reversed arrays are different.");
            }
        }
    
        // Utility method to print a character array
        public static void printArray(char[] array) {
            for (char c : array) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
    
}
