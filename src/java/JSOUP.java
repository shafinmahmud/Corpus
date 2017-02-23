
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import javax.swing.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * JSoup HTML parser
 *
 * @author BUDDHIMA
 */
public class JSOUP{

    static int count = 0;
    /**
     * @param args the command line arguments
     */
    public void goParse() throws IOException {
        CheckData();
        HashSet<String> set = new HashSet<String>();
        try{
                //Document doc = Jsoup.connect("http://www.prothom-alo.com/archive/2017-02-07").get();
            Document doc = Jsoup.connect("http://www.prothom-alo.com/archive/2017-02-07").timeout(250000).get();
        //Elements links = doc.getElementsByClass("tabs_content").select("a[href]");
        Elements links = doc.select("a[href]");
                for (Element link : links) {
                if ((link.attr("abs:href").toString().contains("bangladesh/article"))) {
                    new ReadArticle(link);
                }
            //set.add(link.attr("abs:href").toString());
                }
            }
            catch(Exception ee){
                ee.printStackTrace();
            }
    }

    private static void CheckData() throws FileNotFoundException, IOException {

        BufferedWriter out = new BufferedWriter(new FileWriter("F:\\Rafi\\My_Study\\4_1\\Thesis\\4-2 Resources\\JSOUP parser\\maxdata.txt", true));
        out.close();

        FileInputStream fstream = new FileInputStream("F:\\Rafi\\My_Study\\4_1\\Thesis\\4-2 Resources\\JSOUP parser\\maxdata.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String line;
        boolean read = false;
        while ((line = br.readLine()) != null) {
            read = true;
            count = Integer.parseInt(line);
        }
    }

    private static void WriteData() throws FileNotFoundException, IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter("F:\\Rafi\\My_Study\\4_1\\Thesis\\4-2 Resources\\JSOUP parser\\maxdata.txt", false));
        out.write((count) + "");
        out.close();
    }
    
    
    
    public class Event implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
             
        }
    }
}
