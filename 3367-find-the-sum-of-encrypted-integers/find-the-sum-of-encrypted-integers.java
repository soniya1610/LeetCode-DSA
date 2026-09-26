class Solution {
    public int sumOfEncryptedInt(int[] nums) {
        return encrypt(nums);
    }
    private int encrypt(int[] nums){
        int sum = 0;
        for(int i : nums){
            int original = i;
            int max = 0;

            while( i > 0 ){
                int  temp = i % 10;
                max = Math.max(max , temp);
                i /= 10;
            }
            int num = 0;
            int place = 1;
            while(original > 0){
                int digit = original % 10;
                num += max * place;
                place *= 10;
                original /= 10;
            }
            sum += num;
            
        }
        return sum;
    }
}