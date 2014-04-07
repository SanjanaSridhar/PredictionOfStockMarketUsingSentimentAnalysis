import java.io.File;
import java.io.*;
public class ExtractRSS {

public void extractrss()
{
    
    String strLine;
    String[] companylist =   {"ACC","Ambuja Cements","Asian Paints","Axis Bank","Bajaj Auto",
                              "Bank of Baroda","BHEL","Bharat Petroleum Corporation","Bharti Airtel","Cairn Bharat",
                              "Cipla","Coal India","DLF","Dr.Reddy's Lab","GAIL",
                              "Grasim Industries","HCL","HDFC Bank","Hero MotoCorp","Hindalco",
                              "Hindustan Unilever","Housing Development Finance Corporation","ICICI","IDFC","Infosys",
                              "ITC","Jaiprakash Associates","Jindal Steel and Power","Kotak Mahindra","L&T",
                              "Lupin","Mahindra & Mahindra","Maruti Suzuki India","NTPC","ONGC",
                              "Power Grid Corporation","Punjab National Bank","Ranbaxy Laboratories","Reliance Industries","Reliance Infrastructure",
                              "Sesa Goa","Siemens","State Bank of India","Sun Pharmaceutical Industries","TCS",
                              "Tata Motors","Tata Power","Tata Steel","Ultratech Cement","Wipro"};

    String path="C:/Documents and Settings/105000/My Documents/Dropbox/Final Year Project 2013";

    
    File f;

    File folder = new File(path+"/RSS Feeds");
    File [] listOfFiles;
    FileWriter fout;
    BufferedWriter out1;

    File [] listOfFiles2;
    File folder2;
    try{
     folder2 = new File(path+"/Testing Phase/Files");
    listOfFiles2 = folder2.listFiles();
    
    
    try
    {
    
    
        //clearing contents of all the files
        for( int j=0; j<listOfFiles2.length;j++)
        {
            try
            {
                String name = listOfFiles2[j].getName();
                fout = new FileWriter(listOfFiles2[j]);
                out1 = new BufferedWriter(fout);
                out1.write("");
                out1.close();
            }
            catch(Exception e)
            {
                System.out.println(e.toString());
            }
        }
    }
    catch(Exception e)
    {
        System.out.println(e.toString());
    }
    
    int index=0;
    FileInputStream fstream;
    DataInputStream in;
    BufferedReader br;
    listOfFiles = folder.listFiles();
    
    for(int j=0; j<listOfFiles.length;j++)
    {
        System.out.println(listOfFiles[j].getName());
        try
        {
            for(index=0;index<50;index++)
            {
                fstream = new FileInputStream(listOfFiles[j]);
                in = new DataInputStream(fstream);
                br = new BufferedReader(new InputStreamReader(in));

                while ((strLine = br.readLine()) != null)
                {
                    if (strLine.contains(companylist[index]))
                    {
                        System.out.println(companylist[index]);
                        f = new File(path+"/Testing Phase/Files/"+companylist[index]+".txt");
                        if(!f.exists())
                        {
                            f.createNewFile();
                            
                        }
                        fout = new FileWriter(path+"/Testing Phase/Files/"+companylist[index]+".txt",true);
                        out1 = new BufferedWriter(fout);
                        out1.write(strLine);
                        out1.close();
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
   

    catch(Exception e)
    {
        System.out.println(e.toString());
    }
    
    //ClearRSS c = new ClearRSS();
    //c.clearrss();
}
}