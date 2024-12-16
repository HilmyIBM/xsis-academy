public class Day01PR {

    public static char grade(int nilai) {
        if (nilai >= 80) return 'A';
        if (nilai >= 60 & nilai < 80) return 'B';
        return 'C';
    }

    public static void oddEven(int n) {
        if (n % 2 == 0) System.out.println("Angka " + n + " adalah genap" );
        else System.out.println("Angka " + n + " adalah ganjil");
    }

    public static void main(String[] args) {
        // No 1.
        int nilai = 60;
        char grade = grade(nilai);
        System.out.println("Dengan Nilai " + nilai + ", Grade yang didapat adalah " + grade);
        System.out.println();

        // No 2.
        int n = 19;
        oddEven(n);
        System.out.println();
    }
}
