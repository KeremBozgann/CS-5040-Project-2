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

/**
 * @author Kerem Bozgan kerembozgan
 * @version 2022-09-18
 */
public class Token {

    private Character operator;
    private Double operand;
    private boolean isOperator;
    
    /**
     * set up character token
     * @param operator
     */
    public Token(Character operator) {
        this.operand = 0.0;
        this.operator = operator;
        this.isOperator = true;
    }

    /**
     * set up character token
     * @param operand
     */
    public Token(Double operand) {
        this.isOperator = false;
        this.operand = operand;
        this.operator = ' ';
    }

    /**
     * 
     * apply operator
     * 
     * @param value1 
     * 
     * @param value2 
     *     
     * @return result
     */
    Double applyOperator(Double value1, Double value2) {
        if (operator == '+') {
            return value1 + value2;
        }
        else if (operator == '-') {
            return value1 - value2;
        }
        else if (operator == '*') {
            return value1 * value2;
        }
        else if (operator == '/') {
            return value1 / value2;
        }
        else {
            throw new java.lang.RuntimeException("Invalid operator");

        }
    }

    /**
     * 
     * return identity operand of operator
     * @return identity
     */
    public Double getIdentity() {
        if (operator == '+') {
            return 0.0;
        }
        else if (operator == '-') {
            return 0.0;
        }
        else if (operator == '*') {
            return 1.0;
        }
        else if (operator == '/') {
            return 1.0;
        }
        else {
            throw new java.lang.RuntimeException("Invalid operator");

        }
    }

    /**
     * 
     * whether operator can be called withouth an operand
     * 
     * @param boolean
     */
    public boolean takesNoOperands() {
        if (operator == '+') {
            return true;
        }
        else if (operator == '-') {
            return false;
        }
        else if (operator == '*') {
            return true;
        }
        else if (operator == '/') {
            return false;
        }
        else {
            throw new java.lang.RuntimeException("Invalid operator");

        }
    }

    /**
     * whether a valid opeartor is entered
     *     
     * @return boolean
     */
    public boolean isOperator() {
        if (operator == '+' || operator == '-' || operator == '*'
            || operator == '/')
            return true;
        else
            return false;
    }
    
    public Double getValue() { 
        return this.operand;
    }
    
    public Character getOperator() { 
        return this.operator;
    }


    
}
