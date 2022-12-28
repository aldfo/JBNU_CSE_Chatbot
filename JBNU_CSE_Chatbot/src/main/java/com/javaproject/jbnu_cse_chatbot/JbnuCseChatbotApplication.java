// 201716351 강건, 201716443 장명래, 201946527 장재혁
// ****************조원 프로젝트 수행 기록****************
// 장명래 : intelli j 개발 toll에서 spring boot 초기 설정
// 장명래 : gradle로 telegrambots api 의존성 추가
// 장명래 : gradle로 komoran api 의존성 추가
// 장재혁 : gradle로 jsoup api 의존성 추가
// 장명래 : telegrambots api 활용 TelegramLongPollingBot override 작성 및 세부 동작 구현
// 장재혁 : 전북대 컴퓨터공학부 홈페이지 url 정보 수집 및 활용(학사공지, 취업공지, 일반공지, 연구실 소개)
// 강건, 장재혁 : 전북대학교 컴퓨터공학부 홈페이지에서 학사공지, 취업공지, 일반공지 & 세미나 크롤링 test
// 강건, 장재혁 : Crawling class 생성 및 공지사항 페이지 크롤링 세부 내용을 CrawlingPage() 메서드에 구현
// 장재혁 : 전북대학교 컴퓨터 공학부 홈페이지에서 연구실 정보 페이지 크롤링 test
// 강건, 장재혁 : Crawling_lab class 생성 및 연구실 소개 페이지 크롤링 세부 내용을 CrawlingPage() 메서드 오버라이딩
// 강건 : 공지사항 크롤링한 데이터에 하이퍼링크 형식 포멧 설정
// 장명래 : kormoran api 활용 사용자 입력 문장을 형태소로 분리하여 '학사', '취업', '일반', '연구실'의 4가지 케이스에 대한 처리 방식 설정
// 장명래 : send_data()메서드 세부 동작 구현
// 강건, 장명래 : telegreambot api에서 SendMessage의 setText() 메서드 실매개변수 문자열 입력

package com.javaproject.jbnu_cse_chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class JbnuCseChatbotApplication {
    // 웹 서버 동작 및 telegrambots api 활용 Chatbot 객체 실행
    public static void main(String[] args) {
        SpringApplication.run(JbnuCseChatbotApplication.class, args);
        try {
            TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(new Chatbot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
