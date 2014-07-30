parse_youtube_stream_url
========================

* Example

```
//  Youtube hash id
String youtubeID = "MQy8_7NqU78";
    
VideoParse parse = new YoutubeParse();
parse.parse(youtubeID);
    
System.out.println("Title: " + parse.getTitle());
System.out.println("Image url: " + parse.getImageUrl());
System.out.println("Stream url: " + parse.getUrl());
```
