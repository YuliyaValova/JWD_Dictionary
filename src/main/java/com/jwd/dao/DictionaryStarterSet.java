package com.jwd.dao;

import com.jwd.entity.PairOfWords;

import java.util.ArrayList;

public class DictionaryStarterSet {
    private ArrayList<PairOfWords> startSet = new ArrayList<PairOfWords>();
    private PairOfWords wordPair;

    public DictionaryStarterSet() {
        wordPair = new PairOfWords("Cat", "Кот");
        startSet.add(wordPair);

        wordPair = new PairOfWords("Dog", "Собака");
        startSet.add(wordPair);

        wordPair = new PairOfWords("Hamster", "Хомяк");
        startSet.add(wordPair);

        wordPair = new PairOfWords("Apple", "Яблоко");
        startSet.add(wordPair);

        wordPair = new PairOfWords("Tree", "Дерево");
        startSet.add(wordPair);

        wordPair = new PairOfWords("Table", "Стол");
        startSet.add(wordPair);

        wordPair = new PairOfWords("Chair", "Стул");
        startSet.add(wordPair);

        wordPair = new PairOfWords("Sun", "Солнце");
        startSet.add(wordPair);

        wordPair = new PairOfWords("Wizard", "Волшебник");
        startSet.add(wordPair);

        wordPair = new PairOfWords("Attention", "Внимание");
        startSet.add(wordPair);

        wordPair = new PairOfWords("Salt", "Соль");
        startSet.add(wordPair);

        wordPair = new PairOfWords("Sea", "Море");
        startSet.add(wordPair);

        wordPair = new PairOfWords("Cucumber", "Огурец");
        startSet.add(wordPair);

        wordPair = new PairOfWords("Language", "Язык");
        startSet.add(wordPair);

        wordPair = new PairOfWords("Carrot", "Морковь");
        startSet.add(wordPair);

    }

    public ArrayList<PairOfWords> getStartSet() {
        return startSet;
    }

}
