class Solution {
    public int sumOfEncryptedInt(int[] nums) {
        // int sum = 0;
        // for(int i : nums){
        //     int original = i;
        //     int max = 0;

        //     while( i > 0 ){
        //         int  temp = i % 10;
        //         max = Math.max(max , temp);
        //         i /= 10;
        //     }
        //     int num = 0;
        //     int place = 1;
        //     while(original > 0){
        //         int digit = original % 10;
        //         num += max * place;
        //         place *= 10;
        //         original /= 10;
        //     }
        //     sum += num;
            
        // }
        // return sum;
        return encrypt(nums , 0);
    } 
    
    // Process array recursively
    private int encrypt(int[] nums, int index) {

        if (index == nums.length) {
            return 0;
        }

        int num = nums[index];

        int maxDigit = findMaxDigit(num);

        int encrypted = createEncryptedNumber(num, maxDigit, 1);

        return encrypted + encrypt(nums, index + 1);
    }

    // Find maximum digit recursively
    private int findMaxDigit(int n) {

        if (n < 10) {
            return n;
        }

        return Math.max(n % 10, findMaxDigit(n / 10));
    }

    // Create encrypted number recursively
    private int createEncryptedNumber(int n, int max, int place) {

        if (n == 0) {
            return 0;
        }

        return max * place
                + createEncryptedNumber(n / 10, max, place * 10);
    }
}