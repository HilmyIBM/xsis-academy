public class PR_Day1 {
    public static void gradeCheck(int nilai) {
        if (nilai >= 80) {
            System.out.println("Grade A");
        } else if (nilai >= 60 && nilai < 80) {
            System.out.println("Grade B");
        } else {
            System.out.println("Grade C");
        }
    }

    public static void oddEvenCheck(int num) {
        if (num % 2 == 0) {
            System.out.println("Angka " + num + " adalah genap");
        } else {
            System.out.println("Angka " + num + " adalah ganjil");
        }
    }

    public static void main(String[] args) {
        gradeCheck(70);
        oddEvenCheck(10);
    }
}
