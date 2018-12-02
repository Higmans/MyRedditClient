package biz.lungo.myredditclient.common;

public class Utils {
    public static boolean isUrl(String input) {
        return android.util.Patterns.WEB_URL.matcher(input).matches();
    }
}
