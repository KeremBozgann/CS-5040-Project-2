// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

import java.util.*;
import java.io.IOException;

/**
 * 
 * @author Kerem Bozgan kerembozgan
 * @version 2022-09-25
 */
public class ExpressionEvaluator2 { 
    
    
//    public static void main(String[] args) { 

//        System.out.println(ExpressionEvaluator2.evaluate
//            ("(+ (- height)\n"
//                + "  (* 4 4 5)\n"
//                + "  (/ 3 width length)\n"
//                + "  (* radius radius)\n"
//                + ")"));

//      System.out.println(ExpressionEvaluator2.evaluate
//      ("(+ (- 6 7) (* 234 455 256) (/ (/ 3) (*) (-2 3 1)))"));
        
//      System.out.println(ExpressionEvaluator2.evaluate
//      ( "(+ (- var2var) (* 3 3 4) (/  wt3len) (* (+) rad))"));
    
//      System.out.println(ExpressionEvaluator2.evaluate
//      ( "(+ (- height) (* 3 3 4) (/ 3 width length) (* radius radius))"));
//    
//      System.out.println(ExpressionEvaluator2.evaluate
//      ( "(+ (- 6) (* var3variable4bc))"));
      
//    } 
    
    /**
     * implementation of evaluate for 
     * ExpressionEvaluator2
     * @param expr LISP expression   
     * @return result
     */
    public static Double evaluate(String expr) { 
        //check if expression is balanced
        if (expr.isEmpty() || !ExpressionEvaluator.isBalanced(expr) ) {
        throw new RuntimeException("Expression is not a valid Lisp expression");}
        
        //separate expression into components. Different than
        //Expression evaluator, seperate word by word, not letter by
        //letter:
        ArrayList<String> substrings = new ArrayList<String>(
            Arrays.asList(expr.split
            ("((?=:|(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)|-|\\*|/|\\+|\\W|\\s+|\\(|\\))|(?<=:|-|(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)|\\*|/|\\+|\\W|\\s+|\\(|\\)))"))
            );
        substrings.removeAll(Arrays.asList("", " "));

        //DEFINE OPERATOR AND OPERAND STACK
        LStack<Token> operatorStack = new LStack<Token>();
        LStack<Token> operandStack = new LStack<Token>();
        
        //Process expression char by char:
        for (int i = 0; i< substrings.size(); i++) {
            String elem = substrings.get(i);
            //Ask user for input and replace variable with its value
            if (Character.isLetter(elem.charAt(0)))
            {
                boolean invalid = true; 
                while (invalid) {
                try {
                    int num  = readInput2(elem);
                invalid = false;
                //replace variable in expression with its value
                substrings.set(i, String.valueOf(num));
                }
                catch (IOException e) { 
                    System.out.println(e);
                }}

                }
            //next character in expression:    
            char opr = (substrings.get(i)).charAt(0);
                
            try {
                
                //check if char is an integer, if so, push it 
                //to operand stack: 
                double number = Integer.parseInt(substrings.get(i));
                Token oprnd  = new Token(number);
                operandStack.push(oprnd); 
              
            //if not integer, check what char is:   
            } catch (NumberFormatException nfe) {
                //check if it is open parant.
                if (opr == '(') {
                    operatorStack.push(new Token('('));
                    operandStack.push(new Token('('));
                }
                //check if it is arithm. operator:
                else if (opr == '+' || opr == '-' || opr == '*'|| opr == '/')
                {
                    operatorStack.push(new Token(opr));
                }
                //check if it is closed parant.:
                else if ( opr == ')')
                {   
                    //get the operator from operatorStack
                    Token operator = operatorStack.pop(); 
                    if (operatorStack.topValue().getOperator() == '(')
                        //pop open parant.:
                        operatorStack.pop();
                    // check if we do not have more than 1 operator,
                    //if so, throw error:
                    else
                        throw new java.lang.RuntimeException("Did not expect arithmetic operator");
                
                    if (operandStack.topValue().isOpenParanthesis()) 
                        {
                        //if no operand inside parant., when operator is 
                        //- or /, then throw error:
                        Character oprtSymb = operator.getOperator(); 
                        if (oprtSymb == '-' || oprtSymb == '/') {
                            throw new RuntimeException("Expression is not a valid Lisp expression");

                        }
                        //if single operand, when operator is 
                        //+ or *, return identity
                        double result = operator.getIdentity();
                        //remove '(' from operandStack:
                        operandStack.pop();
                        operandStack.push(new Token(result));}
                    //if at least one operand between paranth.:
                    else
                    {
                        //get top operand
                        double result = operandStack.pop().getValue();
                        //if count 0 in the end, means single operand between
                        //parant. which requires different treatment
                        int count = 0; 
                        Character oprtSymb= operator.getOperator();
                        if ( oprtSymb== '+' || oprtSymb == '*') {
                            while (true) { 
                                  if (operandStack.topValue().isOpenParanthesis())
                                      {operandStack.pop();
                                      break;}
                                  else
                                  {double opVal =  operandStack.pop().getValue();
                                      result = operator.applyOperator(result,opVal);
                                      count++;}
                                  }}
                        //if operator is -,  different evaluation process:
                        else if( oprtSymb== '-'){
                            while (true) { 
                                if (operandStack.topValue().isOpenParanthesis())
                                    {operandStack.pop();          
                                    break;}
                                else
                                {double opVal =  operandStack.pop().getValue();
                                    if (operandStack.topValue().isOpenParanthesis())
                                        result = opVal - result;
                                    else 
                                        result = result + opVal;
                                        
                                    count++;}
                                }
                        }
                        // if /, different evaluation process:
                        else{
                            while (true) { 
                                if (operandStack.topValue().isOpenParanthesis())
                                    {operandStack.pop();          
                                    break;}
                                else
                                {double opVal =  operandStack.pop().getValue();
                                    if (operandStack.topValue().isOpenParanthesis())
                                        result = opVal/result;
                                    else 
                                        result = result * opVal;
                                    count++;}
                                }
                        }
                        
                        
                        //when there is single operand between paranthesis:
                        if (count ==0) { 
                            if (operator.getOperator() == '+') 
                            {operandStack.push(new Token(result));}
                            else if (operator.getOperator() == '-') 
                            {operandStack.push(new Token(-1 * result));}
                            else if (operator.getOperator() == '*') 
                            {operandStack.push(new Token(result));}
                            else if (operator.getOperator() == '/') 
                            {operandStack.push(new Token(1.0/result));}}
                        //otherwise:
                        else {operandStack.push(new Token(result));}
                    
                                }
                        
                            

                           }


                            }
        }
        return operandStack.pop().getValue();
    }
    
    /**
     * ask user for variable value
     * @param elem variable name
     * @return  number 
     * @throws IOException invalid entry
     */
    public static Integer readInput2(String elem) throws IOException  { 
        Scanner sc= new Scanner(System.in); 
        System.out.println("What is the value of '" + elem + "'?");
        int number = (int) sc.nextInt();
//        sc.close();
        return number;}
    
}