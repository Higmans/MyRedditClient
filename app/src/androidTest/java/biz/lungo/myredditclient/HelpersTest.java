package biz.lungo.myredditclient;

import org.junit.Assert;
import org.junit.Test;

import static biz.lungo.myredditclient.util.Helpers.isUrl;

public class HelpersTest {

    @Test
    public void testIsUrl() {
        Assert.assertFalse(isUrl(""));
        Assert.assertFalse(isUrl("qwerty"));
        Assert.assertTrue(isUrl("google.com"));
        Assert.assertTrue(isUrl("www.google.com"));
        Assert.assertFalse(isUrl("http://gogole.c"));
        Assert.assertFalse(isUrl("http:///gogole.cc"));
        Assert.assertFalse(isUrl("htttp://gogole.cc"));
        Assert.assertTrue(isUrl("http://gogole.cc"));
        Assert.assertTrue(isUrl("https://google.com"));
        Assert.assertTrue(isUrl("http://google.com"));
        Assert.assertTrue(isUrl("http://gogole.cc.grj.egeoiheroehieg.reoerhegoeghorgohrio.erhgo"));
    }
}