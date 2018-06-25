package biz.lungo.myredditclient.util;

public class Helpers {
    public static boolean isUrl(String input) {
        return android.util.Patterns.WEB_URL.matcher(input).matches();
    }
}
