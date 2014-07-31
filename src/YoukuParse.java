import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class YoukuParse extends VideoParse {
	
	private final static String URL = "http://v.youku.com/player/getPlayList/VideoIDS/";

	@Override
	public boolean parse(String hashID) {
		String source = getHTML(URL + hashID);
		
		double seed = 0.0;
		JSONObject obj1 = null;
		JSONObject obj2 = null;
		try {
			JSONObject jsobj = new JSONObject(source);
			JSONArray jsonarr = jsobj.getJSONArray("data");
			obj1 = jsonarr.getJSONObject(0);
			String title = obj1.getString("title");
			seed = obj1.getDouble("seed");
			obj2 = obj1.getJSONObject("streamfileids");
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
		
		
		String mp4id = null;
		String flvid = null;
		String format = null;
		try {
			mp4id = obj2.getString("mp4");
			format = "mp4";
		} catch (JSONException e) {
			//System.out.println("没有MP4格式");
			e.printStackTrace();
			try {
				flvid = obj2.getString("flv");
				format = "flv";
			} catch (JSONException e1) {
				e1.printStackTrace();
				//System.out.println("没有FLV格式");
				return false;
			}
		}
		String realfileid = null;
		if (format.equals("mp4")) {
			realfileid = getFileID(mp4id, seed);
		} else {
			realfileid = getFileID(flvid, seed);
		}

		String idLeft = realfileid.substring(0, 8);
		String idRight = realfileid.substring(10);

		String sid = genSid();
		String videoUrl = "";
		try {
			JSONObject obj3 = obj1.getJSONObject("segs");
			JSONArray mp4arr = obj3.getJSONArray(format);
			for (int i = 0; i < mp4arr.length(); i++) {
				JSONObject o = mp4arr.getJSONObject(i);
				String k = o.getString("k");
				String url = "http://f.youku.com/player/getFlvPath/sid/" + sid
						+ "_" + String.format("%1$02X", i) + "/st/" + format
						+ "/fileid/" + idLeft + String.format("%1$02X", i)
						+ idRight + "?K=" + k;
				System.out.println(url);
				videoUrl += url + "#";
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
		
		System.out.println(videoUrl);
		
		return true;
	}
	
	private String getFileID(String fileid, double seed) {
		String mixed = getFileIDMixString(seed);
		String[] ids = fileid.split("\\*");
		StringBuilder realId = new StringBuilder();
		int idx;
		for (int i = 0; i < ids.length; i++) {
			idx = Integer.parseInt(ids[i]);
			realId.append(mixed.charAt(idx));
		}
		return realId.toString();
	}

	public String genSid() {
		int i1 = (int) (1000 + Math.floor(Math.random() * 999));
		int i2 = (int) (1000 + Math.floor(Math.random() * 9000));
		return System.currentTimeMillis() + "" + i1 + "" + i2;
	}

	private String getFileIDMixString(double seed) {
		StringBuilder mixed = new StringBuilder();
		StringBuilder source = new StringBuilder("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ/\\:._-1234567890");
		int index, len = source.length();
		for (int i = 0; i < len; ++i) {
			seed = (seed * 211 + 30031) % 65536;
			index = (int) Math.floor(seed / 65536 * source.length());
			mixed.append(source.charAt(index));
			source.deleteCharAt(index);
		}
		return mixed.toString();
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getImageUrl() {
		// TODO Auto-generated method stub
		return null;
	}

}
