import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;


public class DialymotionParse extends VideoParse {
	
	private final static String URL = "http://www.dailymotion.com/embed/video/";
	private final static String TITLE = "title";
	private final static String IMAGE_URL = "thumbnail_large_url";
	private final static String PATTERN_INFO = "var info = (\\{.+\\}\\})";
	private JSONObject jsonObject = null;

	@Override
	public boolean parse(String hashID) {
		stream.clear();
		String source = getHTML(URL + hashID);
		Pattern pattern = Pattern.compile(PATTERN_INFO);
		Matcher matcher = pattern.matcher(source);
		if (matcher.find()) {
			String info = matcher.group(1);
			//System.out.println(info);
			
			try {
				jsonObject = new JSONObject(info);
				if ( ! jsonObject.isNull("stream_h264_hd1080_url") && ! checkNull(jsonObject.getString("stream_h264_hd1080_url"))) {
					stream.put(QUALITY.HIGH, jsonObject.getString("stream_h264_hd1080_url"));
				} else if ( ! jsonObject.isNull("stream_h264_hd_url") && ! checkNull(jsonObject.getString("stream_h264_hd_url"))) {
					stream.put(QUALITY.HIGH, jsonObject.getString("stream_h264_hd_url"));
				}
				
				if ( ! jsonObject.isNull("stream_h264_hq_url") && ! checkNull(jsonObject.getString("stream_h264_hq_url"))) {
					stream.put(QUALITY.MEDIUM, jsonObject.getString("stream_h264_hq_url"));
				} else if ( ! jsonObject.isNull("stream_h264_url") && ! checkNull(jsonObject.getString("stream_h264_url"))) {
					stream.put(QUALITY.MEDIUM, jsonObject.getString("stream_h264_url"));
				}
				
				if ( ! jsonObject.isNull("stream_h264_ld_url")) {
					stream.put(QUALITY.SMALL, jsonObject.getString("stream_h264_ld_url"));
				}
			} catch (JSONException e) {
				e.printStackTrace();
				return false;
			}
			
		}
		return true;
	}
	
	private boolean checkNull(String content) {
		if (content == null || "".equals(content)) {
			return true;
		}
		return false;
	}

	@Override
	public String getTitle() {
		String title = null;
		if (jsonObject != null) {
			try {
				title = jsonObject.getString(TITLE);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return title;
	}

	@Override
	public String getDescription() {
		//TODO:
		return null;
	}

	@Override
	public String getImageUrl() {
		String imageUrl = null;
		if (jsonObject != null) {
			try {
				imageUrl = jsonObject.getString(IMAGE_URL);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return imageUrl;
	}

}
