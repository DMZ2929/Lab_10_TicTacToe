import java.util.Scanner;

public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String[][] board = new String[ROWS][COLS];
    private static String currentPlayer = "X";
    private static int moveCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        System.out.println("Welcome to Tic Tac Toe!");

        while (playAgain) {
            clearBoard();
            currentPlayer = "X";
            moveCount = 0;
            boolean gameOver = false;

            while (!gameOver) {
                display();

                System.out.println("Player " + currentPlayer + "'s turn");
                int row = SafeInput.getRangedInt(scanner, "Enter row (1-3)", 1, 3) - 1;
                int col = SafeInput.getRangedInt(scanner, "Enter column (1-3)", 1, 3) - 1;

                while (!isValidMove(row, col)) {
                    System.out.println("That position is already taken!");
                    row = SafeInput.getRangedInt(scanner, "Enter row (1-3)", 1, 3) - 1;
                    col = SafeInput.getRangedInt(scanner, "Enter column (1-3)", 1, 3) - 1;
                }

                board[row][col] = currentPlayer;
                moveCount++;

                if (isWin(currentPlayer)) {
                    display();
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameOver = true;
                } else if (moveCount == ROWS * COLS) {
                    display();
                    System.out.println("The game is a tie!");
                    gameOver = true;
                } else {
                    currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
                }
            }

            playAgain = SafeInput.getYNConfirm(scanner, "Would you like to play again? (Y/N)");
        }

        System.out.println("Thanks for playing Tic Tac Toe!");
    }

    private static void clearBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = " ";
            }
        }
    }

    private static void display() {
        System.out.println();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(" " + board[i][j] + " ");
                if (j < COLS - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < ROWS - 1) {
                System.out.println("-----------");
            }
        }
        System.out.println();
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int i = 0; i < ROWS; i++) {
            if (board[i][0].equals(player) &&
                    board[i][1].equals(player) &&
                    board[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int j = 0; j < COLS; j++) {
            if (board[0][j].equals(player) &&
                    board[1][j].equals(player) &&
                    board[2][j].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        if (board[0][0].equals(player) &&
                board[1][1].equals(player) &&
                board[2][2].equals(player)) {
            return true;
        }
        if (board[0][2].equals(player) &&
                board[1][1].equals(player) &&
                board[2][0].equals(player)) {
            return true;
        }
        return false;
    }
}