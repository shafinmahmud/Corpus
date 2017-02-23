
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author shuvo
 */
public class ReadArticle {

    public ReadArticle(Element link) throws IOException {
        ProthomAlo(link);
        //kalerkonto(link);
        //bdnews24(link);
        //banglanews24(link);
        //mzamin(link);
        //inqilab(link);

    }

    public void ProthomAlo(Element link) {
        try {
            Document news = Jsoup.connect(link.attr("abs:href")).get();

            String title = news.getElementsByClass("right_title").select("h1").text();
            System.out.println(title);

            Elements additionalInfo = news.getElementsByClass("fl additional_info");

            Elements paragraphs = news.select("article").first().select("p");
            String body = "";

            BufferedWriter out = new BufferedWriter(new FileWriter("C:\\pure\\new " + JSOUP.count + ".txt", false));
            out.write("প্রথম আলো"
                    + "");
            out.write(System.getProperty("line.separator"));
            out.write(news.select("span[itemprop=\"datePublished\"]").text());
            out.write(System.getProperty("line.separator"));
            out.write(title);
            out.write(System.getProperty("line.separator"));

            ArrayList<String> paras = new ArrayList<String>();
            for (Element p : paragraphs) {
                //paras.add(p.html());
                StringBuilder sb = new StringBuilder(p.html());
                String ss = "";
                for (int i = 0; i < sb.length(); ++i) {
                    if (sb.charAt(i) == '<' && sb.charAt(i + 1) == 'i' && sb.charAt(i + 2) == 'm' && sb.charAt(i + 3) == 'g') {
                        while (sb.charAt(i) != '>') {
                            ++i;
                        }
                        ++i;
                    }
                    ss += sb.charAt(i);
                }
                StringBuilder s3 = new StringBuilder(ss.toString());
                ss = "";
                String[] brokenStr = s3.toString().split("</br>|<br>|</p>|<p>");
                for (int i = 0; i < brokenStr.length; ++i) {
                    String[] brokenStr2 = brokenStr[i].split("<[^<>]+>|</[^<>]+>");
                    brokenStr[i] = "";
                    for (int j = 0; j < brokenStr2.length; ++j) {
                        StringBuilder stb = new StringBuilder(brokenStr2[j]);
                        for (int k = 0; k < stb.length(); ++k) {
                            if ((stb.charAt(k) >= 'a' && stb.charAt(k) <= 'z')
                                    || (stb.charAt(k) >= 'A' && stb.charAt(k) <= 'Z')
                                    || stb.charAt(k) == '&') {
                                continue;
                            } else {
                                brokenStr[i] += stb.charAt(k);
                            }
                        }
                    }
                }
                for (int i = 0; i < brokenStr.length; ++i) {
                    if (brokenStr[i].length() > 2) {
                        System.out.println(brokenStr[i]);
                        out.write(brokenStr[i].toString());
                        out.write(System.getProperty("line.separator"));

                    }
                }
            }

            out.close();
            JSOUP.count += 1;

        } catch (Exception err) {
        }
    }

