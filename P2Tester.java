
/**
 * On Mac/Linux:
 * javac -cp .:junitTester.jar *.java # compile everything
 * java -cp .:junitTester.jar P2Tester # run tests
 *
 * On windows replace colons with semicolons: (: with ;)
 * javac -cp .;junitTester.jar *.java # compile everything
 * java -cp .;junitTester.jar P2Tester # run tests
 */
import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class P2Tester {
    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main("P2Tester");
    }

    // *******************************************Token Class
    // Tester*************************
    private final double ERR = 0.000001;

    @Test
    public void Token_construct_def_01() {
        Token s1 = new Token('/');
        Token s2 = new Token(25.5);

        assertEquals("Token constructor is incorrect", new Double(25.5), s2
            .getValue());
        assertEquals("Token constructor is incorrect", new Character('/'), s1
            .getOperator());
        assertEquals("Token constructor is incorrect", new Character(' '), s2
            .getOperator());
        assertEquals("Token constructor is incorrect", new Double(0.0), s1
            .getValue());
        assertTrue("Token constructor is incorrect", s1.isOperator());
        assertFalse("Token constructor is incorrect", s2.isOperator());
    }

    Character[] res = { '*', '/', '+', '-' };

    void Token_getOperator(int a) {
        Token t = new Token(res[a]);
        assertEquals("Token getOperator is incorrect", res[a], t.getOperator());
    }


    @Test
    public void Token_getOperator_00() {
        Token_getOperator(0);
    }


    @Test
    public void Token_getOperator_01() {
        Token_getOperator(1);
    }


    @Test
    public void Token_getOperator_02() {
        Token_getOperator(2);
    }


    @Test
    public void Token_getOperator_03() {
        Token_getOperator(3);
    }

    Double[] resV = { 1879.55, 22.0, -55.69, 0.55 };

    void Token_getValue(int a) {
        Token t = new Token(resV[a]);
        assertEquals("Token getValue is incorrect", resV[a], t.getValue());
    }


    @Test
    public void Token_getValue_00() {
        Token_getValue(0);
    }


    @Test
    public void Token_getValue_01() {
        Token_getValue(1);
    }


    @Test
    public void Token_getValue_02() {
        Token_getValue(2);
    }


    @Test
    public void Token_getValue_03() {
        Token_getValue(3);
    }

    Double[][] values = { { 1879.55, 22.0 }, { -55.69, 0.55 }, { 0.0, 45.5 }, {
        -2589.0, 4589.0 } };
    char[] op = { '*', '+', '-', '/' };
    Double results[] = { 41350.1, -55.14, -45.5, -0.5641752015689693 };

    public void Token_applyOperator(int a) {

        Token t = new Token(op[a]);
        Double v = t.applyOperator(values[a][0], values[a][1]);

        String errMsg1 = String.format("Token applyOperator() is incorrect");
        assertEquals(errMsg1, results[a], v);
    }


    @Test
    public void Token_applyOperator_00() {
        Token_applyOperator(0);
    }


    @Test
    public void Token_applyOperator_01() {
        Token_applyOperator(1);
    }


    @Test
    public void Token_applyOperator_02() {
        Token_applyOperator(2);
    }


    @Test
    public void Token_applyOperator_03() {
        Token_applyOperator(3);
    }

    Double resultsI[] = { 1.0, 0.0, 0.0, 1.0 };

    public void Token_getIdentity(int a) {

        Token t = new Token(op[a]);

        String errMsg1 = String.format("Token getIdentity() is incorrect");
        assertEquals(errMsg1, resultsI[a], t.getIdentity());
    }


    @Test
    public void Token_getIdentity_00() {
        Token_getIdentity(0);
    }


    @Test
    public void Token_getIdentity_01() {
        Token_getIdentity(1);
    }


    @Test
    public void Token_getIdentity_02() {
        Token_getIdentity(2);
    }


    @Test
    public void Token_getIdentity_03() {
        Token_getIdentity(3);
    }

    boolean resultsB[] = { true, true, false, false };

    public void Token_takesZeroOperands(int a) {

        Token t = new Token(op[a]);

        String errMsg1 = String.format(
            "Token takesZeroOperands() is incorrect");
        assertEquals(errMsg1, resultsB[a], t.takesNoOperands());
    }


    @Test
    public void Token_takesZeroOperands_00() {
        Token_takesZeroOperands(0);
    }


    @Test
    public void Token_takesZeroOperands_01() {
        Token_takesZeroOperands(1);
    }


    @Test
    public void Token_takesZeroOperands_02() {
        Token_takesZeroOperands(2);
    }


    @Test
    public void Token_takesZeroOperands_03() {
        Token_takesZeroOperands(3);
    }


    @Test
    public void Token_isOperator_01() {
        Token s1 = new Token('*');
        Token s2 = new Token(25.5);

        assertTrue("Token constructor is incorrect", s1.isOperator());
        assertFalse("Token constructor is incorrect", s2.isOperator());
    }

    // *******************************ExpressionEvaluator
    // Tests*********************
    String[] bal = { "(+ ((- 6 7) (* 234 455 256) (/ (+ 3) (*) (-2 3 1)",
        "(/ ((- 632) (/ ) 4 3 4) (/ (+ 32) (*) (- 21 3 1)))",
        "(+ (- 632) (* 4 3 4) (/ (+ 32))) (*) (- 21 3 1)))",
        "(+ (/ 2) (* 2 2) (/ (((- 10) (+112) (- 2 1 )))",
        "(+ (/ 2) (* 2 2) (/ (- 10) (+112) ((- 2 (1 ))))", "((((())))" };
    boolean[] resE = { false, true, false, false, false, false };

    public void ExpressionEvaluator_isBalanced(int a) {

        String errMsg1 = String.format(
            "ExpressionEvaluator isBalanced() is incorrect");
        assertEquals(errMsg1, resE[a], ExpressionEvaluator.isBalanced(bal[a]));
    }


    @Test
    public void ExpressionEvaluator_isBalanced_00() {
        ExpressionEvaluator_isBalanced(0);
    }


    @Test
    public void ExpressionEvaluator_isBalanced_01() {
        ExpressionEvaluator_isBalanced(1);
    }


    @Test
    public void ExpressionEvaluator_isBalanced_02() {
        ExpressionEvaluator_isBalanced(2);
    }


    @Test
    public void ExpressionEvaluator_isBalanced_03() {
        ExpressionEvaluator_isBalanced(3);
    }


    @Test
    public void ExpressionEvaluator_isBalanced_04() {
        ExpressionEvaluator_isBalanced(4);
    }


    @Test
    public void ExpressionEvaluator_isBalanced_05() {
        ExpressionEvaluator_isBalanced(5);
    }

    String excep[] = { "(+ ((- a b) (* 234 455 256) (/ (+ 3) (*) (-2 3 1)))",
        "(+  (* 234 455 256) (/ (/) (*) (-2 3 1)))",
        "(+  (/ (/ 6) (*) (-2 0 1))))",
        "(+ (- 6 7) (* / 234 455 256) (/ (/ 3) (*) (-2 3 1)))", "( )",
        "( 9 + 8 + 9 ) ", "(*(-)(*25))" };

    public void ExpressionEvaluator_Exception(int a, String st) {
        try {
            ExpressionEvaluator.evaluate(excep[a]);
            fail(st);
        }
        catch (RuntimeException e) {
            throw e;
        }

    }


    @Test(expected = RuntimeException.class)
    public void ExpressionEvaluator_Exception_00() {

        String st = "Bad Expression: It is not balanced";
        ExpressionEvaluator_Exception(0, st);
    }


    @Test(expected = RuntimeException.class)
    public void ExpressionEvaluator_Exception_01() {

        String st = " operator / requires at least one operand";
        ExpressionEvaluator_Exception(1, st);
    }


    @Test(expected = RuntimeException.class)
    public void ExpressionEvaluator_Exception_02() {

        String st = " a is not a legal expression operator";
        ExpressionEvaluator_Exception(2, st);
    }


    @Test(expected = RuntimeException.class)
    public void ExpressionEvaluator_Exception_03() {

        String st = "found an operator when we should not";
        ExpressionEvaluator_Exception(3, st);
    }


    @Test(expected = RuntimeException.class)
    public void ExpressionEvaluator_Exception_04() {

        String st = "Empty expression ";
        ExpressionEvaluator_Exception(4, st);
    }


    @Test(expected = RuntimeException.class)
    public void ExpressionEvaluator_Exception_05() {

        String st = "Bad Expression: need an operator for the data";
        ExpressionEvaluator_Exception(5, st);
    }


    @Test(expected = RuntimeException.class)
    public void ExpressionEvaluator_Exception_06() {

        String st = "operator - requires at least one operand";
        ExpressionEvaluator_Exception(6, st);
    }

    String[] expEval = { "(+ (- 6 7) (* 234 455 256) (/ (/ 3) (*) (-2 3 1)))",
        "(+ (- 632) (* 4 3 4) (/ (+ 32) (*) (- 21 3 1)))",
        "(+ (/ 2) (* 2 2) (/ (- 10) (+112) (- 2 1 )))",
        " (+ (- 6) (/ 2 4 2) (* (* 3 1) (+) (- 2 3 1)))",
        " (* (+ 6 3 2) (* 4 34) (/ (+ 32) (/ 20) (+ 21 (- 3) (- 1))))",
        "(+ (+ 112) (* 2 1 ))" };
    Double resEval[] = { 2.7256318833333332E7, -582.1176470588,
        4.410714285714286, -5.75, 56320.0, 114.0 };

    public void ExpressionEvaluator_evaluate(int a) {

        String errMsg1 = String.format(
            "ExpressionEvaluator evaluate(%s) is incorrect", expEval[a]);
        assertEquals(errMsg1, resEval[a], ExpressionEvaluator.evaluate(
            expEval[a]), ERR);
    }


    @Test
    public void ExpressionEvaluator_evaluate_00() {
        ExpressionEvaluator_evaluate(0);
    }


    @Test
    public void ExpressionEvaluator_evaluate_01() {
        ExpressionEvaluator_evaluate(1);
    }


    @Test
    public void ExpressionEvaluator_evaluate_02() {
        ExpressionEvaluator_evaluate(2);
    }


    @Test
    public void ExpressionEvaluator_evaluate_03() {
        ExpressionEvaluator_evaluate(3);
    }


    @Test
    public void ExpressionEvaluator_evaluate_04() {
        ExpressionEvaluator_evaluate(4);
    }


    @Test
    public void ExpressionEvaluator_evaluate_05() {
        ExpressionEvaluator_evaluate(5);
    }

    String sep = System.getProperty("line.separator");
    @Rule
    public final TextFromStandardInputStream systemInMock =
        emptyStandardInputStream();

    String[] expEval2_1 = { "(+ (- a) (* 3 3 4) (/ 3 a b) (* r r))",
        "(+ (- 6) (* 3 3 4) (/ 3 w l) (* r r))",
        "(+ (- l) (* 3 3 4)  (* (/ 3 s d) (* k l)))",
        "(+ (- v2v) (* 3 3 4) (/  w3l) (* (+) r))",
        "(+ (- r) (* 3 3 4) (/ 3 g l) (* v r))" };// ,"(+ (- 632) (* 4 3 4) (/
                                                  // (+ 32) (*) (- 21 3
                                                  // 1)))","(+ (/ 2) (* 2 2) (/
                                                  // (- 10) (+112) (- 2 1 )))","
                                                  // (+ (- 6) (/ 2 4 2) (* (* 3
                                                  // 1) (+) (- 2 3 1)))", " (*
                                                  // (+ 6 3 2) (* 4 34) (/ (+
                                                  // 32) (/ 20) (+ 21 (- 3) (-
                                                  // 1))))","(+ (+ 112) (* 2 1
                                                  // ))"};
    Double resEval2_1[] = { 81.05, 130.0002, 38.27777777777777,
        -22.866666666666667, 640.000488519785 };// ,4.410714285714286,-5.75,
                                                // 56320.0,114.0};

    @Test
    public void ExpressionEvaluator_evaluate_06() {
        systemInMock.provideLines("4", "6", "10", "7", "7");//
        setCapture();
        String errMsg1 = String.format(
            "ExpressionEvaluator evaluate(%s) is incorrect", expEval2_1[0]);
        assertEquals(errMsg1, resEval2_1[0], ExpressionEvaluator.evaluate(
            expEval2_1[0]), ERR);
    }


    @Test
    public void ExpressionEvaluator_evaluate_07() {
        systemInMock.provideLines("100", "150", "10", "10");//
        setCapture();
        String errMsg1 = String.format(
            "ExpressionEvaluator evaluate(%s) is incorrect", expEval2_1[1]);
        assertEquals(errMsg1, resEval2_1[1], ExpressionEvaluator.evaluate(
            expEval2_1[1]), ERR);
    }


    @Test
    public void ExpressionEvaluator_evaluate_08() {
        systemInMock.provideLines("23", "6", "9", "7", "65");//
        setCapture();
        String errMsg1 = String.format(
            "ExpressionEvaluator evaluate(%s) is incorrect", expEval2_1[2]);
        assertEquals(errMsg1, resEval2_1[2], ExpressionEvaluator.evaluate(
            expEval2_1[2]), ERR);
    }


    @Test
    public void ExpressionEvaluator_evaluate_09() {
        systemInMock.provideLines("12", "69", "2", "5", "3");//
        setCapture();
        String errMsg1 = String.format(
            "ExpressionEvaluator evaluate(%s) is incorrect", expEval2_1[3]);
        assertEquals(errMsg1, resEval2_1[3], ExpressionEvaluator.evaluate(
            expEval2_1[3]), ERR);
    }


    @Test
    public void ExpressionEvaluator_evaluate_10() {
        systemInMock.provideLines("44", "69", "89", "12", "54");//
        setCapture();
        String errMsg1 = String.format(
            "ExpressionEvaluator evaluate(%s) is incorrect", expEval2_1[4]);
        assertEquals(errMsg1, resEval2_1[4], ExpressionEvaluator.evaluate(
            expEval2_1[4]), ERR);
    }

    // *******************************ExpressionEvaluator2
    // Tests*********************

    String[] expEval2 = {
        "(+ (- height) (* 3 3 4) (/ 3 width length) (* radius radius))",
        "(+ (- 6) (* 3 3 4) (/ 3 width length) (* radius radius))",
        "(+ (- l) (* 3 3 4)  (* (/ 3 s d) (* k ll)))",
        "(+ (- var2var) (* 3 3 4) (/  wt3len) (* (+) rad))",
        "(+ (- rd) (* 3 3 4) (/ 3 glk len) (* var rfs))" };// ,"(+ (- 632) (* 4
                                                           // 3 4) (/ (+ 32) (*)
                                                           // (- 21 3 1)))","(+
                                                           // (/ 2) (* 2 2) (/
                                                           // (- 10) (+112) (- 2
                                                           // 1 )))"," (+ (- 6)
                                                           // (/ 2 4 2) (* (* 3
                                                           // 1) (+) (- 2 3
                                                           // 1)))", " (* (+ 6 3
                                                           // 2) (* 4 34) (/ (+
                                                           // 32) (/ 20) (+ 21
                                                           // (- 3) (-
                                                           // 1))))","(+ (+ 112)
                                                           // (* 2 1 ))"};
    Double resEval2[] = { 81.05, 130.0002, 38.27777777777777,
        -22.866666666666667, 640.000488519785 };// ,4.410714285714286,-5.75,
                                                // 56320.0,114.0};

    @Test
    public void ExpressionEvaluator2_evaluate_00() {
        systemInMock.provideLines("4", "6", "10", "7", "7");//
        setCapture();
        String errMsg1 = String.format(
            "ExpressionEvaluator2 evaluate(%s) is incorrect", expEval2[0]);
        assertEquals(errMsg1, resEval2[0], ExpressionEvaluator2.evaluate(
            expEval2[0]), ERR);
    }


    @Test
    public void ExpressionEvaluator2_evaluate_01() {
        systemInMock.provideLines("100", "150", "10", "10");//
        setCapture();
        String errMsg1 = String.format(
            "ExpressionEvaluator2 evaluate(%s) is incorrect", expEval2[1]);
        assertEquals(errMsg1, resEval2[1], ExpressionEvaluator2.evaluate(
            expEval2[1]), ERR);
    }


    @Test
    public void ExpressionEvaluator2_evaluate_02() {
        systemInMock.provideLines("23", "6", "9", "7", "65");//
        setCapture();
        String errMsg1 = String.format(
            "ExpressionEvaluator2 evaluate(%s) is incorrect", expEval2[2]);
        assertEquals(errMsg1, resEval2[2], ExpressionEvaluator2.evaluate(
            expEval2[2]), ERR);
    }


    @Test
    public void ExpressionEvaluator2_evaluate_03() {
        systemInMock.provideLines("12", "69", "2", "5", "3");//
        setCapture();
        String errMsg1 = String.format(
            "ExpressionEvaluator2 evaluate(%s) is incorrect", expEval2[3]);
        assertEquals(errMsg1, resEval2[3], ExpressionEvaluator2.evaluate(
            expEval2[3]), ERR);
    }


    @Test
    public void ExpressionEvaluator2_evaluate_04() {
        systemInMock.provideLines("44", "69", "89", "12", "54");//
        setCapture();
        String errMsg1 = String.format(
            "ExpressionEvaluator2 evaluate(%s) is incorrect", expEval2[4]);
        assertEquals(errMsg1, resEval2[4], ExpressionEvaluator2.evaluate(
            expEval2[4]), ERR);
    }


    public void ExpressionEvaluator2_Exception(int a, String st) {
        try {
            ExpressionEvaluator2.evaluate(excep[a]);
            fail(st);
        }
        catch (RuntimeException e) {
            throw e;
        }

    }


    @Test(expected = RuntimeException.class)
    public void ExpressionEvaluator2_Exception_00() {

        String st = "Bad Expression: It is not balanced";
        ExpressionEvaluator2_Exception(0, st);
    }


    @Test(expected = RuntimeException.class)
    public void ExpressionEvaluator2_Exception_01() {

        String st = " operator / requires at least one operand";
        ExpressionEvaluator2_Exception(1, st);
    }


    @Test(expected = RuntimeException.class)
    public void ExpressionEvaluator2_Exception_02() {

        String st = " a is not a legal expression operator";
        ExpressionEvaluator2_Exception(2, st);
    }


    @Test(expected = RuntimeException.class)
    public void ExpressionEvaluator2_Exception_03() {

        String st = "found an operator when we should not";
        ExpressionEvaluator2_Exception(3, st);
    }


    @Test(expected = RuntimeException.class)
    public void ExpressionEvaluator2_Exception_04() {

        String st = "Empty expression ";
        ExpressionEvaluator2_Exception(4, st);
    }


    @Test(expected = RuntimeException.class)
    public void ExpressionEvaluator2_Exception_05() {

        String st = "Bad Expression: need an operator for the data";
        ExpressionEvaluator2_Exception(5, st);
    }


    @Test(expected = RuntimeException.class)
    public void ExpressionEvaluator2_Exception_06() {

        String st = "operator - requires at least one operand";
        ExpressionEvaluator2_Exception(6, st);
    }

    private static ByteArrayOutputStream localOut, localErr;
    private static ByteArrayInputStream localIn;
    private static PrintStream sysOut, sysErr;
    private static InputStream sysIn;
    public static final String[] empty = {};

    // @BeforeClass
    public static void setUp() {
        sysOut = System.out;
        sysErr = System.err;
        sysIn = System.in;
    }


    // @AfterClass
    public static void cleanUp() {
        unsetCapture();
        unsetInput();
    }


    public static void resetIO() {
        setCapture();
        setInput("");
    }


    // Before every test is run, reset the streams to capture
    // stdout/stderr
    // @Before
    public static void setCapture() {
        localOut = new ByteArrayOutputStream();
        localErr = new ByteArrayOutputStream();
        System.setOut(null);
        System.setErr(null);
        System.setOut(new PrintStream(localOut));
        System.setErr(new PrintStream(localErr));
    }


    public static String getCapture() {
        return localOut.toString().replaceAll("\\r?\\n", "\n");
    }


    // After every test, restore stdout/stderr
    // @After
    public static void unsetCapture() {
        System.setOut(null);
        System.setOut(sysOut);
        System.setErr(null);
        System.setErr(sysErr);
    }


    public static void setInput(String input) {
        localIn = new ByteArrayInputStream(input.getBytes(
            StandardCharsets.UTF_8));
        System.setIn(localIn);
    }


    public static void unsetInput() {
        try {
            if (localIn != null)
                localIn.close();
        }
        catch (Exception e) {
        }
        System.setIn(null);
        System.setIn(sysIn);
    }
}