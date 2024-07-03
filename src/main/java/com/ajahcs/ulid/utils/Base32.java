package com.ajahcs.ulid.utils;

import java.util.Map;
import static java.util.Map.entry;

public class Base32 {
    private static final int base = 32;
    private static char[] chars = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K',
        'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X',
        'Y', 'Z'
    };

    private static Map<Character, Integer> charsMap = Map.ofEntries(
       entry('0', 0),
       entry('1', 1),
       entry('2', 2),
       entry('3', 3),
       entry('4', 4),
       entry('5', 5),
       entry('6', 6),
       entry('7', 7),
       entry('8', 8),
       entry('9', 9),
       entry('A', 10),
       entry('B', 11),
       entry('C', 12),
       entry('D', 13),
       entry('E', 14),
       entry('F', 15),
       entry('G', 16),
       entry('H', 17),
       entry('J', 18),
       entry('K', 19),
       entry('M', 20),
       entry('N', 21),
       entry('P', 22),
       entry('Q', 23),
       entry('R', 24),
       entry('S', 25),
       entry('T', 26),
       entry('V', 27),
       entry('W', 28),
       entry('X', 29),
       entry('Y', 30),
       entry('Z', 31)
    );

    public static char getChar(int index){
        return chars[index];
    }

    /**
     * toBase converts a base 10 number to a base 32 number string.
     * It tries to get the base 32 characters up to the specified length
     * @param number - base 10 number to convert to base 32
     * @param length - the length of the base 32 number to be generated
     * @return String - the base 32 number
     */
    public static String toBase(long number, int length){
        StringBuilder result = new StringBuilder();
        while(length > 0){
            int remnant = (int) (number % 32L);
            number = number / 32;
            result.append(chars[remnant]);
            length -= 1;
        }
        return  result.reverse().toString();
    }

    /**
     * increment adds the specified number to the base 32 number string
     * @param base32Number - base 32 number to increment by specified value
     * @param by - number that will be added to base 32 number string
     * @return
     */
    public static String increment(String base32Number, int by){
        int startPosition = 1;
        StringBuilder result = new StringBuilder(base32Number);
        char lastChar = result.charAt(result.length() - startPosition);
        int incrementResult = charsMap.get(lastChar) + by;
        int carry = incrementResult / base;
        int remainder = incrementResult % base;
        result.setCharAt(result.length() - startPosition, chars[remainder]);
        startPosition += 1;

        while(carry != 0 && startPosition < result.length()){
            lastChar = result.charAt(result.length() - startPosition);
            incrementResult = charsMap.get(lastChar) + carry;
            carry = incrementResult / base;
            remainder = incrementResult % base;
            result.setCharAt(result.length() - startPosition, chars[remainder]);
            startPosition += 1;
        }

        if(carry > 0){
            result.insert(0, chars[carry]);
        }
        return result.toString();
    }
}
