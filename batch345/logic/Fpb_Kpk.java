public class Fpb_Kpk {
    public static void main(String[] args) {
        int num1 = 12;
        int num2 = 15;
        System.out.println("Hasil dari FPB " + num1 + " dan " + num2 + " adalah " + hitungFPB(num1, num2));
        System.out.println("Hasil dari KPK " + num1 + " dan " + num2 + " adalah " + hitungKPK(num1, num2));
    }

    static int hitungFPB(int a, int b) {
        int temp = 0;
        if (b == 0) {
            return a;
        }
        while (b != 0) {
            temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    static int hitungKPK(int a, int b) {
        if (b == 0)
            return a;
        int fpb = hitungFPB(a, b);
        int kpk = (a * b) / fpb;
        return kpk;
    }
}
