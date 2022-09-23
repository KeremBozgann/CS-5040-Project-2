public class ExpressionEvaluator { 
    public static boolean isBalanced(String expr) {
        
        LStack<Character> balanceStack = new LStack<Character>();

        for (int i = 0; i< expr.length(); i++) {
            if (expr.charAt(i) == '(') {
                balanceStack.push('(');
            }
            else if (expr.charAt(i) == ')') {
                if (balanceStack.isEmpty())
                        return false; 
                else
                    balanceStack.pop();
                            }
            else
                continue;
        }
        
        if (!balanceStack.isEmpty())
            return false; 
        return true; 
    }
    
    public static Double evaluate(String expr) { 
        return 1.0; 
    }
    
}