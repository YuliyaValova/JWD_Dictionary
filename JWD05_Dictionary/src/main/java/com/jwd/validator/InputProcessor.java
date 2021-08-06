package com.jwd.validator;

import com.jwd.Exceptions.EmptyDictionaryException;
import com.jwd.Exceptions.InvalidInputException;
import com.jwd.entity.PairOfWords;
import com.jwd.service.RequestManager;


public class InputProcessor {
    public PairOfWords inputPair(String en, String ru) throws InvalidInputException {
      if (en!=null&&ru!=null) {
          return new PairOfWords(en,ru);
      } else {
          throw new InvalidInputException("InvalidInputException: Check that you are entering the correct words.");
      }
    }

    public void isEmpty(RequestManager manager) throws EmptyDictionaryException {
        if (manager.showDictionarySize()==0){
            throw new EmptyDictionaryException("EmptyDictionaryException: Your dictionary is empty.");
        }
    }

    public void isCorrect(String searchingWord) throws InvalidInputException {
        if(searchingWord==null) {
            throw new InvalidInputException("InvalidInputException: Check that you are entering the correct word.");
        }
    }
}
