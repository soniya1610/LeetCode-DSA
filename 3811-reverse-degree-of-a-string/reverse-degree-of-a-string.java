class Solution {
    public int reverseDegree(String s) {
        int sum = 0;
        for(int i=0; i < s.length(); i++){ 
            char c = s.charAt(i);
            int num = 26 -  c + 'a';
            num *= i+1;
            sum += num;
        }
        return sum;
    }
}