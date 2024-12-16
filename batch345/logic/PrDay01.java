public class PrDay01 {
    public static void main(String[] args) {
        nilai(55);
        even(22);
    }

    public static void nilai(int n){
        if(n >= 80){
            System.out.println("Grade A");
        }else if (n >= 60 && n < 80) {
            System.out.println("Grade B");
        }else if(n < 60 ){
            System.out.println("Grade C");
        }
    }

    public static void even(int i){
        if(i%2!=0){
            System.out.println("Angka "+ i +" adalah ganjil");
        }else{
            System.out.println("Angka "+ i +" adalah genap");
        }
    }
}
