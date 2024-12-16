public class MyFirstProgramJava{

    public static void main(String[] args){
        System.out.println("Hello World\n");
    
        // Switch Case
        int i = 10;
        
        switch (i = 10) {
            case 10:
                System.out.println("Ten");
                break;
            case 20:
                System.out.println("Twenty");
                break;
        
            default:
                System.out.println("Not Match");
                break;
        }

        //Ternary Operator
        String title = "";
        boolean isMale = true, isMarried = false;
        
        title = isMale ? (isMarried ? "hello" : "hai")  : "Mrs";
        System.out.println(title);
    
    }
}