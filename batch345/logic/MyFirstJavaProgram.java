public class MyFirstJavaProgram{
    public static void main(String[] args) {
        System.out.println("Hello World\nCase Test"); 

        String title="";
        boolean ismale=true;
        title=(ismale? "Mr.":"Mrs.");
        System.out.println(title);

        int i =10;
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

        forloop();
    }

    public static void forloop(){
        for(int num=1;;num++){
            if (num==10)break;
            if (num%2==0) continue;
            System.out.println(num);
        }
    }
}