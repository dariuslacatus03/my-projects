import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final Map<String, Integer> wordCountMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        String filePath = "src/random_phrases_combined_1_million_words.txt";
        int n = 6;
        String[] words = readFile(filePath);
        if (words == null) {
            System.out.println("Error reading the file.");
            return;
        }
        ExecutorService executor = Executors.newFixedThreadPool(n);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            int threadIndex = i;
            executor.submit(() -> {
                for (int j = threadIndex; j < words.length; j += n) {
                    String word = words[j];
                    wordCountMap.merge(word, 1, Integer::sum);
                }
            });
        }
        executor.shutdown();
        while (!executor.isTerminated()) {}
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Time taken to count words: " + totalTime + " milliseconds");

        System.out.println("\n\nAll word counts: \n");
        printWordCounts();
    }

    private static String[] readFile(String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            return content.split("\\s+");
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return null;
        }
    }

    private static void printWordCounts() {
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
