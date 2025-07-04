import java.util.InputMismatchException;
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
        System.out.println("▶ Instructions   : Enter one of the positions that will shows in the Board Game");
        System.out.println("▶ Goal           : First to align 3 symbols wins!");
        System.out.println("▶ Round          : you can select 1 or 3 Rounds");
        System.out.println("=====================================");
        System.out.println("        Let's Begin the Game!        ");
        System.out.println();

        int round = 0;
        int counter = 0;
        while (!(round == 1 || round == 3)) {
            System.out.println("How many round you wana play?");
            try {
                round = in.nextInt();
                if (round != 1 && round != 3) {
                    System.out.println("⚠ Please enter either 1 or 3 only.");
                }
            } catch (InputMismatchException e) {
                System.out.println("⚠ Invalid input! Please enter a number (1 or 3).");
            } catch (Exception e) {
                System.out.println("⚠ Something went wrong. Please try again.");
            } finally {
                in.nextLine(); // to Clear the invalid input (important!)
            }
        }

        if(round == 3){//
            String[] gameRecord = new String[round];
            int x_Record = 0;
            int o_Record = 0;
            int tie_Record = 0;
            while (counter < round){
                System.out.println("============{Round "+(counter+1)+"}===============");
                String result = board();
                gameRecord[counter] = result;
               if (result.contains("X")) {
                    gameRecord[counter] = "X";
                    System.out.println("🎉 Congratulations! You (X) won this Round{"+(counter+1)+"} ! Keep it up🔥");
               }else if (result.contains("O")) {
                    gameRecord[counter] = "O";
                   System.out.println("💻 The computer (O) wins the this Round{"+(counter+1)+"}! try your Best🤧");
               }else {
                    gameRecord[counter] = "Tie";
                   System.out.println("At this Round{"+(counter+1)+"}🤝 It's a tie between you and the computer! ");

               }
                counter++;

            }
            for (String s : gameRecord) {
                if ("X".equals(s)) {
                    x_Record++;
                } else if ("O".equals(s)) {
                    o_Record++;
                } else if("Tie".equals(s)) {
                    tie_Record++;
                }
            }

            if(x_Record > o_Record){
                showRecordAndWinner("X",x_Record,gameRecord);//send the winner & record of the game to print it .
            } else if (x_Record < o_Record) {
                showRecordAndWinner("O",o_Record,gameRecord);
            }else{
                showRecordAndWinner("Tie",tie_Record,gameRecord);
            }
        }else{
            String result = board();
            System.out.println(result);
        }





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

            showBoard(board);
            String position = "" ;

            if (turn == 'X') {
                System.out.println("Enter a number from the board:");
                position = in.nextLine();
                if (!position.matches("[1-9]")) {
                    System.out.println("⚠ Invalid input! Please enter a number from 1 to 9.");
                    continue;
                }
            }else {
                position = generateComputerRandomNumberFrom1To9();
            }
            if(validatPosition(turn ,position, board)){

                if(turn != 'X'){//char is not a reference variable like string so you can compare like this ,the char is considered as primitive data type
                    System.out.println("Computer (O) selected position " + position);
                }else{
                    System.out.println("Player (X) selected position " + position);
                }
                turn = (turn == 'X')?('O'):('X');
            }
        }
        showBoard(board);
        return winner;
    }
    public static boolean validatPosition(char turn, String position , String [][]board){
        for (int i = 0; i < board.length;i++) {
            for (int j = 0; j < board.length;j++) {
                    if(board[i][j].equals(position)){
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
    public static void showRecordAndWinner(String winner,int numberOfRoundWin , String[] gameRecord){
        System.out.println("\n\n============{ Record of the Rounds }============\n");
        //print the Record of the game :
        for (int i = 0; i < gameRecord.length; i++) {
            System.out.print("at the round {"+(i+1)+"} the Round goes to { "+gameRecord[i]+" }\n");
        }

        System.out.println("============{ OVERALL RESULT }============");
        if (winner.equals("X")) {
            System.out.println("🎉 Congratulations! You (X) won the game!");
            System.out.println("👏 Well played! You won " + numberOfRoundWin + " out of 3 rounds against the computer.");
        } else if (winner.equals("O")) {
            System.out.println("💻 The computer (O) wins the game!");
            System.out.println("Don't worry, better luck next time! The computer won " + numberOfRoundWin + " out of 3 rounds.");
        } else {
            System.out.println("🤝 It's a tie between you and the computer!");
            System.out.println("Each of you won " + numberOfRoundWin + " round(s). Great effort!");
        }

    }
}