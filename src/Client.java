
public class Client {

	public static void main(String[] args) {
		String youtubeID = args[0];
		VideoParse parse = new YoutubeParse();
		parse.parse(youtubeID);
		
		System.out.println(parse.getUrl());
	}
}
