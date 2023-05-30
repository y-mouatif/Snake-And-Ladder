/**
 * @author Yasmine Mouatif 40249967 Hanine Tydrini 40226729
 * COMP249
 * Assignment #1 
 * February 6th, 2023
 */
import java.util.Random;
import java.util.Scanner;

   public class LadderAndSnake{
        /**
         * @param positionboard [][] is our position board
         * @param board [][] is our playing board
         * @param position1 stores the position of player one
         * @param position2 stores the position of player 2
         * @param nPlayers is the number of players in the game
        */
        private int nPlayers;
        private int[][] positionBoard;
        private final int [][] board ={{38,2,3,14,5,6,7,8,31,10},{11,12,13,14,15,6,17,18,19,20},{42,22,23,24,25,26,27,84,29,30},{31,32,33,34,35,44,37,38,39,40},{41,42,43,44,45,46,47,30,49,50},{67,52,53,54,55,56,57,58,59,60},{61,62,63,60,65,66,67,68,69,70},{91,72,73,74,75,76,77,78,79,100},{81,82,83,84,85,86,87,88,89,90},{91,92,68,94,24,96,76,78,99,100}}; 
        private int position1;
        private int position2;
        Scanner in = new Scanner(System.in);

        /**default constructor */
        public LadderAndSnake(){
            nPlayers=2;
            positionBoard = new int [10][10];
        }
        /**parametarized constructor that checks the number of players, 
         * terminates the program if the parameter is smaller than 2, 
         * and sets number of players to 2 if the parameter is bigger 
         * @param np the input for number of players by the user */
        public LadderAndSnake(int np){
            if( np<2){
                System.out.println("Error: Cannot execute the game with less than 2 players! Will exit”");
                System.exit(0);
            }
            else if(np!=2){
                System.out.println("Warning : the number of players has been set to 2.");
            }
            nPlayers = 2;
            positionBoard = new int [10][10];
        }
                    
        /**accessor for the positionBoard
        * @return the current positionBoard */
        public int[][] getPositioBoard(){
		return this.positionBoard;
	    }
        /**mutator for the positionBoard
         * @param positionBoard input of an array that represents a positionBoard */
        public void setPositionBoard(int positionBoard[][]){
		this.positionBoard =positionBoard;
	    }
        /**accessor for the nPlayers
        * @return the number of players*/
        public int getNPlayers() {
            return this.nPlayers;
        }
        /**
         * mutator for nPlayers
         * @param nPlayers
         */
        public void setNPlayers(int nPlayers) {
            this.nPlayers = nPlayers;
        }
        /**
         * accessor for position 1
         * @return position of player one
         */
        public int getPosition1() {
            return this.position1;
        }
        /**
         * mutator for position of player one
         * @param position1
         */
        public void setPosition1(int position1) {
            this.position1 = position1;
        }
        /**
         * accessor for position 2
         * @return position of player two
         */
        public int getPosition2() {
            return this.position2;
        }
        /**
         * mutator for position of player two
         * @param position2
         */
        public void setPosition2(int position2) {
            this.position2 = position2;
        }

        /**Method that fills the reference board with numbers from 1 to 100 corresponding to positions
         * Will later on be used as a reference to fing the index of a specific position that the player gets */
        public void fillPositionArray(){
            for ( int i=0; i<10; i++){
                for ( int k=0;k<10; k++){
                    positionBoard[i][k] = i*10 + ( k+1);
                }
            }

        }
        /**Method that prints the playing board for the user to see in the console
         * This board contains the actual position that the player gets when he stops on a specific case, 
         * taking into account the snakes and ladders that the player might find
        */
        public void PrintBoard(){
            System.out.println("Let the game begin!");
            for ( int i=0; i<10; i++){
                for ( int k=0;k<10; k++){
                    System.out.print(board[i][k]+"\t");
                }
                System.out.println("\n");
            }
        }
        /**Method that flips the dice, but first asks the user to input any character. 
         * The input acts as a checkup, not letting the dice get rolled automatically to 
         * allow the user to play in a realistic rate as if he was playing in real time
         * @return the result of the random dice flip (integer from 0 to 6)
         */
        public int flipDice(){
            int result;
            System.out.print("To play, enter a random letter to roll the dice: ");
            String roll = in.next();
            Random r = new Random();
            result = r.nextInt(6)+1; 
            System.out.println("\tDice has been rolled : result is " + result);
            return result;
        }
        /**method that allows the user to play. It is the method in the class that performs all the other methods
         * Takes no parameters, has no returns*/
        public void play(){                
            System.out.print("Enter the name for player 1:");
            final String p1 = in.next();
            System.out.print("Enter the name for player 2:");
            final String p2 = in.next();
            fillPositionArray();//fills the reference array 
            PrintBoard();//prints the board for the user to see
            order(p1,p2);// decides the order of the players 
                //we have two if statements, this one determines the order if the first player is starting 

                if (position1>position2){
                    position1 = 0;//reset the positions to 0 to have the players start from outside the board
                    position2 = 0;
                    position1 = move(p1, position1);// move the first player 
                    System.out.println(p1+" is at position " + position1);//announce his position 
                    do {//keep alternating the players as long as both of them have not reached the position 100
                        position2 = move(p2, position2);
                        position2 = check(p2, position2);
                        position1 = sameCase(p2, p1, position2, position1);
                        System.out.println(p2+" is at position " + position2);
                        position1 = move(p1, position1);
                        position1 = check(p1, position1);
                        position2= sameCase(p1, p2, position1, position2);
                        System.out.println(p1+" is at position " + position1);
                    } while ( position1 < 100 && position2 < 100);
                }
                //this one determines teh order if the second player is starting 
                if (position1<position2){
                    position1 = 0;//reset the positions to 0 to have the players start from outside the board
                    position2 = 0;
                    position2 = move(p2, position2);
                    System.out.println(p2+" is at position " + position2);
                    do {//keep alternating the players as long as both of them have not reached the position 100
                        position1 = move(p1, position1);
                        position1 = check(p1, position1);
                        position2= sameCase(p1, p2, position1, position2);//compares both player positions and, if both are on the same position, resets first player that got there to position 0 
                        System.out.println(p1+" is at position " + position1);//announces player position 
                        position2 = move(p2, position2);
                        position2 = check(p2, position2);
                        position1= sameCase(p2, p1, position2, position1);
                        System.out.println(p2+" is at position " + position2);
                    } while ( position1 < 100 && position2 < 100);
                }
                
        }
        /**
         * This method checks if the player is at a position higher than 100 to rectify it. Or if the position is 100 it ends the game.
         * @param p is the player playing his turn right now
         * @param position is his position after rolling the dice
         * @return his new position if applicable
         */
        public int check(String p,int position){
            //if player position is 104, then difference is 4 and player position is set back to 100-4 = 96
            if ( position > 100){
                int difference = position - 100;
                position = 100 - difference;
                System.out.println( p +" has been set back to " + position + " with difference of " + difference);
                                
                for (int i=0; i<10; i++){
                    for ( int k=0;k<10; k++){
                        if(positionBoard[i][k]==position){
                            int row=i;
                            int col=k;
                            position = board[row][col];
                            
                        }
                    }
                }
                
            }
            
            if ( position == 100){
                System.out.println("Game has ended, "+p+" has won !");
                System.exit(0);
            }
            return position;
        }
        /**
         * this method compares the position of both players setting the player's position previsouly on the case to 0 if the new player ends up on the same case
         * @param pNow is the player that just played
         * @param pLast is the player that was previously on the case before rolling the dice
         * @param now is the position of the pNow
         * @param last is the position of pLast
         * @return the position of the player that was previously on the case
         */
        public int sameCase(String pNow, String pLast, int now, int last){
            if(now==last){
                last=0; 
                System.out.println(pLast+" was previously on this case and is now on case 0");
            }
            return last;
        }
        /**
         * method that evaluates the new position of the player by flipping the dice and looping through the array taking into account the snakes
         * and ladders to find its new position.
         * @param p is the player playing 
         * @param position is the position of p
         * @return the new position of p
         */
        public int  move( String p, int position){

                    System.out.println("\n"+p+" is playing...");
                    int start = flipDice();
                    
                    position = position + start; 
                    int positionsl = position; 
                    for ( int i=0; i<10; i++){
                        for ( int k=0;k<10; k++){
                           if(positionBoard[i][k]==position){
                                int row=i;
                                int col=k;
                                position = board[row][col];
                           }
                        }
                    }
                    LadderOrSnake(positionsl, position);
                    return position;
        }
        /**
         * method that will evaluate the position to see if the player has been bitten by a snake or took a ladder
         * @param before is the position before moving
         * @param after is the position after moving
         */
        public void LadderOrSnake(int before, int after ){
            if(before>after){
                System.out.println("You have been bitten by a snake! You are going down !");
            }
            else if(after>before){
                System.out.println("It's a ladder! You are going up.");
            }
        }


        /**
         * method that will decide the first player
         * @param p1 is the player one
         * @param p2 is the player two
         */
        public void order(String p1, String p2){
            System.out.println("Now deciding which player will start playing;");
            int attempts = 0;
            do{
                attempts++;
                position1 = flipDice();
                System.out.println(p1+" got a dice value of " + position1);
                position2 = flipDice();
                System.out.println(p2+" got a dice value of " + position2);
                if ( position1 == position2){
                    System.out.println("A tie was achieved between Player 1 and Player 2. Attempting to break the tie");
                }
                else if (position1>position2){
                    System.out.println("Reached final decision on order of playing: "+p1+" then "+p2+". It took "+ attempts +" attempts before a decision could be made. ");

                }
                else if (position1<position2){
                    System.out.println("Reached final decision on order of playing: "+p2+" then "+p1+". It took "+ attempts +" attempts before a decision could be made. ");
                }
            }while (position1==position2);       
        }
   }