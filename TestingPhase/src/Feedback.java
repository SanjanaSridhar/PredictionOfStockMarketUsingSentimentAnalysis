import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;


import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.objectbank.TokenizerFactory;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;


public class Feedback {
    
    public void feedback(int index, String stockprev, String stocknow)
    {
        
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/";
        String db = "features";
        String driver = "com.mysql.jdbc.Driver";
        ResultSet rs = null;

        
        //open the file and extract the contents and put into the three temporary files! 
        
        String path = "C:/Documents and Settings/105000/My Documents/Dropbox/Final Year Project 2013/Testing Phase";
        File folder = new File(path+"/Files");
        File[] listOfFiles = folder.listFiles();
        
        FileInputStream fstream;
        DataInputStream in;
        BufferedReader br;
        
        FileWriter fout;
        BufferedWriter out1;
    
        String strLine;
        String text = new String("");
        String [] temp = new String[3];
        Statement st;
        double now = Double.parseDouble(stocknow);
        double prev = Double.parseDouble(stockprev);
        
        String[] pos={"IN","JJS","JJR","JJ","NNS","NN","RB","RP","VBZ","VBN","VBD","VBG","VBP","VB"};
        System.out.println(listOfFiles[index].getName());
        
        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(url+db,"root","sanyo");

            fstream = new FileInputStream(listOfFiles[index]);
            in = new DataInputStream(fstream);
            br = new BufferedReader(new InputStreamReader(in));

            while ((strLine = br.readLine()) != null)
            {
                text += strLine;
            }
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        try
        {
            
            temp = text.split("~~~~~");
            
            File f = new File(path+"/articletemp.txt");
            fout = new FileWriter(f);
            out1 = new BufferedWriter(fout);
            out1.write(temp[0]);
            out1.close();
            
            if(temp.length>1)
            {
            f = new File(path+"/tweettemp.txt");
            fout = new FileWriter(f);
            out1 = new BufferedWriter(fout);
            out1.write(temp[1]);
            out1.close();
            } 
            if(temp.length>2)
            {
            f = new File(path+"/messagetemp.txt");
            fout = new FileWriter(f);
            out1 = new BufferedWriter(fout);
            out1.write(temp[2]);
            out1.close();
            }
    
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
            
        }
        
        
        //ARTICLES
        
        
        text = new String();
        
        try
        {
            //potag the file and store it in temp
            MaxentTagger tagger = new MaxentTagger("wsj-0-18-bidirectional-distsim.tagger");
            TokenizerFactory<CoreLabel> ptbTokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(),
									   "untokenizable=noneKeep");
            BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(path+"/articletemp.txt"), "utf-8"));
            DocumentPreprocessor documentPreprocessor = new DocumentPreprocessor(r);
            documentPreprocessor.setTokenizerFactory(ptbTokenizerFactory);
            
            for (List<HasWord> sentence : documentPreprocessor)
            {
                List<TaggedWord> tSentence = tagger.tagSentence(sentence);
                text += Sentence.listToString(tSentence,false);
            }
            
           //retrieve only the words that we want and store in an array!
           String [] words = text.split("\\s+");
           String word;
           
           for(int j=0;j<words.length;j++)
           {
                for(int z=0;z<14;z++)
                {
                    if(words[j].endsWith(pos[z]))
                    {
                        word = words[j].substring(0,words[j].indexOf("/"));
                        if(now>prev)
                        {
                            //positive case
                            System.out.println("Positive");
                            st = con.createStatement();
                            try{
                            rs = st.executeQuery("select * from words where word='"+word+"' and tag='"+pos[z]+"';");
                            if(rs.first() == false)
                            {
                                st = con.createStatement();
                                st.executeUpdate("INSERT INTO words(word,tag,pcount,ncount) VALUES('"+word+"','"+pos[z]+"',1,0);");
                            }
                            else
                            {
                                st = con.createStatement();
                                st.executeUpdate("update words set pcount = pcount + 1 where word ='"+word+"' and tag = '"+pos[z]+"';");
                            }
                            st = con.createStatement();
                            st.executeUpdate("update count set positive = positive + 1 where type ='article'");
                        
                            }
                            catch(Exception e)
                            {
                                System.out.println(e.toString());
                            }
                        }
                        else if(now<prev)
                        {
                            //negative case
                            st = con.createStatement();
                            try{
                            rs = st.executeQuery("select * from words where word='"+word+"' and tag='"+pos[z]+"';");
                            if(rs.first() == false)
                            {
                                st.executeUpdate("INSERT INTO words(word,tag,pcount,ncount) VALUES('"+word+"','"+pos[z]+"',0,1);");
                            }
                            else
                            {
                                st.executeUpdate("update words set ncount = ncount + 1 where word ='"+word+"' and tag = '"+pos[z]+"';");
                            }
                            st.executeUpdate("update count set negative = negative + 1 where type ='article'");
                        
                            }
                            catch(Exception e)
                            {
                                System.out.println(e.toString());
                            }
                        }
                        
                        
                    }
                }
            }
           try
           {
                //CALCULATING PWORDPOS PWORDNEG
                st = con.createStatement();
                int sumpcount=0,sumncount=0,wcount=0;
                rs = st.executeQuery("select count(*) from words;");
                if(rs.first())
                {
                    wcount= rs.getInt(1);
                }
                
                rs = st.executeQuery("select sum(pcount) from words;");
                if(rs.first())
                {
                    sumpcount= rs.getInt(1);
                }
                st.executeUpdate("update words set PwordPos=(pcount+1)/("+sumpcount+"+"+wcount+");");
                        
                rs = st.executeQuery("select sum(ncount) from words;");
                if(rs.first())
                {
                    sumncount= rs.getInt(1);
                }
                st.executeUpdate("update words set PwordNeg=(ncount+1)/("+sumncount+"+"+wcount+");");
           }
           catch(Exception e)
           {
               System.out.println(e.toString());
           }
            
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
            
        }
        
        //TWEETS
        
        text = new String();
        
        try
        {
            //potag the file and store it in temp
            MaxentTagger tagger = new MaxentTagger("wsj-0-18-bidirectional-distsim.tagger");
            TokenizerFactory<CoreLabel> ptbTokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(),
									   "untokenizable=noneKeep");
            BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(path+"/tweettemp.txt"), "utf-8"));
            DocumentPreprocessor documentPreprocessor = new DocumentPreprocessor(r);
            documentPreprocessor.setTokenizerFactory(ptbTokenizerFactory);
            
            for (List<HasWord> sentence : documentPreprocessor)
            {
                List<TaggedWord> tSentence = tagger.tagSentence(sentence);
                text += Sentence.listToString(tSentence,false);
            }
            
           //retrieve only the words that we want and store in an array!
           String [] words = text.split("\\s+");
           String word;
           
           for(int j=0;j<words.length;j++)
           {
                for(int z=0;z<14;z++)
                {
                    if(words[j].endsWith(pos[z]))
                    {
                        word = words[j].substring(0,words[j].indexOf("/"));
                        if(now>prev)
                        {
                            //positive case
                            st = con.createStatement();
                            rs = st.executeQuery("select * from twitter_words where word='"+word+"' and tag='"+pos[z]+"';");
                            if(rs.first() == false)
                            {
                                st.executeUpdate("INSERT INTO twitter_words(word,tag,pcount,ncount) VALUES('"+word+"','"+pos[z]+"',1,0);");
                            }
                            else
                            {
                                st.executeUpdate("update twitter_words set pcount = pcount + 1 where word ='"+word+"' and tag = '"+pos[z]+"';");
                            }
                            st.executeUpdate("update count set positive = positive + 1 where type ='tweet'");
                        }
                        else if(now<prev)
                        {
                            //negative case
                            st = con.createStatement();
                            rs = st.executeQuery("select * from twitter_words where word='"+word+"' and tag='"+pos[z]+"';");
                            if(rs.first() == false)
                            {
                                st.executeUpdate("INSERT INTO twitter_words(word,tag,pcount,ncount) VALUES('"+word+"','"+pos[z]+"',0,1);");
                            }
                            else
                            {
                                st.executeUpdate("update twitter_words set ncount = ncount + 1 where word ='"+word+"' and tag = '"+pos[z]+"';");
                            }
                            st.executeUpdate("update count set negative = negative + 1 where type ='tweet'");
                        }
                    }
                }
            }
            
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        
        //MESSAGES
        
        
        text = new String();
        
        try
        {
            //potag the file and store it in temp
            MaxentTagger tagger = new MaxentTagger("wsj-0-18-bidirectional-distsim.tagger");
            TokenizerFactory<CoreLabel> ptbTokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(),
									   "untokenizable=noneKeep");
            BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(path+"/messagetemp.txt"), "utf-8"));
            DocumentPreprocessor documentPreprocessor = new DocumentPreprocessor(r);
            documentPreprocessor.setTokenizerFactory(ptbTokenizerFactory);
            
            for (List<HasWord> sentence : documentPreprocessor)
            {
                List<TaggedWord> tSentence = tagger.tagSentence(sentence);
                text += Sentence.listToString(tSentence,false);
            }
            
           //retrieve only the words that we want and store in an array!
           String [] words = text.split("\\s+");
           String word;
           
           for(int j=0;j<words.length;j++)
           {
                for(int z=0;z<14;z++)
                {
                    if(words[j].endsWith(pos[z]))
                    {
                        word = words[j].substring(0,words[j].indexOf("/"));
                        if(now>prev)
                        {
                            //positive case
                            st = con.createStatement();
                            rs = st.executeQuery("select * from messageboard_words where word='"+word+"' and tag='"+pos[z]+"';");
                            if(rs.first() == false)
                            {
                                st.executeUpdate("INSERT INTO messageboard_words(word,tag,pcount,ncount) VALUES('"+word+"','"+pos[z]+"',1,0);");
                            }
                            else
                            {
                                st.executeUpdate("update messageboard_words set pcount = pcount + 1 where word ='"+word+"' and tag = '"+pos[z]+"';");
                            }
                            st.executeUpdate("update count set positive = positive + 1 where type ='tweet'");
                        }
                        else if(now<prev)
                        {
                            //negative case
                            st = con.createStatement();
                            rs = st.executeQuery("select * from messageboard_words where word='"+word+"' and tag='"+pos[z]+"';");
                            if(rs.first() == false)
                            {
                                st.executeUpdate("INSERT INTO messageboard_words(word,tag,pcount,ncount) VALUES('"+word+"','"+pos[z]+"',0,1);");
                            }
                            else
                            {
                                st.executeUpdate("update messageboard_words set ncount = ncount + 1 where word ='"+word+"' and tag = '"+pos[z]+"';");
                            }
                            st.executeUpdate("update count set negative = negative + 1 where type ='tweet'");
                        }
                    }
                }
            }
            
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        
    }
    
    
}
