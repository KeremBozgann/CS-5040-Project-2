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
    
    /**
     * implementation of evaluate for 
     * ExpressionEvaluator2
     *    
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
            Arrays.asList(expr.split("((?=:|-|\\*|/|\\+|\\W|\\p{Digit}|\\s+|\\(|\\))|(?<=:|-|\\*|/|\\+|\\W|\\p{Digit}|\\s+|\\(|\\)))"))
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
//                if (Character.isLetter(opr)){ 
//                    boolean invalid = true; 
//                    while (invalid) {
//                    try {
//                    double number = ExpressionEvaluator.readInput(opr);
//                    invalid = false;
//                    Token oprnd  = new Token(number);
//                    operandStack.push(oprnd); }
//                    catch (IOException e) { 
//                        System.out.println(e);
//                    }}
//
//                }
                
                //check if char is an integer, if so, push it 
                //to operand stack: 
//                else { 
                    double number = Integer.parseInt(substrings.get(i));
                    Token oprnd  = new Token(number);
                    operandStack.push(oprnd); 
//                }
              
                
            } catch (NumberFormatException nfe) {
                //Since the string has single character, just use charAt(0):
                if (opr == '(') {
                    operatorStack.push(new Token('('));
                    operandStack.push(new Token('('));
                }
                else if (opr == '+' || opr == '-' || opr == '*'|| opr == '/')
                {
                    operatorStack.push(new Token(opr));
                }
                else if ( opr == ')')
                {   
                    //get the operator from operatorStack
                    Token operator = operatorStack.pop(); 
                    // check if we do not have more than 1 operator:
                    if (operatorStack.topValue().getOperator() == '(')
                        operatorStack.pop();
                    else
                        throw new java.lang.RuntimeException("Did not expect arithmetic operator");
                //remove '(' from operatorStack:
                
                    if (operandStack.topValue().isOpenParanthesis()) 
                        {
                        Character oprtSymb = operator.getOperator(); 
                        if (oprtSymb == '-' || oprtSymb == '/') {
                            throw new RuntimeException("Expression is not a valid Lisp expression");

                        }
                        double result = operator.getIdentity();
                        //remove '(' from operandStack:
                        operandStack.pop();
                        operandStack.push(new Token(result));}
                    else
                    {
                        double result = operandStack.pop().getValue();
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
    
    public static Integer readInput2(String elem) throws IOException  { 
        Scanner sc= new Scanner(System.in); 
        System.out.println("What is the value of ?" + elem);
        int number = (int) sc.nextInt();
        sc.close();
        return number;}
    
}