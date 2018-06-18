package sportloto;

import bitoperations.*;

public class SportLotoApplication {
    private static long historyValues=0;

    public static void main(String[] args) {
        int i=7;
        while (i > 0) {
            int number = getRandomNumber(1, 49);
            if (addValue(number)) {
                System.out.println(number);
                i--;
            }
        }

        LongBits longBits = new LongBits(0);


    }

    public static void resetHystoryValues() {
        SportLotoApplication.historyValues=0;
    }

    public static boolean addValue(int number) {
       if (number>63 || number<0){
           return false;
       }
        if (isNumberNotEncounter(number)) {
            historyValues = BitOperations.setBitValue(historyValues, number);
            return true;
        }
        return false;

    }

    public static long getHistoryValues() {
        return historyValues;
    }

    public static void setHistoryValues(long historyValues) {
        SportLotoApplication.historyValues = historyValues;
    }

    private static boolean isNumberNotEncounter(int number) {
        return BitOperations.getBitValue(historyValues, number) != 1;
    }



    private static int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max-min+1) + min);
    }
}
