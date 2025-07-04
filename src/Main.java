import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in); // Shared scanner for user input
    static String winner = ""; // Global variable to hold the winner of the game

    public static void main(String[] args) {
        welcoming(); // Show welcome message
        System.out.println();
        start(); // Start the game logic
    }

    public static void welcoming() {
        // Welcoming message printed at the beginning
        System.out.println("=====================================");
        System.out.println("  Welcome to the Tic Tac Toe Game!  ");
        System.out.println("=====================================");
        System.out.println("Get ready to challenge the Computer!");
        System.out.println("Let the battle begin!");
        System.out.println();
    }

    public static void start() {
        // Show dashboard and game details
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

        int round = 0;     // Number of rounds user wants to play
        int counter = 0;   // To count how many rounds have been played

        // Loop until valid input for round count is received (only 1 or 3)
        while (!(round == 1 || round == 3)) {
            System.out.println("How many round you wana play?");
            try {
                round = in.nextInt(); // Try to get input as integer
                if (round != 1 && round != 3) {
                    System.out.println("⚠ Please enter either 1 or 3 only.");
                }
            } catch (InputMismatchException e) {
                // Catch invalid input (non-integer)
                System.out.println("⚠ Invalid input! Please enter a number (1 or 3).");
            } catch (Exception e) {
                // General exception handler
                System.out.println("⚠ Something went wrong. Please try again.");
            } finally {
                in.nextLine(); // Always clear input buffer to avoid infinite loop
            }
        }

        if (round == 3) {
            // If player chose to play 3 rounds
            String[] gameRecord = new String[round]; // Store result of each round
            int x_Record = 0;
            int o_Record = 0;
            int tie_Record = 0;

            while (counter < round) {
                System.out.println("============{Round " + (counter + 1) + "}===============");
                String result = board(); // Play one full round
                gameRecord[counter] = result; // Save result of the round

                // Update readable result format (X, O, or Tie)
                if (result.contains("X")) {
                    gameRecord[counter] = "X";
                    System.out.println("🎉 Congratulations! You (X) won this Round{" + (counter + 1) + "} ! Keep it up🔥");
                } else if (result.contains("O")) {
                    gameRecord[counter] = "O";
                    System.out.println("💻 The computer (O) wins the this Round{" + (counter + 1) + "}! try your Best🤧");
                } else {
                    gameRecord[counter] = "Tie";
                    System.out.println("At this Round{" + (counter + 1) + "}🤝 It's a tie between you and the computer! ");
                }

                counter++; // Move to the next round
            }

            // Count wins for each player
            for (String s : gameRecord) {
                if ("X".equals(s)) {
                    x_Record++;
                } else if ("O".equals(s)) {
                    o_Record++;
                } else if ("Tie".equals(s)) {
                    tie_Record++;
                }
            }

            // Decide overall winner and show results
            if (x_Record > o_Record) {
                showRecordAndWinner("X", x_Record, gameRecord);
            } else if (x_Record < o_Record) {
                showRecordAndWinner("O", o_Record, gameRecord);
            } else {
                showRecordAndWinner("Tie", tie_Record, gameRecord);
            }
        } else {
            // If player chose 1 round only
            String result = board();
            System.out.println(result);
        }
    }

    public static String board() {
        // Initialize game board
        String[][] board = {
                {"1", "2", "3"},
                {"4", "5", "6"},
                {"7", "8", "9"},
        };

        winner = getWinner(board); // Start the game and get the winner
        // Return a friendly message
        return winner.equals("Tie") ? ("Game ended in Tie") : "\n{" + winner + "} wins the game!";
    }

    public static void showBoard(String[][] board) {
        // Print the current game board nicely
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

    public static String getWinner(String[][] board) {
        char turn = 'X'; // Start with player
        while (gameStatus(board)) {
            showBoard(board); // Show current state of the board
            String position = "";

            if (turn == 'X') {
                // Get input from user
                System.out.println("Enter a number from the board:");
                position = in.nextLine();

                // Validate input format
                if (!position.matches("[1-9]")) {
                    System.out.println("⚠ Invalid input! Please enter a number from 1 to 9.");
                    continue;
                }
            } else {
                // Let computer pick a position
                position = generateComputerRandomNumberFrom1To9();
            }

            // Update board if position is valid
            if (validatPosition(turn, position, board)) {
                if (turn != 'X') {
                    System.out.println("Computer (O) selected position " + position);
                } else {
                    System.out.println("Player (X) selected position " + position);
                }

                // Switch turn
                turn = (turn == 'X') ? 'O' : 'X';
            }
        }

        showBoard(board); // Final state of the board
        return winner; // Return who won
    }

    public static boolean validatPosition(char turn, String position, String[][] board) {
        // Check if the chosen position is available and update it
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].equals(position)) {
                    board[i][j] = String.valueOf(turn); // Update cell with X or O
                    return true;
                }
            }
        }

        // If user entered an already taken position
        if (turn == 'X') {
            System.out.println("Invalid move! choose from the board number please >> Try again.");
        }
        return false;
    }

    public static String generateComputerRandomNumberFrom1To9() {
        // Randomly generate a number from 1 to 9 (inclusive)
        Random rand = new Random();
        return String.valueOf(rand.nextInt(9) + 1);
    }

    public static boolean gameStatus(String[][] board) {
        // Check if someone has won by row, column or diagonal
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])) {
                winner = board[i][0];
                return false;
            }
            if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i])) {
                winner = board[0][i];
                return false;
            }
        }

        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
            winner = board[0][0];
            return false;
        }
        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) {
            winner = board[0][2];
            return false;
        }

        // If no winner and board is full, it's a tie
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!board[i][j].equals("X") && !board[i][j].equals("O")) {
                    return true; // Game still ongoing
                }
            }
        }

        winner = "Tie";
        return false;
    }

    public static void showRecordAndWinner(String winner, int numberOfRoundWin, String[] gameRecord) {
        // Show round-by-round results
        System.out.println("\n\n============{ Record of the Rounds }============\n");
        for (int i = 0; i < gameRecord.length; i++) {
            System.out.print("at the round {" + (i + 1) + "} the Round goes to { " + gameRecord[i] + " }\n");
        }

        // Show overall result message
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