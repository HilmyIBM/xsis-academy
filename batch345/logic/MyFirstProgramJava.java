public class MyFirstProgramJava{

    public static void main(String[] args){
        System.out.println("Hello World\n");
        
        //Ternary Operator
        String title = "";
        boolean isMale = true, isMarried = false;
        
        title = isMale ? (isMarried ? "hello" : "hai")  : "Mrs";
        System.out.println(title);
    
    }
}