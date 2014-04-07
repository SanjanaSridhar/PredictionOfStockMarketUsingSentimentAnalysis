import java.io.*;

public class TwitterWords {

    public static void main(String args[])
    {
            String path = "C:/Documents and Settings/105000/My Documents/Dropbox/Final Year Project 2013/Tweets";
            File folder = new File(path);
            File[] listOfFiles = folder.listFiles();
            File fl;
            String files,strLine;
            
            try{
            
            for (int i = 0; i < listOfFiles.length; i++)
            {
                if(listOfFiles[i].isFile())
                {
                    files = listOfFiles[i].getName();
                    System.out.println(files);
                    FileInputStream fstream = new FileInputStream(path+"/"+files);
                    DataInputStream in = new DataInputStream(fstream);
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));

                    while ((strLine = br.readLine()) != null)
                    {
                        System.out.println(strLine);
                    }
                
            }

            }
            }
            catch(Exception e)
            {
                
            }
    }
}
