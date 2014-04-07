
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class ReadArticle {

    String Title,Link,PubDate;
    SimpleDateFormat parse = new SimpleDateFormat("dd MMM yyyy hh:mm:ss");

    
    
    public void read(String Title, String Link, String PubDate)
    {
        this.Title=Title;
        this.PubDate=PubDate;
        this.Link=Link;
        try
        {
            
            String content = new String();
            String path = new String(); 
            
            PubDate = PubDate.substring(5);
            java.util.Date parsed =parse.parse(PubDate);
            long a=parsed.getTime();
            long b= a+(330*60*1000);
            java.util.Date newdate=new java.util.Date(b); 
            java.util.Date today = new java.util.Date();
            System.out.println(today.toString());
            System.out.println(newdate.toString());
            
            long val=((today.getTime()-newdate.getTime())/(60*1000));
            System.out.print("time diff      "+val);
            System.out.println(" ");
            
            if(val<0 || val>500)
                return;
                    
            System.out.println("Read Article");
            File f;
            FileWriter fout;
            BufferedWriter out1;
            
            path = "C:/Documents and Settings/105000/My Documents/Dropbox/Final Year Project 2013/RSS Feeds";
            
            f=new File(path+"/"+Title+".txt");
            if(!f.exists())
            {
                f.createNewFile();
            }

            fout = new FileWriter(path+"/"+Title+".txt");
            out1 = new BufferedWriter(fout);
            
            
            //URL u = new URL(Link); // your feed url
            //System.setProperty("http.proxyHost", "proxy.ssn.net");
            //System.setProperty("http.proxyPort", "8080");
            
            out1.write(Title);
            out1.newLine();
            out1.write(PubDate);
            out1.newLine();
            out1.write(Link);
            
            out1.close();
            System.out.println("Read Article done");
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
    }
    
    
    
}
