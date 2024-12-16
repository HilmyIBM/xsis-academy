import java.util.Scanner;

public class MyFirstJavaProgram{
    public static void main(String []args){
        Scanner sc = new  Scanner(System.in);
        int  i = sc.nextInt();
        if(i > 80){
            System.out.println("Grade A");
        }else if(i < 80 && i > 60){
            System.out.println("Grade B");
        }else{
            System.out.println("Grade C");
        }
    }
    public static void nilai(){

    }
    public static void oddeven(){
        
    }
}
