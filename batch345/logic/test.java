import java.util.*;
import java.util.stream.Collectors;

public class test {
    public static void main(String[] args) {
/*         ArrayList<String> list = new ArrayList<>();
        list.add("example");
        list.add("hello");
        list.add("world");
        list.add("xenon");
        list.add("text");

        for (int i = 0; i < list.size(); i++) {
            String temp = list.get(i);
            System.out.println(temp);
            if (temp.contains("x")) {
                list.remove(i);
            }
        }

        // Print the updated list
        System.out.println("Updated list: " + list); */
        String test="00110001";
        String hasil="";
        for(int i=0 ; i<test.length();i+=2){
            String s=test.substring(i, i+2);
            System.out.println(s);
            if(s.equals("00")){
                hasil+="A";
            }else if(s.equals("01")){
                hasil+="B";
            }else{
                hasil+="C";
            }
        }
        System.out.println(hasil);

        count_abc();
        System.out.println();
        gcd("12/6");

        List<String> intList = Arrays.asList("1, 2, 3");
        String x1=String.join(",", intList);
        System.out.println("x1 ="+x1);
    }

    public static String kangaroo(int x1, int v1, int x2, int v2) {
        // Write your code here
        boolean ketemu = false;
        int temp1 = x1 + v1;
        int temp2 = x2 + v2;
        for (int i = 0; i < 10000; i++) {
            if (temp1 == temp2) {
                ketemu = true;
                break;
            }
            temp1 += v1;
            temp2 += v2;
        }
        if (ketemu == true) {
            return "YES";
        } else {
            return "NO";
        }
    }

    public static List<Integer> breakingRecords(List<Integer> scores) {
        // Write your code here
        List<Integer> hasil = new ArrayList<>();
        int high = scores.get(0);
        int low = scores.get(0);
        int count_high = 0;
        int count_low = 0;
        for (int i = 0; i < scores.size(); i++) {
            if (scores.get(i) > high) {
                high = scores.get(i);
                count_high++;
            }
            if (scores.get(i) < low) {
                low = scores.get(i);
                count_low++;
            }
        }
        hasil.add(count_high);
        hasil.add(count_low);
        return hasil;
    }

    public static int birthday(List<Integer> s, int d, int m) {
        // Write your code here
        int n = s.size();
        int count = 0;
        int sum = 0;

        for (int i = 0; i < m; i++) {
            sum += s.get(i);
        }

        if (sum == d)
            count++;

        for (int i = m; i < n; i++) {
            sum += s.get(i);
            sum -= s.get(i - m);
            if (sum == d)
                count++;
        }

        return count;
    }

