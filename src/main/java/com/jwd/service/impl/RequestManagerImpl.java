package com.jwd.service.impl;

import com.jwd.dao.DictionaryDatabase;
import com.jwd.dao.DictionaryStarterSet;
import com.jwd.entity.PairOfWords;
import com.jwd.service.RequestManager;

import java.util.ArrayList;

public class RequestManagerImpl implements RequestManager {
    private final DictionaryStarterSet startPack = new DictionaryStarterSet();
    private final DictionaryDatabase database = new DictionaryDatabase(startPack.getStartSet());

    @Override
    public void addWordsPair(PairOfWords pair) {
        int index = -1;
        boolean isRewritten = false;
        for (PairOfWords i : database.getVocabulary()) {
            index++;
            if (i.getEnWord().equalsIgnoreCase(pair.getEnWord())) {
                System.out.println("Such a word was already in the dictionary. Its meaning has been rewritten.");
                PairOfWords rewritenPair = new PairOfWords(i.getEnWord(), pair.getRuWord());
                database.setChosenWord(index, rewritenPair);
                isRewritten = true;
                break;
            }
        }
        if (!isRewritten) {
            database.addPair(pair);
        }
    }

    @Override
    public String findEnglishWord(String inputWord) {
        String searchingResult = "";
        for (PairOfWords i : database.getVocabulary()) {
            if (i.getRuWord().equalsIgnoreCase(inputWord)) {
                searchingResult += "[" + i.getEnWord() + "] ";
            }
        }
        return searchingResult;
    }

    @Override
    public String findRussianWord(String inputWord) {
        for (PairOfWords i : database.getVocabulary()) {
            if (i.getEnWord().equalsIgnoreCase(inputWord)) {
                return i.getRuWord();
            }
        }
        return null;
    }

    @Override
    public int showDictionarySize() {
        return database.getSize();
    }

    @Override
    public void printDictionary() {
        database.printVocabulary();
    }

    @Override
    public ArrayList<PairOfWords> selectWordsForQuiz() {
        int min = 0, max = database.getSize() - 1;
        ArrayList<PairOfWords> selectList = new ArrayList<PairOfWords>();
        for (int i = 0; i < 5; i++) {
            int rand = min + (int) (Math.random() * ((max - min) + 1));
            selectList.add(database.getChosenPair(rand));
        }
        return selectList;
    }
}
