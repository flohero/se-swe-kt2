package at.fhooe;

import java.util.List;
import java.util.Random;

public class Threading {

    private static class Words {
        List<String> nouns = List.of("house", "flower", "sun");
        List<String> adjectives = List.of("pretty", "Floridian", "wonderful", "supercalifragilisticexpialidocious", "bijektiv", "serif");
        Random rng = new Random();

        public String randomWordCombinationBoilerplate() {
            synchronized (this) {
                String noun = nouns.get(rng.nextInt(nouns.size()));
                String adjective = adjectives.get(rng.nextInt(adjectives.size()));
                return adjective + " " + noun;
            }
        }

        public synchronized String randomWordCombination() {
            String noun = nouns.get(rng.nextInt(nouns.size()));
            String adjective = adjectives.get(rng.nextInt(adjectives.size()));
            return adjective + " " + noun;
        }
    }

    private static class DumbThread extends Thread {
        private final Words words;
        private final int number;

        public DumbThread(int number ,Words words) {
            this.number = number;
            this.words = words;
        }

        @Override
        public void run() {
            System.out.println(number + ": " + words.randomWordCombination());
        }
    }


    public static void main(String[] args) {
        Words words = new Words();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            final Thread outerThread = new Thread(() -> {

                Thread thread = new DumbThread(finalI, words);
                thread.start(); // asynchron
//                try {
//                    thread.join(); // synchronisiert
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            });
            outerThread.start();
//            try {
//                outerThread.join(); // synchronisiert
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

}
