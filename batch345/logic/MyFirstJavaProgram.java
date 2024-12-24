public class MyFirstJavaProgram {
    public static void main(String[] args) {
        /*
         * int i = 10;
         * 
         * switch (i) {
         * case 1:
         * System.out.println("One");
         * break;
         * case 2:
         * System.out.println("Two");
         * break;
         * case 10:
         * System.out.println("Ten");
         * break;
         * default:
         * System.out.println("Hello World!");
         * break;
         * }
         */

        // forLoop();
        // Human jack = new Human("Fito", "laki-laki");
        // System.out.println(jack.getName());
        // String test = "";
        // char a = 'a';
        // char k = 'k';
        // test += a;
        // test += k;
        // System.out.println(test);
        System.out.println(String.format("%05d", 100));
    }

    public static void forLoop() {
        for (int i = 1; i < 10; i++) {
            System.out.println(i);
        }
    }
}
