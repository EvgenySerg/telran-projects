package bitoperations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BitOperationsTest {


    @Test
    void getBitValue() {
        int value=0x100823;
        assertEquals(1, BitOperations.getBitValue(value,0));
        assertEquals(1, BitOperations.getBitValue(value,5));
        assertEquals(0, BitOperations.getBitValue(value,4));
        assertEquals(0, BitOperations.getBitValue(value,6));
    }

    @Test
    void setBitValue() {
        int value=0x100823;                    //‭000100000000100000100011‬
        //                                                     9876543210
        long []expectedValues={
                //                                            9876543210
                Integer.parseInt("000100000000100000110011", 2),  // 4 bit
                Integer.parseInt("000100000000100100100011", 2),  // 8 bit
                Integer.parseInt("100100000000100000100011", 2),  // last 23 bit
                0,
        };
        long []resValues={
                BitOperations.setBitValue(value, 4),
                BitOperations.setBitValue(value, 8),
                BitOperations.setBitValue(value, 23),
                BitOperations.setBitValue(value,-10)
        };
        assertArrayEquals(expectedValues, resValues);
    }

    @Test
    void resetBitValue() {
        int value=0x100823;                    //‭000100000000100000100011‬
        long []expectedValues={
                //                                9876543210
                Integer.parseInt("000100000000100000100010", 2),  // 0 bit
                Integer.parseInt("000100000000000000100011", 2),  // 11 bit
                Integer.parseInt("000000000000100000100011", 2),  // 20 bit
                -1,
        };
        long []resValues={
                BitOperations.resetBitValue(value, 0),
                BitOperations.resetBitValue(value, 11),
                BitOperations.resetBitValue(value, 20),
                BitOperations.resetBitValue(value,-10)
        };
        assertArrayEquals(expectedValues, resValues);
    }

    @Test
    public void toggleBitValue() {
        int value=0x100823;                    //‭000100000000100000100011‬
        long []expectedValues={
                //                                9876543210
                Integer.parseInt("000100000000100000100010", 2),  // 0 bit
                Integer.parseInt("000100000000100010100011", 2),  // 7 bit
                Integer.parseInt("000100000000000000100011", 2),  // 11 bit
                -1,
        };
        long []resValues={
                BitOperations.toggleBitValue(value, 0),
                BitOperations.toggleBitValue(value, 7),
                BitOperations.toggleBitValue(value, 11),
                BitOperations.toggleBitValue(value,-10)
        };
        assertArrayEquals(expectedValues, resValues);
    }

    @Test
    public void fastMultiply10() {
        long[] expectedValues = {0, 5000, -4000};
        long[] inputValues = {
                BitOperations.fastMultiply10(0),
                BitOperations.fastMultiply10(500),
                BitOperations.fastMultiply10(-400)};
        assertArrayEquals(expectedValues, inputValues);
    }

    @Test void fastDivision10(){
        long[] expectedValues = {0, 50, -40};
        long[] inputValues = {
                BitOperations.fastDivision10(0),
                BitOperations.fastDivision10(500),
                BitOperations.fastDivision10(-400)};
        assertArrayEquals(expectedValues, inputValues);
    }

    @Test
    void isNbitsSum() {

        boolean[] expValues = {
                false,
                true,
                true,
                true,
                true
        };
        assertArrayEquals(expValues, new boolean[]{
                BitOperations.isNbitsSum(0, 1),
                BitOperations.isNbitsSum(0xffff, 105),
                BitOperations.isNbitsSum(Long.MAX_VALUE, 1953),
                BitOperations.isNbitsSum(0, 0),
                BitOperations.isNbitsSum(0x45FD5L, 101),
        });



    }

    @Test
    void is2BitSum() {
        boolean[] expValues = {
                false, // 0
                true,  // 1
                true,  // 2
                true,  // 3
                true,  // 4
                true,  // 5
                false, // 6
                false, // 7
                false, // 8
                false, // 9
                true,  // 10
                false, // 11
                true, // 12
                true,   //13
                true    // 14
        };
        assertArrayEquals(expValues, new boolean[]{
                BitOperations.is2BitSum(0, 1),                  // 0
                BitOperations.is2BitSum(0xffff, 2),             // 1
                BitOperations.is2BitSum(Long.MAX_VALUE, 15),            // 2
                BitOperations.is2BitSum(0, 0),                  // 3
                BitOperations.is2BitSum(0x45FD5L, 20),          // 4
                BitOperations.is2BitSum( 0xC000000000000000L, 125),       //5
                BitOperations.is2BitSum(Long.MAX_VALUE, 130),           // 6
                BitOperations.is2BitSum(Long.MIN_VALUE, 10),            // 7

                BitOperations.is2BitSum(Long.MAX_VALUE, 62+62),         // 8

                BitOperations.is2BitSum(0x1001, 10),            // 9
                BitOperations.is2BitSum(0x1001, 12) ,           // 10
                BitOperations.is2BitSum(1,1),                   // 11
                BitOperations.is2BitSum(-1,1),                  // 12
                BitOperations.is2BitSum(0x2AAA, 10),             // 13
                BitOperations.is2BitSum(0xaa, 8)                 // 14
        });
    }
}