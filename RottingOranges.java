import java.util.*;

/**
 * Rotting Oranges - Multi-Source BFS Solution
 * 
 * Problem: Find minimum time for all fresh oranges to rot via adjacent spreading.
 * 
 * Approach: Multi-Source Breadth-First Search (BFS)
 * - Start with ALL rotten oranges in queue simultaneously
 * - Process level by level (each level = 1 minute)
 * - Rot adjacent fresh oranges in 4 directions
 * - Time Complexity: O(m × n) - visit each cell at most once
 * - Space Complexity: O(m × n) - queue can hold all cells in worst case
 */
public class RottingOranges {
    
    // Directions: Up, Down, Left, Right
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    /**
     * Finds minimum minutes for all fresh oranges to rot
     * 
     * @param grid m x n grid with 0 (empty), 1 (fresh), 2 (rotten)
     * @return minimum minutes until all fresh oranges rot, or -1 if impossible
     */
    public static int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return -1;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;
        
        // Step 1: Find all initial rotten oranges and count fresh ones
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    queue.offer(new int[]{r, c}); // Add rotten orange position
                } else if (grid[r][c] == 1) {
                    freshCount++; // Count fresh oranges
                }
            }
        }
        
        // Edge case: No fresh oranges at all
        if (freshCount == 0) {
            return 0;
        }
        
        // Step 2: Perform Multi-Source BFS
        int minutes = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size(); // Process all oranges at current time level
            boolean rottedThisMinute = false;
            
            // Process all oranges that are rotten at current minute
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int row = current[0];
                int col = current[1];
                
                // Check all 4 adjacent cells
                for (int[] dir : DIRECTIONS) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];
                    
                    // Check bounds and if it's a fresh orange
                    if (newRow >= 0 && newRow < rows && 
                        newCol >= 0 && newCol < cols && 
                        grid[newRow][newCol] == 1) {
                        
                        // Rot the fresh orange
                        grid[newRow][newCol] = 2;
                        queue.offer(new int[]{newRow, newCol});
                        freshCount--;
                        rottedThisMinute = true;
                    }
                }
            }
            
            // Only increment time if we actually rotted oranges this minute
            if (rottedThisMinute) {
                minutes++;
            }
        }
        
        // Step 3: Check if all fresh oranges have rotted
        return freshCount == 0 ? minutes : -1;
    }
    
    /**
     * Verbose version with step-by-step visualization
     */
    public static int orangesRottingVerbose(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return -1;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;
        
        System.out.println("=== Rotting Oranges Solution ===");
        System.out.println("Initial Grid:");
        printGrid(grid);
        
        // Find initial rotten oranges and count fresh ones
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    queue.offer(new int[]{r, c});
                    System.out.println("Initial rotten orange at: (" + r + ", " + c + ")");
                } else if (grid[r][c] == 1) {
                    freshCount++;
                }
            }
        }
        
        System.out.println("Initial fresh orange count: " + freshCount);
        
        if (freshCount == 0) {
            System.out.println("No fresh oranges to rot!");
            return 0;
        }
        
        // Perform Multi-Source BFS
        int minutes = 0;
        System.out.println("\n--- BFS Process ---");
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean rottedThisMinute = false;
            
            System.out.println("\nMinute " + minutes + ": Processing " + size + " rotten orange(s)");
            
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int row = current[0];
                int col = current[1];
                
                System.out.println("  Processing rotten orange at (" + row + ", " + col + ")");
                
                for (int[] dir : DIRECTIONS) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];
                    
                    if (newRow >= 0 && newRow < rows && 
                        newCol >= 0 && newCol < cols && 
                        grid[newRow][newCol] == 1) {
                        
                        grid[newRow][newCol] = 2;
                        queue.offer(new int[]{newRow, newCol});
                        freshCount--;
                        rottedThisMinute = true;
                        System.out.println("    → Rotted orange at (" + newRow + ", " + newCol + 
                                         "), fresh remaining: " + freshCount);
                    }
                }
            }
            
            if (rottedThisMinute) {
                minutes++;
                System.out.println("\n  Grid after minute " + minutes + ":");
                printGrid(grid);
            }
        }
        
        System.out.println("\n=== Final Result ===");
        System.out.println("Total minutes: " + minutes);
        System.out.println("Fresh oranges remaining: " + freshCount);
        
        return freshCount == 0 ? minutes : -1;
    }
    
    /**
     * Helper method to print the grid
     */
    private static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            System.out.print("  [");
            for (int i = 0; i < row.length; i++) {
                System.out.print(row[i]);
                if (i < row.length - 1) System.out.print(", ");
            }
            System.out.println("]");
        }
    }
    
    /**
     * Demonstrates the Multi-Source BFS concept
     */
    public static void demonstrateMultiSourceBFS() {
        System.out.println("\n╔═══════════════════════════════════════════════════════╗");
        System.out.println("║      MULTI-SOURCE BFS - KEY CONCEPTS                  ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("1. WHAT IS MULTI-SOURCE BFS?");
        System.out.println("   - Traditional BFS starts from ONE source");
        System.out.println("   - Multi-Source BFS starts from MULTIPLE sources simultaneously");
        System.out.println("   - All sources are added to queue at the START");
        System.out.println();
        System.out.println("2. WHY USE IT FOR THIS PROBLEM?");
        System.out.println("   - Multiple rotten oranges spread infection simultaneously");
        System.out.println("   - All rotten oranges at time T rot their neighbors at time T+1");
        System.out.println("   - We need to track the MINIMUM time (parallel spreading)");
        System.out.println();
        System.out.println("3. KEY ALGORITHM STEPS:");
        System.out.println("   Step 1: Add ALL initial rotten oranges (2) to queue");
        System.out.println("   Step 2: Count ALL fresh oranges (1)");
        System.out.println("   Step 3: Process queue level-by-level (each level = 1 minute)");
        System.out.println("   Step 4: For each rotten orange, rot adjacent fresh oranges");
        System.out.println("   Step 5: Check if all fresh oranges rotted");
        System.out.println();
        System.out.println("4. LEVEL-BY-LEVEL PROCESSING:");
        System.out.println("   - Queue size at start of loop = oranges rotten at current time");
        System.out.println("   - Process exactly that many oranges (one complete level)");
        System.out.println("   - Newly rotted oranges go in queue for NEXT level (next minute)");
        System.out.println();
        System.out.println("5. 4-DIRECTIONAL ADJACENCY:");
        System.out.println("   - Up:    (-1,  0)");
        System.out.println("   - Down:  ( 1,  0)");
        System.out.println("   - Left:  ( 0, -1)");
        System.out.println("   - Right: ( 0,  1)");
        System.out.println();
        System.out.println("6. TIME COMPLEXITY: O(m × n)");
        System.out.println("   - Visit each cell at most once");
        System.out.println("   - m = number of rows, n = number of columns");
        System.out.println();
    }
    
    public static void main(String[] args) {
        demonstrateMultiSourceBFS();
        
        // Example 1
        System.out.println("\n" + "=".repeat(60));
        System.out.println("EXAMPLE 1: Normal Case");
        System.out.println("=".repeat(60));
        int[][] grid1 = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        int result1 = orangesRottingVerbose(grid1);
        System.out.println("\nExpected: 4");
        System.out.println("Got:      " + result1);
        System.out.println("Match: " + (result1 == 4));
        
        // Example 2
        System.out.println("\n" + "=".repeat(60));
        System.out.println("EXAMPLE 2: Impossible Case (Isolated Fresh Orange)");
        System.out.println("=".repeat(60));
        int[][] grid2 = {{2, 1, 1}, {0, 1, 1}, {1, 0, 1}};
        int result2 = orangesRottingVerbose(grid2);
        System.out.println("\nExpected: -1");
        System.out.println("Got:      " + result2);
        System.out.println("Match: " + (result2 == -1));
        
        // Example 3: No fresh oranges
        System.out.println("\n" + "=".repeat(60));
        System.out.println("EXAMPLE 3: No Fresh Oranges");
        System.out.println("=".repeat(60));
        int[][] grid3 = {{2, 2, 0}, {0, 2, 0}, {0, 0, 2}};
        int result3 = orangesRottingVerbose(grid3);
        System.out.println("\nExpected: 0");
        System.out.println("Got:      " + result3);
        System.out.println("Match: " + (result3 == 0));
        
        // Example 4: Single fresh orange surrounded by rotten
        System.out.println("\n" + "=".repeat(60));
        System.out.println("EXAMPLE 4: Multi-Source Convergence");
        System.out.println("=".repeat(60));
        int[][] grid4 = {{2, 1, 2}};
        int result4 = orangesRottingVerbose(grid4);
        System.out.println("\nExpected: 1");
        System.out.println("Got:      " + result4);
        System.out.println("Match: " + (result4 == 1));
        
        // Example 5: Large grid with multiple sources
        System.out.println("\n" + "=".repeat(60));
        System.out.println("EXAMPLE 5: Complex Multi-Source Scenario");
        System.out.println("=".repeat(60));
        int[][] grid5 = {
            {2, 1, 1, 1, 1},
            {1, 1, 1, 1, 1},
            {1, 1, 1, 1, 2}
        };
        int result5 = orangesRottingVerbose(grid5);
        System.out.println("\nExpected: 3");
        System.out.println("Got:      " + result5);
        System.out.println("Match: " + (result5 == 3));
    }
}
