// 977. Squares of a Sorted Array
// time complexity : O(nlogn) because we are sorting the array after calculating the squares.
// space complexity : O(n) because we are creating a new array to store the squares of the numbers.

import java.util.Arrays;

public class SquaresOfSortedArray {
    // public static void main(String[] args) {
    //     int[] nums = {-4,-1,0,3,10};
    //     int[] ans = sortedSquares(nums);
    //     System.out.println(Arrays.toString(ans));
    // }

    public static int[] sortedSquares(int[] nums) {
    int n =  nums.length;
    int[] result  = new int[n];

    for(int i=0; i<n; i++){ 
        result[i] = nums[i] * nums[i]; 
    }
    Arrays.sort(result); // time complexity : O(nlogn) because we are sorting the array after calculating the squares.
    return result;
   }
}
// time complexity : o(n) because we use two pointer approach here 
//space can't be reduced 

// public class SquaresOfSortedArray{

//     public int[] sortedSquares(int[] nums) {
//     int n =  nums.length;
//     int[] result  = new int[n];

//     int left = 0 , right = n - 1;
//     int idx = n - 1;

//     while(left <= right){
//         int leftSq = nums[left] * nums[left];
//         int rightSq = nums[right] * nums[right];

//         if(leftSq > rightSq){
//             result[idx] = leftSq;
//             left++;
//         }else{
//             result[idx] = rightSq;
//             right--;
//         }
//         idx--;
//     }
//     return result;

//  }
// }
