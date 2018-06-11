package sportloto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SportLotoApplicationTest {


    @org.junit.jupiter.api.Test
    void main() {
        fail("No test!");
    }

    @org.junit.jupiter.api.Test
    void getHistoryValues() {
        fail("No test!");
    }

    @org.junit.jupiter.api.Test
    void setHistoryValues() {
        fail("No test!");
    }

    @org.junit.jupiter.api.Test
    void addValue() {

        assertArrayEquals(new boolean[]{true,true,true,false, true}, new boolean[]{
                SportLotoApplication.addValue(0),
                SportLotoApplication.addValue(1),
                SportLotoApplication.addValue(33),
                SportLotoApplication.addValue(33),
                SportLotoApplication.addValue(63)});
        System.out.println("History values: "+Long.toBinaryString(SportLotoApplication.getHistoryValues()));
    }
}