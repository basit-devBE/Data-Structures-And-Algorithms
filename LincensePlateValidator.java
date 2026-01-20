import java.util.*;

/**
 * License Plate Validator
 * 
 * Validates and formats license plates according to specific rules:
 * - Optional 2-letter region code at the start
 * - Exactly 6 alphanumeric characters in the main part
 * - At least 2 letters and 2 digits in the main part
 * - Only spaces, hyphens, and underscores allowed as separators
 * 
 * Output format: [REGION-]XXX-XXX
 */
public class LincensePlateValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseNumber = 1;
        
        while (true) {
            int n = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            if (n == 0) {
                break;
            }
            
            System.out.println("Case " + caseNumber + ":");
            
            for (int i = 1; i <= n; i++) {
                String plate = scanner.nextLine();
                processPlate(i, plate);
            }
            
            System.out.println();
            caseNumber++;
        }
        
        scanner.close();
    }
    
    private static void processPlate(int plateNumber, String input) {
        ValidationResult result = validatePlate(input);
        
        if (result.isValid) {
            System.out.println("Plate " + plateNumber + ": " + result.formatted);
        } else {
            System.out.println("Plate " + plateNumber + ": Invalid");
            System.out.println("Reasons: " + String.join(", ", result.violations));
        }
    }
    
    private static ValidationResult validatePlate(String input) {
        ValidationResult result = new ValidationResult();
        
        // Step 1: Detect region code
        String regionCode = "";
        String mainPart = input;
        
        // Check if input starts with 2 letters followed by a separator
        if (input.length() >= 3) {
            char thirdChar = input.charAt(2);
            if (thirdChar == '-' || thirdChar == '_' || thirdChar == ' ') {
                String potentialRegion = input.substring(0, 2).toUpperCase();
                
                if (potentialRegion.matches("[A-Z]{2}")) {
                    // Valid region code found
                    regionCode = potentialRegion;
                    mainPart = input.substring(3); // Skip region and separator
                }
                // If not valid letters, treat entire input as main part (no region)
            } else if (Character.isLetter(input.charAt(0)) && Character.isLetter(input.charAt(1))) {
                // No separator after first 2 chars, but they are letters
                // Check if we have 8 total alphanumeric chars (2 region + 6 main)
                String allAlpha = input.replaceAll("[^a-zA-Z0-9]", "");
                if (allAlpha.length() == 8) {
                    regionCode = input.substring(0, 2).toUpperCase();
                    mainPart = input.substring(2);
                }
            }
        }
        
        // Step 2: Extract alphanumeric characters from main part
        String alphanumeric = mainPart.replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
        
        // Step 3: Validate according to rules (check in specified order)
        
        // Check 1: Invalid region code (this is checked during parsing above)
        // We don't add this violation if region was simply absent
        
        // Check 2: Wrong character count
        if (alphanumeric.length() != 6) {
            result.violations.add("Wrong character count");
        }
        
        // Check 3: Invalid characters
        if (mainPart.matches(".*[^a-zA-Z0-9\\s\\-_].*")) {
            result.violations.add("Invalid characters");
        }
        
        // Count letters and digits
        int letterCount = 0;
        int digitCount = 0;
        for (char c : alphanumeric.toCharArray()) {
            if (Character.isLetter(c)) {
                letterCount++;
            } else if (Character.isDigit(c)) {
                digitCount++;
            }
        }
        
        // Check 4: Insufficient letters
        if (letterCount < 2) {
            result.violations.add("Insufficient letters");
        }
        
        // Check 5: Insufficient digits
        if (digitCount < 2) {
            result.violations.add("Insufficient digits");
        }
        
        // Format output if valid
        if (result.violations.isEmpty()) {
            result.isValid = true;
            String formatted = alphanumeric.substring(0, 3) + "-" + alphanumeric.substring(3, 6);
            if (!regionCode.isEmpty()) {
                result.formatted = regionCode + "-" + formatted;
            } else {
                result.formatted = formatted;
            }
        }
        
        return result;
    }
    
    private static class ValidationResult {
        boolean isValid = false;
        String formatted = "";
        List<String> violations = new ArrayList<>();
    }
}