package twitter;

import static org.junit.Assert.*;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.Test;

@SuppressWarnings("unused")
public class ExtractTest {
    
    // Define some example timestamps and tweets
    private static final Instant t1 = Instant.parse("2024-09-30T10:00:00Z");
    private static final Instant t2 = Instant.parse("2024-09-30T11:00:00Z");
    private static final Instant t3 = Instant.parse("2024-09-30T12:00:00Z");
    
    private static final Tweet tweet1 = new Tweet(1, "saleha", "Tweet 1", t1);
    private static final Tweet tweet2 = new Tweet(2, "zainab", "Tweet 2", t2);
    private static final Tweet tweet3 = new Tweet(3, "fatima", "Tweet 3", t3);
    
    // Testing strategy for getTimespan:
    // 1. Single tweet.
    // 2. Two tweets very close in time.
    // 3. Tweets far apart in time.
    
    @Test
    public void testGetTimespanOneTweet() {
        // Test with a single tweet (start and end time should be the same)
        Timespan timespan = Extract.getTimespan(Collections.singletonList(tweet1));
        assertEquals("expected start", t1, timespan.getStart());
        assertEquals("expected end", t1, timespan.getEnd());
    }
    
    @Test
    public void testGetTimespanTwoTweetsCloseTime() {
        // Test with two tweets close in time
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2));
        assertEquals("expected start", t1, timespan.getStart());
        assertEquals("expected end", t2, timespan.getEnd());
    }
    
    @Test
    public void testGetTimespanTweetsFarApart() {
        // Test with tweets far apart in time
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet3));
        assertEquals("expected sta"
        		+ "rt", t1, timespan.getStart());
        assertEquals("expected end", t3, timespan.getEnd());
    }
    
    

    // Test cases for getMentionedUsers:
    // 1. No mentions.
    // 2. Single valid mention.
    // 3. Multiple valid mentions.
    // 4. Case insensitivity for mentions.
    // 5. No mentions in email-like patterns.
    
    private static final Tweet tweetNoMention = new Tweet(1, "saleha", "Hello world!", Instant.now());
    private static final Tweet tweetMention = new Tweet(2, "zainab", "Hey @saleha", Instant.now());
    private static final Tweet tweetMultipleMentions = new Tweet(3, "fatima", "@saleha @zainab", Instant.now());
    private static final Tweet tweetCaseMention = new Tweet(4, "BESE-13B", "Hey @everyone!", Instant.now());
    private static final Tweet tweetEmailLike = new Tweet(5, "Emad", "Is the code working? If not, email me at i222072@nu.edu.pk", Instant.now());
    
    @Test
    public void testGetMentionedUsersNoMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Collections.singletonList(tweetNoMention));
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }
    
    @Test
    public void testGetMentionedUsersSingleMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Collections.singletonList(tweetMention));
        assertTrue("expected to contain saleha", mentionedUsers.contains("saleha"));
        assertEquals("expected size 1", 1, mentionedUsers.size());
    }
    
    @Test
    public void testGetMentionedUsersMultipleMentions() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Collections.singletonList(tweetMultipleMentions));
        assertTrue("expected to contain saleha", mentionedUsers.contains("saleha"));
        assertTrue("expected to contain zainab", mentionedUsers.contains("zainab"));
        assertEquals("expected size 2", 2, mentionedUsers.size());
    }
    
    @Test
    public void testGetMentionedUsersCaseInsensitive() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Collections.singletonList(tweetCaseMention));
        assertTrue("expected to contain everyone in lowercase", mentionedUsers.contains("everyone"));
        assertEquals("expected size 1", 1, mentionedUsers.size());
    }
    
    @Test
    public void testGetMentionedUsersNoEmailMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Collections.singletonList(tweetEmailLike));
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }
    
    
}