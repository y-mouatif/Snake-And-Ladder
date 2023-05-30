/**
 * @author Yasmine Mouatif 40249967 Hanine Tydrini
 * COMP249
 * Assignment #1 
 * February 6th, 2023
 */
import java.util.Scanner;
public class Main {

    
    /** 
     * @param args
     * 
     */
    public static void main(String[] args) {
        int pn = verif();
        System.out.println("players : " + pn);
        LadderAndSnake one = new LadderAndSnake(pn);
        one.play();
        
    }
    /**method that verifies the input for the number of players of the game.
     * Keeps prompting the user as long as the input is not an integer
     * @return the number of players in the game */
    public static int verif(){
        int p=0;
        System.out.print ("Please enter the number of players of the game:");
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()==false){//loop to keep asking as long as the input isn't a valid integer
            String wrong = in.next();//stores the wrong input in a garbage string
            System.out.println("You entered a wrong input. Please try again:");           
        }
        p = in.nextInt();//stores the right input integer in an integer
        return p; 
    }
}