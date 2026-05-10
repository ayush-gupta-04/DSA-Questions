class Solution {
    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        
        StringBuilder currentString = new StringBuilder();
        int k = 0;
        
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                // Handle multi-digit numbers
                k = k * 10 + (ch - '0');
            } else if (ch == '[') {
                // Save current state and reset
                countStack.push(k);
                stringStack.push(currentString);
                currentString = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                // Decode current bracket and append to previous state
                StringBuilder decodedString = stringStack.pop();
                int currentK = countStack.pop();
                
                for (int i = 0; i < currentK; i++) {
                    decodedString.append(currentString);
                }
                currentString = decodedString;
            } else {
                // Append character to current string
                currentString.append(ch);
            }
        }
        
        return currentString.toString();
    }
}
