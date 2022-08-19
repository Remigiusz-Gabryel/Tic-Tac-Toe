package com.example.ticktacktoe;

import com.example.ticktacktoe.models.PlayerModel;
import com.example.ticktacktoe.tictactoe.TickTackToeCompBasicAlgorithm;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TicTacToeGame {
    private final String symbolO = "O";
    private final String symbolX = "X";
    private String symbol = symbolX;
    private boolean gameEnded = false;
    private Alert alert;
    private GridPane gridBoard;
    private List<String> gridValues;
    private final List<PlayerModel> players;
    private int numberOfTurns;
    private Random random;
    private String winner;

    public TicTacToeGame(Alert alert) {
        this.alert = alert;
//        this.gridBoard = gridBoard;
        this.gridValues = new ArrayList<>(Arrays.asList("", "", "", "", "", "", "", "", ""));
        this.players = new ArrayList<>();
        PlayerModel playerModel = new PlayerModel();
        this.players.add(playerModel);
        this.random = new Random();

    }

    // Tu poprawić logikę...
    // Czy potrzeba przekazywać przycisk  i symbol jednocześnie  ?
    // symbol zdefiniować tu w klasie

    /**
     * Setting game mode,
     * by default game is in Versus mode
     * (player vs player).
     * <p>
     * Setting it as true, sets player2
     * (O symbol by default) as AI.
     * <p>
     * Note, by default game use basic AI algorithm,
     * you can set your own with setAIAlgorithm(TicTackToeAIAlgorithm algorithm) method.
     *
     * @param isOpponentAI true to set second player as an AI
     */
    public void setGameMode(boolean isOpponentAI) {
        players.get(0).setAI(isOpponentAI);
    }

    public boolean getGameMode() {
        return players.get(0).isAI();
    }

    public void aiMode(Button button) {
        button.setText(symbol);
        setPlayerChoiceInGrid();

        gameEnded = checkForWinner(symbol);
        if (numberOfTurns < 4 && !gameEnded) {
            if (players.stream().anyMatch(playerModel -> playerModel.isAI())) {
//                Random random = new Random();
                boolean isValid;
                do {
                    isValid = setAIChoiceInGrid(aiMove());
                } while (!isValid || !gridValues.stream().anyMatch(v -> v.isEmpty()));

                gameEnded = checkForWinner(symbolO);

                if (gameEnded) {
                    return;
                }
            }
        }
        numberOfTurns++;
    }

    public void versusMode(Button button) {

        button.setText(symbol);
        setPlayerChoiceInGrid();
        numberOfTurns++;
        if (!(numberOfTurns < 4)) {
            gameEnded = checkForWinner(symbol);
        }

        if (symbolX.equals(symbol)) {
            symbol = symbolO;
        } else {
            symbol = symbolX;
        }

    }

    public boolean checkForWinner(String symbol) {
        boolean isWinner = false;
        boolean gameEnded = false;

        for (int i = 0; i <= 6; i++) {
            if (i == 0 || i == 3 || i == 6) {
                isWinner = checkRows(symbol, i);
            }
            if (!isWinner && i == 0 || i == 1 || i == 2) {
                isWinner = checkColumns(symbol, i);
            }
            if (!isWinner && i == 0) {
                isWinner = checkFirstCross(symbol, i);
            }
            if (!isWinner && i == 2) {
                isWinner = checkSecondCross(symbol, i);
            }

            if (isWinner) {
                break;
            }
        }
        if (isWinner) {
            displayWinner(symbol);
            gameEnded = true;
            restartGame();
        } else if (gridValues.stream().allMatch(v -> !v.isEmpty()) && !isWinner) {
            displayTie();
            gameEnded = true;
            restartGame();
        }
        return gameEnded;
    }

    private boolean checkRows(String symbol, int index) {
        return symbol.equals(gridValues.get(index)) && symbol.equals(gridValues.get(index + 1)) && symbol.equals(gridValues.get(index + 2));

    }

    private boolean checkColumns(String symbol, int index) {
        return symbol.equals(gridValues.get(index)) && symbol.equals(gridValues.get(index + 3)) && symbol.equals(gridValues.get(index + 6));
    }

    private boolean checkFirstCross(String symbol, int index) {
        return symbol.equals(gridValues.get(index)) && symbol.equals(gridValues.get(index + 4)) && symbol.equals(gridValues.get(index + 8));
    }


    private boolean checkSecondCross(String symbol, int index) {
        return symbol.equals(gridValues.get(index)) && symbol.equals(gridValues.get(index + 2)) && symbol.equals(gridValues.get(index + 4));
    }


    private void displayWinner(String symbol) {
        winner = symbol;
        alert.setTitle("Koniec gry");
        alert.setHeaderText(String.format("Wygrał %s", symbol));
        alert.showAndWait();
    }

    private void displayTie() {
        winner = "tie";
        alert.setTitle("Koniec gry");
        alert.setHeaderText(String.format("Remis!"));
        alert.showAndWait();
    }

    public void restartGame() {
        for (Node node : gridBoard.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                if (!button.getText().isEmpty()) {
                    button.setText("");
                }
            }
        }
        random = new Random();
        gridValues.replaceAll(s -> s = "");
        numberOfTurns = 0;
    }

    private void setPlayerChoiceInGrid() {
        int index = 0;
        for (Node node : gridBoard.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;

                if (!button.getText().isEmpty() && gridValues.get(index).isEmpty()) {
                    gridValues.set(index, button.getText());
                    break;
                }
                index++;
            }
        }
    }

    private boolean setAIChoiceInGrid(int aiMove) {
        boolean isValidMove = false;
        int index = 0;
        for (Node node : gridBoard.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
//              if (index == 0 || index == 3 || index == 6 ) {
//                aiMove = 0;
//                  TickTackToeAIBasicAlgorithm tickTackToeAIBasicAlgorithm = new TickTackToeAIBasicAlgorithm();
//                  int move = tickTackToeAIBasicAlgorithm.makeMove(gridValues);
//                  button.setText("O");
//                  gridValues.set(move, button.getText());
//                    isValidMove = true;
//                    break;
//              }

                if (button.getText().isEmpty() && gridValues.get(aiMove).isEmpty() && index == aiMove) {
                    button.setText(symbolO);
                    gridValues.set(aiMove, button.getText());
                    isValidMove = true;
                    break;
                }
                index++;
            }
        }
        return isValidMove;
    }

    private int aiMove() {
        TickTackToeCompBasicAlgorithm algorithm = new TickTackToeCompBasicAlgorithm();
        int aiPick = -1;

                aiPick = algorithm.makeMove(gridValues);
//        for (int i = 0; i <= 6; i++) {
//            if (i == 0 || i == 3 || i == 6) {
//            }
//            if (aiPick < 0 && i == 0 || i == 1 || i == 2) {
//
//            }
////            if (!isValidMove && i == 0) {
////
////            }
////            if (!isValidMove && i == 2) {
////
////            }
            if (aiPick >= 0) {
                return aiPick;
            }

            return random.nextInt(0, 9);
    }

    public boolean isGameEnded() {
        return gameEnded;
    }

    public void setGridBoard(GridPane gridBoard) {
        this.gridBoard = gridBoard;
    }

    public String getWinner() throws NullPointerException {
        if (winner != null) {
            return winner;
        } else {
            throw new NullPointerException("Winner not set");
        }
    }

}
