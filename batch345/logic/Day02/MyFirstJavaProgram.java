package Day02;
public class MyFirstJavaProgram {

    public static void main(String[] args) {
        System.out.println("Hello World");

        int i = 10;

        // switch case
        switch (i) {
            case 10:
                System.out.println("Ten");
                break;
            case 20:
                System.out.println("Twenty");
                break;
            default:
                System.out.println("No Match");
                break;
        }

        // Ternary
        String title = "";
        boolean isMale = false, isMarried = true;

        title = isMale ? "Mr. " : (isMarried ? "Mrs." : "Ms. ");
        System.out.println(title);

        
        ForLoop();
        Array();
    }

    // For-loop
    public static void ForLoop() {
        for (int num = 1;;num++){
            if(num == 10) break;
            if(num % 2 == 0) continue;
            System.out.println(num);
        };
    }

    // Array
    public static void Array() {
        int[] numList = {10, 20, 30, 40, 50};

        // USE FOR
        for(int i=0;i<numList.length;i++){
            System.out.println(numList[i]);
        }

        // USE FOREACH
        for(int i : numList){
            System.out.println(i);
        }
    }
}