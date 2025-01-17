import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class DemoCollections {

    /** Returns a lower case version of the string with
     * all characters except letters removed. */
    public static String cleanString(String s) {
        return s.toLowerCase().replaceAll("[^a-z]", "");
    }

    /** Gets a list of all words in the file. */
    public static List<String> getWords(String inputFilename) {
        List<String> words = new ArrayList<String>();
        In in = new In(inputFilename);
        while (!in.isEmpty()) {
            String nextWord = cleanString(in.readString());
            words.add(nextWord);
        }
        return words;
    }

    /** Returns the count of the number of unique words in words. */
    public static int countUniqueWords(List<String> words) {
      Set<String> wordSet = new HashSet<String>();
      for (String ithWord : words) {
          wordSet.add(ithWord);
      }
      return wordSet.size();
    }

    /** Returns a map (a.k.a. dictionary) that tracks the count of all specified
     * target words in words. */
//    public static Map<String, Integer> collectWordCount(List<String> words, List<String> targets) {
//        Map<String, Integer> counts = new HashMap<String, Integer>();
//        for (String t : targets) {
//            counts.put(t, 0);
//        }
//        for (String s : words) {
//            if (counts.containsKey(s)) {
//                int oldCount = counts.get(s);
//                counts.put(s, oldCount + 1);
//            }
//        }
//        return counts;
//    }

    public static Map<String, Integer> collectWordCount(List<String> words, List<String> targets) {
        Map<String, Integer> counts = new HashMap<>();
        /* makes a note that we have seen none of the words. */
        for (String t : targets) {
            counts.put(t, 0);
        }
        for (String s : words) {
            if (counts.containsKey(s)) {
                int oldCount = counts.get(s);
                counts.put(s, oldCount + 1);
            }
        }
        return counts;
    }


    public static void main(String[] args) {
        List<String> w = getWords("libraryofBabylon.txt");
        // System.out.println("List: " + w);
        System.out.println("Set: " + countUniqueWords(w));

        List<String> targets = new ArrayList<String>();
        targets.add("romeo");
        targets.add("juliet");
        targets.add("love");
        System.out.println("Map: " + collectWordCount(w, targets));
    }

}