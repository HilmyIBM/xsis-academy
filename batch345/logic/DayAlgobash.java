public class DayAlgobash {
    public static void main(String[] args) {
        commonDifisor("5/25");
    }

    public static void commonDifisor (String data){
        String[] numberString = data.split("/");
        int pembilang = Integer.parseInt(numberString[0]);
        int penyebut = Integer.parseInt(numberString[1]);

        int n = (pembilang > penyebut ? penyebut : pembilang);

        for (int i = 1; i<n; i++){
            if (pembilang % i == 0 && penyebut % i == 0){
                pembilang /= i;
                penyebut /= i;
            }
        }
        StringBuilder result = new StringBuilder();
        if (pembilang % penyebut == 0){
            result.append(pembilang/penyebut);
        } else {    
            result.append(pembilang);
            result.append('/');
            result.append(penyebut);
        }

        System.out.println(result.toString());
    }
}
