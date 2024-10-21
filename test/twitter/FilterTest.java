/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class FilterTest {

    private static final Instant d1 = Instant.parse("2023-09-27T10:00:00Z");
    private static final Instant d2 = Instant.parse("2023-09-28T11:00:00Z");
    private static final Instant d3 = Instant.parse("2023-09-29T12:00:00Z");

    private static final Tweet tweet1 = new Tweet(1, "saleha", "First tweet", d1);
    private static final Tweet tweet2 = new Tweet(2, "madi", "Second tweet", d2);
    private static final Tweet tweet3 = new Tweet(3, "saleha", "Third tweet", d3);

    @Test
    public void testWrittenBy() {
        List<Tweet> result = Filter.writtenBy(Arrays.asList(tweet1, tweet2, tweet3), "saleha");
        assertEquals(2, result.size());
    }

//    @Test
//    public void testInTimespan() {
//        Timespan timespan = new Timespan(d1, d2);
//        List<Tweet> result = Filter.inTimespan(Arrays.asList(tweet1, tweet2, tweet3), timespan);
//        assertEquals(2, result.size());
//    }
    @Test
    public void testInTimespan() {
        Timespan timespan = new Timespan(d1, d2);
        List<Tweet> result = Filter.inTimespan(Arrays.asList(tweet1, tweet2, tweet3), timespan);
        assertEquals(2, result.size());
    }


    @Test
    public void testContaining() {
        List<Tweet> result = Filter.containing(Arrays.asList(tweet1, tweet2, tweet3), Arrays.asList("First", "Third"));
        assertEquals(2, result.size());
    }
}
