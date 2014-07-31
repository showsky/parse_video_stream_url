
public class Client {

	public static void main(String[] args) {
		
		int size = args.length;
		if (size < 2) {
			System.err.println("input error");
		}
		
		int type = Integer.parseInt(args[0]);
		String hashID = args[1];
		
		//System.out.println(type);
		//System.out.println(hashID);
		
		VideoParse parse = null;
		switch (type) {
			case 0:
				parse = new YoutubeParse();
				break;
			case 1:
				parse = new DialymotionParse();
				break;
			case 2:
				parse = new YoukuParse();
				break;
		}
		if (parse != null) {
			parse.parse(hashID);
			System.out.println(parse.getUrl());
		}
	}
}
