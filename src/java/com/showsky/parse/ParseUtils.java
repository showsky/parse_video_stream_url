package com.showsky.parse;

public class ParseUtils {

    public enum VIDEO_TYPE {
        YOUTUBE,
        DIALYMOTION,
        YOUKU,
    }

    public static VideoParse parse(VIDEO_TYPE type, String hashID) {
        VideoParse parse = null;
        switch (type) {
            case YOUTUBE:
                parse = new YoutubeParse();
                break;
            case DIALYMOTION:
                parse = new DialymotionParse();
                break;
        }

        if (parse != null) {
            parse.parse(hashID);
        }

        return parse;
    }
}
