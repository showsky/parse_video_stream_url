
public class Client {

	public static void main(String[] args) {
		String url = args[0];
		YoutubeParse youtubeParse = new YoutubeParse();
		youtubeParse.parse(url);
		System.out.println(youtubeParse.getUrl());
	}
}
