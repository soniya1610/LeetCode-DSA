class Solution {
    public int fib(int n) {
        //  if( n <= 1) return n;

        //  int first = 0; //f(2)
        //  int second = 1;// f(1)

        //  for(int i = 2; i <=n; i++){
        //     int  curr = first + second ;
        //     first = second;
        //     second = curr;
        //  }
        //  return second;
        return fun(n);
    }
    // using recursion 
    private int fun(int n){ 
        if(n < 2 ) return n;

        return fun(n-1) + fun(n-2);
    }
}