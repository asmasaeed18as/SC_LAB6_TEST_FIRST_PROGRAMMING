/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class Filter {
//	original imlpementation

//    public static List<Tweet> writtenBy(List<Tweet> tweets, String username) {
//        List<Tweet> result = new ArrayList<>();
//        for (Tweet tweet : tweets) {
//            if (tweet.getAuthor().equalsIgnoreCase(username)) {
//                result.add(tweet);
//            }
//        }
//        return result;
//    }
//
//  
//	1st implementation  Using an Iterator
//	public static List<Tweet> writtenBy(List<Tweet> tweets, String username) {
//        List<Tweet> result = new ArrayList<>();
//        Iterator<Tweet> iterator = tweets.iterator();
//
//        while (iterator.hasNext()) {
//            Tweet tweet = iterator.next();
//            if (tweet.getAuthor().equalsIgnoreCase(username)) {
//                result.add(tweet);
//            }
//        }
//        return result;
//    }
	
//	2nd  Extended For-Loop with Conditions to check the running of implementation
//	public static List<Tweet> writtenBy(List<Tweet> tweets, String username) {
//        List<Tweet> result = new ArrayList<>();
//
//        for (Tweet tweet : tweets) {
//            if (tweet.getAuthor().equalsIgnoreCase(username)) {
//                result.add(tweet);
//            }
//        }
//        return result;
//    }
//	
//	Using List's Index
//	In this version, we access the list of tweets using indices to collect tweets written by the specified username.
	
	 public static List<Tweet> writtenBy(List<Tweet> tweets, String username) {
	        List<Tweet> result = new ArrayList<>();

	        for (int i = 0; i < tweets.size(); i++) {
	            Tweet tweet = tweets.get(i);
	            if (tweet.getAuthor().equalsIgnoreCase(username)) {
	                result.add(tweet);
	            }
	        }
	        return result;
	    }
    public static List<Tweet> inTimespan(List<Tweet> tweets, Timespan timespan) {
        List<Tweet> result = new ArrayList<>();
        for (Tweet tweet : tweets) {
            Instant timestamp = tweet.getTimestamp();
            if (!timestamp.isBefore(timespan.getStart()) && !timestamp.isAfter(timespan.getEnd())) {
                result.add(tweet);
            }
        }
        return result;
    }
//    buggy implementation  of in timespan function
//    public static List<Tweet> inTimespan(List<Tweet> tweets, Timespan timespan) {
//        // Bug: Returning all tweets without any filtering
//        return new ArrayList<>(tweets);
//    }
//


    public static List<Tweet> containing(List<Tweet> tweets, List<String> words) {
        List<Tweet> result = new ArrayList<>();
        for (Tweet tweet : tweets) {
            String text = tweet.getText().toLowerCase();
            for (String word : words) {
                if (text.contains(word.toLowerCase())) {
                    result.add(tweet);
                    break; // one word match is enough
                }
            }
        }
        return result;
    }
}