import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ArrayIntTest {
    @Test
    void searchUnsorted() {
        int[] arNS = {4, 7, 2, 3, 10, 3};
        ArrayInt arrayIntNotSorted = new ArrayInt(arNS);
        assertEquals(1,arrayIntNotSorted.search(7));
        assertEquals(-6,arrayIntNotSorted.search(6));
    }

    @Test
    void searchSorted() {
        int ar1[] = {20, 25, 40, 100};
        ArrayInt arrayIntSorted = new ArrayInt(ar1);
        assertEquals(0, arrayIntSorted.search(20));
        assertEquals(-1, arrayIntSorted.search(5));
        assertEquals(-4, arrayIntSorted.search(77));
        assertEquals(-3, arrayIntSorted.search(26));
        assertEquals(-5, arrayIntSorted.search(500));
    }

    @Test
    void getValue(){
        int ar1[] = {20, 25, 40, 100};
        ArrayInt arrayIntSorted = new ArrayInt(ar1);
        assertEquals(100, (int)arrayIntSorted.getValue(3));
        assertNull(arrayIntSorted.getValue(4));
    }

    @Test
    void addValue() {
        ArrayInt arrayInt = new ArrayInt();
        fillArray(arrayInt);
        testArray(arrayInt);

    }

    private void fillArray(ArrayInt arrayInt, int[] ar) {
        for (int i = 0; i < ar.length; i++) {
            arrayInt.addValue(i);
            ar[i]=i;
        }
    }

    private boolean testArray(ArrayInt arrayInt ) {
        for (int i = 0; i < arrayInt.size;i++) {
            assertEquals(i,(int)arrayInt.getValue(i));
        }
        return true;
    }

    @Test
    void addValueToSortedAdrray() {
        int[] ar=new int[]{10,20,33,40,50};
        ArrayInt arrayInt = new ArrayInt(ar);
        arrayInt.addValue(25);
        arrayInt.addValue(2);
        arrayInt.addValue(33);

        assertEquals(3, arrayInt.search(25));
        assertEquals(0, arrayInt.search(2));
//        assertEquals(0, arrayInt.search(2));
    }

    @Test
    void deleteValueFromArray() {
        int[] ar = new int[]{10, 20, 30, 40, 50};
        int[] arExp = new int[]{10, 30, 40, 50};
        ArrayInt arrayInt = new ArrayInt(ar);
        arrayInt.deleteValue(20);
        assertTrue(testArrays(arExp, arrayInt));
    }

    private boolean testArrays(int[] expArray, ArrayInt ar) {
        for (int i=0;i<ar.size;i++){
            assertEquals(expArray[i], ar.getAr()[i]);
        }
        return true;
    }

    @Test
    public void shuffle() {
        int ar[] = new int[10000];
        ArrayInt array= new ArrayInt();
        fillArray(array, ar);
        ArrayInt arraySh = new ArrayInt(ar);
        arraySh.shuffle();
        testAfterShuffle(array, arraySh);
        ArrayInt arraySh1 = new ArrayInt(ar);
        arraySh1.shuffle();
        testAfterShuffle(arraySh, arraySh1);

        fail("The test does not implemented");
    }

    private void testAfterShuffle(ArrayInt array, ArrayInt arraySh) {
        int[] ar1 = getArray(array);
        int[] ar2=getArray(arraySh);
        testSameNumbers(ar1, ar2);
        assertFalse(Arrays.equals(ar1,ar2));
    }

    private void testSameNumbers(int[] ar1, int[] ar2) {

        int[] ar1Sorted = Arrays.copyOf(ar1, ar1.length);
        int[] ar2Sorted = Arrays.copyOf(ar2, ar2.length);
        Arrays.sort(ar1Sorted);
        Arrays.sort(ar2Sorted);
        assertArrayEquals(ar1,ar2);
    }

    private int[] getArray(ArrayInt array) {
        int size=array.getSize();
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = array.getValue(i);
        }
    }

    @Test
    public void sort() {
        int ar[]=new int[10000];
        ArrayInt arrayInt = new ArrayInt();
        fillArray(arrayInt,ar);
        arrayInt.shuffle();
        arrayInt.sort();
    }

    private void testSort(ArrayInt arrayInt) {
        assertTrue(arrayInt.isSorted());
        int size=arrayInt.getSize();
        for (int i = 1; i < size; i++) {
           assertTrue(arrayInt.getValue(i-1)<=arrayInt.getValue(i));
        }
    }

}