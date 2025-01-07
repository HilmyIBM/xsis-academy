package ft1;

import java.util.Scanner;

public class Soal8 {
    public static void soal8(int[][] matrix, char pola, char action) {
        if (pola == 'x') {
            int result = matrix[0][0];
            if (action == '+') {
                for (int i = 1; i < matrix.length; i++) {
                    result += matrix[i][i];
                }
                for (int i = 0; i < matrix.length; i++) {
                    result += matrix[i][3 - 1 - i];
                }
                System.out.println("Output : " + result);
            } else if (action == '-') {
                for (int i = 1; i < matrix.length; i++) {
                    result -= matrix[i][i];
                }
                for (int i = 0; i < matrix.length; i++) {
                    result -= matrix[i][3 - 1 - i];
                }
                System.out.println("Output : " + result);
            } else if (action == 'x') {
                for (int i = 1; i < matrix.length; i++) {
                    result *= matrix[i][i];
                }
                for (int i = 0; i < matrix.length; i++) {
                    result *= matrix[i][3 - 1 - i];
                }
                System.out.println("Output : " + result);
            }
        } else if (pola == '+') {
            int result = matrix[0][1];
            if (action == '+') {
                for (int i = 1; i < matrix.length; i++) {
                    result += matrix[i][1];
                }
                for (int i = 0; i < matrix.length; i++) {
                    result += matrix[1][i];
                }
                System.out.println("Output : " + result);
            } else if (action == '-') {
                for (int i = 1; i < matrix.length; i++) {
                    result -= matrix[i][1];
                }
                for (int i = 0; i < matrix.length; i++) {
                    result -= matrix[1][i];
                }
                System.out.println("Output : " + result);
            } else if (action == 'x') {
                for (int i = 1; i < matrix.length; i++) {
                    result *= matrix[i][1];
                }
                for (int i = 0; i < matrix.length; i++) {
                    result *= matrix[1][i];
                }
                System.out.println("Output : " + result);
            }
        } else if (pola == '-') {
            int result = matrix[1][0];
            if (action == '+') {
                for (int i = 1; i < matrix.length; i++) {
                    result += matrix[1][i];
                }
            } else if (action == '-') {
                for (int i = 1; i < matrix.length; i++) {
                    result -= matrix[1][i];
                }
            } else if (action == 'x') {
                for (int i = 1; i < matrix.length; i++) {
                    result *= matrix[1][i];
                }
            }
            System.out.println("Output : " + result);
        } else {
            System.out.println("pola tidak sesuai");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] matrix = new int[3][3];
        System.out.print("Masukkan deret matriks = ");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        sc.nextLine();
        System.out.print("Masukkan pola : ");
        char pola = sc.next().charAt(0);
        System.out.print("Masukkan action : ");
        char action = sc.next().charAt(0);
        soal8(matrix, pola, action);
        sc.close();
    }
}
