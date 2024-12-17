import java.util.ArrayList;
import java.util.Scanner;

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
        ArrayList<String> cars = new ArrayList<String>();
        cars.add("Volvo");
        cars.add("bmw");
        cars.add("Ford");
        cars.add("Mazda");
        System.out.println(cars);
        cars.add(0,cars.get(cars.size()-1));
        cars.remove(cars.size()-1);
        System.out.println(cars);

        divbyzero();

        System.out.println(sum(10));

        humanobject();
        MyFirstJavaProgram objectTest = new MyFirstJavaProgram();
        objectTest.mamaliaobject();
    }

    public void mamaliaobject(){
        Sapi sapiputih = new Sapi();
        sapiputih.jumlah_kaki=4;
        sapiputih.bergerak();
        sapiputih.bersuara();

        Paus pausbiru= new Paus();
        pausbiru.jumlah_kaki=0;
        pausbiru.berenang();
    }

    static void humanobject(){
        Human jack = new Human("Jacky","Male");
        System.out.println(jack.getName());

        Human saitama = new Human("Saitama", "male");
        System.out.println(saitama.getGender());

        System.out.println(Human.count);

        saitama.setName("john");
        System.out.println(saitama.getName());
    }

    public static void forloop(){
        for(int num=1;;num++){
            if (num==10)break;
            if (num%2==0) continue;
            System.out.println(num);
        }
    }

    public static void divbyzero(){
        try {
            Scanner input = new Scanner(System.in);
            int x,y;
            double z;
            System.out.print("Masukkan x : ");
            x=input.nextInt();

            System.out.print("Masukkan Z : ");
            y=input.nextInt();

            z=division(x, y);
            System.out.println("Hasil : "+z);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Alert : " + e.getMessage());
            System.out.println("Check your Input");
            divbyzero();
        }
        finally{
            System.out.println("Selesai");
        }
    }

    public static double division(int x, int y) throws Exception{
        double result=0.0;
        try {
            result=x/y;
            
        } catch (ArithmeticException ae) {
            // TODO: handle exception
            throw new Exception("Gagal : "+ae.getMessage());

        }
        return result;
    }

    public static int sum(int k){
        System.out.print(k +",");
        if(k > 0){
            return k + sum(k-1);
        }else{
            return 0;
        }
    }
}