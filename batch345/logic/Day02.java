import java.util.InputMismatchException;
import java.util.Scanner;

public class Day02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Grade Nilai");
        System.out.println("2. Pulsa");
        System.out.println("3. Diskon Grab");
        System.out.println("4. Free Ongkir");
        System.out.println("5. Istilah Generasi");
        System.out.print("Masukkan menu: ");
        switch (Integer.parseInt(sc.nextLine())) {
            case 1:
                gradeNilai(90);
                break;
            case 2:
                pulsa(51000);
                break;
            case 3:
                try {
                    System.out.print("Masukkan Belanja (Dalam Angka): ");
                    int uang = Integer.parseInt(sc.nextLine());
                    System.out.print("Masukkan Jarak (Dalam Angka): ");
                    int jarak = Integer.parseInt(sc.nextLine());
                    System.out.print("Masukkan promo: ");
                    String promo = sc.nextLine();
                    int hasil = diskonGrab(uang, jarak, promo);
                    System.out.println(hasil);
                } catch (Exception e) {
                    // TODO: handle exception
                    System.err.println("Error: " + e.getMessage());
                    main(args);
                }
                break;
            case 4:
                try {
                    System.out.print("Masukkan ongkos belanja: ");
                    int shoping = Integer.parseInt(sc.nextLine());
                    System.out.print("Masukkan harga ongkir: ");
                    int ongkir = Integer.parseInt(sc.nextLine());
                    if (shoping >= 100000) {
                        System.out.print("1. Min Order 30rb free ongkir 5rb dan potongan harga belanja 5rb");
                        System.out.print("2. Min Order 50rb free ongkir 10rb dan potongan harga belanja 10rb");
                        System.out.print("3. Min Order 100rb free ongkir 20rb dan potongan harga belanja 10rb");
                    } else if (shoping >= 50000) {
                        System.out.println("1. Min Order 30rb free ongkir 5rb dan potongan harga belanja 5rb");
                        System.out.println("2. Min Order 50rb free ongkir 10rb dan potongan harga belanja 10rb");
                    } else {
                        System.out
                                .println("1. Min Order 30rb free ongkir 5rb dan potongan harga belanja 5rb\t\t\t\t\t");
                    }
                    System.out.print("Pilih Voucher: ");
                    int voucher = Integer.parseInt(sc.nextLine());
                    freeOngkir(shoping, ongkir, voucher);
                } catch (Exception e) {
                    // TODO: handle exception
                    System.err.println("Error: " + e.getLocalizedMessage());
                    main(args);
                }
                break;
            case 5:
                try {
                    System.out.print("Masukkan nama anda: ");
                    String name = sc.nextLine();
                    System.out.print("Tahun berapa anda lahir? ");
                    int born = Integer.parseInt(sc.nextLine());
                    generation(born,name);
                } catch (Exception e) {
                    // TODO: handle exception
                    System.err.println(e.getMessage());
                    main(args);
                }
                break;
            default:
                System.out.println("Terminate program");
                System.exit(0);
                break;
        }
        sc.close();
    }

    static void gradeNilai(int data) {
        if (data >= 90) {
            System.out.println("Grade A");
        } else if (data >= 70) {
            System.out.println("Grade B");
        } else if (data >= 50) {
            System.out.println("Grade C");
        } else {
            System.out.println("Grade E");
        }
    }

    static void pulsa(int pulsa) {
        int point;
        if (pulsa >= 100000) {
            point = 800;
        } else if (pulsa >= 50000) {
            point = 400;
        } else if (pulsa >= 25000) {
            point = 200;
        } else {
            point = 10;
        }

        System.out.println("Pulsa : " + point);
        System.out.println("Point : " + pulsa);
    }

    static int diskonGrab(int money, int jarak, String promo) throws Exception {
        int result = 0;
        int discount = 0;
        int ongkir = 0;
        try {
            if (money >= 30000) {
                if (promo.equals("JKTOVO")) {
                    discount = (int) ((40.0 / 100.0) * money);
                }
                ongkir = jarak * 1000;
            } else {
                ongkir = jarak * 1000;
            }
            result = (money - discount) + ongkir;
            return result;
        } catch (InputMismatchException e) {
            // TODO: handle exception
            throw new Exception(e.getLocalizedMessage());
        }
    }

    static void freeOngkir(int money, int ongkir, int voucher) {
        int potonganOngkir = 0;
        int potonganBelanja = 0;
        int result;
        try {
            if (voucher == 3 && money >= 100000) {
                potonganOngkir = 20000;
                potonganBelanja = 10000;
            } else if (voucher == 2 && money >= 50000) {
                potonganBelanja = 10000;
                potonganOngkir = 10000;
            } else if (voucher == 1 && money >= 10000) {
                potonganOngkir = 5000;
                potonganBelanja = 5000;
            } else {
                System.out.println("Voucher tidak valid atau minimum order tidak tercapai.");
            }

            result = (money + ongkir) - (potonganOngkir + potonganBelanja);

            System.out.println("Belanja: " + money);
            System.out.println("Ongkos Kirim: " + ongkir);
            System.out.println("Diskon Ongkir: " + potonganOngkir);
            System.out.println("Diskon Belanja: " + potonganBelanja);
            System.out.println("Total Belanja: " + result);
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println(e.getMessage());
        }
    }

    static void generation(int kelahiran, String name) {
        String generation = "";
        if (kelahiran >= 1995 && kelahiran <= 2015) {
            generation = "Generasi Z";
        } else if (kelahiran >= 1980) {
            generation = "Generasi Y";
        } else if (kelahiran >= 1965) {
            generation = "Generasi X";
        } else if (kelahiran >= 1944) {
            generation = "Baby boomer";
        } else {
            generation = "none";
        }
        System.out.printf("%s, berdasarkan tahun lahir anda tergolong %s", name, generation);
    }

}
