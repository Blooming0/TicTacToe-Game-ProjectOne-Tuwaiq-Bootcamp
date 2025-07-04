import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);
    static String winner = "";
    public static void main(String[] args) {
        welcoming ();
        System.out.println();
        start();
    }
    public static void welcoming (){
        System.out.println("=====================================");
        System.out.println("  Welcome to the Tic Tac Toe Game!  ");
        System.out.println("=====================================");
        System.out.println("Get ready to challenge the Computer!");
        System.out.println("Let the battle begin!");
        System.out.println();
    }
    public static void start(){

        System.out.println("=====================================");
        System.out.println("         TIC TAC TOE DASHBOARD       ");
        System.out.println("=====================================");
        System.out.println("▶ Game Mode      : Player vs Computer");
        System.out.println("▶ Board Size     : 3 x 3");
        System.out.println("▶ Player Symbols : X (Player 1), O (Computer)");
        System.out.println("▶ Instructions   : Enter one of the positions that shows in the Board below");
        System.out.println("▶ Goal           : First to align 3 symbols wins!");
        System.out.println("=====================================");
        System.out.println("        Let's Begin the Game!        ");
        System.out.println();
        String result = board();
        System.out.println(result);
    }
    public static String board(){
        String [][]board = {
                {"1", "2", "3"},
                {"4", "5", "6"},
                {"7", "8", "9"},
        };
        winner = getWinner(board);
        return winner.equals("Tie") ? ("Game ended in Tie") : "\n{"+winner +"} wins the game!";
    }
    public static void showBoard(String [][]board){
        //show the board :
        System.out.println("=====================================");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(" " + (board[i][j]) + " ");
                if (j < board[i].length - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < board.length - 1) {
                System.out.println("---+---+---");
            }
        }
        System.out.println("=====================================");
    }

    public static String getWinner(String [][]board){
        char turn = 'X';
        while(gameStatus(board)){
            //show the board :
            showBoard(board);
            String position ;
            //message only if it's user turn
            if(turn == 'X'){
                System.out.println("Enter a number from the board");//I will check if the number is valid or no .
                position = in.nextLine();
            }else{
                position = generateComputerRandomNumberFrom1To9();
            }
            if(validatPosition(turn ,position, board)){
                //swapping the turn using ternary operator.
                if(turn != 'X'){
                    System.out.println("Computer(O) Selection board");
                }
                turn = (turn == 'X')?('O'):('X');
            }
        }//while loop will break if and only if the game (END or Tie).
        showBoard(board);
        return winner;
    }
    public static boolean validatPosition(char turn, String position , String [][]board){
        for (int i = 0; i < board.length;i++) {
            for (int j = 0; j < board.length;j++) {
                    if(board[i][j].equals(position)){
//                  updating method, you only need to check if the number consider as a valid number or not
                        board[i][j] = String.valueOf(turn);// then update statement .
                        return true;//this will end the method
                    }
            }
        }
                    if(turn == 'X'){// if the user enter invalid position, this message will show up. and if the Computer generate invalid number this message will never show up .
                        System.out.println("Invalid move! choose from the board number please >> Try again.");
                    }
                    //يعني استمر بالادخال الى ان يكون البوزشن المدخل غير محجوز في البورد والاستمرار يكون بالوايل لووب الموجوده في القيت وينر ميثود
        return false;
    }
    public static String generateComputerRandomNumberFrom1To9() {
        Random rand = new Random();
        return String.valueOf(rand.nextInt(9) + 1);    //generate number from 1 to 9 then Convert to String
    }
    public static boolean gameStatus(String [][]board){
        //decide weather the game is End (Win or Tie)
        for (int i = 0; i < 3; i++) {
            // Check rows
            if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])) {
                winner = board[i][0];
                return false;
            }
            // Check columns
            if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i])) {
                winner = board[0][i];
                return false;
            }
        }
        // Check diagonals
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
            winner = board[0][0];
            return false;
        }
        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) {
            winner = board[0][2];
            return false;
        }
        // Check for tie (no numbers left)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!board[i][j].equals("X") && !board[i][j].equals("O")) {
                    return true; // Game still running
                }
            }
        }
        winner = "Tie";
        return false;
    }
}