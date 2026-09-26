class Solution {
    public boolean isPowerOfThree(int n) {

       return fun(n);
    }
    private boolean fun(int n){
        if(n == 1) {
            return true;
        }
        if(n <= 0 || n % 3 != 0){
            return false;
        }
        return fun( n/3);
    }
    
}