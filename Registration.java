import java.util.*;

public class Registration {

    static void main() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Number Plate: ");
        String input = scanner.nextLine();

        List<String> reasons = new ArrayList<>();

        String cleaned = input.replaceAll("[\\s_-]", "");

        String region = "";
        String mainPart;

        if (cleaned.length() == 8) {
            region = cleaned.substring(0, 2);
            mainPart = cleaned.substring(2);

            if (!region.matches("[A-Z]{2}")) {
                reasons.add("Invalid region code");
            }
        } else if (cleaned.length() == 6) {
            mainPart = cleaned;
        } else {
            reasons.add("Wrong character count");
            mainPart = "";
        }

        if (!cleaned.matches("[A-Za-z0-9]+")) {
            reasons.add("Invalid characters");
        }

        if (mainPart.length() == 6) {
            int letters = 0;
            int digits = 0;

            for (char c : mainPart.toCharArray()) {
                if (Character.isLetter(c)) letters++;
                if (Character.isDigit(c)) digits++;
            }

            if (letters < 2) reasons.add("Insufficient letters");
            if (digits < 2) reasons.add("Insufficient digits");
        }

        if (reasons.isEmpty()) {
            mainPart = mainPart.toUpperCase();
            String formattedMain =
                    mainPart.substring(0, 3) + "-" + mainPart.substring(3);

            if (!region.isEmpty()) {
                System.out.println("Valid Plate: " + region + "-" + formattedMain);
            } else {
                System.out.println("Valid Plate: " + formattedMain);
            }
        } else {
            System.out.println("Invalid Plate");
            System.out.println("Reasons: " + String.join(", ", reasons));
        }
    }
}
