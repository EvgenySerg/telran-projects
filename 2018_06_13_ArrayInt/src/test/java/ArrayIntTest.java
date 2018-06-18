import org.junit.jupiter.api.Test;

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
        int ar[] = new int[10000];
        ArrayInt arrayInt = new ArrayInt(new int[0]);
        fillArray(arrayInt);
        testArray(arrayInt);

    }

    private void fillArray(ArrayInt arrayInt) {
        for (int i = 0; i < 1000; i++) {
            arrayInt.addValue(i);
        }
    }

    private void testArray(ArrayInt arrayInt ) {
        for (int i = 0; i < arrayInt.size;i++) {
            assertEquals(arrayInt.getAr()[i],(int)arrayInt.getValue(i));
        }
    }

    @Test
    void addValueToSortedAdrray() {
        int[] ar=new int[]{10,20,33,40,50};
        ArrayInt arrayInt = new ArrayInt(ar);
        arrayInt.addValue(25);
        arrayInt.addValue(2);
        assertEquals(3, arrayInt.search(25));
        assertEquals(0, arrayInt.search(2));
//        assertEquals(0, arrayInt.search(2));
    }

    void deleteValueFromSortedArray() {
        int[] ar = new int[]{10, 20, 30, 40, 50};
        ArrayInt arrayInt = new ArrayInt(ar);
        arrayInt.deleteValue(20);

    }




}