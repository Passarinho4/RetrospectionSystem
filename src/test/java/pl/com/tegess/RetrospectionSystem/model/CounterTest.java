package pl.com.tegess.RetrospectionSystem.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CounterTest {

    @Test
    public void testGetId(){

        //given
        Counter underTest = new Counter("test");

        //when

        //then
        Assert.assertEquals("test", underTest.getId());
    }

    @Test
    public void  testGetSequence(){

        //given
        Counter underTest = new Counter("test");

        //when

        //then
        Assert.assertEquals(0, underTest.getSequence());
    }

}