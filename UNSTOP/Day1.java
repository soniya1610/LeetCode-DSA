package UNSTOP;

import java.util.*;

public class Day1{
    public static int goodSum(int n , int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int x : arr) {

            // Positive or zero
            if (x >= 0) {
                list.add(x);
            }

            // Negative
            else {
                int need = Math.abs(x);
                int sum = 0;

                // Remove from end until sum >= need
                while (!list.isEmpty() && sum < need) {
                    int last = list.get(list.size() - 1);
                    list.remove(list.size() - 1);
                    sum += last;
                }

                // Add absolute value
                list.add(need);
            }
        }

        int ans = 0;
        for (int val : list) {
            ans += val;
        }

        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();  
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();  // Remaining input is the array of integers
        }
        
        // Call user logic function and print the output
        int result = goodSum(N, A);
        System.out.println(result);
    }
}