package Day03;
public class Kelelawar extends Mamalia {
    private boolean bersayap;

    public Kelelawar() {
        bersayap = true;
    }

    public void bergerak() {
        System.out.println("Terbang");
    }

    public boolean isBersayap() {
        return bersayap;
    }
}
