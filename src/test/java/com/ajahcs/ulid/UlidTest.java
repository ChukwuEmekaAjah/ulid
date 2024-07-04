package com.ajahcs.ulid;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class UlidTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test(expected = RuntimeException.class)
    public void shouldRaiseExceptionForNegativeTimestamp(){
        long timestamp = -1534353434L;
        Ulid.get(timestamp);
    }

    @Test(expected = RuntimeException.class)
    public void shouldRaiseExceptionForAboveMaxTimestamp(){
        long timestamp = 281474976710656L;
        Ulid.get(timestamp);
    }

    @Test
    public void shouldReturn26CharacterString(){
        for(int i = 0; i < 10; i++){
            assertTrue(Ulid.get(System.currentTimeMillis()).length() == 26);
        }
    }
    
}
