package ru.netology;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Application {
    public void run() throws InterruptedException {

        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = Generator.generateText("abc", 3 + random.nextInt(3));
        }

        List<Thread> threadList = List.of(
                new Thread(new Checker(texts, CheckType.POLINDROM)),
                new Thread(new Checker(texts, CheckType.SAME)),
                new Thread(new Checker(texts, CheckType.SAME))
        );

        for (Thread thread : threadList) {
            thread.start();
            thread.join();
        }

        System.out.println(String.format("Красивых слов с длиной 3: %s", Checker.getThreeSymbol()));
        System.out.println(String.format("Красивых слов с длиной 4: %s", Checker.getFourSymbol()));
        System.out.println(String.format("Красивых слов с длиной 5: %s", Checker.getFiveSymbol()));
    }
}
