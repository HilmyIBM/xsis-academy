public class PRDay01 {
    public static void main(String[] args) {
        //Nomor 1
        int nilai = 100;
        System.out.println("Grade nilai yang di Input : " + gradeNilai(nilai));

        //Nomor 2
        int angka = 10;
        System.out.println(genapGanjil(angka));


    }
    public static String gradeNilai(int x){
        if (x >= 80) {
            return "A\n";
        }else if (x < 80 && x >= 60) {
            return "B\n";
        }else{
            return "c\n";
        }
    
    }

    public static String genapGanjil(int x){
        return x % 2 == 0 ? "Angka " + x + " adalah Genap" : "Angka " + x + " adalah Ganjil";
    }
}
