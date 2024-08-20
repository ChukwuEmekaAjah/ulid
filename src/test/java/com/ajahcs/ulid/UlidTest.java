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

    @Test
    public void shouldReturnMonotonicallyIncreasingString(){
        long timestamp = 1724181910663L;
        String uniqueId1 = Ulid.get(timestamp);
        String uniqueId2 = Ulid.get(timestamp);
        String uniqueId3 = Ulid.get(timestamp);

        assertTrue(uniqueId1.substring(0,10).equals(uniqueId2.substring(0,10)));
        assertTrue(uniqueId2.substring(0,10).equals(uniqueId3.substring(0,10)));

        // incremented random string tied to the first occurrence of the current timestamp
        assertTrue(uniqueId2.charAt(uniqueId2.length()-1) > uniqueId1.charAt(uniqueId1.length() - 1));
        assertTrue(uniqueId3.charAt(uniqueId3.length()-1) > uniqueId2.charAt(uniqueId2.length() - 1));
       
    }
    
    
    
}
