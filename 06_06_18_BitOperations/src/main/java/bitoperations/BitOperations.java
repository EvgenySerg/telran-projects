package bitoperations;

public class BitOperations {
    private static int MIN_BIT_NUMBER=0;
    private static int MAX_BIT_NUMBER = 63;
    private static long ONE=1L;
    /**
     *
     * @param number
     * @param nBit - sequential number of bit
     * @return bit value(0,1); -1 in the case of wrong bit's number
     */
    public static long getBitValue(long number, int nBit) {
        if (nBit < MIN_BIT_NUMBER || nBit > MAX_BIT_NUMBER) {
            return -1;
        }
        return  ((number&(ONE<<nBit))==0)?0:1;
    }

    /**
     *
     * @param number
     * @param nBit - position of the bit to set 1
     * @return new value with changed specified bit to 1.
     *             0 in case of wrong bit position
     */
    public static long setBitValue(long number, int nBit) {
        if (nBit < MIN_BIT_NUMBER || nBit > MAX_BIT_NUMBER) {
            return 0;
        }
        return (number | (ONE << nBit));
    }
    /**
     *
     * @param number
     * @param nBit - position of the bit which set to 0
     * @return new value with changed specified bit to 0.
     *             -1 in case of wrong bit position
     */
    public static long resetBitValue(long number, int nBit) {
        if (nBit < MIN_BIT_NUMBER || nBit > MAX_BIT_NUMBER) {
            return -1;
        }
//        return (((1<<nBit)^Integer.MAX_VALUE) & number);
        return number & ~(ONE << nBit);
    }

    /**
     *
     * @param number
     * @param nBit - position of the bit to toggle
     * @return new value with toggled specified bit
     *          -1 in case of wrong bit position
     */
    public static long toggleBitValue(long number, int nBit) {
        if (nBit < MIN_BIT_NUMBER || nBit > MAX_BIT_NUMBER) {
            return -1;
        }
        return ((ONE<<nBit) ^ number);
    }

    /**
     ** 10 with only one operator + and without *
     * @param number
     * @return number * 10 with only one operator + and without *
     */
    public static long fastMultiply10(long number) {
        return ((number<<ONE)+(number<<3L));
    }

    /**
     *
     * @param number
     * @return / 10 with only one operator - and without /
     */
    public static long fastDivision10(int number) {

        return -1;
    }

    /**
     *
     * @param number to find inside desired sum
     * @param sum sum value to find
     * @return true - sum was calculated, false - unable to calculate sum
     */
    public static boolean isNbitsSum(long number, int sum) {
        //TODO в числе есть 2 бита, что если сумму номеов находим то получаем sum
        // сумма индексов битов. Найти первую комбинацию, которая получится
//        if (sum>MAX_BIT_NUMBER+MAX_BIT_NUMBER-1){
//            return false;
//        }

        if (sum > (MIN_BIT_NUMBER+MAX_BIT_NUMBER-1)/2*MAX_BIT_NUMBER){
            return false;
        }

        int index=MIN_BIT_NUMBER;
        int resultSum=0;
        while (index!=MAX_BIT_NUMBER){
            if (getBitValue(number, index)!=0){
                resultSum+=index;
                if (resultSum==sum){
                    return true;
                }
            }
            index++;
//            if (resultSum<=sum-index){
//                return false;
//            }
        }

        return sum==0;
    }


    public static boolean is2BitSum(long number, int sum) {
        if (sum>MAX_BIT_NUMBER-1+MAX_BIT_NUMBER || sum<0){
            return false;
        }

        if (number==0){
            return sum == 0;
        }

        int smallIndex=sum>>1;
        int bigIndex=sum-smallIndex;
        if (bigIndex==smallIndex) {
            smallIndex--;
            if (smallIndex<0){
                return BitOperations.getBitValue(number, bigIndex) == sum;
            }
        }

        int result;
        while (smallIndex>=MIN_BIT_NUMBER || bigIndex<=MAX_BIT_NUMBER){
                result = (int)  (BitOperations.getBitValue(number,bigIndex)*bigIndex+BitOperations.getBitValue(number,smallIndex)*smallIndex);
                if (result==sum) {
                    return true;
                } else if (result>sum){
                    if (smallIndex>MIN_BIT_NUMBER){
                        smallIndex--;
                    } else {
                        return false;
                    }
                } else  {
                    if (bigIndex<MAX_BIT_NUMBER){
                        bigIndex++;
                        if (bigIndex>sum){
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
        }
        return false;
    }
}
//
