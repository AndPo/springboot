package lits.com.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MainTest4JUnit {

    @Test(expected = RuntimeException.class)
    public void test(){
//        assertEquals(1, div(2, 2));
        assertFalse("AssertFalse not pass", div(3, 0) == 5);
//        assertTrue(div(2, 5) == 7);
 //     throw new RuntimeException();
    }

    private int div(int n1, int n2) {
        return n1 /n2;
    }


}
