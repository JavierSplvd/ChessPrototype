package com.chess;

import java.util.HashMap;
import java.util.Map;

public class Score {

    private Map<String, Integer> scores;

    public Score() {
        scores = new HashMap<String, Integer>();
        scores.put("Pawn", 1);
        scores.put("King", 100);
        scores.put("Queen", 9);
        scores.put("Bishop", 3);
        scores.put("Knight", 3);
        scores.put("Rook", 5);
    }
}
