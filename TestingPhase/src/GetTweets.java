
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import org.json.JSONArray;
import org.json.JSONObject;


public class GetTweets {

    
    public void read() {

        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        SimpleDateFormat parse = new SimpleDateFormat("dd MMM yyyy hh:mm:ss");
        String urlstr = "http://search.twitter.com/search.json?q=";
        StringBuffer buff = new StringBuffer();
        String name;
        String path="C:/Documents and Settings/105000/My Documents/Dropbox/Final Year Project 2013/Testing Phase/Files";
        //System.setProperty("http.proxyHost", "proxy.ssn.net");
        //System.setProperty("http.proxyPort", "8080");

        File [] listOfFiles;
        File folder = new File(path);
        listOfFiles = folder.listFiles();
        FileInputStream fstream;
        DataInputStream input;
        BufferedReader br;
        FileWriter fout;
        BufferedWriter out;
                    
    
        for( int i=0; i<listOfFiles.length;i++)
        {
            name = listOfFiles[i].getName();
            if((name.contains("articletemp"))||(name.contains("tweettemp"))||(name.contains("messagetemp")))
                continue;
            int pos = name.indexOf(".");
            String name1 = name.substring(0, pos);
            String name2=name1.replace(" ", "%20");
            
            try{
                
            
            fstream = new FileInputStream(listOfFiles[i]);
            input = new DataInputStream(fstream);
            br = new BufferedReader(new InputStreamReader(input));
            if ((br.readLine()) != null)
                {
                    fout= new FileWriter(path+"/"+name,true);
                    out = new BufferedWriter(fout);
                    out.newLine();
                    out.write("~~~~~");
                    urlstr+=name2;
                    urlstr +="%20&rpp=100";
                    urlstr+="&lang=en";
                    URL url = new URL(urlstr);
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                    int c;
                    while((c=br1.read())!=-1)
                    {
                        buff.append((char)c);
                    }
                    br1.close();
                
            
            
        JSONObject js = new JSONObject(buff.toString());
        JSONArray tweets = js.getJSONArray("results");
        JSONObject tweet;
        for(int j=0;j<tweets.length();j++) 
        {
            tweet = tweets.getJSONObject(j);
            if (tweets.getJSONObject(j).getString("text").contains(name1))
            {
                String result=tweet.getString("created_at");
                String strLine=tweets.getJSONObject(j).getString("text");
                pos=result.indexOf("+0000");
                result=result.substring(0,pos-1);
                
                result = result.substring(5);
                java.util.Date parsed =parse.parse(result);
                long a=parsed.getTime();
                long b= a+(330*60*1000);
                java.util.Date newdate=new java.util.Date(b); 
                java.util.Date today = new java.util.Date();
                if(((today.getTime()-newdate.getTime())/(60*1000))>10)
                continue;
                pos = strLine.indexOf("http");
                        if(pos != -1)
                        {
                           strLine = strLine.substring(0,pos);
                        }
                        
                        pos = strLine.indexOf("RT");
                        if(pos != -1)
                        {
                            strLine = strLine.substring(pos+2, strLine.length());
                        }
                        
                        pos = strLine.indexOf("@");
                        if(pos != -1)
                        {
                            String str = strLine.substring(pos, strLine.length());
                            int pos2 = str.indexOf(" ");
                            strLine = strLine.substring(0,pos)+str.substring(pos2, str.length());
                        }
                        
                System.out.println(result);
                System.out.println(strLine);
                out.newLine();
                out.write(strLine);
                
            }
            
            
        }
        out.close();
            
        }   
            }
            catch(Exception e)
            {
                System.out.println(e.toString());
                
            }
        }    
  
    }
}