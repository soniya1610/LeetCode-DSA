class Solution {
    public boolean isPowerOfFour(int n) {
        return fun(n);
    }
    private boolean fun(int n){
        if(n == 1) return true;
        if( n <= 0 || n % 4 != 0){
            return false;
        }
        return fun(n/4);
    }
}