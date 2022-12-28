package com.javaproject.jbnu_cse_chatbot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class Crawling_lab extends Crawling { //연구실 정보를 크롤링하는 클래스
    @Override
    public String[] CrawlingPage(String URL) throws IOException {
        Document doc = Jsoup.connect(URL).get(); //연구실 홈페이지로 부터 정보를 가져와 doc에 저장
        Elements contents = doc.select("div.imgWrap"); //div.ingWrap을 포함한 정보들을 contents에 저장
        String[] titles = new String[contents.size()+1]; //담아온 contents를 하이퍼링크로 변환하기위해 스트링 배열생성
        int i = 0;
        for(Element content : contents) {
            titles[i] = content.select("strong").text() + "\n" + content.select("li").get(0).text(); //연구실 이름과 교수님 정보를 context에서 차례대로 추출
            i++;
        }
        titles[contents.size()] = "[연구실 정보 자세히 보기](https://cse.jbnu.ac.kr/cse/3580/subview.do)"; //컴퓨터공학과 연구실 페이지로 가는 하이퍼 링크생성
        return titles;
    }
}