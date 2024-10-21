package twitter;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extract {

    /**
     * Get the time period spanned by tweets.

     */
//	original implementation
//	//IMPLEMENTATION OF getTimespan
//    public static Timespan getTimespan(List<Tweet> tweets) {
//     
//        // Initialize start and end with the timestamp of the first tweet
//        Instant start = tweets.get(0).getTimestamp();
//        Instant end = start;
//
//        // Loop through tweets to find the earliest and latest timestamps
//        for (Tweet tweet : tweets) {
//            Instant timestamp = tweet.getTimestamp();
//            if (timestamp.isBefore(start)) {
//                start = timestamp;
//            }
//            if (timestamp.isAfter(end)) {
//                end = timestamp;
//            }
//        }
//
//        return new Timespan(start, end);
//    }
//	//my strict implementation
//	public static Timespan getTimespan(List<Tweet> tweets) {
//	    // Find the earliest and latest timestamp using streams
//	    Instant start = tweets.stream().map(Tweet::getTimestamp).min(Instant::compareTo).get();
//	    Instant end = tweets.stream().map(Tweet::getTimestamp).max(Instant::compareTo).get();
//
//	    return new Timespan(start, end);
//	}
//2nd implementation:
	//looping back
//	public static Timespan getTimespan(List<Tweet> tweets) {
//	    Instant start = tweets.get(0).getTimestamp();
//	    Instant end = start;
//
//	    // Loop backward through the tweets
//	    for (int i = tweets.size() - 1; i >= 0; i--) {
//	        Instant timestamp = tweets.get(i).getTimestamp();
//	        if (timestamp.isBefore(start)) {
//	            start = timestamp;
//	        }
//	        if (timestamp.isAfter(end)) {
//	            end = timestamp;
//	        }
//	    }
//
//	    return new Timespan(start, end);
//	}
  //3rd immplementation
	public static Timespan getTimespan(List<Tweet> tweets) {
	    // Find the earliest and latest timestamp using streams
	    Instant start = tweets.stream().map(Tweet::getTimestamp).min(Instant::compareTo).get();
	    Instant end = tweets.stream().map(Tweet::getTimestamp).max(Instant::compareTo).get();

	    return new Timespan(start, end);
	}


//original IMPLEMENTATION OF getMentionedUsers
//    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
//        Set<String> mentionedUsers = new HashSet<>();
//        // BUG: Remove Pattern.CASE_INSENSITIVE to introduce the bug
//        Pattern mentionPattern = Pattern.compile("(?<!\\w)@(\\w+)");
//
//        // Loop through all tweets
//        for (Tweet tweet : tweets) {
//            String text = tweet.getText();
//            Matcher matcher = mentionPattern.matcher(text);
//
//            // Find all valid mentions in the tweet
//            while (matcher.find()) {
//                String username = matcher.group(1);  // No .toLowerCase()
//                mentionedUsers.add(username); // Adding username without converting to lowercase
//            }
//        }
//
//        return mentionedUsers;
//    }
//    
	// 1st implimentation
//	public static Set<String> getMentionedUsers(List<Tweet> tweets) {
//	    Set<String> mentionedUsers = new HashSet<>();
//	    Pattern mentionPattern = Pattern.compile("(?<!\\w)@(\\w+)(?!\\S*@)", Pattern.CASE_INSENSITIVE);
//
//	    for (Tweet tweet : tweets) {
//	        Matcher matcher = mentionPattern.matcher(tweet.getText());
//	        while (matcher.find()) {
//	            mentionedUsers.add(matcher.group(1).toLowerCase());
//	        }
//	    }
//
//	    return mentionedUsers;
//	}

//second implementation
	//manual string
	public static Set<String> getMentionedUsers(List<Tweet> tweets) {
	    Set<String> mentionedUsers = new HashSet<>();
	    // Regex to find mentions that aren't part of an email
	    Pattern mentionPattern = Pattern.compile("(?<!\\w)@(\\w+)(?!\\S*@)", Pattern.CASE_INSENSITIVE);

	    for (Tweet tweet : tweets) {
	        String text = tweet.getText();
	        Matcher matcher = mentionPattern.matcher(text);

	        while (matcher.find()) {
	            String username = matcher.group(1).toLowerCase(); // Ensure username is in lowercase
	            mentionedUsers.add(username);
	        }
	    }

	    return mentionedUsers;
	}


}