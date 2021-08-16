package com.jwd.dao;

import com.jwd.entity.PairOfWords;

import java.util.ArrayList;

public class DictionaryDatabase {

    private ArrayList<PairOfWords> pairs = new ArrayList<PairOfWords>();

    //public DictionaryDatabase(){}

    public DictionaryDatabase(ArrayList<PairOfWords> list) {
        this.pairs = list;
    }

    public PairOfWords getChosenPair(int choise) {
        return pairs.get(choise);
    }

    public String getChosenEnWord(int choice) {
        return pairs.get(choice).getEnWord();
    }

    public String getChosenRuWord(int choice) {
        return pairs.get(choice).getRuWord();
    }

    public void printVocabulary() {
        int index = 0;
        for (PairOfWords i : pairs) {
            index++;
            System.out.println("[" + index + "] " + i);
        }
    }

    public int getSize() {
        return this.pairs.size();
    }

    public void addPair(PairOfWords pair) {
        this.pairs.add(pair);
    }

    public ArrayList<PairOfWords> getVocabulary() {
        return pairs;
    }

    public void setChosenWord(int index, PairOfWords pair) {
        this.pairs.set(index, pair);
    }
}
