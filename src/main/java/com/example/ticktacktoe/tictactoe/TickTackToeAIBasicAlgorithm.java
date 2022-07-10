package com.example.ticktacktoe.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class TickTackToeAIBasicAlgorithm implements TicTackToeAIAlgorithm{
    private List<String> gameBoard;
    private final String SYMBOL_O = "O";
    private final String SYMBOL_X = "X";

    @Override
    public int makeMove(List<String> gameBoard) {
        this.gameBoard = gameBoard;
        int aiPick = -1;

        for (int i = 0; i <= 6; i++) {

            if (i == 0 || i == 3 || i == 6) {
//                aiPick = checkRows(i);
                aiPick = blockOrFinishGameIfTwoElementsInLineHaveTheSameSymbol(i,i + 1,i + 2);
            }
            if (aiPick < 0 && i == 0 || i == 1 || i == 2) {
//                aiPick = checkColumns(i);
                aiPick = blockOrFinishGameIfTwoElementsInLineHaveTheSameSymbol(i,i + 3,i + 6);
            }
            if (aiPick < 0 && i == 0) {
//                aiPick = checkFirstCross(i);
                aiPick = blockOrFinishGameIfTwoElementsInLineHaveTheSameSymbol(i,i + 4,i + 8);
            }
            if (aiPick < 0 && i == 2) {
//                aiPick = checkSecondCross(i);
                aiPick = blockOrFinishGameIfTwoElementsInLineHaveTheSameSymbol(i,i + 2,i + 4);
            }

            if (aiPick >= 0){
                break;
            }
        }
        if (aiPick < 0 && gameBoard.stream().filter(s -> !s.isEmpty()).count() < 2 && (gameBoard.get(0).equals("X") || gameBoard.get(2).equals("X") || gameBoard.get(6).equals("X") || gameBoard.get(8).equals("X"))){
            aiPick = 4;
        }
        return aiPick;
    }
    private int checkRows(int index) {

        List<String> row = gameBoard.subList(index,index+3);

        if (row.stream().filter(number -> number.isEmpty()).count() == 1 && !row.contains(SYMBOL_O)){
            return index + row.lastIndexOf("");
        }
        return -1;
    }

    private int checkColumns(int index) {
        List<String> column = new ArrayList<>();
                column.add(gameBoard.get(index));
                column.add(gameBoard.get(index + 3));
                column.add(gameBoard.get(index + 6));


        if (column.stream().filter(number -> number.isEmpty()).count() == 1 && !column.contains(SYMBOL_O)){
            if (column.lastIndexOf("") == 0){
                return  index;
            } else if (column.lastIndexOf("") == 1){
                return index + 3;
            } else if (column.lastIndexOf("") == 2){
                return index + 6;
            }
        }
        return -1;
    }

    private int checkSecondCross(int index) {
        List<String> firstCross = new ArrayList<>();
        firstCross.add(gameBoard.get(index));
        firstCross.add(gameBoard.get(index + 2));
        firstCross.add(gameBoard.get(index + 4));


        if (firstCross.stream().filter(number -> number.isEmpty()).count() == 1 && !firstCross.contains(SYMBOL_O)){
            if (firstCross.lastIndexOf("") == 0){
                return  index;
            } else if (firstCross.lastIndexOf("") == 1){
                return index + 2;
            } else if (firstCross.lastIndexOf("") == 2){
                return index + 4;
            }
        }
        return -1;
    }


    private int checkFirstCross(int index) {
        List<String> firstCross = new ArrayList<>();
        firstCross.add(gameBoard.get(index));
        firstCross.add(gameBoard.get(index + 4));
        firstCross.add(gameBoard.get(index + 8));


        if (firstCross.stream().filter(number -> number.isEmpty()).count() == 1 && !firstCross.contains(SYMBOL_O)){
            if (firstCross.lastIndexOf("") == 0){
                return  index;
            } else if (firstCross.lastIndexOf("") == 1){
                return index + 4;
            } else if (firstCross.lastIndexOf("") == 2){
                return index + 8;
            }
        }
        return -1;
    }

    private int blockOrFinishGameIfTwoElementsInLineHaveTheSameSymbol(int firstElementIndex, int secondElementIndex, int thirdElementIndex){
        List<String> elementsInLine = new ArrayList<>();
        elementsInLine.add(gameBoard.get(firstElementIndex));
        elementsInLine.add(gameBoard.get(secondElementIndex));
        elementsInLine.add(gameBoard.get(thirdElementIndex));


        if (elementsInLine.stream().filter(number -> number.isEmpty()).count() == 1 && (!elementsInLine.contains(SYMBOL_X) || !elementsInLine.contains(SYMBOL_O))){
            if (elementsInLine.lastIndexOf("") == 0){
                return  firstElementIndex;
            } else if (elementsInLine.lastIndexOf("") == 1){
                return secondElementIndex;
            } else if (elementsInLine.lastIndexOf("") == 2){
                return thirdElementIndex;
            }
        }
        return -1;
    }
}
