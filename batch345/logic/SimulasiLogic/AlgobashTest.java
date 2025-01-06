package SimulasiLogic;

import java.util.Arrays;
import java.util.List;

class AlgobashTest {
    public static void main(String[] args) {
        theSlicer();
    }
    
    public static void theSlicer() {
        theSlicer("You can either experience the pain of discipline or the pain of regret. The choice is yours.");
    }
    
    /*
     * You are given input in the form of a series of words or sentences
     * and n which is the length of the word fragment.
     * 
     * Constraints:
     * Consists of letters of the alphabet [a-z, A-Z]
     * Slice word from the front and back
     * You have to change the uppercase letter into lowercase
     * Sorted in ascending based on the length of each word first and then alphabetically
     */
    public static List<String> theSlicer(String input) {
        String temp;
        String[] arrInput = input.toLowerCase().replaceAll("[^A-Za-z\\s]","").split(" ");

        for(int i=0; i<arrInput.length-1; i++) {
        for (int j=i+1; j<arrInput.length; j++){
            if (arrInput[i].length() > arrInput[j].length() && i!=j) {
            temp = arrInput[j];
            arrInput[j] = arrInput[i];
            arrInput[i] = temp;
            }
            else if (arrInput[i].length() == arrInput[j].length() && i!=j){
            if(arrInput[i].compareTo(arrInput[j]) > 0) {
                temp = arrInput[j];
                arrInput[j] = arrInput[i];
                arrInput[i] = temp;
            }
            }
        }
        }

        // Your code starts here.
        return Arrays.asList(arrInput);
    }

}