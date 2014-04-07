import java.sql.*;
import java.io.*;
public class FrequencyCountTweets {
  public static void main(String[] args) {
  Connection con = null;
  String url = "jdbc:mysql://localhost:3306/";
  String db = "features";
  String driver = "com.mysql.jdbc.Driver";

  String path = "C:/Documents and Settings/105000/My Documents/Dropbox/Final Year Project 2013/Tweets";
  
  String[] pos={"IN","JJS","JJR","JJ","NNS","NN","RB","RP","VBZ","VBN","VBD","VBG","VBP","VB"};

  FileInputStream fstream;
  DataInputStream in;
  BufferedReader br;
  ResultSet rs = null;

  String strline,word,temp;

  try{

      Class.forName(driver);
      con = DriverManager.getConnection(url+db,"root","sanyo");

            System.out.println(path+"/ntweets.txt");
            fstream = new FileInputStream(path+"/ntweets.txt");
            in = new DataInputStream(fstream);
            br = new BufferedReader(new InputStreamReader(in));

            while((strline = br.readLine())!=null)
            {
                String [] words = strline.split("\\s+");
                for(int j=0;j<words.length;j++)
                {
                    for(int z=0;z<14;z++)
                    {
                        if(words[j].endsWith(pos[z]))
                        {
                            word = words[j].substring(0,words[j].indexOf("/"));

                            try
                            {   Statement st = con.createStatement();
                                rs = st.executeQuery("select * from twitter_words where word='"+word+"' and tag='"+pos[z]+"';");
                                if(rs.first() == false)
                                {
                                int val = st.executeUpdate("INSERT INTO twitter_words(word,tag,pcount,ncount) VALUES('"+word+"','"+pos[z]+"',0,1);");
                                System.out.println(word+"   "+pos[z]);
                                }
                                else
                                {
                                    st.executeUpdate("update twitter_words set ncount = ncount + 1 where word ='"+word+"' and tag = '"+pos[z]+"';");
                                }
                            }
                            catch (SQLException s)
                            {   System.err.println(s.toString());
                            }
                            break;
                        }
                    }
                }
            }
        }

  
  catch (Exception e){
  e.printStackTrace();
  }
  }

}
