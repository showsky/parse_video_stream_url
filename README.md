parse_youtube_stream_url
========================

* Example

```
String youtube = "https://www.youtube.com/watch?v=pDkGB8OCrXg";

YoutubeParse youtubeParse = new YoutubeParse();
youtubeParse.parse(youtube);
        
System.out.println(youtubeParse.getUrl());
```
