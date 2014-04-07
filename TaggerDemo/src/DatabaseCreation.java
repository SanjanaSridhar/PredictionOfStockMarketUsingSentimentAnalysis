import java.sql.*;
import java.io.*;
public class DatabaseCreation {

    
    public static void main(String[] args) {
 
  Connection con = null;
  String url = "jdbc:mysql://localhost:3306/";
  String db = "features";
  String driver = "com.mysql.jdbc.Driver";

  String path = "C:/Documents and Settings/105000/My Documents/Dropbox/Final Year Project 2013/News Articles";
  String folders;
  File folder = new File(path);
  File[] listOfDirectories = folder.listFiles();

  String[] pos={"IN","JJS","JJR","JJ","NNS","NN","RB","RP","VBZ","VBN","VBD","VBG","VBP","VB"};

  FileInputStream fstream;
  DataInputStream in;
  BufferedReader br;

  String strline,word,temp;

  try{

      Class.forName(driver);
      con = DriverManager.getConnection(url+db,"root","sanyo");

      for (int i = 0; i < listOfDirectories.length; i++)
      {
        if (listOfDirectories[i].isDirectory())
        {
            folders = listOfDirectories[i].getName();
            System.out.println(path+"/"+folders+"/pwords.txt");
            fstream = new FileInputStream(path+"/"+folders+"/pwords.txt");
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
                            System.out.println(word+"    "+pos[z]);
                            try
                            {

                                Statement st = con.createStatement();
                                int val = st.executeUpdate("INSERT INTO words VALUES('"+word+"','positive','"+pos[z]+"');");

                            }
                            catch (SQLException s)
                            {
                                System.out.println(e.toString);
                            }
                            break;
                        }
                    }
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
