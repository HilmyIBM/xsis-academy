package Day06;

import java.util.ArrayList;
import java.util.List;

public class Day06 {
    public static void main(String[] args) {
        gunungLembah();
    }

    public static void gunungLembah() {
        String input = "UDUDDUUUDUDUDUUDUUDDDDDUDUDDDDUUDDUDDUUUUDUUDUDDDDUDUDUUUDDDUUUDUDDUUDDDUUDDUDDDUDUUDUUDUUDUDDDUUUUU";
        // String input = "DUDU";
        int mdpl = 0, gunung=0, lembah=0;
        boolean isStart=true;

        for (char c : input.toCharArray()) {
            mdpl += (c=='U') ? 1:-1;

            if (mdpl==0 && !isStart) {
                if (c=='D') {
                    gunung++;
                }
                else{
                    lembah++;
                }
            }

            if(isStart) isStart=false;
        }

        System.out.println("Jumlah Gunung: " + gunung);
        System.out.println("Jumlah Lembah: " + lembah);
    }

    public static void vokalKonsonan() {
        ArrayList<Character> arrVokal = new ArrayList<Character>();
    }
}
