package helper;

import java.util.*;
public class SpellCheck {


    public int editDistance(String stringone, String stringtwo) {

        int len1 = stringone.length();
        int len2 = stringtwo.length();

        int[][] matrix = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i++) {
            matrix[i][0] = i;
        }

        for (int j = 0; j <= len2; j++) {
            matrix[0][j] = j;
        }

        for (int i = 0; i < len1; i++) {

            char characterone = stringone.charAt(i);
            for (int j = 0; j < len2; j++) {

                char charactertwo = stringtwo.charAt(j);
                if (characterone == charactertwo) {
                    matrix[i + 1][j + 1] = matrix[i][j];
                }
                else
                {
                    int a = matrix[i][j] + 1;
                    int b = matrix[i][j + 1] + 1;
                    int c = matrix[i + 1][j] + 1;

                    int min = a > b ? b : a;
                    min = c > min ? min : c;
                    matrix[i + 1][j + 1] = min;
                }
            }
        }
        return matrix[len1][len2];
    }

    public List<String> sortpairs(String input, List<String> words) {
        //use priority queue to sort key value pair for

        List<String> stringList = new ArrayList<>();

        PriorityQueue<StringIntPair> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(StringIntPair::getValue));
        int threshold = Math.min(5, input.length());;
        for (String word : words) {

            int editDist = editDistance(input, word);
            if (editDist <= threshold) { // Check if the edit distance is less than or equal to the threshold
                priorityQueue.add(new StringIntPair(word, editDist));
              //  System.out.println(word +" - "+editDist);
            }
        }

//        threshold set to top three values

        int n = 3;
        while (!priorityQueue.isEmpty() && n >0 ) {

            StringIntPair pair = priorityQueue.poll();
            stringList.add(pair.key);

            n -= 1;
        }

        if (!stringList.isEmpty() && stringList.get(0).equals(input)) {
            return Collections.emptyList();
        } else if (stringList.isEmpty()) {
            return Collections.emptyList();
        }

        return stringList;

    }
    class StringIntPair {
        String key;
        int value;
        public StringIntPair(String key, int value) {
            this.key = key;
            this.value = value;

        }
        public int getValue()
        {
            return this.value;
        }
    }
}

