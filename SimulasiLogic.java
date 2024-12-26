import java.util.Scanner;

public class SimulasiLogic {
    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        int num = 0;
        System.out.println("Enter exercise number:\n");
        Scanner scan = new Scanner(System.in);
        num = scan.nextInt();

        switch (num) {
            case 1:
                no1();
                break;

            case 2:
                no2();
                break;

            case 3:
                // no3();
                break;

            case 4:
                // no4();
                break;

            case 5:
                // no5();
                break;

            case 6:
                no6();
                break;

            case 7:
                no7();
                break;

            case 8:
                no8();
                break;

            case 9:
                // no9();
                break;
            case 10:
                no10();
                break;

            case 11:
                no11();
                break;
        }
        scan.close();

    }

    public static void no1() {
        System.out.print("Input: ");

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        int count = 0;

        for (int i = 0; i < input.length(); i++) {
            char inputChar = input.charAt(i);
            if (Character.isUpperCase(inputChar)) {
                count++;
            }
        }
        System.out.println();
        System.out.print("Output: " + count);
        scan.close();
    }

    public static void no2() {
        System.out.print("Input start: ");
        Scanner scan = new Scanner(System.in);
        int inputStart = scan.nextInt();

        scan.nextLine();

        System.out.print("Input end: ");
        int inputEnd = scan.nextInt();

        String inisialPerusahaan = "XA";
        String tanggal = "07";
        String bulan = "08";
        String tahun = "2022";

        for (int i = inputStart; i < inputEnd + 1; i++) {
            System.out.println(inisialPerusahaan + "-" + tanggal + bulan + tahun + "-" + String.format("%05d", i));
        }

    }

    public static void no6() {

        System.out.print("Input pangram: ");

        Scanner scan = new Scanner(System.in);

        String input = scan.nextLine().toLowerCase();

        boolean alphabet[] = new boolean[26];
        boolean result = false;

        for (char c : input.toCharArray()) {
            // check if the character is a letter using ascii
            if (c >= 'a' && c <= 'z') {
                // mark the corresponding index as true
                alphabet[c - 'a'] = true;
            }
        }

        for (boolean b : alphabet) {
            if (!b) {
                result = false;
                break;
            } else
                result = true;
        }

        if (result == true) {
            System.out.println("This is a Pangram");
        } else {
            System.out.println("This is not a Pangram");
        }
    }

    public static void no7() {
        System.out.print("Input maksimal himpunan: ");

        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        //fibonacci section
        int fibbonaciTotal = 0;


        int a = 1, b = 1, c;

        System.out.print("fibbonaci: 1, 1");
        fibbonaciTotal +=2;

        for (int i = 2; i < input; i++) {
            c = a+b;
            fibbonaciTotal+=c;
            System.out.print(  ", " + c);
            a = b;
            b = c;
        }
        System.out.println();

        System.out.println("Fibonacci total: " + fibbonaciTotal);

        System.out.print("fibonacci average: " + fibbonaciTotal*1.0F/input);

        System.out.println();


        //ganjil genap section
        int totalGenap = 0;
        int totalGanjil = 0;
        StringBuilder ganjilString = new StringBuilder();
        StringBuilder genapString = new StringBuilder();

        for (int j = 1; j <= input*2 ; j++){
            if (j%2 == 0){
                genapString.append(j + ", ");
                totalGenap+= j;
            } else {
                ganjilString.append(j + ", ");
                totalGanjil+=j;
            }
        }
        System.out.println("Ganjil: " + ganjilString);
        System.out.println("Total Ganjil: " + totalGanjil);
        System.out.println("Ganjil average: " + totalGanjil*1.0F/input);
        System.out.println("Genap: " + genapString);
        System.out.println("Total Genap: " + totalGenap);
        System.out.println("Genap average: " + totalGenap*1.0F/input);

    }

    public static void no8() {
        System.out.print("Input number and recursion count (e.g., '153,3'): ");
        Scanner scan = new Scanner(System.in);
    
        // Read input as a string
        String input = scan.nextLine();
    
        // Split the input on the comma and parse numbers
        String[] parts = input.split(",");
        int number = Integer.parseInt(parts[0].trim());
        int recursionCount = Integer.parseInt(parts[1].trim());
    
        // Compute the recursive digit sum
        int result = recursiveDigitSum(number, recursionCount);
    
        System.out.println("Output: " + result);
        scan.close();
    }
    
    private static int recursiveDigitSum(int number, int count) {
        int sum = 0;

        // Repeat the number `count` times in sequence
        for (int i = 0; i < count; i++) {
            int temp = number;
            while (temp > 0) {
                sum += temp % 10; // Add the last digit
                temp /= 10;       // Remove the last digit
            }
        }

        // Reduce the sum to a single digit
        while (sum >= 10) {
            int newSum = 0;
            while (sum > 0) {
                newSum += sum % 10;
                sum /= 10;
            }
            sum = newSum;
        }

        return sum;
    }

    public static void no10(){
        System.out.print("Input: ");
        Scanner scan = new Scanner(System.in);

        int input = scan.nextInt();

        scan.nextLine();

        System.out.println();
        System.out.print("Input elemen array: ");

        String inputArr = scan.nextLine();
        String inputArrString[] = inputArr.split(" ");
        Integer elemenArr[] = new Integer[inputArrString.length];
        int count = 0;

        for (int k = 0; k < inputArrString.length; k++){
            elemenArr[k] = Integer.parseInt(inputArrString[k]);
        }
        for (int i = 0; i < elemenArr.length-1; i++) {
            for (int j = 1; j < elemenArr.length-2; j++){
                if ((elemenArr[i] - elemenArr [j]) == input || -(elemenArr[i] - elemenArr [j]) == input) {
                    count ++;
                    System.out.println(count);
                }
            }
        }
        System.out.println("Output: " + count);
    }
    

    public static void no11(){
        System.out.print("Input: ");
        Scanner scan = new Scanner(System.in);

        int input = scan.nextInt();
        int start = 100;
        int count = 0;
        
        theOne(input, start, count);
    }
    
    public static void theOne(int input, int start, int count){
        while (count < input){
            for (int i = 0; i < input+1; i++){
                for (int k = start; k <= 1000; k++){
                    if (count < input){
                        String startingNum = "" + k;
                        String startingNumStr[] = startingNum.split("");
                        Integer startingNumArr[] = new Integer[startingNumStr.length];
                        int numTotalSquared = 0;
                
                        for (int j = 0; j < startingNumArr.length; j++) {
                            startingNumArr[j] = Integer.parseInt(startingNumStr[j]);
                            numTotalSquared+=(startingNumArr[j] * startingNumArr[j]);
                        }
                        if (numTotalSquared == 1) {
                            count++;
                            System.out.println(k + " is The One Number.");
                        } else {
                            theOne(input, k, count);
                        }
    
    
                    }
                }
                
            }

        }
        System.out.println("done");
    }

}
