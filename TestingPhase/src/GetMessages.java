
import java.net.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;

public class GetMessages{

    String path = new String();
    
    String[] Link = {   "http://mmb.moneycontrol.com/stock-message-forum/acc/comments/865",
                        "http://mmb.moneycontrol.com/stock-message-forum/ambujacements/comments/3666",
                        "http://mmb.moneycontrol.com/stock-message-forum/asianpaints/comments/262",
                        "http://mmb.moneycontrol.com/stock-message-forum/axisbank/comments/3142",
                        "http://mmb.moneycontrol.com/stock-message-forum/bajajauto/comments/246891",
                        "http://mmb.moneycontrol.com/stock-message-forum/bankofbaroda/comments/261",
                        "http://mmb.moneycontrol.com/stock-message-forum/bharatheavyelectricals/comments/4062",
                        "http://mmb.moneycontrol.com/stock-message-forum/bharatpetroleumcorporation/comments/1145",
                        "http://mmb.moneycontrol.com/stock-message-forum/bhartiairtel/comments/1202",
                        "http://mmb.moneycontrol.com/stock-message-forum/cairnindia/comments/246657",
                        "http://mmb.moneycontrol.com/stock-message-forum/cipla/comments/421",
                        "http://mmb.moneycontrol.com/stock-message-forum/coalindia/comments/509175",
                        "http://mmb.moneycontrol.com/stock-message-forum/dlf/comments/246763",
                        "http://mmb.moneycontrol.com/stock-message-forum/drreddyslaboratories/comments/2201",
                        "http://mmb.moneycontrol.com/stock-message-forum/gailindia/comments/1084",
                        "http://mmb.moneycontrol.com/stock-message-forum/grasimindustries/comments/2261",
                        "http://mmb.moneycontrol.com/stock-message-forum/hcltechnologies/comments/682",
                        "http://mmb.moneycontrol.com/stock-message-forum/hdfcbank/comments/4962",
                        "http://mmb.moneycontrol.com/stock-message-forum/heromotocorp/comments/1104",
                        "http://mmb.moneycontrol.com/stock-message-forum/hindalcoindustries/comments/6261",
                        "http://mmb.moneycontrol.com/stock-message-forum/hindustanunilever/comments/1147",
                        "http://mmb.moneycontrol.com/stock-message-forum/housingdevelopmentfinancecorporation/comments/1125",
                        "http://mmb.moneycontrol.com/stock-message-forum/icicibank/comments/6422",
                        "http://mmb.moneycontrol.com/stock-message-forum/idfc/comments/246146",
                        "http://mmb.moneycontrol.com/stock-message-forum/infosys/comments/4001",
                        "http://mmb.moneycontrol.com/stock-message-forum/itc/comments/341",
                        "http://mmb.moneycontrol.com/stock-message-forum/jaiprakashassociates/comments/243590",
                        "http://mmb.moneycontrol.com/stock-message-forum/jindalsteelpower/comments/43561",
                        "http://mmb.moneycontrol.com/stock-message-forum/kotakmahindrabank/comments/3441",
                        "http://mmb.moneycontrol.com/stock-message-forum/larsentoubro/comments/4721",
                        "http://mmb.moneycontrol.com/stock-message-forum/lupin/comments/4061",
                        "http://mmb.moneycontrol.com/stock-message-forum/mahindramahindra/comments/4662",
                        "http://mmb.moneycontrol.com/stock-message-forum/marutisuzukiindia/comments/51385",
                        "http://mmb.moneycontrol.com/stock-message-forum/ntpc/comments/164775",
                        "http://mmb.moneycontrol.com/stock-message-forum/oilnaturalgascorporation/comments/2161",
                        "http://mmb.moneycontrol.com/stock-message-forum/powergridcorporationindia/comments/246800",
                        "http://mmb.moneycontrol.com/stock-message-forum/punjabnationalbank/comments/684",
                        "http://mmb.moneycontrol.com/stock-message-forum/ranbaxylaboratories/comments/2024",
                        "http://mmb.moneycontrol.com/stock-message-forum/relianceindustries/comments/322",
                        "http://mmb.moneycontrol.com/stock-message-forum/relianceinfrastructure/comments/129895",
                        "http://mmb.moneycontrol.com/stock-message-forum/sesagoa/comments/5141",
                        "http://mmb.moneycontrol.com/stock-message-forum/siemens/comments/3322",
                        "http://mmb.moneycontrol.com/stock-message-forum/statebankindia/comments/406",
                        "http://mmb.moneycontrol.com/stock-message-forum/sunpharmaceuticalindustries/comments/7841",
                        "http://mmb.moneycontrol.com/stock-message-forum/tataconsultancyservices/comments/148716",
                        "http://mmb.moneycontrol.com/stock-message-forum/tatamotors/comments/1605",
                        "http://mmb.moneycontrol.com/stock-message-forum/tatapowercompany/comments/1904",
                        "http://mmb.moneycontrol.com/stock-message-forum/tatasteel/comments/1421",
                        "http://mmb.moneycontrol.com/stock-message-forum/ultratechcement/comments/186021",
                        "http://mmb.moneycontrol.com/stock-message-forum/wipro/comments/1901"
                    };
    
    
    
    public void read()
    {
        try
        {
            int pos;
            File f;
            FileWriter fout;
            BufferedWriter out1;
            
            path = "C:/Documents and Settings/105000/My Documents/Dropbox/Final Year Project 2013/Testing Phase/Files";
            
            //System.setProperty("http.proxyHost", "proxy.ssn.net");
            //System.setProperty("http.proxyPort", "8080");
            File [] listOfFiles;
            File folder = new File(path);
            listOfFiles = folder.listFiles();
            FileInputStream fstream;
            DataInputStream input;
            BufferedReader br;
                    
    
            for( int i=0; i<listOfFiles.length;i++)
            {
                String name = listOfFiles[i].getName();
                        
                fstream = new FileInputStream(listOfFiles[i]);
                input = new DataInputStream(fstream);
                br = new BufferedReader(new InputStreamReader(input));
                if ((br.readLine()) != null)
                {
                    fout= new FileWriter(path+"/"+name,true);
                    out1 = new BufferedWriter(fout);
                    out1.newLine();
                    out1.write("~~~~~");
                    Document doc = Jsoup.connect(Link[i]).timeout(0).get();
                    
                int count = doc.select("div.PT18.PB18.brdb.iepb").size();
            
            
                Element MemberValue,Info,Time;
                for(int j=0;j<count;j++)
                {
                    if((MemberValue = doc.select("div.PT18.PB18.brdb.iepb").get(j).select("div.b_11.PT5.PB2.d85").first())!=null)
                    {      
                       if(MemberValue.text().equals("Platinum Member"));
                        else
                            continue;
                    }
            
                    
            
                    if((Time = doc.select("div.PT18.PB18.brdb.iepb").get(j).select("div.gL_11.PT10.PB10").first())!=null)
                    {
                        pos = Time.text().indexOf("m");
                        if(pos==8);
                        else if(pos==9)
                        {
                            int time = Integer.parseInt(Time.text().substring(6, 8));
                            if(time>10)
                                continue;
                        }
                        else 
                            continue;
                          
                    }
                    if((Info = doc.select("div.PT18.PB18.brdb.iepb").get(j).select("div.info a").first())!=null)
                    {
                        System.out.println(" Message :"+name+"       "+Info.text());
                        out1.newLine();
                        out1.write(Info.text());
                    }
                }
                out1.close();
           
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
    }
    
    
}
