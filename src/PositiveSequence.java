import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * Class that determines the pattern of a given sequence of 4 integers by returning the missing
 * number.  If the sequence contains more than one unknown number or any numbers less than 1 (besides)
 * the -1 representing the missing number) then an exception is thrown.  If there is no pattern -1 is 
 * returned. 
 * @author John Sweet
 * @version 01.18.2016
 *
 */

public class PositiveSequence {
    
    /**
     * The method that returns the missing number.
     * @param a the first number in the sequence
     * @param b the second number
     * @param c the third number
     * @param d the fourth number
     * @return  the missing number
     */
    public static int process(int a, int b, int c, int d) {
        double answer = -1;
        int position = 0;
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        checkForException(list);
        list.removeAll(list);
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        
        //determine position of missing number
        int i = 0;
        while(i < list.size()) {
            if(list.get(i) == -1) {
                position = i;
                break;
            }
            i++;
        }
        
        //determine missing number based on its position and whether the sequence is geometric or arithmetic
        if(position == 0) {
            if(isArithmeticEndPos(list.get(1), list.get(2), list.get(3))) {
                answer = list.get(1) - (list.get(3) - list.get(2));
            }
            else if(isGeometricEndPos(list.get(1), list.get(2), list.get(3))) {
                answer = list.get(1) / (double)(list.get(3) / list.get(2));
            }
        }
        if(position == 1) {
            if(isArithmeticMiddlePos(list.get(0), list.get(2), list.get(3))) {
                answer = list.get(0) + (list.get(3) - list.get(2));
            }
            else if(isGeometricMiddlePos(list.get(0), list.get(2), list.get(3))) {
                answer = list.get(0) * (double)(list.get(3) / list.get(2));
            }
        }
        if(position == 2) {
            if(isArithmeticMiddlePos(list.get(0), list.get(1), list.get(3))) {
                answer = list.get(1) + (list.get(1) - list.get(0));
            }
            else if(isGeometricMiddlePos(list.get(0), list.get(1), list.get(3))) {
                answer = list.get(1) * (double)(list.get(1) / list.get(0));
            }
        }
        if(position == 3) {
            if(isArithmeticEndPos(list.get(0), list.get(1), list.get(2))) {
                answer = list.get(2) + (list.get(2) - list.get(1));
            }
            else if(isGeometricEndPos(list.get(0), list.get(1), list.get(2))) {
                answer = list.get(2) * (double)(list.get(2) / list.get(1));
            }
        }
        
        if(answer <= 1 || answer % 1 != 0) {
            return -1;
        }
        return (int)answer;
    }
    
    /**
     * Determine if a sequence with the missing number in the middle is arithmetic.
     * @param a the first known number in the sequence
     * @param b the second known number
     * @param c the third known number
     * @return true if the sequence is arithmetic and false otherwise
     */
    public static boolean isArithmeticMiddlePos(int a, int b, int c) {
        return (b - a == c - b || ((2 * (b - a) == c - b)) || ((b - a == 2 * (c - b))));
    }
    
    /**
     * Determine if a sequence with the missing number at the beginning or end is arithmetic.
     * @param a the first known number in the sequence
     * @param b the second known number
     * @param c the third known number
     * @return true if the sequence is arithmetic and false otherwise
     */
    public static boolean isArithmeticEndPos(int a, int b, int c) {
        return (b - a == c - b);
    }
    
    /**
     * Determine if a sequence with the missing number in the middle is geometric.
     * @param a the first known number in the sequence
     * @param b the second known number
     * @param c the third known number
     * @return true if the sequence is geometric and false otherwise
     */
    public static boolean isGeometricMiddlePos(double a, double b, double c) {
        return (b / a == c / b || (2 * (b / a) == c / b) || (b / a == 2 * (c / b)));
    }
    
    /**
     * Determine if a sequence with the missing number at the beginning or end is geometric.
     * @param a the first known number in the sequence
     * @param b the second known number
     * @param c the third known number
     * @return true if the sequence is geometric and false otherwise
     */
    public static boolean isGeometricEndPos(double a, double b, double c) {
        return (b / a == c / b);
    }
    
    /**
     * Checks the sequence for any errors that can throw exceptions
     * @param arrayList the list received to be checked (the sequence)
     */
    public static void checkForException(ArrayList<Integer> arrayList) {
        int count = 0;
        ArrayList<Integer> arrayListSorted = new ArrayList<Integer>();
        arrayListSorted.addAll(arrayList);
        Collections.sort(arrayListSorted);
        
        for(int i : arrayList) {
            if(i == -1) {
                count++;
            }
        }
        
        if(arrayListSorted.contains(-1)) {
            arrayListSorted.remove(arrayListSorted.lastIndexOf(-1));
            arrayList.remove(arrayList.lastIndexOf(-1));
        }
        int count2 = 0;
        for(int i : arrayList) {
            if(i < -1 || i != arrayListSorted.get(count2) || i == 0) {
                throw new IllegalArgumentException();
            }
            count2++;
        }
        
        int j = 0;
        while(j < arrayList.size() - 1) {
            if(arrayList.get(j) == arrayList.get(j + 1)) {
                throw new IllegalArgumentException();
            }
            j++;
        }
        
        if(count > 1) {
            throw new IllegalArgumentException();
        }
        if(count < 1) {
            throw new NoSuchElementException();
        }
    }
}