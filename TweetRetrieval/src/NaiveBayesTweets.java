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

public class NaiveBayesTweets {

    public void naivebayes()
    {
        try
        {
            Connection con = null;
            String url = "jdbc:mysql://localhost:3306/";
            String db = "features";
            String driver = "com.mysql.jdbc.Driver";
            ResultSet rs = null;

            Class.forName(driver);
            con = DriverManager.getConnection(url+db,"root","sanyo");


            Double[] PwordPos = new Double [400];
            Double[] PwordNeg = new Double [400];

            String[] pos={"IN","JJS","JJR","JJ","NNS","NN","RB","RP","VBZ","VBN","VBD","VBG","VBP","VB"};
            String text = new String();
            String[] word = new String[400];
            int count=-1;
            MaxentTagger tagger = new MaxentTagger("wsj-0-18-bidirectional-distsim.tagger");
            TokenizerFactory<CoreLabel> ptbTokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(),
									   "untokenizable=noneKeep");
            BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Documents and Settings/105000/My Documents/Dropbox/Final Year Project 2013/RSS Feeds/temp.txt"), "utf-8"));
            DocumentPreprocessor documentPreprocessor = new DocumentPreprocessor(r);
            documentPreprocessor.setTokenizerFactory(ptbTokenizerFactory);
            for (List<HasWord> sentence : documentPreprocessor)
            {

                List<TaggedWord> tSentence = tagger.tagSentence(sentence);
                text+=Sentence.listToString(tSentence,false);
            }

            String temp = new String();
            String [] words = text.split("\\s+");
            for(int j=0;j<words.length;j++)
            {
                for(int z=0;z<14;z++)
                {
                    if(words[j].endsWith(pos[z]))
                    {
                        temp = words[j].substring(0,words[j].indexOf("/"));
                        Statement st = con.createStatement();
                        rs = st.executeQuery("select * from words where word='"+temp+"' and tag='"+pos[z]+"';");
                        if(rs.first()== true)
                        {
                            count++;
                            word[count]=temp;
                            PwordPos[count] = rs.getDouble("PwordPos");
                            PwordNeg[count] = rs.getDouble("PwordNeg");
                            System.out.println(word[count]+"      "+pos[z]+"      "+PwordPos[count]+"         "+PwordNeg[count]);
                        }
                        else
                        {
                            System.out.println(temp);
                        }
                     }
                }
            }

            System.out.println(count);
            
            double Ppos = 1 ,Pneg = 1;
            for(int i=0;i<=count;i++)
            {
                  Ppos = Ppos*PwordPos[i]*100;
                  Pneg = Pneg*PwordNeg[i]*100;
            }

            Ppos *= java.lang.Math.pow(10,105);
            Pneg *= java.lang.Math.pow(10,105);
            Ppos = Ppos * (78);
            Pneg = Pneg * (57);

            Ppos /= 135;
            Pneg /= 135;

            System.out.println(Ppos+"             "+Pneg);

        }

        catch(Exception e)
        {
            System.err.println(e.toString());
        }

    }
}
