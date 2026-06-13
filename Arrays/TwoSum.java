// Two Sum problem: Given an array of integers and a target integer, return the indices of the two numbers that add up to the target.https://leetcode.com/problems/two-sum/description/
// time complexity : O(n) because we traverse the array once.
// space complexity : O(1) because we are using in place sorting and no extra space will be used .
class Solution{
    public int[] twoSum(int[] nums, int target) {
        int left = 0; // Pointer for the start of the array
        int right = nums.length - 1; // Pointer for the end of the array
        
        while (left < right) {
            int sum = nums[left] + nums[right]; // Calculate the sum of the two pointers
            
            if (sum == target) { // If we found the target sum
                return new int[]{left, right}; // Return the indices of the two numbers
            } else if (sum < target) { // If the sum is less than the target, move the left pointer to the right
                left++;
            } else { // If the sum is greater than the target, move the right pointer to the left
                right--;
            }
        }
        
        return new int[0]; // Return an empty array if no solution is found
    }
    
}
