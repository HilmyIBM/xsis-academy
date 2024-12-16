public class Day01PR {

    public static char Grade(int nilai) {
        if (nilai >= 80) return 'A';
        if (nilai >= 60 & nilai < 80) return 'B';
        return 'C';
    }

    public static void EvenOdd(int n) {
        if (n % 2 == 0) System.out.println("Angka " + n + " adalah genap" );
        else System.out.println("Angka " + n + " adalah ganjil");
    }

    public static void main(String[] args) {
        // No 1.
        int nilai = 59;
        char grade = Grade(nilai);
        System.out.println("Dengan Nilai " + nilai + ", Grade yang didapat adalah " + grade);
        System.out.println();

        // No 2.
        int n = 19;
        EvenOdd(n);
        System.out.println();
    }
}
