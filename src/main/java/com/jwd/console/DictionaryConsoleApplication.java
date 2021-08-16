package com.jwd.console;


import com.jwd.exceptions.EmptyDictionaryException;
import com.jwd.exceptions.InvalidInputException;
import com.jwd.entity.PairOfWords;
import com.jwd.service.RequestManager;
import com.jwd.service.impl.RequestManagerImpl;
import com.jwd.validator.InputProcessor;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryConsoleApplication {

    public DictionaryConsoleApplication() {
        this.closeables = new Closeable[]{scanner};
    }

    public static final String TEXT_MENU = " Press:" +
            "\n 0 - to Exit" +
            "\n 1 - to Add a pair EN-RU words" +
            "\n 2 - to Find a translation of a RU word" +
            "\n 3 - to Find a translation of an EN word" +
            "\n 4 - to Show the number of words in the dictionary" +
            "\n 5 - to List all pairs of words from a dictionary" +
            "\n 6 - to Play a quiz";

    public static final String DELIMITER = "_____________________";
    public static final int INITIAL_VALUE = -1, EXIT = 0, ADD = 1, FIND_EN = 2, FIND_RU = 3, SHOW_NUMBER = 4, LIST_DICTIONARY = 5, QUIZ = 6;


    private final Scanner scanner = new Scanner(System.in);
    private final RequestManager manager = new RequestManagerImpl();
    private final InputProcessor validator = new InputProcessor();
    private final Closeable[] closeables;


    public void start() throws IOException {
        processMenu();
        cleanUpCloseables();
    }

    private void processMenu() throws IOException {
        boolean isContinue = true, correctInput = false;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int usersChoice = INITIAL_VALUE;
        while (isContinue) {
            printConsole(TEXT_MENU);
            printConsole(DELIMITER);
            try {
                usersChoice = Integer.parseInt(reader.readLine());
                correctInput = true;
            } catch (Exception e) {
                printConsole("Invalid input. You can enter only numbers.\n" + DELIMITER);
                correctInput = false;
            }
            if (correctInput) {
                isContinue = executeUsersChoice(isContinue, usersChoice);
            }

        }

    }

    private boolean executeUsersChoice(boolean isContinue, int usersChoice) {
        switch (usersChoice) {
            case EXIT:
                isContinue = false;
                printConsole("The program is closed." + "\n" + DELIMITER);
                break;
            case ADD:
                add();
                break;
            case FIND_EN:
                findEn();
                break;
            case FIND_RU:
                findRu();
                break;
            case SHOW_NUMBER:
                showNumber();
                break;
            case LIST_DICTIONARY:
                listDictionary();
                break;
            case QUIZ:
                quiz();
                break;
            default:
                printConsole("Unknown function. Try again." + "\n" + DELIMITER);
        }
        return isContinue;
    }


    private void quiz() {
        printConsole("Enter consecutively the translation of the specified words:");
        ArrayList<PairOfWords> words = manager.selectWordsForQuiz();
        for (int i = 0; i < 5; i++) {
            System.out.print((words.get(i).getEnWord() + " "));
        }
        printConsole("");
        ArrayList<String> input = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            input.add(scanner.next());
            cleanScannerBuffer(scanner);
        }
        String result = checkingAnswers(words, input);
        printConsole(result + "\n" + DELIMITER);

    }

    private String checkingAnswers(ArrayList<PairOfWords> words, ArrayList<String> input) {
        String checkResult = "", resultMessage = "";
        int rightAnswers = 0;
        for (int i = 0; i < 5; i++) {
            if (words.get(i).getRuWord().equalsIgnoreCase(input.get(i))) {
                rightAnswers++;
            } else {
                checkResult += words.get(i).getEnWord() + "[" + words.get(i).getRuWord() + "].\n";
            }
        }
        double persentRightAnsw = (double) rightAnswers / 5 * 100;
        if (rightAnswers == 5) {
            resultMessage = "Great! All answers are correct.\n";
        } else {
            resultMessage = "You have not guessed the following words:\n";
        }
        resultMessage += checkResult + "You solved the quiz correctly on " + persentRightAnsw + "% [" + rightAnswers + "/5]";
        return resultMessage;
    }


    private void findRu() {
        try {
            String searchingWord = inputEnWord();
            validator.isCorrect(searchingWord);
            String translation = manager.findRussianWord(searchingWord);
            if (translation == null) {
                printConsole("There is no such word in the dictionary." + "\n" + DELIMITER);
            } else {
                printConsole(translation + "\n" + DELIMITER);
            }
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    private String inputEnWord() {
        printConsole("Enter the word you want to translate into Russian");
        return scanner.nextLine();
    }

    private void findEn() {
        try {
            String searchingWord = inputRuWord();
            validator.isCorrect(searchingWord);
            String translation = manager.findEnglishWord(searchingWord);
            if (translation == "") {
                printConsole("There is no such word in the dictionary." + "\n" + DELIMITER);
            } else {
                printConsole(translation + "\n" + DELIMITER);
            }
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    private String inputRuWord() {
        printConsole("Enter the word you want to translate into English");
        return scanner.nextLine();
    }

    private void showNumber() {
        printConsole("You have " + manager.showDictionarySize() + " pairs of words in the dictionary" + "\n" + DELIMITER);
    }

    private void listDictionary() {
        try {
            validator.isEmpty(this.manager);
            manager.printDictionary();
            System.out.println(DELIMITER);
        } catch (EmptyDictionaryException e) {
            System.out.println(e.getMessage());
        }
    }

    private void add() {
        try {
            printConsole("Input EN word:");
            String enWord = scanner.next();
            cleanScannerBuffer(scanner);
            printConsole("Input RU word:");
            String ruWord = scanner.next();
            cleanScannerBuffer(scanner);

            PairOfWords newPair = validator.inputPair(enWord, ruWord);
            manager.addWordsPair(newPair);
            printConsole(DELIMITER);
        } catch (InvalidInputException e) {
            printConsole(e.getMessage());
        }
    }

    private void cleanScannerBuffer(Scanner scanner) {
        String next = scanner.nextLine();
    }

    private void printConsole(String message) {
        System.out.println(message);
    }

    private void cleanUpCloseables() {
        for (final Closeable closeable : closeables) {
            try {
                closeable.close();
                System.out.println("Resource is closed, " + closeable.getClass());

            } catch (final IOException e) {
                System.out.println("Something went wrong during closing " + closeable.getClass());
                e.printStackTrace();
            }
        }
    }
}
