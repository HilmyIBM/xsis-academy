import java.util.Scanner;
import java.util.regex.Pattern;

public class Day06 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Menu");
        System.out.println("1. Menghitung lembah");
        System.out.println("2. Sandi Caesar");
        System.out.println("3. Calculate berat index");
        System.out.println("4. Vokal atau Konsonan (Ascending Order for both)");
        System.out.println("5. Making Password");
        System.out.print("Masukkan nomor: ");
        switch (Integer.parseInt(sc.nextLine())) {
            case 1:
                System.out.print("Masukkan kalimat: ");
                String input = sc.nextLine();
                countLembah(input);
                break;
            case 2:
                System.out.print("Masukkan kalimat: ");
                String caesarInput = sc.nextLine();
                System.out.print("Masukkan rotasi angka: ");
                int rotate = sc.nextInt();
                sc.nextLine();
                caesarChipher(caesarInput, rotate);
                break;
            case 3:
                System.out.print("Masukkan panjang angka: ");
                String inputPanjang = sc.nextLine();
                String[] trimInputPanjang = inputPanjang.split(" ");
                if (trimInputPanjang.length < 26 && trimInputPanjang.length > 26) {
                    System.out.println("Harus memiliki jumlah huruf a ke z dengan jumlah 26");
                    sc.close();
                    return;
                }
                int[] convertInputPanjang = new int[trimInputPanjang.length];
                for (int i = 0; i < convertInputPanjang.length; i++) {
                    convertInputPanjang[i] = Integer.parseInt(trimInputPanjang[i]);
                }
                System.out.print("Masukkan kata: ");
                String kata = sc.nextLine();
                hitungBeratIndex(convertInputPanjang, kata.toLowerCase());
                break;
            case 4:
                System.out.print("Masukkan panjang kata: ");
                String inputVokalKonsonan = sc.nextLine();
                counterVokalAndConsonant(inputVokalKonsonan);
                break;
            case 5:
                System.out.print("Masukkan password: ");
                String pw = sc.nextLine();
                passwordChecker(pw);
                break;
            default:
                System.exit(0);
                break;
        }
        sc.close();
    }

    // Number 1
    static void countLembah(String input) {
        int lembah = 0, posisi = 0, bukit = 0;
        boolean isDibawahPermukaan = false;
        StringBuilder pattern = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            if (character == 'D') {
                posisi--;
                pattern.append("\\");
            } else if (character == 'U') {
                posisi++;
                pattern.append('/');
            } else {
                System.err.println("Terdapat karakter buka D atau U");
                return;
            }

            // Operation for counting lembah
            if (posisi == 0 && character == 'U' && isDibawahPermukaan) {
                lembah++;
                isDibawahPermukaan = false; // sudah tidak dibawah permukaan laut
            }

            // check if the current entering lembah
            if (posisi < 0 && !isDibawahPermukaan) {
                isDibawahPermukaan = true; // dibawah permukaan laut
            }

            // Operation count bukit
            if (posisi == 0 && character == 'D' && !isDibawahPermukaan) {
                bukit++; // bukit berhasil dibuat
                isDibawahPermukaan = false; // kembali ke surface
            }
        }
        System.out.println(lembah);
        System.out.println(bukit);
        System.out.println(pattern.toString());
    }

    // Number 2
    static void caesarChipher(String input, int rotate) {
        StringBuilder sb = new StringBuilder();
        char characterShifting;

        // logic
        /*
         * 1. ambil index character
         * 2. check jika berada pada jangkauan huruf besar maupun huruf kecil
         * 3. convert menjadi char dengan operasi (nomor ascii - nomor ascii huruf 'a' +
         * rotasi) % 26 + 'a/A'
         * 4. fungsi dari % 26 untuk menjaga bahwa kata tersebut berada pada jangkauan
         * 0-25
         */

        // Looping through the character
        for (int i = 0; i < input.length(); i++) {
            characterShifting = input.charAt(i);
            if (characterShifting >= 'a' && characterShifting <= 'z') {
                characterShifting = (char) ((characterShifting - 'a' + rotate) % 26 + 'a');
            } else if (characterShifting >= 'A' && characterShifting <= 'Z') {
                characterShifting = (char) ((characterShifting - 'A' + rotate) % 26 + 'A');
            }
            sb.append(characterShifting);
        }

        System.out.println(sb.toString());
    }

    // Number 3
    static void hitungBeratIndex(int[] inputan, String kata) {
        int terbesar = 0, hasilInputan = 0, index = 0;
        char character;
        // Looping berdasarkan panjang karakter
        for (int i = 0; i < kata.length(); i++) {
            character = kata.charAt(i);
            if (character >= 'a' && character <= 'z') {
                index = character - 'a';

            }
            hasilInputan = inputan[index];
            if (terbesar <= hasilInputan) {
                terbesar = hasilInputan;
            }
        }
        System.out.println(terbesar * kata.length());
    }

    // Number 4 (menghitung vokal dan consonant)
    static void counterVokalAndConsonant(String input) {
        String lowerString = input.toLowerCase();
        StringBuilder vokal = new StringBuilder();
        StringBuilder consonant = new StringBuilder();
        for (int i = 0; i < lowerString.length(); i++) {
            if (lowerString.charAt(i) == 'a' || lowerString.charAt(i) == 'i' || lowerString.charAt(i) == 'u'
                    || lowerString.charAt(i) == 'e'
                    || lowerString.charAt(i) == 'o') {
                vokal.append(lowerString.charAt(i));
            } else {
                consonant.append(lowerString.charAt(i));
            }
        }
        String resultVokal = bubbleSort(vokal);
        String resultConsonant = bubbleSort(consonant);
        System.out.println("Vokal: " + resultVokal.trim());
        System.out.println("Consonant: " + resultConsonant.trim());
    }

    // bubble sort for string
    static String bubbleSort(StringBuilder inputString) {
        StringBuilder sb = new StringBuilder(inputString);
        char temp;
        for (int i = 0; i < inputString.length() - 1; i++) {
            for (int j = 0; j < inputString.length() - i - 1; j++) {
                if (sb.charAt(j) > sb.charAt(j + 1)) {
                    temp = sb.charAt(j);
                    sb.setCharAt(j, sb.charAt(j + 1));
                    sb.setCharAt(j + 1, temp);
                }
            }
        }
        return sb.toString();
    }

    // Number 5 (Membuat Password Checker)
    static void passwordChecker(String input) {
        String minimumSixLength = "^.{6,}$"; // minimum 6 atau lebih karakter ('HARUS - seluruh string')
        String digitPattern = "(?=.*\\d)"; // minimum ada 1 angka di keseluruhan string (HARUS ada 1 angka di mana saja
                                           // dalam string)
        String lowerCase = "(?=.*[a-z])";// minimum ada 1 karakter kecil di keseluruhan string (HARUS ada 1 karakter
                                         // kecil di mana saja dalam string)
        String upperCase = "(?=.*[A-Z])"; // minimum ada 1 karakter besar di keseluruhan string (HARUS ada 1 karakter
                                          // besar dimana saja dalam string)
        String specialCharacter = "(?=.*[!@#$%^&*()\\-\\+])"; // minimum ada special character (HARUS ada 1 karakter
                                                              // special dimana saja dalam string)
        String injection = "(?i)(?=.*<script.*?>.*?</script>)";

        boolean length = input.matches(minimumSixLength);
        boolean digit = Pattern.compile(digitPattern).matcher(input).find();
        boolean lower = Pattern.compile(lowerCase).matcher(input).find();
        boolean upper = Pattern.compile(upperCase).matcher(input).find();
        boolean specialChar = Pattern.compile(specialCharacter).matcher(input).find();
        boolean scripting = input.matches(injection);

        if (length && digit && lower && upper && specialChar) {
            System.out.println("Password Strong");
        } else {
            StringBuilder sb = new StringBuilder();
            if (!length) {
                sb.append("Password Weak & Kurang dari 6 digit\n");
            }
            if (!digit) {
                sb.append("Password Weak & Kurang angka\n");
            }
            if (!lower) {
                sb.append("Password Weak & Kurang huruf kecil\n");
            }
            if (!upper) {
                sb.append("Password Weak & Kurang Huruf Besar\n");
            }
            if (!specialChar) {
                sb.append("Password Weak & Kurang Symbol\n");
            }
            if (!scripting) {
                sb.append("Terdapat Injection Javascript\n");
            }
            System.out.println(sb.toString());
        }
    }
}
