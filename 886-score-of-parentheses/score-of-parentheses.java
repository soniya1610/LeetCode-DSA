class Solution {
    public int scoreOfParentheses(String s) {
        int count = 0 , dep = 0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) =='('){
                ++dep;
            }else{
                --dep;
                if(s.charAt(i-1) == '('){
                    count += 1 << dep;
                }
            }
        }
        return count;
    }
}