# License Plate Validator

## Problem Description
A vehicle registration system validator that checks if license plates follow proper formatting rules and converts them to a standard format.

## Validation Rules

### Input Format
- May optionally start with a region code (2 uppercase letters)
- Must contain exactly 6 alphanumeric characters after the region code
- Can contain spaces, hyphens (-), or underscores (_) as separators
- Must have at least 2 letters and at least 2 digits
- No other special characters allowed

### Validation Checks (in order)
1. **Invalid region code** - Region must be 2 uppercase letters
2. **Wrong character count** - Must have exactly 6 alphanumeric characters (excluding region)
3. **Invalid characters** - Only alphanumeric and separators (space, -, _) allowed
4. **Insufficient letters** - At least 2 letters required in main part
5. **Insufficient digits** - At least 2 digits required in main part

### Output Format
- Valid plates: `[REGION-]XXX-XXX`
- All letters converted to uppercase
- Hyphen separates two groups of 3 characters

## Examples

| Input | Output | Status |
|-------|--------|--------|
| CA-ABC123 | CA-ABC-123 | ✓ Valid |
| XYZ789 | XYZ-789 | ✓ Valid (no region) |
| 12-AB34CD | Invalid | Wrong character count |
| GH_1A2B3C | GH-1A2-B3C | ✓ Valid |
| AB-12.34.56 | Invalid | Invalid characters |
| TX ABC1 | Invalid | Wrong character count, Insufficient digits |

## How to Run

```bash
# Compile
javac LincensePlateValidator.java

# Run with input file
java LincensePlateValidator < input.txt

# Or run interactively
java LincensePlateValidator
```

## Input Format
```
N (number of plates in this test case)
plate1
plate2
...
plateN
0 (to exit)
```

## Implementation Highlights

### Region Code Detection
The validator detects region codes in two ways:
1. **Explicit separator**: If characters at positions 0-1 are letters and position 2 is a separator
   - Example: `CA-ABC123` → region: CA, main: ABC123
2. **8 character total**: If first 2 chars are letters and total alphanumeric count is 8
   - Example: `CAABC123` → region: CA, main: ABC123

### Character Extraction
- Removes all non-alphanumeric characters except when checking for invalid characters
- Converts all letters to uppercase for consistency
- Counts letters and digits separately for validation

### Validation Order
Follows the specified order to ensure consistent error reporting across all test cases.
