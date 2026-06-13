package UNSTOP;

import java.util.Scanner;

public class Main {
    public static int highestAltitude(int n, int[] arr) {
        // Write your logic here
        int sum = 0;
        int CurSum = 0;
        for(int i = 0; i<n; i++){
            CurSum += arr[i];
            if(CurSum > sum){
                sum = CurSum;
            }
        }
        return sum; // Placeholder return
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int result = highestAltitude(n, arr);
        System.out.println(result);
    }
}