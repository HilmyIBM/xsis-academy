import java.util.*;

public class test {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("example");
        list.add("hello");
        list.add("world");
        list.add("xenon");
        list.add("text");

        for(int i=0;i<list.size();i++){
            String temp=list.get(i);
            System.out.println(temp);
            if (temp.contains("x")) {
                list.remove(i);
            }
        }

        // Print the updated list
        System.out.println("Updated list: " + list);
    }

}
