package sentimentanalysistablegeneration;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        
        //ArrayList<String> tweets = new DBFetcher().getTweets();
        
        
        ArrayList<ArrayList<WordPosTagsValuePair>> posListOfTweets = new ArrayList<>();
        
        SnlpPosOfWord snlpPos = new SnlpPosOfWord();
        PosTagMapper posTageMapper = new PosTagMapper();
        
        
        ArrayList<Double> scores = new ArrayList<>();
        
        
        //for (String tweet : tweets) {
            
            String tweet = "It's sadly not waterproof though - that's going to be on the S6 Active, whenever that appears";
            System.out.println("Tweet: "+tweet+"\n");
            
            
            tweet = tweet.toLowerCase();
            //tweet = tweet.replaceAll("([^a-zA-Z\\s])", "");
            
            //System.out.println(tweet);
            
            ArrayList<WordPosTagsValuePair> posOfTweet = snlpPos.getPosOfTweet(tweet);
            posListOfTweets.add(posOfTweet);
            
            double totalScore = 0.0;
        
            
            for (WordPosTagsValuePair posOfTweet1 : posOfTweet) {
                String mapTag = posTageMapper.getMapTag(posOfTweet1.getPosTag());
                double sentiScore = -100.0;
                
                if(mapTag!=null){
                    try{
                        sentiScore = SentiWordNet.getInstance().extract(posOfTweet1.getWord(), mapTag);
                        totalScore += sentiScore;
                    }catch(Exception ex)
                    {
                    }
                }
                System.out.println(posOfTweet1.getWord()+"\t"+posOfTweet1.getPosTag()+"\t"+mapTag+"\t"+sentiScore);
            }
            
            scores.add(totalScore);
            System.out.println("\n\tTotal score : "+totalScore);
            //System.out.println();
                
            //break;
        //}
       
    /*
        double avgScore = 0.0;
        for (Double score : scores) {
            avgScore+=score;
        }
      */
    
        //avgScore= avgScore/(double)tweets.size();
        //System.out.println("\n\n********Average score : "+avgScore);
    }
}