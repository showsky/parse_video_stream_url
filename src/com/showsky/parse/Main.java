package com.showsky.parse;

public class Main {
	
	public static void main(String[] args) {
		
		//	Youtube hash id
		String youtubeID = "FVsJ35gCcQA";
		VideoParse youtubeParse = new YoutubeParse();
		youtubeParse.parse(youtubeID);
		
		System.out.println("[youtube]");
		System.out.println("Title: " + youtubeParse.getTitle());
		System.out.println("Image url: " + youtubeParse.getImageUrl());
		System.out.println("Stream url: " + youtubeParse.getUrl());
		
		/*
		// dailymotion hash id
		String dailymotionID = "x1sr8o3";
		VideoParse dailymotionParse = new DialymotionParse();
		dailymotionParse.parse(dailymotionID);
		System.out.println("[dailymotion]");
		System.out.println("Stream url: " + dailymotionParse.getUrl());
		
		//	Yukuo hash id
		String youkuId = "XNzQ4MTYwNzk2";
		VideoParse youkuParse = new YoukuParse();
		youkuParse.parse(youkuId);
		*/
	}
}
