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

public class NaiveBayes {

   
    public String[] naivebayes(String[] direction)
    {
        int compcount=-1;
        try
        {
            
            String[] arr = new String[100];
            String path = "C:/Documents and Settings/105000/My Documents/Dropbox/Final Year Project 2013/Testing Phase";
            File folder = new File(path+"/Files");
            File[] listofFiles;
            String files;
            File f;
            File file = new File(path+"/articletemp.txt");
            if (!file.exists())
                {
                file.createNewFile();
                }
            File file1 = new File(path+"/tweettemp.txt");
            if (!file1.exists())
                {
                file1.createNewFile();
                }
            File file2 = new File(path+"/messagetemp.txt");
            if (!file2.exists())
                {
                file2.createNewFile();
                }
            
            
            listofFiles = folder.listFiles();

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

            String[] pos={"IN","JJS","JJR","JJ","NNS","NN","RB","RP","VBZ","VBN","VBD","VBG","VBP","VB"};
            String text = new String();
            String[] word = new String[3000];
            int count=-1,pos1=-1,pos2=-1;
            
            BufferedReader r;

            for(int i=0;i<listofFiles.length;i++)
            {
                if(listofFiles[i].isFile())
                {
                    
                    files = listofFiles[i].getName();
                    
                    int x=0;
                    try{
                    System.out.println(files);
                    r = new BufferedReader(new InputStreamReader(new FileInputStream(path+"/Files/"+files), "utf-8"));
                    
                    
                    String line = r.readLine();
                    while (line != null) 
                    {
                        arr[x] = line;
                        x++;
                        line = r.readLine();
                    }
                    
                    pos1=-1;
                    pos2=-1;
                    for(int j=0;j<x;j++)
                    {
                        System.out.println(arr[j]);
                        if(arr[j].length()<1)
                            continue;
                        if((arr[j].charAt(0))=='~')
                        {
                            if(pos1==-1)
                               pos1=j;
                            else 
                                pos2=j;
                        }
                    }
                    
                    }
                    catch(Exception e)
                    {
                        System.out.println(e.toString());
                        
                    }
                    if(pos1 == -1)
                    {
                        compcount++;
                        continue;
                    }
                    
                    //Writing temp article
                    FileWriter fstream = new FileWriter(path+"/articletemp.txt");
                    BufferedWriter out = new BufferedWriter(fstream);
                    try{
                    for(int j=0;j<pos1;j++)
                    {
                        arr[j] = arr[j].replace("'"," ");
                        arr[j] = arr[j].replace("/", " ");
                        out.write(arr[j]);
                        out.newLine();
                        System.out.println(arr[j]);
                    }
                    out.close();
                    }
                    catch(Exception e)
                    {
                        System.out.println(e.toString());
                    }
                    try{
                    //Wrting temp tweets
                    
                    fstream = new FileWriter(path+"/tweettemp.txt");
                    out = new BufferedWriter(fstream);
                    for(int j=pos1+1;j<pos2;j++)
                    {
                        arr[j] = arr[j].replace("'"," ");
                        arr[j] = arr[j].replace("/", " ");
                        
                        out.write(arr[j]);
                        out.newLine();
                    }
                    out.close();
                    }
                    catch(Exception e)
                    {
                        System.out.println(e.toString());
                    }
                    
                    //Wrting temp msg
                    try{
                    fstream = new FileWriter(path+"/messagetemp.txt");
                    out = new BufferedWriter(fstream);
                    for(int j=pos2+1;j<x;j++)
                    {
                        arr[j] = arr[j].replace("'"," ");
                        arr[j] = arr[j].replace("/", " ");
                        
                        out.write(arr[j]);
                        out.newLine();
                        
                    }
                    out.close();
                    }
                    catch(Exception e)
                    {
                        System.out.println(e.toString());
                    }
                    
                    //POSTAG for articles
                    count=-1;
                    text="";
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(path+"/articletemp.txt"), "utf-8"));
                    if(br1.read()==-1)
                    {
                        compcount++;
                        System.out.println(i);
                        continue;
                    }
                    br1.close();
                    MaxentTagger tagger = new MaxentTagger("wsj-0-18-bidirectional-distsim.tagger");
                    TokenizerFactory<CoreLabel> ptbTokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(),
									   "untokenizable=noneKeep");
                    r = new BufferedReader(new InputStreamReader(new FileInputStream(path+"/articletemp.txt"), "utf-8"));
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
                                    if(temp.contains("'"))
                                        break;
                                    rs = st.executeQuery("select * from words where word='"+temp+"' and tag='"+pos[z]+"';");
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
                    rs = st.executeQuery("select * from count where type='article';");
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
                    System.out.println("compcount :"+compcount);
                    System.out.println("Articles            "+Ppos+"             "+Pneg);
                    
                    compcount++;
                    
                    if(Ppos>Pneg)
                        direction[compcount] = "Positive";
                    else
                        direction[compcount] = "Negative";
                    
                    }
                    
                    //POStag for messages
                    NaiveBayesTweets naivebayestweets = new NaiveBayesTweets();
                    
                    direction[compcount+50] = naivebayestweets.bayestweet();
                    
                    NaiveBayesMessages naivebayesmessages = new NaiveBayesMessages();
                    
                    direction[compcount+100] = naivebayesmessages.bayesmessage();
                    
                }
                System.out.println(compcount);
            }
        }

        catch(Exception e)
        {
            System.err.println(e.toString());
        }
        return direction;
    }
}
