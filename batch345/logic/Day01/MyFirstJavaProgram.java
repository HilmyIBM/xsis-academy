package Day01;
public class MyFirstJavaProgram {
    /* This is my first java program.
     * This will print 'Hello World' as the output.
     */

     public static void main(String[] args) {
        System.out.println("Hello World");  //prints Hello World

        String title = "";
        boolean isMale = false, isMarried = false;

        title = (isMale ? "Mr." : (isMarried ? "Mrs." : "Ms."));

        System.out.println(title);

        System.out.println();

        int i = 10;
        switch (i) {
         case 10:
            System.out.println("Ten");
            break;
         case 20:
            System.out.println("Twenty");
            break;
         default:
         System.out.println("No-Match");
            break;
        }
     }
}