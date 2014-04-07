import java.io.*;

public class extractpara {
    
    public static void main(String args[])
    {
        try
        {
            FileInputStream fstream = new FileInputStream("C:/Documents and Settings/105000/My Documents/Dropbox/Final Year Project 2013/News Articles/ACC/Apr 19.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            // the list of companies
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
                                    
        
            int n=1,index=0;
            while ((strLine = br.readLine()) != null)
            {
                if(n==1)
                {   
                    for(index=0;index<50;index++)
                    {
                        if (strLine.contains(companylist[index]))
                        {
                            System.out.println (n+strLine);
                            break;
                        }
                    }
                }
                else
                {
                    if(strLine.contains(companylist[index]))
                        System.out.println (n+strLine);
                }
                n++;
            }
            in.close();
        }
        catch(Exception e)
        {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}
