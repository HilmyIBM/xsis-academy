public class Day01 {
    public static void main(String[] args) {
        float jari = 28.0F;

        System.out.println("Keliling lingkaran dengan jari-jari " + jari + ": " + kelilingLingkaran(jari));
    }

    public static float kelilingLingkaran (float jari) {
        // System.out.println(22/7.0);
        return (2 * (22/7.0F) * jari);
    }
}
