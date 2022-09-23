import student.TestCase;

/**
 * Contains all the tests of classes
 * 
 * @author Kerem Bozgan kerembozgan
 * @version 2022-09-03
 */
public class Test extends TestCase {
    
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

    }
}