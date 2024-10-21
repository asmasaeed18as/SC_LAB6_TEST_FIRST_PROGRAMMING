/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;
import org.junit.Test;
import twitter.Tweet;

import java.time.Instant;
import java.util.*;

public class SocialNetworkTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
   
 // Test case 1: Empty List of Tweets
    @Test
    public void testGuessFollowsGraphEmptyList() {
        List<Tweet> tweets = new ArrayList<>();
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);
        
        assertTrue("IT IS EXPECTED THAT THERE IS empty map for empty list of tweets", followsGraph.isEmpty());
    }
    
    // Test case 2: Tweets Without Mentions
    @Test
    public void testGuessFollowsGraphNoMentions() {
    	 Tweet tweet = new Tweet(1, "Asma", "Just a tweet without mentions.", Instant.now());
    	    List<Tweet> tweets = Arrays.asList(tweet);

    	    Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);

        assertTrue("IT IS EXPECTED THAT THE LIST OF TWEETS IS HAVING NO MENTIONS", followsGraph.isEmpty());
    }
    
    // Test case 3: Single Mention
    @Test
    public void testGuessFollowsGraphSingleMention() {
        Tweet tweet = new Tweet(1, "Ernie", "Hey @Bert!", Instant.now());
        List<Tweet> tweets = Arrays.asList(tweet);
        
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);
        
        assertTrue("Ernie should follow Bert", followsGraph.get("Ernie").contains("Bert"));
    }
    
    // Test case 4: Multiple Mentions in a Single Tweet
    @Test
    public void testGuessFollowsGraphMultipleMentions() {
        Tweet tweet = new Tweet(1, "Bert", "Hey @Ernie and @ASMA!", Instant.now());
        List<Tweet> tweets = Arrays.asList(tweet);
        
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);
        
        assertTrue("Bert should follow both Ernie and ASMA", followsGraph.get("Bert").contains("Ernie"));
        assertTrue("Bert should follow both Ernie and ASMA", followsGraph.get("Bert").contains("ASMA"));
    }
    
    // Test case 5: Multiple Tweets from the Same User
    @Test
    public void testGuessFollowsGraphMultipleTweetsSameUser() {
        Tweet tweet1 = new Tweet(1, "ASMA", "Hey @MUNNAZA!", Instant.now());
        Tweet tweet2 = new Tweet(2, "ASMA", "@EMAN check this outfit!", Instant.now());
        List<Tweet> tweets = Arrays.asList(tweet1, tweet2);
        
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);
        
        assertTrue("ASMA should follow MUNNAZA and EMAN", followsGraph.get("ASMA").contains("MUNNAZA"));
        assertTrue("ASMA should follow MUNNAZA and EMAN", followsGraph.get("ASMA").contains("EMAN"));
    }
 // Test case 1: empty influencer list
    @Test
    public void testInfluencersEmpty() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertTrue(" IT IS EXPECTED THERE IS empty list", influencers.isEmpty());
    }
    // Test case 2: Single User with No Followers
    @Test
    public void testInfluencersSingleUserNoFollowers() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        followsGraph.put("Maheen", new HashSet<>()); // MAHEEN follows no one
        
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertTrue("IT IS EXPECTED THAT THERE IS empty list when no followers", influencers.isEmpty());
    }
    

 // Test case 3: Single Influencer
 @Test
 public void testInfluencersSingleInfluencer() {
     Map<String, Set<String>> followsGraph = new HashMap<>();
     
     // Use Collections.singleton() for single-element sets in Java 8
     followsGraph.put("ASMA", Collections.singleton("MUNNAZA"));
     followsGraph.put("EMAN", Collections.singleton("MUNNAZA"));
     
     List<String> influencers = SocialNetwork.influencers(followsGraph);
     
     assertEquals("MUNNAZA should be the top influencer", "MUNNAZA", influencers.get(0));
 }

 // Test case 4: Multiple Influencers
 @Test
 public void testInfluencersMultipleInfluencers() {
     Map<String, Set<String>> followsGraph = new HashMap<>();
     
     // Use Collections.singleton() for single-element sets
     followsGraph.put("ASMA", Collections.singleton("MUNNAZA"));
     
     // Use new HashSet<>(Arrays.asList()) for multiple elements
     followsGraph.put("EMAN", new HashSet<>(Arrays.asList("MUNNAZA", "SALEHA")));
     
     List<String> influencers = SocialNetwork.influencers(followsGraph);
     
     assertEquals("MUNNAZA should be the top influencer", "MUNNAZA", influencers.get(0));
     assertEquals("SALEHA should be the second influencer", "SALEHA", influencers.get(1));
 }

 // Test case 5: Tied Influence
 @Test
 public void testInfluencersTiedInfluence() {
     Map<String, Set<String>> followsGraph = new HashMap<>();
     
     // Use Collections.singleton() for single-element sets
     followsGraph.put("ASMA", Collections.singleton("MUNNAZA"));
     followsGraph.put("EMAN", Collections.singleton("SALEHA"));
     
     List<String> influencers = SocialNetwork.influencers(followsGraph);
     
     // Check that both influencers are present in the list
     assertTrue("MUNNAZA and SALEHA should both be in the influencer list", 
                influencers.contains("MUNNAZA") && influencers.contains("SALEHA"));
 }

    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version.
     * DO NOT strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in SocialNetwork, because that means you're testing a
     * stronger spec than SocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */

}
