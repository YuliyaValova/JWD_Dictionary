package com.jwd.entity;

import java.util.Objects;

public class PairOfWords {
    private String enWord;
    private String ruWord;

    public PairOfWords(String enWord, String ruWord) {
        this.enWord = enWord;
        this.ruWord = ruWord;
    }

    public PairOfWords() {
        this.enWord = " ";
        this.ruWord = " ";
    }

    public String getEnWord() {
        return enWord;
    }

    public String getRuWord() {
        return ruWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PairOfWords that = (PairOfWords) o;
        return Objects.equals(enWord, that.enWord) && Objects.equals(ruWord, that.ruWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enWord, ruWord);
    }

    @Override
    public String toString() {
        return "[" + enWord +
                ";" + ruWord + "]";
    }

}
