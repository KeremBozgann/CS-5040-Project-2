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

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import student.TestCase;

/**
 * Contains all the tests of classes I have indivually
 * constracted for validation 
 * 
 * @author Kerem Bozgan kerembozgan
 * @version 2022-09-03
 */

public class Test extends TestCase {
    public final TextFromStandardInputStream systemInMock =
        emptyStandardInputStream();

    private static ByteArrayOutputStream localOut, localErr;
    private static ByteArrayInputStream localIn;
    private static PrintStream sysOut, sysErr;
    private static InputStream sysIn;
    public static final String[] empty = {};


    public static void setCapture() {
        localOut = new ByteArrayOutputStream();
        localErr = new ByteArrayOutputStream();
        System.setOut(null);
        System.setErr(null);
        System.setOut(new PrintStream(localOut));
        System.setErr(new PrintStream(localErr));
    }
    
    
    
    
    
    
    private Double delta = 0.001; 
    public void testToken() { 
        Token tok  = new Token('+'); 
        
        //test apply operator
        assertEquals(tok.applyOperator(1.0,1.0), 2.0, delta);
        
        //test getIdentity:
        assertEquals(tok.getIdentity(), 0.0, delta);
    }
    
    public void testExpressionEvaluator (){ 
        assertEquals(ExpressionEvaluator.isBalanced("(* r r)"), true);
        assertEquals(ExpressionEvaluator.isBalanced("(* (/ 3 w l)"), false);
        assertEquals(ExpressionEvaluator.isBalanced("(* (/ 3 w l)(* r r))"), true);
        assertEquals(ExpressionEvaluator.isBalanced("(* (/ 3 w l)) (* (r r)))"), false);
        assertEquals(ExpressionEvaluator.isBalanced("(* (/ 3 w l) * ( r r))"), true);
        assertEquals(ExpressionEvaluator.isBalanced("((* (/ 3 w l) (* r r))"), false);
        
        assertEquals(ExpressionEvaluator.evaluate("(* ( +  8 2) 7)"), 70.0, delta);
        

        
        assertEquals(ExpressionEvaluator.evaluate("(+ (- 6 7) (* 234 455 256) (/ (/ 3) (*) (-2 3 1)))"), 2.7256318833333332E7, 100);
        assertEquals(ExpressionEvaluator.evaluate("(+ (- 632) (* 4 3 4) (/ (+ 32) (*) (- 21 3 1)))"), -582.1176470588, delta);

        assertEquals(ExpressionEvaluator.evaluate("(+ (- 12) (* 4 2 3) (/ (+4) (*) (- 2 3 1)))"), 10, delta);

        assertEquals(ExpressionEvaluator.evaluate("(+ (- 5)\n"
            + "  (* 4 4 5)\n"
            + "  (/ 3 2 2)\n"
            + "  (* 4 4)\n"
            + ")"), 91.75, delta);
        
        
//        System.out.println(ExpressionEvaluator.evaluate("((* (/ 3 w l) (* r r))"));
        
        assertEquals(ExpressionEvaluator.evaluate("(* 3255)"), 3255, delta);
//      System.out.println(ExpressionEvaluator.evaluate("()"));
//      System.out.println(ExpressionEvaluator.evaluate("( / * w l)"));
//      System.out.println(ExpressionEvaluator.evaluate("(1 + 2 + 3 + 4)"));
//      System.out.println(ExpressionEvaluator.evaluate("(* (/ 3 w l) (* (r r))"));
//      System.out.println(ExpressionEvaluator.evaluate("(* (/ 3 2 2 * 1 1))"));
//      System.out.println(ExpressionEvaluator.evaluate("(* (/ 3 a 2) (/) (* 5 5))"));

//        systemInMock.provideLines("4", "6");//
//        setCapture();
//        ExpressionEvaluator.evaluate("(+ a b)");
        

    }
    
//    public void testExpressionEvaluator2 (){ 
//
//        ExpressionEvaluator2.evaluate("(* ( +  width depth) 7)");
//        

       
//        systemInMock.provideLines("4", "6");//
//        setCapture();
//        ExpressionEvaluator.evaluate("(+ a b)");
        

//    }
    
}