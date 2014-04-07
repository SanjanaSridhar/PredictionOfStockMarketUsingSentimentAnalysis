import java.io.File;
import java.io.*;

public class RetrieveStock {
    
    public String getstock(int index,String date)
    {
        String company = new String();
        String[] companylist =   {"ACC","AMBUJACEM","ASIANPAINT","AXISBANK", "BAJAJAUTO",
                                    "BANKBARODA","BHARTIARTL","BHEL","BPCL","CAIRN",
                                    "CIPLA","COALINDIA","DLF","DRREDDY","GAIL",
                                    "GRASIM","HCLTECH","HDFCBANK","HEROMOTOCO","HINDALCO",
                                    "HINDUNILVR","HDFC","ICICIBANK","IDFC","INFY",
                                    "ITC","JPASSOCIAT","JINDALSTEL","KOTAKBANK","LT",
                                    "LUPIN","MARUTI","MM","NTPC","ONGC",
                                    "POWERGRID","PNB","RANBAXY","RELIANCE","RELINFRA",
                                    "SESAGOA","SIEMENS","SBIN","SUNPHARMA","TATAMOTORS",
                                    "TATAPOWER","TATASTEEL","TCS","ULTRACEMCO","WIPRO"
                                };
        
        company = companylist[index];
        
        String path = "C:/Documents and Settings/105000/My Documents/Dropbox/Final Year Project 2013/StockDataToday/"+company+".txt";
        String strLine;
        String temp = new String();
        
        
        try
        {
            FileInputStream fstream = new FileInputStream(path);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            
            
            while((strLine = br.readLine()) != null)
            {
               int t = strLine.indexOf(":00,") + 4;
               temp = strLine.substring(t);
               
               if(strLine.contains(date))
                {
                    int pos = strLine.indexOf(date) + 18 ;
                    return strLine.substring(pos);
                }
            }
            
            br.close();
            
            
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        return temp;
        
    }
    
    
}
