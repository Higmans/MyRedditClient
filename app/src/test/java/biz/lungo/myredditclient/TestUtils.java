package biz.lungo.myredditclient;

import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

class TestUtils {

    static String getFileContents(String filepath) {
        String result = "";
        URL resource = Thread.currentThread().getContextClassLoader().getResource(filepath);
        if (resource == null) {
            return  "";
        }
        try {
            final URI path = resource.toURI();
            result = new String(Files.readAllBytes(Paths.get(path)));
        } catch (Exception ignored){}
        return result;
    }
}
