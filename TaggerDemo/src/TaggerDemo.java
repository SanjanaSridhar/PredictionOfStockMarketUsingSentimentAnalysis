
import java.io.*;
import java.util.List;

import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.objectbank.TokenizerFactory;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;


class TaggerDemo {

  String path,folder;
  int check;


  public TaggerDemo(String x,String y,int n)
  {
      path=x;
      folder=y;
      
      System.out.println();
      System.out.println(path);
      System.out.println();
      check=n;
      tag();
  }

  public void tag()
  {
      try
      {
        MaxentTagger tagger = new MaxentTagger("wsj-0-18-bidirectional-distsim.tagger");
        TokenizerFactory<CoreLabel> ptbTokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(),
									   "untokenizable=noneKeep");

        String path2=path;
        if(check==1)
        {
            path=path+"/"+folder+"/pwords.txt";
            
        }
        else
        {
            path=path+"/"+folder+"/nwords.txt";
            
        }


        BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(path2+"/temp.txt"), "utf-8"));
        DocumentPreprocessor documentPreprocessor = new DocumentPreprocessor(r);
        documentPreprocessor.setTokenizerFactory(ptbTokenizerFactory);


        FileWriter fout;
        BufferedWriter out1;

        
        File f = new File(path);

        if(!f.exists())
            {
                f.createNewFile();
            }


        fout = new FileWriter(path);
        out1 = new BufferedWriter(fout);
        out1.write("");
        out1.close();



        for (List<HasWord> sentence : documentPreprocessor)
        {
            List<TaggedWord> tSentence = tagger.tagSentence(sentence);

            fout = new FileWriter(path,true);
            out1 = new BufferedWriter(fout);
            out1.append("\n"+Sentence.listToString(tSentence,false)+"||~");
            out1.close();


            System.out.println(Sentence.listToString(tSentence, false)+"||~");
        }
      }
      catch(Exception e)
      {
          System.out.println(e.toString());
      }
   }

}
