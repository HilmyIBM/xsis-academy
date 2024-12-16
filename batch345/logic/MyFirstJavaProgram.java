public class MyFirstJavaProgram {
    public static void main(String[] args) {
        System.out.println("Hello World");

        // switch
        int i = 10;
        switch (i) {
            case 10:
                System.out.print("Number 10\n");
                break;
            default:
                System.out.print("Not Number 10\n");
                break;
        }
        System.out.println();

        // Ternary
        System.out.println("Learning Ternary: ");
        boolean isMale = false, isMarried = true;
        String caller = (isMale ? "Mr." : (isMarried ? "Mrs." : "Ms."));
        System.out.println(caller);
        System.out.println();

        // Looping
        System.out.println("Learning Looping with Continue (Lompat ke looping selanjutnya)");
        forLoop();

        // Array
        System.out.println("Learning Array");
        array();
    }

    public static void forLoop() {
        for (int i = 10, d = 20; i < d; i++) {
            if (i == 15) {
                continue;
            }
            System.out.println(i);
        }
        System.out.println();
    }

    public static void array() {
        int[] numList = { 10, 20, 30, 40, 50 };
        for (int data : numList) {
            System.out.print(data + " ");
        }
        System.out.println();
        for (int i = 0; i < numList.length; i++) {
            System.out.print(numList[i] + " ");
        }
    }
}
