package Coba;

import java.util.Scanner;

public class Soal8 {
    /*
     * Soal ini terdiri dari pola deret angka 3x3 yang nantinya akan diolah
     * menggunakan operasi aritmatika dasar yang ditentukan melalui input
     * 
     * Input:
     * - 3 deret angka(dinamis sesuai input)
     * - pola (terdiri dari pola x, +, atau -)
     * - action (tediri dari operasi aritmatika tambah / kurang / kali)
     * Output: hasil dari operasi aritmatika
     * Constraints:
     * - hanya berupa bilangan bulat positif dan atau bilangan bulat negatif
     * (include angka 0)
     * 
     * Example 1:
     * 0 1 2
     * 3 4 5
     * 6 7 8
     * pola: x
     * action: +
     * penjelasan: (0 + 4 + 8) + (2 + 4 + 6) = 24
     * 
     * output: 24
     * 
     * Example 2:
     * 0 1 2
     * 3 4 5
     * 6 7 8
     * pola: -
     * action: x
     * penjelasan: 3 x 4 x 5 = 60
     * 
     * output: 60
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] array = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                array[i][j] = scanner.nextInt();
            }
        }
        String pola = scanner.next();
        String action = scanner.next();

        polaDeretAngka(array, pola, action);

        scanner.close();
    }

    public static void polaDeretAngka(int[][] array, String pola, String action) {
        Integer sum = 1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (pola.equals("x")) {
                    if (action.equals("+")) {
                        if ((i == j)) {
                            sum += array[i][j];
                            // diagonal 2
                        } else if (i + j == 2) {
                            sum += array[i][j];
                        }
                    } else if (action.equals("-")) {
                        if (i == j) {
                            sum -= array[i][j];
                        } else if (i + j == 2) {
                            sum -= array[i][j];
                        }
                    } else if (action.equals("x")) {
                        if (i == j) {
                            sum *= array[i][j];
                        } else if (i + j == 2) {
                            sum *= array[i][j];
                        }
                    }
                } else if (pola.equals("+")) {
                    if (action.equals("+")) {
                        if ((j == 1 && i == 1) && i == 1) {
                            sum += array[i][j];
                        }
                    } else if (action.equals("-")) {
                        if ((j == 1 && i == 1) && i == 1) {
                            sum -= array[i][j];
                        }
                    } else if (action.equals("x")) {
                        if ((j == 1 && i == 1) && i == 1) {
                            sum *= array[i][j];
                        }
                    }
                } else if (pola.equals("-")) {
                    if (action.equals("+")) {
                        if (j == 1) {
                            sum += array[i][j];
                        }
                    } else if (action.equals("-")) {
                        if (j == 1) {
                            sum -= array[i][j];
                        }
                    } else if (action.equals("x")) {
                        if (i == 1) {
                            sum = sum * array[i][j];
                        }
                    }
                }
            }
        }
        System.out.println(sum);
    }

}
