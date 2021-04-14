import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestGit {
    private static String Link  = "https://github.com/ion7Sate";



    private static Map<String, String> cookies;

    static {
        try {
            cookies = AuthentificationToGit.getCookies();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void getAllLinks(){
        Document doc = null;
        try {
            doc = Jsoup.connect(Link).get();
            Elements links = doc.select("a[href]");
            Element link;

            for(int j=0;j<90;j++){
                link=links.get(j);
                System.out.println("a= " +link.attr("abs:href").toString() );
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }




    public static void searchMyName(String myName) {

        Document document = null;
        try {
            document = Jsoup.connect(Link)
                    .cookies(cookies)
                    .method(Connection.Method.GET)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(document.text());
        String allText = document.text();
        if (allText.indexOf(myName) != -1) {
            System.out.println("Found the word " +
                    " at index number " + allText.indexOf(myName));
        } else {
            System.out.println("Can't find " + myName);
        }

        // Or use the String.contains() method if you are not interested
        // with the index of the word.
        if (allText.contains(myName)) {
            System.out.println("We've found your name!");
        }
    }
    public static String headRequest() throws IOException {
        Connection.Response resp = Jsoup.connect(Link).method(Connection.Method.HEAD).cookies(cookies).execute();
        return resp.contentType();
    }

    public static Map<String, List<String>> optionsRequest() throws IOException {
        Connection.Response resp = Jsoup.connect(Link).method(Connection.Method.OPTIONS).cookies(cookies).execute();
        return resp.multiHeaders();
    }


    public static void main(String[] args) throws Exception {
        searchMyName("New repository");
        getAllLinks();



    }

}
