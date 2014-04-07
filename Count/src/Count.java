import java.io.File;
import java.io.*;
import java.sql.*;
import java.lang.*;


public class Count {

    public static void main(String arg[] )
    {


        try
        {
            Connection con = null;
            String url = "jdbc:mysql://localhost:3306/";
            String db = "features";
            String driver = "com.mysql.jdbc.Driver";
            ResultSet rs = null;

            Class.forName(driver);
            con = DriverManager.getConnection(url+db,"root","sanju");


        FileInputStream fstream;
        DataInputStream in;
        BufferedReader br;
        String strLine;
        String path = "C:/Documents and Settings/new user/My Documents/Dropbox/Final Year Project 2013/News Articles";
        double start,end,pcount=0,ncount=0,ptweetcount=0,ntweetcount=0,pmsgcount=0,nmsgcount=0;
        String folders,files;
        File folder = new File(path);
        //articles
        File[] listOfDirectories = folder.listFiles();
        File fl;
        File[] listofFiles;

        for (int i = 0; i < listOfDirectories.length; i++)
        {

            if (listOfDirectories[i].isDirectory())
            {
                folders = listOfDirectories[i].getName();
                System.out.println(folders);
                fl= new File(path+"/"+folders);
                listofFiles = fl.listFiles();
                for( int j=0; j<listofFiles.length;j++)
                {
                    if(listofFiles[j].isFile())
                    {
                        files=listofFiles[j].getName();
                        
                        if((files.contains("positive"))||(files.contains("negative"))||(files.contains("pwords"))||(files.contains("nwords")))
                        {
                            continue;
                        }
                        try
                        {
                            fstream = new FileInputStream(path+"/"+folders+"/"+files);
                            in = new DataInputStream(fstream);
                            br = new BufferedReader(new InputStreamReader(in));

                            while ((strLine = br.readLine()) != null)
                            {
                                if(strLine.startsWith("StockQuote"))
                                {
                                    
                                    String[] ln=strLine.split(" ");
                                    start=Double.parseDouble(ln[2]);
                                    end=Double.parseDouble(ln[3]);
                                    if(start<end)
                                    {
                                        pcount++;
                                    }
                                    else
                                    {
                                        ncount++;
                                    }

                                }
                            }
                            in.close();

                        }
                        catch(Exception e)
                        {
                            System.out.println(e.toString());
                        }



                    }
                }
            }
        }
        Statement st = con.createStatement();

        rs = st.executeQuery("select * from count where type = 'article';");
        if(rs.first()== true)
        {
            st.executeUpdate("update count set positive="+pcount+", negative="+ncount+" where type = 'article';");
        }
        else
        {
            st.executeUpdate("INSERT INTO count values('article',"+pcount+","+ncount+");");
        }

        //tweets
        path = "C:/Documents and Settings/new user/My Documents/Dropbox/Final Year Project 2013/Tweets";
        fstream = new FileInputStream(path+"/positive.txt");
        in = new DataInputStream(fstream);
        br = new BufferedReader(new InputStreamReader(in));
            while ((strLine = br.readLine()) != null)
                ptweetcount++;

        fstream = new FileInputStream(path+"/negative.txt");
        in = new DataInputStream(fstream);
        br = new BufferedReader(new InputStreamReader(in));
            while ((strLine = br.readLine()) != null)
                ntweetcount++;

        System.out.println(ptweetcount+"           "+ntweetcount);
        

        st = con.createStatement();

        rs = st.executeQuery("select * from count where type = 'tweet';");
        if(rs.first()== true)
        {
            st.executeUpdate("update count set positive="+ptweetcount+", negative="+ntweetcount+" where type = 'tweet';");
        }
        else
        {
            st.executeUpdate("INSERT INTO count values('tweet',"+ptweetcount+","+ntweetcount+");");
        }


        //message

        path = "C:/Documents and Settings/new user/My Documents/Dropbox/Final Year Project 2013/Message Board";
        fstream = new FileInputStream(path+"/positive.txt");
        in = new DataInputStream(fstream);
        br = new BufferedReader(new InputStreamReader(in));
            while ((strLine = br.readLine()) != null)
                pmsgcount++;

        fstream = new FileInputStream(path+"/negative.txt");
        in = new DataInputStream(fstream);
        br = new BufferedReader(new InputStreamReader(in));
            while ((strLine = br.readLine()) != null)
                nmsgcount++;

        st = con.createStatement();

        rs = st.executeQuery("select * from count where type = 'message';");
        if(rs.first()== true)
        {
            st.executeUpdate("update count set positive="+pmsgcount+", negative="+nmsgcount+" where type = 'tweet';");
        }
        else
        {
            st.executeUpdate("INSERT INTO count values('message',"+pmsgcount+","+nmsgcount+");");
        }



        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
 
    }

}

