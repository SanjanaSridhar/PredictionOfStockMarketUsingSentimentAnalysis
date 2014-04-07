import java.io.File;
import java.io.*;
import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.objectbank.TokenizerFactory;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.util.List;
public class POSTaggingTweets {
public static void main(String args[])
    {

    try{

            String path = "C:/Documents and Settings/105000/My Documents/Dropbox/Final Year Project 2013/Tweets";
            File f;
            FileWriter fout;
            BufferedWriter out1;

            f=new File(path+"/ptweets.txt");
            if(!f.exists())
            {
                f.createNewFile();
            }
            fout = new FileWriter(f);
            out1 = new BufferedWriter(fout);



               MaxentTagger tagger = new MaxentTagger("wsj-0-18-bidirectional-distsim.tagger");
            TokenizerFactory<CoreLabel> ptbTokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(),
									   "untokenizable=noneKeep");
            BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Documents and Settings/105000/My Documents/Dropbox/Final Year Project 2013/Tweets/positive.txt"), "utf-8"));
            DocumentPreprocessor documentPreprocessor = new DocumentPreprocessor(r);
            documentPreprocessor.setTokenizerFactory(ptbTokenizerFactory);
            for (List<HasWord> sentence : documentPreprocessor)
            {

                List<TaggedWord> tSentence = tagger.tagSentence(sentence);
                out1.write(Sentence.listToString(tSentence,false));
                System.out.println(Sentence.listToString(tSentence,false));
            }

            out1.close();

            f=new File(path+"/ntweets.txt");
            if(!f.exists())
            {
                f.createNewFile();
            }
            fout = new FileWriter(f);
            out1 = new BufferedWriter(fout);

            r = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Documents and Settings/105000/My Documents/Dropbox/Final Year Project 2013/Tweets/negative.txt"), "utf-8"));
            documentPreprocessor = new DocumentPreprocessor(r);
            documentPreprocessor.setTokenizerFactory(ptbTokenizerFactory);
            for (List<HasWord> sentence : documentPreprocessor)
            {

                List<TaggedWord> tSentence = tagger.tagSentence(sentence);
                out1.write(Sentence.listToString(tSentence,false));
                System.out.println(Sentence.listToString(tSentence,false));
            }
            out1.close();
        }
        catch(Exception e)
        {
            System.err.println(e.toString());
        }

}
}
