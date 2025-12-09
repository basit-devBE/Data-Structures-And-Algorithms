public class Recursion {

    public static int sum(int k){
        //here we have indicated the control variable as k..meaning as far as k has a value greater than 0...the recursion will continue
        //now where exactly our recursion ends is entirely dependant on our base case scenario...which in this case is when k equals 0
        if(k > 0){
            return k + sum(k -1);
            //let's try to expand this a bit...what we are really doing is adding the current value of k which is our current number when the loop starts
            //to the result of sum(k-1)...now what is sum(k-1) ...if we're to expand the whole expression...it would look something like this
            //k + (k-1) + (k-2) + (k-3) + ....+ 1 + sum(0)
            //now since our base case scenario is when k equals 0...the recursion stops there and we return 0
            //so the final expression would look something like this
            //k + (k-1) + (k-2) + (k-3) + ....+ 1 + 0
        }else{
            return 0;
        }
    };

    public static int sumtoend(int start, int end){
        if(end > start){
            return end + sumtoend(start, end -1);
        }else{
            return end;
        }
    }

    public static int factorial(int n){
        if(n > 0){
            return n * factorial(n -1);
        }else{
            return 1;
        }
    }

}


/*Recursion is a technique in programming where a function or method in the case of Java..repeatedly calls itself to solve a particular problem
or perform a specific task. Each time the function calls itself, it works on a smaller or simpler version of the original problem until it reaches a base case that can be solved directly without further recursion.
To make recursion work effectively, two main components are essential:
1.A control Structure: This is basically the condition that determines when the recursion should stop..Usually we use an if -else statement with an integer value.
2.A base Case scenario: This is the simplest form of the problem we are trying to solve..so let's say we're trying to add numbers from 1 to n...the base case of this scenario is when n equals 0..at this point we can directly return 0 without further recursion.
After getting this control established..we look at how to  break down the problem into smaller subproblems..this is where the recursive calls come in.

*/