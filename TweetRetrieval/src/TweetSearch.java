
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;


public class TweetSearch {

    
    public static void main(String[] args) throws Exception{

        BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in));
        String urlstr = "http://search.twitter.com/search.json?q=";
        StringBuffer buff = new StringBuffer();
        String name;
        
        System.setProperty("http.proxyHost", "proxy.ssn.net");
        System.setProperty("http.proxyPort", "8080");

        System.out.print("Search for : ");
        name= in.readLine();
        urlstr+=name;
        urlstr +="%20&rpp=100";
        urlstr+="&lang=en";
        URL url = new URL(urlstr);
        BufferedReader br = new BufferedReader(
                                    new InputStreamReader(
                                    url.openConnection().getInputStream()));
        int c;
        while((c=br.read())!=-1)
        {
            buff.append((char)c);
        }
        br.close();


        System.out.println("Enter name of file :");
        name= in.readLine();
  File file = new File("C:/Documents and Settings/105000/My Documents/Dropbox/Final Year Project 2013/Tweets/"+name+".txt");
  if (!file.exists())
  {
      file.createNewFile();
  }
  FileWriter fstream = new FileWriter("C:/Documents and Settings/105000/My Documents/Dropbox/Final Year Project 2013/Tweets/"+name+".txt");
  BufferedWriter out = new BufferedWriter(fstream);
  

        JSONObject js = new JSONObject(buff.toString());
        JSONArray tweets = js.getJSONArray("results");
        JSONObject tweet;
        for(int i=0;i<tweets.length();i++) {
            tweet = tweets.getJSONObject(i);
            if (tweets.getJSONObject(i).getString("text").contains(name))
            {
                out.write((i+1)+") "+tweet.getString("created_at"));
                out.write(tweets.getJSONObject(i).getString("text"));
                out.newLine();
            }
            
            
        }
        out.close();
  System.out.println("File created successfully.");
  
  //}
    }
}