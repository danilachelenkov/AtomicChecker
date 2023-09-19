package ru.netology;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Checker implements Runnable {
    private final static AtomicInteger threeSymbol = new AtomicInteger(0);
    private final static AtomicInteger fourSymbol = new AtomicInteger(0);
    private final static AtomicInteger fiveSymbol = new AtomicInteger(0);

    private final String[] texts;
    private final CheckType checkType;

    public Checker(String[] texts, CheckType checkType) {
        this.texts = texts;
        this.checkType = checkType;
    }

    public void run() {
        switch (checkType) {
            case POLINDROM:
                calcPolindrome();
                break;
            case SAME:
                calcSame();
                break;
            case SORTED:
                calcSorted();
                break;
        }
    }

    public static int getThreeSymbol() {
        return threeSymbol.get();
    }

    public static int getFourSymbol() {
        return fourSymbol.get();
    }

    public static int getFiveSymbol() {
        return fiveSymbol.get();
    }

    private void calcPolindrome() {
        for (int i = 0; i < texts.length; i++) {
            if (isPolindrome(texts[i]))
                writeAtomicValue(texts[i].length());
        }
    }

    private void calcSame() {
        for (int i = 0; i < texts.length; i++) {
            if (isTheSameSymbol(texts[i]))
                writeAtomicValue(texts[i].length());
        }
    }

    private void calcSorted() {
        for (int i = 0; i < texts.length; i++) {
            if (isSymbolSorted(texts[i]))
                writeAtomicValue(texts[i].length());

        }
    }

    private void writeAtomicValue(int length) {
        switch (length) {
            case 3:
                threeSymbol.addAndGet(1);
            case 4:
                fourSymbol.addAndGet(1);
            case 5:
                fiveSymbol.addAndGet(1);
        }
    }

    private boolean isPolindrome(String stringValue) {
        return
                new StringBuilder(stringValue.toLowerCase())
                        .reverse()
                        .toString()
                        .equals(stringValue.toLowerCase());
    }

    private boolean isTheSameSymbol(String stringValue) {
        if (stringValue.isEmpty() || stringValue == null) return false;

        String regex = String.format("%s+", stringValue.toLowerCase().toCharArray()[0]);

        return stringValue.toLowerCase()
                .matches(regex);
    }

    private boolean isSymbolSorted(String stringValue) {

        List<String> symbolList = new ArrayList<>();
        List<String> sortedList = new ArrayList<>();

        char[] chars = stringValue.toCharArray();

        for (int j = 0; j < stringValue.length(); j++) {
            if (!symbolList.contains(String.valueOf(chars[j]))) {
                symbolList.add(String.valueOf(chars[j]));
            }
        }

        sortedList.addAll(symbolList);

        Collections.sort(sortedList);

        return sortedList.equals(symbolList);
    }
}
