import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;

public class Test {
    public static void main( String [] args ) throws IOException  {

        URL url = new URL("http://mmb.moneycontrol.com/stock-message-forum/icicibank/comments/6422");
        System.setProperty("http.proxyHost", "proxy.ssn.net");
            System.setProperty("http.proxyPort", "8080");
            
        InputStream is = url.openConnection().getInputStream();
        BufferedReader reader = new BufferedReader( new InputStreamReader( is )  );
        String line = null;
        while( ( line = reader.readLine() ) != null )
        {
            System.out.println(line);
        
        }
}
}