    public static int divisibleSumPairs(int n, int k, List<Integer> ar) {
        // Write your code here
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((ar.get(i) + ar.get(j)) % k == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void bonAppetit(List<Integer> bill, int k, int b) {
        // Write your code here
        int sum = 0;
        for (int i = 0; i < bill.size(); i++) {
            if (i != k) {
                sum += bill.get(i);
            }
        }
        if (sum / 2 == b) {
            System.out.println("Bon Appetit");
        } else {
            System.out.println(b - (sum / 2));
        }
    }

    public static int migratoryBirds(List<Integer> arr) {
        // Write your code here
        List<Integer> hasil = new ArrayList<>();
        int[] burung = { 1, 2, 3, 4, 5 };
        int type1 = 0;
        int type2 = 0;
        int type3 = 0;
        int type4 = 0;
        int type5 = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) == 1) {
                type1++;
            } else if (arr.get(i) == 2) {
                type2++;
            } else if (arr.get(i) == 3) {
                type3++;
            } else if (arr.get(i) == 4) {
                type4++;
            } else if (arr.get(i) == 5) {
                type5++;
            }
        }
        hasil.add(type1);
        hasil.add(type2);
        hasil.add(type3);
        hasil.add(type4);
        hasil.add(type5);
        int temp = 0;
        int max = hasil.get(0);
        for (int i = 0; i < hasil.size(); i++) {
            if (hasil.get(i) > max) {
                max = hasil.get(i);
                temp = i;
            }
        }
        return burung[temp];
    }

    public static int sockMerchant(int n, List<Integer> ar) {
        // Write your code here
        HashSet<Integer> hasil = new HashSet<>();
        int count = 0;
        for (int i : ar) {
            if (hasil.contains(i)) {
                hasil.remove(i);
                count++;
            } else {
                hasil.add(i);
            }
        }
        return count;
    }

    static String catAndMouse(int x, int y, int z) {
        int cat1 = Math.abs(x - z);
        int cat2 = Math.abs(y - z);
        if (cat1 == cat2) {
            return "Mouse C";
        } else if (cat1 < cat2) {
            return "Cat A";
        } else {
            return "Cat B";
        }
    }

    public static String superReducedString(String s) {
        // Write your code here
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                s = s.substring(0, i - 1) + s.substring(i + 1);
                i = 0;
            }
        }
        if (s.length() == 0) {
            return "Empty String";
        } else {
            return s;
        }
    }

    public static int camelcase(String s) {
        // Write your code here
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                count++;
            }
        }
        return count + 1;
    }

    public static int alternate(String s) {
        // Write your code here
        int count = 0;
        boolean b = true;
        int result = 0;
        char arr[] = s.toCharArray();

        List<Character> list = new ArrayList<>();
        for (char c : arr) {
            list.add(c);
        }
        list = list.stream().distinct().collect(Collectors.toList());
        String st = s;

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                st = s;
                st = st.replaceAll("[^" + list.get(i) + list.get(j) + "]", "");
                for (int k = 0; k < st.length() - 1; k++) {
                    if (st.charAt(k) == st.charAt(k + 1)) {
                        b = false;
                        count = 0;
                        break;
                    }
                    b = true;
                }

                if (b) {
                    count = st.length();
                    if (count > result) {
                        result = count;
                        b = true;
                    }
                }
            }
        }
        return result;

    }

    public static void separateNumbers(String s) {
        int n = s.length();
        for (int i = 1; i <= n / 2; i++) {
            int j = i;
            long a = Long.parseLong(s.substring(0, i));
            boolean flag = true;

            while (j < n) {
                String b = Long.toString(a + 1);
                if (j + b.length() > n || !s.substring(j, j + b.length()).equals(b)) {
                    flag = false;
                    break;
                }
                j += b.length();
                a = Long.parseLong(b);
            }

            if (flag) {
                System.out.println("YES " + s.substring(0, i));
                return;
            }
        }
        System.out.println("NO");
    }

    public static String processSubstring(String s) {
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j <= s.length() - i; j++) {
                String sub = s.substring(j, j + i);
                System.out.println("Substring: " + sub);

                if (sub.equals("0")) {
                    return "A";
                } else if (sub.equals("00")) {
                    return "B";
                } else if (sub.equals("001")) {
                    return "C";
                } else if (sub.equals("0011")) {
                    return "D";
                } else if (sub.equals("10")) {
                    return "E";
                }
            }
        }
        return "No match";
    }

    public static void processSubstring(String s, int length) {
        System.out.println("Mengambil substring dengan panjang " + length);

        for (int i = 0; i <= s.length() - length; i++) {
            String sub = s.substring(i, i + length);
            System.out.println("Substring: " + sub);

            if (sub.equals("00")) {
                System.out.println("Substring ini cocok dengan aturan: B");
            } else if (sub.equals("11")) {
                System.out.println("Substring ini cocok dengan aturan: C");
            }
        }
    }

    public static void count_abc(){
        String myStr = "Hello www123";
        char[] test = new char[26];
        int max=0;
        int lokasi=0;
        for (char c : myStr.toLowerCase().toCharArray()) {
          if (Character.isLetter(c)) {
            test[c - 'a']++;
          }
        }
        for (int i = 0; i < test.length; i++) {
            if(test[i] > max){
                max=test[i];
                lokasi=i;
            }
          System.out.println((char) (i + 'a') + ": " + (int) test[i]);
        }
        System.out.println();
        System.out.print("Huruf Terbanyak : ");
        System.out.print((char)(lokasi+'a') +":"+ max);
      }

    public static void gcd(String s){
        String[] temp=s.split("/");
        int x=Integer.parseInt(temp[0]);
        int y=Integer.parseInt(temp[1]);
        int gcd=0;

        for(int i=1;i<= x && i<=y;i++ ){
            if(x%i == 0 && y % i==0){
                gcd=i;
            }
        }

        x/=gcd;
        y/=gcd;
        if(y==1){
            System.out.println(x);
        }else{
            System.out.println(x+"/"+y);
        }
    }
}
