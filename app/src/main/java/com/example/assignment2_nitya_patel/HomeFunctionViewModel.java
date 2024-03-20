package com.example.assignment2_nitya_patel;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFunctionViewModel extends ViewModel {

    private final List<int[]> winCombinations = new ArrayList<>();
    private final int[] boxPositions = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int currentPlayer = 1;
    private int totalSelectedBoxes = 1;

    public HomeFunctionViewModel() {
        winCombinations.add(new int[]{0, 1, 2});
        winCombinations.add(new int[]{3, 4, 5});
        winCombinations.add(new int[]{6, 7, 8});
        winCombinations.add(new int[]{0, 3, 6});
        winCombinations.add(new int[]{1, 4, 7});
        winCombinations.add(new int[]{2, 5, 8});
        winCombinations.add(new int[]{0, 4, 8});
        winCombinations.add(new int[]{2, 4, 6});
    }

    public int[] getBoxPositions() {
        return boxPositions;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int getTotalSelectedBoxes() {
        return totalSelectedBoxes;
    }

    public void incrementTotalSelectedBoxes() {
        totalSelectedBoxes++;
    }

    public boolean isBoxSelectable(int boxPosition) {
        return boxPositions[boxPosition] == 0;
    }

    public boolean checkWin() {
        for (int[] combination : winCombinations) {
            if (boxPositions[combination[0]] == currentPlayer &&
                    boxPositions[combination[1]] == currentPlayer &&
                    boxPositions[combination[2]] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    public void resetGame() {
        for (int i = 0; i < boxPositions.length; i++) {
            boxPositions[i] = 0;
        }
        currentPlayer = 1;
        totalSelectedBoxes = 1;
    }
}
