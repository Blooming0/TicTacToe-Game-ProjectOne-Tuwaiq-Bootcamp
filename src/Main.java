import java.util.Random;
import java.util.Scanner;

public class Main {
    //TODO static Scanner maybe Do some problems double check on it after Run the program
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
        //TODO to Accept press 1 to decline press 2.
        String win = board();
    }
    //TODO this board shuold return string that says who win the game with "(point) & name of the winner (Either Computer or player 1 )"
    public static String board(){
        String [][]board = {
                {"1", "2", "3"},
                {"4", "5", "6"},
                {"7", "8", "9"},
        };
        winner = getWinner(board);
        return winner;
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
//        Scanner in = new Scanner(System.in);
        //TODO method that decaide weather the game is still running or Not (win or tie).  gameStatus Method... .

        char turn = 'X';
        while(gameStatus(board)){
            //show the board :
            showBoard(board);
            //TODO while loop check the user validation input + Exception handling mismatch.
            String userPosition ;
            //message only if it's user turn
            if(turn == 'X'){
                System.out.println("Enter a number from the board, make sure that number is shown in the board");
                userPosition = in.nextLine();
            }else{
                userPosition = generateComputerRandomNumberFrom1To9();
            }
            if(validatPosition(turn ,userPosition, board)){
                //TODO update the board here .. and check if the game is End(win tie) or still running by the while loop
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board.length; j++) {
                        if(userPosition.charAt(0) == board[i][j].charAt(0)){
                            //update the board but before updating the board check who turn is now( {user = X} or {computer = O} )
                            if(turn == 'X'){
                                //update board by X char
                                board[i][j]="X";
                                //swap the turn
                                turn = 'O';
                            }else {
                                board[i][j]="O";
                                //swap the turn
                                turn = 'X';
                            }
                        }
                    }
                }

            }

        }
        return winner;

    }
    public static boolean validatPosition(char turn, String position , String [][]board){
        //TODO بالنسبة للكمبيوتر خليه يطلع راندوم نمبر وبعدها سويله بارس لسترنق وقارن ✅
        for (int i = 0; i < board.length;i++) {
            for (int j = 0; j < board.length;j++) {


                    //TODO لازم تضمن ان البوزشن ما يحط لك فيه اكس ولا واي
                    if(board[i][j].equals(position)){
                        //update the board but before updating the board check who turn is now( {user = X} or {computer = O} )
//                        if(turn == 'X'){
//                            //update board by X char
//                            board[i][j]="X";
//                            return true;
//                        }else {
//                            board[i][j]="O";
//                            return true;
//                        }
//                  updating method, you only need to check if the number consider as a valid number or not
                        board[i][j] = String.valueOf(turn);//update statement .
                        return true;
                    }
                    if(turn == 'X'){
                        System.out.println("Invalid move! This cell is already taken. Try again.");
                    }
                    //يعني استمر بالادخال الى ان يكون البوزشن المدخل غير محجوز في البورد والاستمرار يكون بالوايل لووب الموجوده في القيت وينر ميثود
            }
        }
        return false;
    }
    public static String generateComputerRandomNumberFrom1To9() {
        Random rand = new Random();
        int number = rand.nextInt(9) + 1; // 1 to 9
        return String.valueOf(number);    // Convert to String
    }
    public static boolean gameStatus(String [][]board){
        //decide weather the game is End (Win or Tie)
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if(board[i][0].equals("X") && board[i][1].equals("X") && board[i][2].equals("X")){
                    winner = "X";
                    return false;
                }else if(board[0][0].equals("X") && board[1][1].equals("X") && board[2][2].equals("X")){
                    winner = "X";
                    return false;
                }else if(board[0][2].equals("X") && board[1][1].equals("X") && board[2][0].equals("X")){
                    winner = "X";
                    return false;
                }else if(board[i][0].equals("O") && board[i][1].equals("O") && board[i][2].equals("O")){
                    winner = "O";
                    return false;
                }else if(board[0][0].equals("O") && board[1][1].equals("X") && board[2][2].equals("O")){
                    winner = "O";
                    return false;
                }else if(board[0][2].equals("O") && board[1][1].equals("O") && board[2][0].equals("O")){
                    winner = "X";
                    return false;
                }//TODO باقي حالتين اما يكون القيم تعادل او ما انتهى اذا تعادل برجع فولس اذا لسى ما انتهى لازم ارجع ترو وهي الحاله الوحيده اللي ارجع فيها ترو
                else if (board[i][j].matches("[OX]")) {
                    winner = "Tie";
                    return false;
                }
            }
        }
        //this true will return if and only if no tie no win .
        return true;
    }
}