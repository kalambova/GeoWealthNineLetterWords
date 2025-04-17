import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static Set<String> allEnglishWordsDictionary = new HashSet<>();
    private static Map<Integer, Set<String>> wordsByLength = new HashMap<>();
    private static Map<String, Boolean> memo = new HashMap<>();


    private static void groupWordsByLength() {
        for (String word : allEnglishWordsDictionary) {
            int len = word.length();
            wordsByLength
                    .computeIfAbsent(len, k -> new HashSet<>())
                    .add(word);
        }
    }

    private static Set<String> loadAllWordsSet() throws IOException {

        URL wordsURL = new URL("https://raw.githubusercontent.com/nikiiv/JavaCodingTestOne/master/scrabble-words.txt");
        try (BufferedReader br = new BufferedReader(new
                InputStreamReader(wordsURL.openConnection().getInputStream()))) {
            return br.lines().skip(2).collect(Collectors.toSet());

        }
    }

    private static boolean canReduce(String word) {
        if (memo.containsKey(word)) {
            return memo.get(word);
        }

        if (word.length() == 1) {
            boolean isBase = word.equals("A") || word.equals("I");
            memo.put(word, isBase);
            return isBase;
        }


        int nextLen = word.length() - 1;
        Set<String> shorterWords = wordsByLength.getOrDefault(nextLen, Set.of());

        for (int i = 0; i < word.length(); i++) {
            String reduced = word.substring(0, i) + word.substring(i + 1);
            if (shorterWords.contains(reduced) && canReduce(reduced)) {
                memo.put(word, true);
                return true;
            }
        }
        memo.put(word, false);
        return false;
    }

    public static void main(String[] args) throws IOException {

        allEnglishWordsDictionary = loadAllWordsSet();
        allEnglishWordsDictionary.add("A");
        allEnglishWordsDictionary.add("I");

        groupWordsByLength();
        
        Set<String> allNineLettersWords = allEnglishWordsDictionary.stream()
                .filter(s -> s.length() == 9)
                .filter(s -> s.contains("A") || s.contains("I"))
                .collect(Collectors.toSet());

        final Set<String> results = new HashSet<>();

        long start = System.currentTimeMillis();

        for (String word : allNineLettersWords) {
            if (canReduce(word)) {
                results.add(word);
            }
        }

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.printf("Time elapsed: %d milliseconds\n", timeElapsed); // 412 milliseconds

        System.out.printf("Count of all reducible 9 letter words: %d\n", results.size()); // 775
        System.out.printf("All reducible 9 letter words: %s\n", results);

    }

}
