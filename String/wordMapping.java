

import java.util.Scanner;

class  wordMapping{
    public String mapWordWeights(String[] words, int[] weights) {
        StringBuilder ans = new StringBuilder();

        for (String word : words) {
            int sum = 0;

            for (char ch : word.toCharArray()) {
                sum = (sum + weights[ch - 'a']) % 26;
            }

            char mapped = (char) ('a' + (25 - sum));
            ans.append(mapped);
        }

        return ans.toString();
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();  
        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = sc.next();  // Remaining input is the array of integers
        }
        int[] weights = new int[26];
        for (int i = 0; i < 26; i++) {
            weights[i] = sc.nextInt();
        }
        
        // Call user logic function and print the output
        wordMapping obj = new wordMapping();
        String result = obj.mapWordWeights(words, weights);
        System.out.println(result);
    }
      
}
