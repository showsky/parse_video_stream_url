parse_youtube_stream_url
========================

* Support
    * youtbue
    * dailymotion

* Example
```
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
