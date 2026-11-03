class Solution {
    public int minMoves(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for(int i=0; i<nums.length; i++){
            count += (nums[nums.length - 1] - nums[i]);
        }
        return count;
    }
}