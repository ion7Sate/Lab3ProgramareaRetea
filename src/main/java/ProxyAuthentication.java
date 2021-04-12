import org.jsoup.Jsoup;
import org.jsoup.Connection;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Map;

import static java.sql.Connection.*;

public class ProxyAuthentication {

    private static String LOGIN_FIELD = "txtEmail";
    private static String PASSWORD_FIELD = "txtPassword";
    private static String LINK ="https://www.pandashop.md/ru/login/?returnurl=%2fru%2fprofile%2f";


    final static  String authUser = "U-161822777585";
    final static  String authPassword = "NEr6K19BV";

    public static Map<String,String> getCookies() {

        Authenticator.setDefault(
                new Authenticator() {
                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                authUser, authPassword.toCharArray());
                    }
                }
        );
        System.setProperty("http.proxyHost", "45.132.50.93");
        System.setProperty("http.proxyPort", "45785");
        Connection.Response response = null;
        try {
            response = Jsoup.connect(LINK)
                    .referrer(LINK)
                    .data(LOGIN_FIELD, "saptesate31@gmail.com")
                    .data(PASSWORD_FIELD, "ion54343")
                    .method(Connection.Method.POST)
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.cookies();
    }
    public static int spliter(String text){
        String[] tokens = text.split(" ");
        return Integer.parseInt(tokens[2]);
    }



}
