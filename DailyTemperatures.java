import java.util.Arrays;
import java.util.Stack;

/**
 * Daily Temperatures - Monotonic Stack Solution
 * 
 * Problem: Given daily temperatures, find how many days until a warmer temperature.
 * 
 * Approach: Monotonic Decreasing Stack
 * - We maintain a stack of indices where temperatures are in decreasing order
 * - When we find a warmer temperature, we pop from stack and calculate the wait days
 * - Time Complexity: O(n) - each element pushed and popped at most once
 * - Space Complexity: O(n) - for the stack in worst case
 */
public class DailyTemperatures {
    
    /**
     * Finds the number of days to wait for a warmer temperature for each day
     * 
     * @param temperatures array of daily temperatures
     * @return array where answer[i] = days to wait for warmer temp after day i
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n]; // Default values are 0
        Stack<Integer> stack = new Stack<>(); // Stores indices, not temperatures
        
        // Iterate through each day
        for (int currDay = 0; currDay < n; currDay++) {
            int currTemp = temperatures[currDay];
            
            // While stack is not empty AND current temp is warmer than temp at stack top
            while (!stack.isEmpty() && temperatures[stack.peek()] < currTemp) {
                // Pop the previous colder day
                int prevDay = stack.pop();
                
                // Calculate how many days to wait
                answer[prevDay] = currDay - prevDay;
            }
            
            // Push current day index onto stack
            stack.push(currDay);
        }
        
        // Remaining indices in stack have no warmer day (answer already 0)
        return answer;
    }
    
    /**
     * Alternative approach with detailed step-by-step visualization
     */
    public static int[] dailyTemperaturesVerbose(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        System.out.println("=== Daily Temperatures Solution ===");
        System.out.println("Input: " + Arrays.toString(temperatures));
        System.out.println("\nStep-by-step process:");
        
        for (int currDay = 0; currDay < n; currDay++) {
            int currTemp = temperatures[currDay];
            System.out.println("\nDay " + currDay + ": Temperature = " + currTemp);
            
            // Process all colder temperatures
            while (!stack.isEmpty() && temperatures[stack.peek()] < currTemp) {
                int prevDay = stack.pop();
                int waitDays = currDay - prevDay;
                answer[prevDay] = waitDays;
                System.out.println("  → Found warmer temp for day " + prevDay + 
                                 " (temp=" + temperatures[prevDay] + 
                                 "), wait days = " + waitDays);
            }
            
            stack.push(currDay);
            System.out.println("  Stack after push: " + stack);
        }
        
        System.out.println("\n=== Final Answer ===");
        System.out.println("Output: " + Arrays.toString(answer));
        return answer;
    }
    
    /**
     * Demonstrates the monotonic stack behavior
     */
    public static void demonstrateMonotonicStack() {
        System.out.println("\n╔═══════════════════════════════════════════════════════╗");
        System.out.println("║      MONOTONIC STACK - KEY CONCEPTS                   ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("1. WHAT IS A MONOTONIC STACK?");
        System.out.println("   - A stack where elements are in strictly increasing/decreasing order");
        System.out.println("   - We use DECREASING order for this problem");
        System.out.println("   - Stack stores INDICES, not values (to calculate distances)");
        System.out.println();
        System.out.println("2. WHY DOES THIS WORK?");
        System.out.println("   - We want to find the NEXT GREATER element for each position");
        System.out.println("   - When we find a warmer temp, it's the answer for ALL colder");
        System.out.println("     temps still waiting in the stack");
        System.out.println();
        System.out.println("3. KEY OPERATIONS:");
        System.out.println("   - PUSH: Add current day to stack (might be waiting for warmer day)");
        System.out.println("   - POP: Current day is warmer → answer found for popped day");
        System.out.println("   - PEEK: Check if current temp is warmer than stack top");
        System.out.println();
        System.out.println("4. TIME COMPLEXITY: O(n)");
        System.out.println("   - Each element is pushed ONCE and popped AT MOST ONCE");
        System.out.println("   - Total operations = 2n in worst case → O(n)");
        System.out.println();
    }
    
    public static void main(String[] args) {
        demonstrateMonotonicStack();
        
        // Example 1
        System.out.println("\n" + "=".repeat(60));
        System.out.println("EXAMPLE 1");
        System.out.println("=".repeat(60));
        int[] temps1 = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result1 = dailyTemperaturesVerbose(temps1);
        System.out.println("\nExpected: [1, 1, 4, 2, 1, 1, 0, 0]");
        System.out.println("Got:      " + Arrays.toString(result1));
        System.out.println("Match: " + Arrays.equals(result1, new int[]{1, 1, 4, 2, 1, 1, 0, 0}));
        
        // Example 2
        System.out.println("\n" + "=".repeat(60));
        System.out.println("EXAMPLE 2");
        System.out.println("=".repeat(60));
        int[] temps2 = {30, 40, 50, 60};
        int[] result2 = dailyTemperaturesVerbose(temps2);
        System.out.println("\nExpected: [1, 1, 1, 0]");
        System.out.println("Got:      " + Arrays.toString(result2));
        System.out.println("Match: " + Arrays.equals(result2, new int[]{1, 1, 1, 0}));
        
        // Example 3: Monotonic decreasing (worst case for stack)
        System.out.println("\n" + "=".repeat(60));
        System.out.println("EXAMPLE 3: Decreasing Temperatures");
        System.out.println("=".repeat(60));
        int[] temps3 = {90, 80, 70, 60};
        int[] result3 = dailyTemperaturesVerbose(temps3);
        System.out.println("\nExpected: [0, 0, 0, 0]");
        System.out.println("Got:      " + Arrays.toString(result3));
        
        // Example 4: Monotonic increasing (best case)
        System.out.println("\n" + "=".repeat(60));
        System.out.println("EXAMPLE 4: Increasing Temperatures");
        System.out.println("=".repeat(60));
        int[] temps4 = {60, 70, 80, 90};
        int[] result4 = dailyTemperaturesVerbose(temps4);
        System.out.println("\nExpected: [1, 1, 1, 0]");
        System.out.println("Got:      " + Arrays.toString(result4));
    }
}
