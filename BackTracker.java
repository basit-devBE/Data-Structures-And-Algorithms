import java.util.*;

public class BackTracker {
    
    /**
     * Generate Parentheses Problem
     * 
     * Concept: Constrained generation using recursion and backtracking
     * 
     * Problem: Given n pairs of parentheses, generate all combinations of well-formed parentheses.
     * 
     * Example: generateParenthesis(3)
     * Output: ["((()))","(()())","(())()","()(())","()()()"]
     * 
     * Approach:
     * - Use backtracking to build valid combinations character by character
     * - Track the count of open '(' and close ')' parentheses
     * - Constraints:
     *   1. Can only add '(' if open count < n
     *   2. Can only add ')' if close count < open count (ensures validity)
     * - Base case: When current string length == n * 2, we have a complete combination
     * 
     * Time Complexity: O(4^n / sqrt(n)) - Catalan number
     * Space Complexity: O(n) - recursion depth
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }
    
    /**
     * Backtracking helper method
     * 
     * @param result - List to store all valid combinations
     * @param current - Current string being built
     * @param open - Count of open parentheses '(' used so far
     * @param close - Count of close parentheses ')' used so far
     * @param max - Maximum pairs of parentheses (n)
     */
    private void backtrack(List<String> result, String current, int open, int close, int max) {
        // Base case: If we've used all n pairs, add to result
        if (current.length() == max * 2) {
            result.add(current);
            return;
        }
        
        // Decision 1: Add an open parenthesis '(' if we haven't used all n
        if (open < max) {
            backtrack(result, current + "(", open + 1, close, max);
        }
        
        // Decision 2: Add a close parenthesis ')' if it won't make the string invalid
        // We can only add ')' if we have more '(' than ')' so far
        if (close < open) {
            backtrack(result, current + ")", open, close + 1, max);
        }
    }
    
    
    /**
     * Main method to demonstrate the solution
     */
    public static void main(String[] args) {
        BackTracker solution = new BackTracker();
        
        System.out.println("=== Generate Parentheses Problem ===\n");
        
        // Test case 1: n = 3
        System.out.println("Input: n = 3");
        List<String> result3 = solution.generateParenthesis(3);
        System.out.println("Output: " + result3);
        System.out.println("Total combinations: " + result3.size());
        
        System.out.println("\n" + "=".repeat(40) + "\n");
        
        // Test case 2: n = 1
        System.out.println("Input: n = 1");
        List<String> result1 = solution.generateParenthesis(1);
        System.out.println("Output: " + result1);
        
        System.out.println("\n" + "=".repeat(40) + "\n");
        
        // Test case 3: n = 2
        System.out.println("Input: n = 2");
        List<String> result2 = solution.generateParenthesis(2);
        System.out.println("Output: " + result2);
        
        System.out.println("\n" + "=".repeat(40) + "\n");
        
        // Test case 4: n = 4
        System.out.println("Input: n = 4");
        List<String> result4 = solution.generateParenthesis(4);
        System.out.println("Output: " + result4);
        System.out.println("Total combinations: " + result4.size());
        
        System.out.println("\n=== How it works ===");
        System.out.println("At each step, we make two decisions:");
        System.out.println("1. Add '(' if we haven't used all n opening parentheses");
        System.out.println("2. Add ')' if it won't make the string invalid (close < open)");
        System.out.println("\nThis ensures we only generate VALID combinations!");
    }
}