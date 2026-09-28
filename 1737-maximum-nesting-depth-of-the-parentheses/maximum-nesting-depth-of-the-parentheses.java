class Solution {
    public int maxDepth(String s) {
        int res = 0;
        int openBrackets = 0;
        for(char ch : s.toCharArray()){
            if(ch == '('){
                openBrackets++;
            }else if( ch == ')'){
                openBrackets--;
            }
            res = Math.max(res , openBrackets);
        }
        return res;
    }
}