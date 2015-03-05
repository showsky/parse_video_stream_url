Parse video stream url v1.0.0
---

### Support ###
* youtube
* dailymotion
* youku (Beta)

### Example ###
```java
//  Youtube hash id
String youtubeID = "fT6URJnpPH0";
VideoParse youtubeParse = new YoutubeParse();
youtubeParse.parse(youtubeID);

System.out.println("[youtube]");
System.out.println("Title: " + youtubeParse.getTitle());
System.out.println("Image url: " + youtubeParse.getImageUrl());
System.out.println("Stream url: " + youtubeParse.getUrl());

// dailymotion hash id
String dailymotionID = "x1sr8o3";
VideoParse dailymotionParse = new DialymotionParse();
dailymotionParse.parse(dailymotionID);
System.out.println("[dailymotion]");
System.out.println("Stream url: " + dailymotionParse.getUrl());
```

### Java run ###
* java -jar parse.jar [type] [hashID]
* [type]
    * 0 youtube
    * 1 dailymotion
    * 2 youku

![image](https://raw.githubusercontent.com/showsky/parse_video_stream_url/master/screenshot/2014-08-01_00-14-23.jpg)
