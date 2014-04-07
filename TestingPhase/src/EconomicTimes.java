import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class EconomicTimes {

static String Title,Link,PubDate;


public void writeNews()
{
    try
    {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        URL u = new URL("http://economictimes.indiatimes.com/Markets/markets/rssfeeds/1977021501.cms"); // your feed url
        
        //System.setProperty("http.proxyHost", "proxy.ssn.net");
        //System.setProperty("http.proxyPort", "8080");

        Document doc = builder.parse(u.openStream());

        NodeList nodes = doc.getElementsByTagName("item");

        for(int i=0;i<nodes.getLength();i++) 
        {

            Element element = (Element)nodes.item(i);
            Title=getElementValue(element,"title");
            Link=getElementValue(element,"link");
            PubDate=getElementValue(element,"pubDate");

            org.jsoup.nodes.Document docs = Jsoup.connect(Link).timeout(0).get();
            org.jsoup.nodes.Element Text = docs.select("div.Normal").first();
            try
            {
                ReadArticle r=new ReadArticle();
                r.read(Title,Text.text().toString(),PubDate);
            }
            catch(Exception e)
            {
                System.out.println(e.toString());
            }
            System.out.println(i);
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
catch(Exception ex) 
{
    System.out.println(ex.toString());
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

}