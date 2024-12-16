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

        forLoop();
     }

     public static void forLoop() {
        /* for (int i=10, j=20; ;);
         for (System.out.println("Hello");;);
         for (int num=1;;num++) {
           if (num == 10) break;
           if (num%2 == 0) continue;
           System.out.println(num);
         } */
         
         int[] numList = {10, 20, 30, 40, 50};
         for (int i=0; i < numList.length; i++) {
            System.out.println(numList[i]);
         }

         int[] numList2 = {100, 200, 300, 400, 500};
         for (int i : numList2) {
            System.out.println(i);
         }
     }
}