
import java.net.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;

public class MessageBoard {

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
            String path2 = new String(); 
            int pos;
            File f;
            FileWriter fout;
            BufferedWriter out1;
            
            path = "C:/Documents and Settings/105000/My Documents/Dropbox/Final Year Project 2013/Message Board";
            
            
            
            for(int j=0;j<50;j++)
            {
                
            path2 = Link[j].substring(48);
            pos = path2.indexOf("/");
            path2 = path2.substring(0, pos);
            System.out.println(path2);
            
            
            f=new File(path+"/"+path2+".txt");
            if(!f.exists())
            {
                f.createNewFile();
            }

            fout = new FileWriter(path+"/"+path2+".txt");
            out1 = new BufferedWriter(fout);
            out1.write("");
                
            System.setProperty("http.proxyHost", "proxy.ssn.net");
            System.setProperty("http.proxyPort", "8080");
            Document doc = Jsoup.connect(Link[j]).timeout(0).get();
            
            System.out.println(Link[j]);
            
            int count = doc.select("div.PT18.PB18.brdb.iepb").size();
            
            
           Element MemberValue , Followers, Price,Info,Time;
           for(int i=0;i<count;i++)
           {
            if((MemberValue = doc.select("div.PT18.PB18.brdb.iepb").get(i).select("div.b_11.PT5.PB2.d85").first())!=null)
            {   
                if(MemberValue.text().equals("Platinum Member"))
                {
                    System.out.println(MemberValue.text());
                    //out1.write(MemberValue.text());
                }
                else
                    continue;
            }
            if((Followers = doc.select("div.PT18.PB18.brdb.iepb").get(i).select("div.gL_10.d85").first())!=null)
            {
                System.out.println(Followers.text());
                //out1.write(Followers.text());
            }
             
            if((Price =doc.select("div.PT18.PB18.brdb.iepb").get(i).select("p.gL_11.MT5.MB5").first())!=null)
            {
                System.out.println(Price.text());
                out1.write(Price.text());
            }
            
            if((Info = doc.select("div.PT18.PB18.brdb.iepb").get(i).select("div.info a").first())!=null)
            {
                System.out.println(Info.text());
                out1.write(Info.text());
            }
            
            if((Time = doc.select("div.PT18.PB18.brdb.iepb").get(i).select("div.gL_11.PT10.PB10").first())!=null)
            {
                System.out.println(Time.text());
                out1.write(Time.text());
            }
            
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            
            out1.newLine();
            out1.newLine();
            
           }
           out1.close();
            }
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
    }
    
    public static void main(String args[])
    {
        MessageBoard m = new MessageBoard();
        m.read();
    }
    
}
