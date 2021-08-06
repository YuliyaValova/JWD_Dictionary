package com.jwd.service;

import com.jwd.entity.PairOfWords;

import java.util.ArrayList;

public interface RequestManager {

    void addWordsPair(PairOfWords pair);
    String findEnglishWord(String input);
    String findRussianWord(String input);
    int showDictionarySize();
    void printDictionary();
    ArrayList<PairOfWords> selectWordsForQuiz();
}
