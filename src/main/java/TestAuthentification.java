import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Map;

public class TestAuthentification {

    private static Map<String, String> cookies = ProxyAuthentication.getCookies();

    public static String getFullName(String link) throws Exception {
        Document document = Jsoup.connect(link)
                .cookies(cookies)
                .get();
        Element text = document.selectFirst("div[class=profile-shortInfo-name]");
        throw new Exception("Can't extract date from string");
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Full name of user is : " +
                getFullName("https://www.pandashop.md/ru/profile/"));
    }
}
