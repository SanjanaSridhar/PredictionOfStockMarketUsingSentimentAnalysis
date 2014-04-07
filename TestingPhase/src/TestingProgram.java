import java.util.*;
import java.text.*;

public class TestingProgram {
    
    
    public String[] testingprogram()
    {
         //Call both the rss programs that are the one from livemint and the other one from economic times
   
        

        LiveMint livemint = new LiveMint();
        livemint.writeNews();
    
        EconomicTimes economictimes = new EconomicTimes();
        economictimes.writeNews();
        
        System.out.println("RSS was retrieved");
        
        //Then the RSS has to be extracted one after the other and the data should be added to the 50 files that are there
        
        ExtractRSS extractrss = new ExtractRSS();
        extractrss.extractrss();
        
        System.out.println("RSS extracted");
        //then get messages and tweets for the corresponding companies
        
        
        GetTweets tweets = new GetTweets();
        tweets.read();
        System.out.println("Tweets Retrieved");
        
        GetMessages messages = new GetMessages();
        messages.read();
        System.out.println("Messages Retrieved");
        
        //call Naive Bayes for this program
        String[] direction = new String[150];
        NaiveBayes naivebayes = new NaiveBayes();
        
        for(int i=0;i<150;i++)
            direction[i]="-";
        
            direction = naivebayes.naivebayes(direction);
        
        
        
        System.out.println("Naive Bayes completed");
        
        
        return direction;
    }
    
    
}
