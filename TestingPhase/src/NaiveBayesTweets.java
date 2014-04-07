import java.io.File;
import java.io.*;
import java.math.*;
import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.objectbank.TokenizerFactory;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;



public class NaiveBayesTweets {
    
    public String bayestweet()
    {
        String direction = new String();
        try
        {
            Connection con = null;
            String url = "jdbc:mysql://localhost:3306/";
            String db = "features";
            String driver = "com.mysql.jdbc.Driver";
            ResultSet rs = null;
    
            Class.forName(driver);
            con = DriverManager.getConnection(url+db,"root","sanyo");
            
            

            Double[] PwordPos = new Double [3000];
            Double[] PwordNeg = new Double [3000];

            int positive=0,negative=0,total=0;
            
            String path = new String("C:/Documents and Settings/105000/My Documents/Dropbox/Final Year Project 2013/Testing Phase/tweettemp.txt");
            File f = new File(path);
            String[] pos={"IN","JJS","JJR","JJ","NNS","NN","RB","RP","VBZ","VBN","VBD","VBG","VBP","VB"};
            String text = new String();
            String[] word = new String[3000];
            int count=-1;
            
            if(f.isFile())
            {
                BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));
                if(br1.read()==-1)
                {
                    //direction = null;
                    return direction;
                }
                   
                MaxentTagger tagger = new MaxentTagger("wsj-0-18-bidirectional-distsim.tagger");
                TokenizerFactory<CoreLabel> ptbTokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(),
									   "untokenizable=noneKeep");
                BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));
                DocumentPreprocessor documentPreprocessor = new DocumentPreprocessor(r);
                documentPreprocessor.setTokenizerFactory(ptbTokenizerFactory);
                for (List<HasWord> sentence : documentPreprocessor)
                {

                    List<TaggedWord> tSentence = tagger.tagSentence(sentence);
                    text+=Sentence.listToString(tSentence,false);
                }
                String temp = new String();
                if(text.length()!=0)
                {
                    //text = text.replace("'"," ");
                    String [] words = text.split("\\s+");
                    for(int j=0;j<words.length;j++)
                    {
                        for(int z=0;z<14;z++)
                        {
                            if(words[j].endsWith(pos[z]))
                            {
                                temp = words[j].substring(0,words[j].indexOf("/"));
                                Statement st = con.createStatement();
                                try
                                {

                                rs = st.executeQuery("select * from twitter_words where word='"+temp+"' and tag='"+pos[z]+"';");
                                if(rs.first()== true)
                                {
                                    count++;
                                    word[count]=temp;
                                    //System.out.println("here!!!");
                                    PwordPos[count] = rs.getDouble("PwordPos");
                                    PwordNeg[count] = rs.getDouble("PwordNeg");
                                   //System.out.println(word[count]+"      "+pos[z]+"      "+PwordPos[count]+"         "+PwordNeg[count]);
                                }
                                }
                                catch(Exception e)
                                {
                                    System.out.println(e.toString());
                                }
                            }
                        }
                    }
                }
                    
            
            
                Statement st = con.createStatement();
                rs = st.executeQuery("select * from count where type='tweet';");
                if(rs.first())
                {
                    positive=rs.getInt("positive");
                    negative=rs.getInt("negative");
                    total=positive+negative;
                }

                double Ppos = 1 ,Pneg = 1;
                for(int k=0;k<=count;k++)
                {
                    Ppos = Ppos*PwordPos[k]*100;
                    Pneg = Pneg*PwordNeg[k]*100;
                }

                Ppos *= java.lang.Math.pow(10,105);
                Pneg *= java.lang.Math.pow(10,105);
                    //Ppos *= 1000000000;
                    //Pneg *= 1000000000;
                Ppos = Ppos * positive;
                Pneg = Pneg * negative;

                Ppos /= total;
                Pneg /= total;

                System.out.println("Tweets            "+Ppos+"             "+Pneg);
                
                    
                if(Ppos>Pneg)
                    direction = "Positive";
                else
                    direction = "Negative";
                    
                    
                }
    

            
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        return direction;
    }
 
    public String[] bayestweets()
    {
    //POSTag for tweets
        
        String[] direction = new String[50];
        int compcount=-1;
        try
        {
            Connection con = null;
            String url = "jdbc:mysql://localhost:3306/";
            String db = "features";
            String driver = "com.mysql.jdbc.Driver";
            ResultSet rs = null;
    
            Class.forName(driver);
            con = DriverManager.getConnection(url+db,"root","sanyo");
            
            

            Double[] PwordPos = new Double [3000];
            Double[] PwordNeg = new Double [3000];

            int positive=0,negative=0,total=0;
            String path = "C:/Documents and Settings/105000/My Documents/Dropbox/Final Year Project 2013/Testing Phase";
            
            
            File folder = new File(path);
            File[] listofFiles;
            String files;
            File f;
            String[] pos={"IN","JJS","JJR","JJ","NNS","NN","RB","RP","VBZ","VBN","VBD","VBG","VBP","VB"};
            String text = new String();
            String[] word = new String[3000];
            int count=-1;

            
            listofFiles = folder.listFiles();
            for(int i=0;i<listofFiles.length;i++)
            {
                if(listofFiles[i].isFile())
                {
                    files = listofFiles[i].getName();
                    if((files.equals("articletemp.txt"))||(files.equals("tweettemp.txt"))||(files.equals("messagetemp.txt")))
                        continue;
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(path+"/tweettemp.txt"), "utf-8"));
                    if(br1.read()==-1)
                    {
                        compcount++;
                        direction[compcount] = null;
                        continue;
                    }
                   
                    MaxentTagger tagger = new MaxentTagger("wsj-0-18-bidirectional-distsim.tagger");
                    TokenizerFactory<CoreLabel> ptbTokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(),
									   "untokenizable=noneKeep");
                    BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(path+"/tweettemp.txt"), "utf-8"));
                    DocumentPreprocessor documentPreprocessor = new DocumentPreprocessor(r);
                    documentPreprocessor.setTokenizerFactory(ptbTokenizerFactory);
                    for (List<HasWord> sentence : documentPreprocessor)
                    {

                        List<TaggedWord> tSentence = tagger.tagSentence(sentence);
                        text+=Sentence.listToString(tSentence,false);
                    }

            
            //the tagges text is there in text! now I have to split based on the tags and find out the relavent ones!
                    
                    String temp = new String();
                    if(text.length()!=0)
                    {
                           String [] words = text.split("\\s+");
                    for(int j=0;j<words.length;j++)
                    {
                        for(int z=0;z<14;z++)
                        {
                            if(words[j].endsWith(pos[z]))
                            {
                                temp = words[j].substring(0,words[j].indexOf("/"));
                                Statement st = con.createStatement();
                                try{

                                rs = st.executeQuery("select * from twitter_words where word='"+temp+"' and tag='"+pos[z]+"';");
                                if(rs.first()== true)
                                {
                                    count++;
                                    word[count]=temp;
                                    PwordPos[count] = rs.getDouble("PwordPos");
                                    PwordNeg[count] = rs.getDouble("PwordNeg");
                                   
                                }
                                }
                                catch(Exception e)
                                {
                                    System.out.println(e.toString());
                                }
                            }
                        }
                    }

                    
            //I have the list of words that will determine the sentiment along with their probabilites!
                    Statement st = con.createStatement();
                    rs = st.executeQuery("select * from count where type='tweet';");
                    if(rs.first())
                    {
                        positive=rs.getInt("positive");
                        negative=rs.getInt("negative");
                        total=positive+negative;
                    }

                    double Ppos = 1 ,Pneg = 1;
                    for(int k=0;k<=count;k++)
                    {
                        Ppos = Ppos*PwordPos[k]*100;
                        Pneg = Pneg*PwordNeg[k]*100;
                    }

                    Ppos *= java.lang.Math.pow(10,105);
                    Pneg *= java.lang.Math.pow(10,105);
                    //Ppos *= 1000000000;
                    //Pneg *= 1000000000;
                    Ppos = Ppos * positive;
                    Pneg = Pneg * negative;

                    Ppos /= total;
                    Pneg /= total;

                    System.out.println("Tweets :         "+Ppos+"             "+Pneg);
                    compcount++;
                    if(Ppos>Pneg)
                        direction[compcount] = "Positive";
                    else
                        direction[compcount] = "Negative";
                    
                    
                    }
                    else
                    {
                        compcount++;
                        direction[compcount] = null;
                    }
                   
                }
            }
        }
        
                    catch(Exception e)
                    {
                        System.out.println(e.toString());
                    }
        return direction;
    }
}
