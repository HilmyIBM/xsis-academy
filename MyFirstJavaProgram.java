public class MyFirstJavaProgram {
    /* This is my first java program.
     * This will print 'Hello World' as the output
     */

     public static void main(String []args) {
        System.out.println("Hello World\n"); //prints Hello World

        int i = 10;
        switch (i) {
         case 10:
            System.out.println("Ten");
            break;
         case 20:
            System.out.println("Twenty");
            break;
         default:
            System.out.println("No match");
            break;
        }

        String title= "";
        boolean isMale = false, isMarried = true;

        title = (isMale ? "Mr" : (isMarried ? "Mrs" : "Ms"));
        System.out.println(title);
     }

     public static void forLoop(){
      for (int i = 10; i < 15; i++){
         
      }
     }

     //changesssss
}