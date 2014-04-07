import java.io.File;
import java.io.*;



public class CategorizingMessages {

    public static void main(String args[])
    {
        String path = "C:/Documents and Settings/105000/My Documents/Dropbox/Final Year Project 2013/Message Board";
        File folder = new File(path);
        File[] listofFiles;
        String files,strLine;
        File f;
        FileWriter fout;
        BufferedWriter out1;

        listofFiles = folder.listFiles();

        try
        {


            f=new File(path+"/positive.txt");
            if(!f.exists())
            {
                f.createNewFile();
            }
            fout = new FileWriter(f);
            out1 = new BufferedWriter(fout);

            out1.write("");
            out1.close();

            f=new File(path+"/negative.txt");
            if(!f.exists())
            {
                f.createNewFile();
            }
            fout = new FileWriter(f);
            out1 = new BufferedWriter(fout);

            out1.write("");
            out1.close();
        }
        catch(Exception e)
        {
            System.err.println(e.toString());
        }

        for(int i=0;i<listofFiles.length;i++)
        {
            if(listofFiles[i].isFile())
            {
                files = listofFiles[i].getName();

                if((files.equals("negative.txt"))||(files.equals("positive.txt"))||(files.equals("nmessages.txt"))||(files.equals("pmessages.txt")))
                    continue;

                System.out.println();
                System.out.println(files);

                try
                {
                    FileInputStream fstream = new FileInputStream(path+"/"+files);
                    DataInputStream in = new DataInputStream(fstream);
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));


                    while ((strLine = br.readLine()) != null)
                    {
                        
                        int pos = strLine.indexOf(" ");
                        double start = Double.parseDouble(strLine.substring(0, pos));
                        strLine = strLine.substring(pos+1);
                        pos = strLine.indexOf("||");
                        double end = Double.parseDouble(strLine.substring(0, pos));
                        strLine = strLine.substring(pos+2);
                        if(start<end)
                        {
                            f = new File(path+"/positive.txt");
                            System.out.println("+     "+strLine);
                        }
                        else
                        {
                            f = new File(path+"/negative.txt");
                            System.out.println("-     "+strLine);
                        }
                        fout = new FileWriter(f,true);
                        out1 = new BufferedWriter(fout);

                        out1.write(strLine+".");
                        out1.newLine();
                        out1.close();

                    }
                    //out1.close();



                }
                catch(Exception e)
                {
                   System.out.println(e.toString());
                }
            }
        }


    }

}
