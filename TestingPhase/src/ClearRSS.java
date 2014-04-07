import java.io.File;

/* 
 * Clear the RSS that have already been extracted and entered for the various companies
 * Called from extractRSS
 */

public class ClearRSS {
    
    public void clearrss()
    {
        try
        {
            String path = "C:/Documents and Settings/105000/My Documents/Dropbox/Final Year Project 2013/RSS Feeds";
            File folder = new File(path);
            File[] listOfFiles = folder.listFiles();
            int x = listOfFiles.length;
            System.out.println(x);
            for(int i=0;i<x;i++)
            {
                System.out.println(listOfFiles[0].getName());
                boolean success = listOfFiles[0].delete();
                listOfFiles = folder.listFiles();
            }
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
    }
    
}