    public void kalerkonto(Element link) {
        System.out.println(JSOUP.count);
        try {
            Document news = Jsoup.connect(link.attr("abs:href")).get();

            String title = news.select("h2").first().text();
            System.out.println(title);
            String date = news.select("p").first().text();

            Elements paragraphs = news.getElementsByClass("some-class-name2");
            String body = "";
            BufferedWriter out = new BufferedWriter(new FileWriter("C:\\pure\\new " + JSOUP.count + ".txt", false));
            out.write("কালের কণ্ঠ");
            out.write(System.getProperty("line.separator"));
            out.write(date);
            out.write(System.getProperty("line.separator"));
            out.write(title);
            out.write(System.getProperty("line.separator"));

            for (Element p : paragraphs) {
                StringBuilder sb = new StringBuilder(p.html());
                String ss = "";
                for (int i = 0; i < sb.length(); ++i) {
                    if (sb.charAt(i) == '<' && sb.charAt(i + 1) == 'i' && sb.charAt(i + 2) == 'm' && sb.charAt(i + 3) == 'g') {
                        while (sb.charAt(i) != '>') {
                            ++i;
                        }
                        ++i;
                    }
                    ss += sb.charAt(i);
                }
                StringBuilder s3 = new StringBuilder(ss.toString());
                ss = "";
                String[] brokenStr = s3.toString().split("</br>|<br>|</p>|<p>");
                for (int i = 0; i < brokenStr.length; ++i) {
                    String[] brokenStr2 = brokenStr[i].split("<[^<>]+>|</[^<>]+>");
                    brokenStr[i] = "";
                    for (int j = 0; j < brokenStr2.length; ++j) {
                        StringBuilder stb = new StringBuilder(brokenStr2[j]);
                        for (int k = 0; k < stb.length(); ++k) {
                            if ((stb.charAt(k) >= 'a' && stb.charAt(k) <= 'z')
                                    || (stb.charAt(k) >= 'A' && stb.charAt(k) <= 'Z')
                                    || stb.charAt(k) == '&') {
                                continue;
                            } else {
                                brokenStr[i] += stb.charAt(k);
                            }
                        }
                    }
                }
                for (int i = 0; i < brokenStr.length; ++i) {
                    if (brokenStr[i].length() > 2) {
                        System.out.println(brokenStr[i]);
                        out.write(brokenStr[i].toString());
                        out.write(System.getProperty("line.separator"));

                    }
                }
            }
            JSOUP.count += 1;
            out.close();

        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public void bdnews24(Element link) {
        try {
            Document news = Jsoup.connect(link.attr("abs:href")).get();

            String title = news.getElementById("news-details-page").select("h1").text();
            System.out.println(title);

            Elements dateSpans = news.getElementsByClass("dateline");
            System.out.println("SIZE " + dateSpans.size());
           

            String[] brokenSpans = dateSpans.html().split("<[^<>]+>|</[^<>]+>");

            Elements paragraphs = news.getElementsByClass("custombody");

            for (Element p : paragraphs) {
                StringBuilder sb = new StringBuilder(p.html());
                String ss = "";
                for (int i = 0; i < sb.length(); ++i) {
                    if (sb.charAt(i) == '<' && sb.charAt(i + 1) == 'i' && sb.charAt(i + 2) == 'm' && sb.charAt(i + 3) == 'g') {
                        while (sb.charAt(i) != '>') {
                            ++i;
                        }
                        ++i;
                    }
                    ss += sb.charAt(i);
                }
                StringBuilder s3 = new StringBuilder(ss.toString());
                ss = "";
                String[] brokenStr = s3.toString().split("<style [^<>]+>[^>]+</style>|</br>|<br>|</p>|<p>");
                for (int i = 0; i < brokenStr.length; ++i) {
                    String[] brokenStr2 = brokenStr[i].split("<[^<>]+>|</[^<>]+>");
                    brokenStr[i] = "";
                    for (int j = 0; j < brokenStr2.length; ++j) {
                        StringBuilder stb = new StringBuilder(brokenStr2[j]);
                        for (int k = 0; k < stb.length(); ++k) {
                            if ((stb.charAt(k) >= 'a' && stb.charAt(k) <= 'z')
                                    || (stb.charAt(k) >= 'A' && stb.charAt(k) <= 'Z')
                                    || stb.charAt(k) == '&') {
                                continue;
                            } else {
                                brokenStr[i] += stb.charAt(k);
                            }
                        }
                    }
                }
                if (brokenStr.length > 0) {
                    BufferedWriter out = new BufferedWriter(new FileWriter("C:\\pure\\new " + JSOUP.count + ".txt", false));
                    out.write("বিডিনিউজ24");
                    out.write(System.getProperty("line.separator"));
                    out.write(title);
                    out.write(System.getProperty("line.separator"));
                    out.write(brokenSpans[brokenSpans.length - 1]);
                    out.write(System.getProperty("line.separator"));
                    for (int i = 0; i < brokenStr.length; ++i) {
                        if (brokenStr[i].length() > 12) {
                            System.out.println(brokenStr[i]);
                            out.write(brokenStr[i].toString());
                            out.write(System.getProperty("line.separator"));
                        }
                    }
                    out.close();
                }
                
            }

            
            JSOUP.count += 1;

        } catch (Exception err) {
        }
    }

    public void banglanews24(Element link) {
        try {
            Document news = Jsoup.connect(link.attr("abs:href")).get();

            String title = news.select("h1").text();
            System.out.println(title);
            BufferedWriter out = new BufferedWriter(new FileWriter("C:\\pure\\new " + JSOUP.count + ".txt", false));
            out.write("বাংলানিউজটোয়েন্টিফোর") ;
            out.write(System.getProperty("line.separator"));
            out.write(title);
            out.write(System.getProperty("line.separator"));
            Elements paragraphs = news.select("p");
            String body = "";
            String date = "";
            ArrayList<String> finalDocs = new ArrayList<String>();
            for (Element p : paragraphs) {
                if (p.text().startsWith("বাংলাদেশ সময়:")){
                    date = p.text().substring(13);
                    break;
                }
                finalDocs.add(p.text());
            }
            out.write(date);
            out.write(System.getProperty("line.separator"));
            for (String finalDoc : finalDocs){
                if (finalDoc.length()>6){
                    out.write(finalDoc);
                    out.write(System.getProperty("line.separator"));
                }
            }
            System.out.println(body);
            System.out.println();

            
                
               
                out.close();
                JSOUP.count += 1;
            
        } catch (Exception err) {
        }
    }

    public void mzamin(Element link) throws IOException {
        try {
            Document news = Jsoup.connect(link.attr("abs:href")).get();

            String title = news.select("h1").text();
            String date = news.getElementsByClass("details-source").text();
            String realDate = "";
            for (int i=0;i<date.length();++i){
                int j=0;
                if (date.charAt(i)=='|'){
                    j=i+1;
                    while(date.charAt(j)!='|'){
                        realDate += date.charAt(j);
                        ++j;
                    }
                    break;
                }
            }
            System.out.println(title);

            Elements paragraphs = news.getElementsByClass("details-text");
            String body = "";
            BufferedWriter out = new BufferedWriter(new FileWriter("C:\\pure\\new " + JSOUP.count + ".txt", false));
            out.write("মানবজমিন");
            out.write(System.getProperty("line.separator"));
            out.write(title);
            out.write(System.getProperty("line.separator"));
            out.write(realDate);
            out.write(System.getProperty("line.separator"));
            for (Element p : paragraphs) {
                
            
                
                StringBuilder sb = new StringBuilder(p.html());
                String ss = "";
                for (int i = 0; i < sb.length(); ++i) {
                    if (sb.charAt(i) == '<' && sb.charAt(i + 1) == 'i' && sb.charAt(i + 2) == 'm' && sb.charAt(i + 3) == 'g') {
                        while (sb.charAt(i) != '>') {
                            ++i;
                        }
                        ++i;
                    }
                    ss += sb.charAt(i);
                }
                
                String ss2="";
                for (int i=0;i<ss.length();++i){
                    if(ss.charAt(i)=='<' && ss.charAt(i+1)=='n' && ss.charAt(i+2)=='o')
                        break;
                    ss2+=ss.charAt(i);
                }
                
                
                StringBuilder s3 = new StringBuilder(ss2.toString());
                ss = "";
                String[] brokenStr = s3.toString().split("<noscript [^<>]*>.*</noscript>|<script [^<>]*>.*</script>|</br>|<br>|</p>|<p>");
                for (int i = 0; i < brokenStr.length; ++i) {
                    String[] brokenStr2 = brokenStr[i].split("<[^<>]+>|</[^<>]+>");
                    brokenStr[i] = "";
                    for (int j = 0; j < brokenStr2.length; ++j) {
                        StringBuilder stb = new StringBuilder(brokenStr2[j]);
                        for (int k = 0; k < stb.length(); ++k) {
                            if ((stb.charAt(k) >= 'a' && stb.charAt(k) <= 'z')
                                    || (stb.charAt(k) >= 'A' && stb.charAt(k) <= 'Z')
                                    || stb.charAt(k) == '&') {
                                continue;
                            } else {
                                brokenStr[i] += stb.charAt(k);
                            }
                        }
                    }
                }
                for (int i = 0; i < brokenStr.length-1; ++i) {
                    if (brokenStr[i].length() > 2) {
                        System.out.println(brokenStr[i]);
                        out.write(brokenStr[i].toString());
                        out.write(System.getProperty("line.separator"));

                    }
                }
            }
            System.out.println(body);
            System.out.println();

//            if (body.length() >= 1000 && title.length() >= 10) {
//                
//                out.write(title + "\n" + body);
                out.close();
                JSOUP.count += 1;
            //}
        } catch (Exception err) {
        }
    }

    public void inqilab(Element link) throws IOException {
        try {
            Document news = Jsoup.connect(link.attr("abs:href")).get();

            
            String title = link.attr("abs:href");
            int lastIndex =  title.lastIndexOf("/");
            title = title.substring(lastIndex+1);
            
            String date = news.getElementsByClass("date").text();
            System.out.println("title "+title);
            System.out.println("date "+date);
            Element p = news.select("p").first();
            StringBuilder sb = new StringBuilder(p.html());
                String ss = "";
                for (int i = 0; i < sb.length(); ++i) {
                    if (sb.charAt(i) == '<' && sb.charAt(i + 1) == 'i' && sb.charAt(i + 2) == 'm' && sb.charAt(i + 3) == 'g') {
                        while (sb.charAt(i) != '>') {
                            ++i;
                        }
                        ++i;
                    }
                    ss += sb.charAt(i);
                }
                StringBuilder s3 = new StringBuilder(ss.toString());
                ss = "";
                String[] brokenStr = s3.toString().split("<style [^<>]+>[^>]+</style>|</br>|<br>|</p>|<p>");
                for (int i = 0; i < brokenStr.length; ++i) {
                    String[] brokenStr2 = brokenStr[i].split("<[^<>]+>|</[^<>]+>");
                    brokenStr[i] = "";
                    for (int j = 0; j < brokenStr2.length; ++j) {
                        StringBuilder stb = new StringBuilder(brokenStr2[j]);
                        for (int k = 0; k < stb.length(); ++k) {
                            if ((stb.charAt(k) >= 'a' && stb.charAt(k) <= 'z')
                                    || (stb.charAt(k) >= 'A' && stb.charAt(k) <= 'Z')
                                    || stb.charAt(k) == '&') {
                                continue;
                            } else {
                                brokenStr[i] += stb.charAt(k);
                            }
                        }
                    }
                }
                if (brokenStr.length > 0) {
                    BufferedWriter out = new BufferedWriter(new FileWriter("C:\\pure\\new " + JSOUP.count + ".txt", false));
                    out.write("দৈনিক ইনকিলাব");
                    out.write(System.getProperty("line.separator"));
                    out.write(title);
                    out.write(System.getProperty("line.separator"));
                    out.write(date);
                    out.write(System.getProperty("line.separator"));
                    for (int i = 0; i < brokenStr.length; ++i) {
                        if (brokenStr[i].length() > 12) {
                            System.out.println(brokenStr[i]);
                            out.write(brokenStr[i].toString());
                            out.write(System.getProperty("line.separator"));
                        }
                    }
                    out.close();
                }
                
               
                JSOUP.count += 1;
            
        } catch (Exception err) {
        }
    }

}
