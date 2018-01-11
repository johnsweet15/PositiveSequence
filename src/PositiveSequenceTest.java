import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.NoSuchElementException;

import org.junit.Test;

public class PositiveSequenceTest {
    @Test
    public void testNoStaticUnlessFinalAllPrivateFields() {
        Field[] iFields = PositiveSequence.class.getDeclaredFields();

        for (Field f : iFields) {
            if (!f.isSynthetic()) {
                assertTrue ( "Field \""+f.getName()+"\" should be private", Modifier.isPrivate( f.getModifiers() ));
                assertFalse( "Field \""+f.getName()+"\" should be final",   Modifier.isStatic ( f.getModifiers() ) &&
                        !Modifier.isFinal  ( f.getModifiers() ));
            }
        }
    }

    @Test(expected=NoSuchElementException.class)
    public void testNoMinusOne() {
        PositiveSequence.process( 1, 2, 3, 4 );
    }
    @Test(expected=IllegalArgumentException.class)
    public void testTwoNegative1sAt01() {
        PositiveSequence.process( -1, -1, 3, 4 );
    }
    @Test(expected=IllegalArgumentException.class)
    public void testTwoNegative1sAt04() {
        PositiveSequence.process( -1, 2, 3, -1 );
    }
    @Test(expected=IllegalArgumentException.class)
    public void testTwoNegative1sAt12() {
        PositiveSequence.process( 1, -1, -1, 4 );
    }
    @Test(expected=IllegalArgumentException.class)
    public void testAllNegative1s() {
        PositiveSequence.process( -1, -1, -1, -1 );
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNotSorted01() {
        PositiveSequence.process( 2, 2, 3, -1 );
    }
    @Test(expected=IllegalArgumentException.class)
    public void testNotSorted12() {
        PositiveSequence.process( -1, 2, 1, 4 );
    }
    @Test(expected=IllegalArgumentException.class)
    public void testNotSorted23() {
        PositiveSequence.process( 1, -1, 3, 3 );
    }

    @Test(expected=IllegalArgumentException.class)
    public void testLessThan1At0() {
        PositiveSequence.process( 0, 2, 3, 4 );
    }
    @Test(expected=IllegalArgumentException.class)
    public void testLessThan1At1() {
        PositiveSequence.process( 1, -2, 3, 4 );
    }
    @Test(expected=IllegalArgumentException.class)
    public void testLessThan1At2() {
        PositiveSequence.process( 1, 2, -3, 4 );
    }
    @Test(expected=IllegalArgumentException.class)
    public void testLessThan1At3() {
        PositiveSequence.process( 1, 2, 3, 0 );
    }

    @Test
    public void test01() {
        int actual   = PositiveSequence.process( 1, 2, -1, 4 );
        int expected = 3;
        assertEquals( "incorrect result", expected, actual);
    }
    @Test
    public void test02() { // keep
        int actual   = PositiveSequence.process( 2, 4, 8, -1 );
        int expected = 16;
        assertEquals( "incorrect result", expected, actual);
    }
    @Test
    public void test04() {
        int actual   = PositiveSequence.process( 5, -1, 11, 14 );
        int expected = 8;
        assertEquals( "incorrect result", expected, actual);
    }
    @Test
    public void test05() { // keep
        int actual   = PositiveSequence.process( -1, 2, 4, 6 );
        int expected = -1;
        assertEquals( "incorrect result", expected, actual);
    }
    @Test
    public void test06() {
        int actual   = PositiveSequence.process( 1, 2, -1, 4 );
        int expected = 3;
        assertEquals( "incorrect result", expected, actual);
    }
    @Test
    public void test07() {
        int actual   = PositiveSequence.process( 2, 4, 8, -1 );
        int expected = 16;
        assertEquals( "incorrect result", expected, actual);
    }
    @Test
    public void test09() {
        int actual   = PositiveSequence.process( 5, -1, 11, 14 );
        int expected = 8;
        assertEquals( "incorrect result", expected, actual);
    }
    @Test
    public void test10() { // keep
        int actual   = PositiveSequence.process( -1, 2, 4, 6 );
        int expected = -1;
        assertEquals( "incorrect result", expected, actual);
    }
    @Test
    public void test11() {
        int actual   = PositiveSequence.process( -1, 1006, 2006, 3006 );
        int expected = 6;
        assertEquals( "incorrect result", expected, actual);
    }
    @Test
    public void test12() { // keep
        int actual   = PositiveSequence.process( -1, 20, 120, 220 );
        int expected = -1;
        assertEquals( "incorrect result", expected, actual);
    }
    @Test
    public void test13() { // keep
        int actual   = PositiveSequence.process( -1, 27, 81, 243 );
        int expected = 9;
        assertEquals( "incorrect result", expected, actual);
    }
    @Test
    public void test16() { // keep
        int actual   = PositiveSequence.process( -1, 1, 2, 3 );
        int expected = -1;
        assertEquals( "incorrect result", expected, actual);
    }
    @Test
    public void test19() {
        int actual   = PositiveSequence.process( 6, -1, 22, 30 );
        int expected = 14;
        assertEquals( "incorrect result", expected, actual);
    }
    @Test
    public void test21() { // keep
        int actual   = PositiveSequence.process( 90, -1, 360, 720 );
        int expected = 180;
        assertEquals( "incorrect result", expected, actual);
    }
    @Test
    public void test22() {
        int actual   = PositiveSequence.process( 9997, -1, 9999, 10000 );
        int expected = 9998;
        assertEquals( "incorrect result", expected, actual);
    }
    @Test
    public void test24() {
        int actual   = PositiveSequence.process( 12, 17, -1, 27 );
        int expected = 22;
        assertEquals( "incorrect result", expected, actual);
    }
    @Test
    public void test26() {
        int actual   = PositiveSequence.process( 7, 14, -1, 56 );
        int expected = 28;
        assertEquals( "incorrect result", expected, actual);
    }
    @Test
    public void test28() {
        int actual   = PositiveSequence.process( 9700, 9800, -1, 10000 );
        int expected = 9900;
        assertEquals( "incorrect result", expected, actual);
    }
    @Test
    public void test29() {
        int actual   = PositiveSequence.process( 25, 27, 29, -1 );
        int expected = 31;
        assertEquals( "incorrect result", expected, actual);
    }
    @Test
    public void test30() {
        int actual   = PositiveSequence.process( 132, 232, 332, -1 );
        int expected = 432;
        assertEquals( "incorrect result", expected, actual);
    }
    @Test
    public void test32() {
        int actual   = PositiveSequence.process( 32, 132, 232, -1 );
        int expected = 332;
        assertEquals( "incorrect result", expected, actual);
    }
    @Test
    public void test33() {
        int actual   = PositiveSequence.process( 450, 900, 1800, -1 );
        int expected = 3600;
        assertEquals( "incorrect result", expected, actual);
    }
    @Test
    public void test35() {
        int actual   = PositiveSequence.process( 1, 100, 10000, -1 );
        int expected = 1000000;
        assertEquals( "incorrect result", expected, actual);
    }   
    @Test
    public void testNoSolution1() {
        {
            int actual   = PositiveSequence.process( -1, 27, 81, 244 );
            int expected = -1;
            assertEquals( "incorrect result", expected, actual);
        } {
            int actual   = PositiveSequence.process( -1, 3, 6, 12 );
            int expected = -1;
            assertEquals( "incorrect result", expected, actual);
        } {
            int actual   = PositiveSequence.process( -1, 37, 185, 925 );
            int expected = -1;
            assertEquals( "incorrect result", expected, actual);
        } {
            int actual   = PositiveSequence.process( -1, 9970, 9990, 10000 );
            int expected = -1;
            assertEquals( "incorrect result", expected, actual);
        }
    }
    @Test
    public void testNoSolution2() {
        {
            int actual   = PositiveSequence.process( 6, -1, 22, 31 );
            int expected = -1;
            assertEquals( "incorrect result", expected, actual);
        } {
            int actual   = PositiveSequence.process( 9, -1, 36, 108 );
            int expected = -1;
            assertEquals( "incorrect result", expected, actual);
        }
    }
    @Test
    public void testNoSolution3() {
        {
            int actual   = PositiveSequence.process( 13, 17, -1, 27 );
            int expected = -1;
            assertEquals( "incorrect result", expected, actual);
        } {
            int actual   = PositiveSequence.process( 7, 21, -1, 56 );
            int expected = -1;
            assertEquals( "incorrect result", expected, actual);
        } {
            int actual   = PositiveSequence.process( 127, 256, -1, 1024 );
            int expected = -1;
            assertEquals( "incorrect result", expected, actual);
        } {
            int actual   = PositiveSequence.process( 7, 8, -1, 21 );
            int expected = -1;
            assertEquals( "incorrect result", expected, actual);
        }
    }
    @Test
    public void testNoSolution4() {
        {
            int actual   = PositiveSequence.process( 26, 27, 29, -1 );
            int expected = -1;
            assertEquals( "incorrect result", expected, actual);
        } {
            int actual   = PositiveSequence.process( 450, 900, 2700, -1 );
            int expected = -1;
            assertEquals( "incorrect result", expected, actual);
        }
    }
}