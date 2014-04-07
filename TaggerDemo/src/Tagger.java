import java.io.File;
import java.io.*;

public class Tagger
{

 public static void main(String[] args)
{

  String path = "C:/Documents and Settings/105000/My Documents/Dropbox/Final Year Project 2013/News Articles";
  String folders;
  File folder = new File(path);
  File[] listOfDirectories = folder.listFiles();

  TaggerDemo t;
  String strLine;
  FileWriter fout;
  BufferedWriter out1;

  try
  {


  for (int i = 0; i < listOfDirectories.length; i++)
  {
      if (listOfDirectories[i].isDirectory())
    {
        folders = listOfDirectories[i].getName();
        System.out.println(folders);
        FileInputStream fstream = new FileInputStream(path+"/"+folders+"/"+"positive.txt");
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        File f;

            f=new File(path+"/temp.txt");
            if(!f.exists())
            {
                f.createNewFile();
            }


        fout = new FileWriter(path+"/temp.txt");
        out1 = new BufferedWriter(fout);
        out1.write("");
        while ((strLine = br.readLine()) != null)
        {
            out1.append(strLine);
            if(strLine.endsWith("||~"))
            {
                out1.close();
                t = new TaggerDemo(path,folders,1);
                fout = new FileWriter(path+"/temp.txt");
                out1 = new BufferedWriter(fout);
                 }



        }
        out1.close();
         }

   }

  }

  catch(Exception e)
  {
    System.err.println(e.toString());
  }

  //same for negative
  try
  {


  for (int i = 0; i < listOfDirectories.length; i++)
  {
      if (listOfDirectories[i].isDirectory())
    {
        folders = listOfDirectories[i].getName();
        System.out.println(folders);
        FileInputStream fstream = new FileInputStream(path+"/"+folders+"/"+"negative.txt");
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        File f;

            f=new File(path+"/temp.txt");
            if(!f.exists())
            {
                f.createNewFile();
            }


        fout = new FileWriter(path+"/temp.txt");
        out1 = new BufferedWriter(fout);
        out1.write("");
        while ((strLine = br.readLine()) != null)
        {
            out1.append(strLine);
            if(strLine.endsWith("||~"))
            {
                out1.close();
                t = new TaggerDemo(path,folders,0);
                fout = new FileWriter(path+"/temp.txt");
                out1 = new BufferedWriter(fout);
                //out1.write("");
            }



        }
        out1.close();
        
    }

   }

  }

  catch(Exception e)
  {
    System.err.println(e.toString());
  }
 }
}