import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.jsoup.Jsoup;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BusinessStandard {

private static BusinessStandard instance = null;

static String Title,Link,PubDate;

public static BusinessStandard getInstance() {
if(instance == null) {
instance = new BusinessStandard();
}
return instance;
}


public void writeNews()
{
    
    try 
    {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        URL u = new URL("http://www.business-standard.com/rss/markets-106.rss"); // your feed url

        //System.setProperty("http.proxyHost", "proxy.ssn.net");
        //System.setProperty("http.proxyPort", "8080");


        org.w3c.dom.Document doc = builder.parse(u.toString());


        NodeList nodes = doc.getElementsByTagName("item");

        for(int i=0;i<nodes.getLength();i++)
        {

            Element element = (Element)nodes.item(i);
            Title=getElementValue(element,"title");

            System.out.println(Title);

        }//for

    }//try
    catch(Exception ex) 
    {
        System.out.println(ex.toString());
    }

}

private String getCharacterDataFromElement(Element e) {
try {
Node child = e.getFirstChild();
if(child instanceof CharacterData) {
CharacterData cd = (CharacterData) child;
return cd.getData();
}
}
catch(Exception ex) {

}
return "";
} //private String getCharacterDataFromElement

protected float getFloat(String value) {
if(value != null && !value.equals("")) {
return Float.parseFloat(value);
}
return 0;
}

protected String getElementValue(Element parent,String label) {
return getCharacterDataFromElement((Element)parent.getElementsByTagName(label).item(0));
}

public static void main(String[] args) {
BusinessStandard reader = BusinessStandard.getInstance();
reader.writeNews();


}
}