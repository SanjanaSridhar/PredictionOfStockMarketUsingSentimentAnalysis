import java.io.File;
import java.io.*;

public class test
{

 public static void main(String[] args)
{

   String strLine;
   int n=1;
  // Directory path here
  String path = "C:/Documents and Settings/105000/My Documents/Dropbox/Final Year Project 2013/News Articles";
  double start,end,percentage=0,poscount=0,ncount=0;
  String folders,files;
  File folder = new File(path);
  File[] listOfDirectories = folder.listFiles();
  File fl;
  File[] listofFiles;
  FileWriter fout;
  BufferedWriter out1;
  for (int i = 0; i < listOfDirectories.length; i++)
  {

   if (listOfDirectories[i].isDirectory())
   {
        folders = listOfDirectories[i].getName();
        System.out.println(folders);
        fl= new File(path+"/"+folders);
        
        try
        {
            File f;

            f=new File(path+"/"+folders+"/positive.txt");
            if(!f.exists())
            {
                f.createNewFile();
            }

            f=new File(path+"/"+folders+"/negative.txt");
            if(!f.exists())
            {
                f.createNewFile();
            }

            fout = new FileWriter(path+"/"+folders+"/positive.txt");
            out1 = new BufferedWriter(fout);
            out1.write("");
            out1.close();
            fout = new FileWriter(path+"/"+folders+"/negative.txt");
            out1 = new BufferedWriter(fout);
            out1.write("");
            out1.close();
            

        }
        catch(Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }
        
        listofFiles = fl.listFiles();
        for( int j=0; j<listofFiles.length;j++)
        {
            if(listofFiles[j].isFile())
            {
                files=listofFiles[j].getName();
                
                if((files.contains("positive"))||(files.contains("negative"))||(files.contains("pwords"))||(files.contains("nwords")))
                {
                    continue;
                }
                System.out.println("\t"+files);
                try
                {
                    FileInputStream fstream = new FileInputStream(path+"/"+folders+"/"+files);
                    DataInputStream in = new DataInputStream(fstream);
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));

                    while ((strLine = br.readLine()) != null)
                    {
                        if(strLine.startsWith("StockQuote"))
                        {
                            System.out.println (strLine);
                            String[] ln=strLine.split(" ");
                            start=Double.parseDouble(ln[2]);
                            end=Double.parseDouble(ln[3]);
                            percentage=((end-start)/start);
                            percentage=percentage*100;
                            System.out.println(percentage);
                        }

                    }

                    in.close();
                    n=1;
                    fstream = new FileInputStream(path+"/"+folders+"/"+files);
                    in = new DataInputStream(fstream);
                    br = new BufferedReader(new InputStreamReader(in));
                    if(percentage>0)
                            {
                                fout = new FileWriter(path+"/"+folders+"/positive.txt",true);
                                out1 = new BufferedWriter(fout);
                                poscount++;
                            }
                    else
                            {
                                fout = new FileWriter(path+"/"+folders+"/negative.txt",true);
                                out1 = new BufferedWriter(fout);
                                ncount++;
                            }
                    while ((strLine = br.readLine()) != null)
                    {
                        if(strLine.contains(folders))
                        {
                            out1.append("\n"+strLine);
                        }
                        n++;
                    }
                    out1.append("||~");
                    
                    out1.close();
                    in.close();
                    System.out.println("Number of Positive articles: "+poscount);
                    System.out.println("Number of Negative articles: "+ncount);
                    

                }
                catch (Exception e)
                {
                        System.out.println("Error: " + e.toString() );
                }

            }
        }
    }
  }
 }
}