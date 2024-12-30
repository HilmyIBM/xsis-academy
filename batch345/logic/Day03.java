public class Day03 {
    public static void main(String[] args) {
        pohonFaktor(5);
        deret(7);      
    }
    public static void pohonFaktor(int angka){
        int i = 2;
        while (angka > 1) {
            if (angka % i == 0 ) {
                System.out.println(angka + "/" + i + " = " + angka/i);
                angka /= i;
                if (angka == 1) {
                    break;
                }
            }else{
                i++;
            }
        }
    }
    public static void deret (int n){
        int awal = -5;
        for(int i =0 ; i< n; i++){
            System.out.print(awal + "\t");
            awal *= (-1); 
            if (awal > 0) {
                awal += 5;
            }else{
                awal -= 5;
            }
        }
    }
}
