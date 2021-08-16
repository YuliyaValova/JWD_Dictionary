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

        if (enWord != null ? !enWord.equals(that.enWord) : that.enWord != null) return false;
        return ruWord != null ? ruWord.equals(that.ruWord) : that.ruWord == null;
    }

    @Override
    public int hashCode() {
        int result = enWord != null ? enWord.hashCode() : 0;
        result = 31 * result + (ruWord != null ? ruWord.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "[" + enWord +
                ";" + ruWord + "]";
    }

}
