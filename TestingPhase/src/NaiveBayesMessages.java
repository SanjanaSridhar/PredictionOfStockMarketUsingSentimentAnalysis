
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.objectbank.TokenizerFactory;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 105000
 */
public class NaiveBayesMessages {
    
    public String bayesmessage()
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
            
            String path = new String("C:/Documents and Settings/105000/My Documents/Dropbox/Final Year Project 2013/Testing Phase/messagetemp.txt");
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

                                rs = st.executeQuery("select * from messageboard_words where word='"+temp+"' and tag='"+pos[z]+"';");
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
                }
                    
            
            
                Statement st = con.createStatement();
                rs = st.executeQuery("select * from count where type='message';");
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
                
                System.out.println("Messages            "+Ppos+"             "+Pneg);
                    
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
 
    
}
