package com.javaproject.jbnu_cse_chatbot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Crawling {
	final int ReadLine = 5; //읽어 올 라인 수
	public String[] CrawlingPage(String URL) throws IOException {
		Document doc = Jsoup.connect(URL).get(); //URL에 적힌 정보를 doc에 저장
		String [] titles = new String[ReadLine]; //읽어올 라인 수 만큼 titles 문자열 배열 생성
		Elements contents = doc.select("tbody tr"); //doc에서 tbody tr부분의 수만큼을 contents에 저장
		int i = 0;
		for (Element content : contents) {

			String ww = content.select("td._artclTdNum").text(); //일반 공지를 제왜하기 위해 artclTdNum부분을 읽어옴
			if(i == ReadLine) break; // 읽어들인 제목의 갯수가 5개면 for문 탈출
			if (!ww.equals("일반공지")) { //일반 공지를 제외한 나머지 공지만을 출력
				String title = content.select("strong").text(); //제목을 읽어옴
				title = title.replace("[", ""); //하이퍼 링크 사용시 [, ] 문자가 잘못 읽히는 것을 방지하고자 문자 교체
				title = title.replace("]", " ");
				titles[i] = "[" + title + "]"+"(https://cse.jbnu.ac.kr" + //읽어온 주소로부터 바로 사이트에 연동되도록 주소를 합쳐줌
						content.getElementsByAttribute("href").attr("href") + ")"; //실제 주소를 가져오는 부분
				i++;
			}
		}
		return titles;
	}
}