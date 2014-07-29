import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class YoutubeParse extends VideoParse {
	
	private final static String PARSE_PATTERN = "\"url_encoded_fmt_stream_map\":\\s\"(.*?)\"";
	private final static String CODECS = "codecs";
	private final static String QUALITY = "quality";
	private final static String URL = "url";
	private String streamContent[] = null;
	private String mediumContent = null;
	private String smallcontent = null;
	private String highContent = null;
	
	@Override
	public String parse(String url) {
		String html = getHTML(url);
		//System.out.println(html);
		Pattern pattern = Pattern.compile(PARSE_PATTERN);
		Matcher matcher = pattern.matcher(html);
		String urlencodeContent = null;
		String urldecodeContent = null;
		while (matcher.find()) {
			urlencodeContent = matcher.group(1);
        }
		
		//System.out.println(urlencodeContent);
		try {
			urldecodeContent = urldecode(urlencodeContent);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String content = urldecodeContent.replace("\\u0026", "&");
		streamContent = content.split(";");
		
		return content;
	}
	
	@Override
	public String getUrl() {
		String result = null;
		for (String stream: streamContent) {
			//System.err.println(stream);
			if (stream.indexOf("url=") != -1) {
				result = stream;
				break;
			}
		}
		
		Pattern pattern = Pattern.compile("url=(.*?)$");
		Matcher matcher = pattern.matcher(result);
		while (matcher.find()) {
			result = matcher.group(1);
		}
		return result;
	}
	
	@Override
	public String getMediumUrl() {
		return null;
	}
	
	@Override
	public String getSmallUrl() {
		return null;
	}
	
	@Override
	public String getHighUrl() {
		return null;
	}
}
