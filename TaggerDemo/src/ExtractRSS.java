import java.io.File;
import java.io.*;
import java.util.Scanner;
public class ExtractRSS {

public static void main(String args[])
{
    Scanner scanner = new Scanner(System.in);
    String strLine;
    String[] companylist =   {"ABB","ACC","Ambuja Cements","BHEL","Bharat Petroleum Corporation",
                                      "Bharti Airtel","Cairn Bharat","Cipla","DLF","GAIL",
                                      "Grasim Industries","HCL Technologies","HDFC Bank","Hero Honda","Hindalco",
                                      "Hindustan Unilever","Housing Development Finance","ITC","ICICI","Idea Cellular",
                                      "Infosys Technologies","L&T","Mahindra & Mahindra","Maruti Suzuki Bharat","NTPC",
                                      "National Aluminium","ONGC","Power Grid","Punjab National Bank","Ranbaxy Laboratories",
                                      "Reliance Communications","Reliance Industries","Reliance Infrastructure","Reliance Petroleum","Reliance Power",
                                      "Satyam Computer Services","Siemens","State Bank of India","Steel Authority of Bharat","Sterlite Industries",
                                      "Sun Pharmaceutical Industries","Suzlon Energy","Tata Communications","TCS","Tata Motors",
                                      "Tata Power","Tata Steel","Unitech","Wipro","Zee Entertainment"};
            
    String path="C:/Documents and Settings/105000/My Documents/Dropbox/Final Year Project 2013/RSS Feeds";   

    File f;
    
    File folder = new File(path);
    File [] listOfFiles;
    FileWriter fout;
    BufferedWriter out1;

    int index=0;
    FileInputStream fstream;
    DataInputStream in;
    BufferedReader br;
    listOfFiles = folder.listFiles();
    for( int j=0; j<listOfFiles.length;j++)
    {
        if(listOfFiles[j].getName().equals("temp.txt"))
            continue;
        System.out.println(listOfFiles[j]+".");
     try
     {
            for(index=0;index<50;index++)
            {
                fstream = new FileInputStream(listOfFiles[j]);
                in = new DataInputStream(fstream);
                br = new BufferedReader(new InputStreamReader(in));
            
                while ((strLine = br.readLine()) != null)
                {
                    //System.out.println(strLine);
                    if (strLine.contains(companylist[index]))
                    {
                        System.out.println(companylist[index]);
                        System.out.println (strLine);
                        f = new File(path+"/temp.txt");
                        if(!f.exists())
                        {
                            f.createNewFile();
                        }
                        fout = new FileWriter(path+"/temp.txt");
                        out1 = new BufferedWriter(fout);
                        out1.write(companylist[index]);
                        out1.newLine();
                        out1.write(strLine);
                        out1.close();
            
                        NaiveBayes n = new NaiveBayes();
                        n.naivebayes();
                        System.out.print("next?");
                        scanner.next();
                        
                        //break;
                    }
                }
            }
                
                
        }
            
     catch(Exception e)
     {
      System.err.println("Error: " + e.getMessage());
          
     }
   
    }        

}
}
