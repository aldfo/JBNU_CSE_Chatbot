package com.javaproject.jbnu_cse_chatbot;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.List;

public class Chatbot extends TelegramLongPollingBot {
    private final static String Academic_info = "학사";
    private final static String Job_announcement = "취업";
    private final static String General_info = "일반";
    private final static String Lab = "연구실";

    // 챗봇이 사용자에게 응답할 때 호출되는 메서드(매개면수 : 유효 입력 확인, 잘못된 입력 확인, 챗봇이 사용자에게 보낼 SendMessege 객체)
    public void send_data(Boolean[] flag, Boolean noData, SendMessage message){
        // 송신자가 잘못된 입력을 전송했을 때 반응
        if(noData){
            message.setText("잘못 된 입력이에요. ( ㅜ.ㅜ)ㅡ ( ㅜ.ㅜ)ㅡ\n\n챗봇 알림이가 알아듣지 못했어요.\n\n" +
                    "아래의 정보가 포함되게 입력해주세요..\n\n" +
                    "학사 or 취업 or 일반 or 연구실\n\n" +
                    "ex) 학사 or 학사정보 뭐있냐? or 학사정보 알려줘~\n\n\n" +
                    "1. 학사 정보\n\n2. 취업 공지\n\n3. 일반 공지\n\n4. 연구실 정보" +
                    "\n\n\n\\( ^-^)/  \\( ^-^)/  \\( ^-^)/\n");
            // 챗봇이 사용자에게 응답
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        // 송신자가 정상적인 입력을 전송했을 때 반응
        else{

            String[] list;
            // 학사공지, 취업공지, 일반공지를 처리하기 위한 크롤링 클래스
            Crawling c = new Crawling();

            // 연구실 정보를 처리하기 위한 크롤링 클래스
            Crawling_lab cl = new Crawling_lab();

            // 유효한 질문 형식이 들어왔는지 확인 후 챗봇이 사용자에게 응답하게 함.
            for(int i=0;i<4;i++) {
                if (flag[i]) {
                    String ret = "";
                    // 챗봇이 사용자로 부터 학사공지 수신 시
                    if (i == 0) {
                        ret += "<최근 올라온 학사공지 5개를 보여드릴게요>\n\n";
                        try{
                            list = c.CrawlingPage("https://cse.jbnu.ac.kr/cse/3586/subview.do?" +
                                                    "enc=Zm5jdDF8QEB8JTJGYmJzJTJGY3NlJTJGNTM4JTJGYXJ0Y2xMaXN0LmRvJTNG");
                            for(String str : list){
                                ret += str + "\n\n";
                            }
                            ret += "[학사공지의 더 자세한 정보를 보고싶으면 클릭하세요]" +
                                    "(https://cse.jbnu.ac.kr/cse/3586/subview.do?" +
                                    "enc=Zm5jdDF8QEB8JTJGYmJzJTJGY3NlJTJGOTI3JTJGYXJ0Y2xMaXN0LmRvJTNG)";
                            message.setText(ret);
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                    // 챗봇이 사용자로 부터 취업공지 수신 시
                    else if (i == 1) {
                        ret += "<최근 올라온 취업공지 5개를 보여드릴게요>\n\n";
                        try{
                            list = c.CrawlingPage("https://cse.jbnu.ac.kr/cse/3589/subview.do?" +
                                                    "enc=Zm5jdDF8QEB8JTJGYmJzJTJGY3NlJTJGOTI5JTJGYXJ0Y2xMaXN0LmRvJTNG");
                            for(String str : list){
                                ret += str + "\n\n";
                            }
                            ret += "[취업공지의 더 자세한 정보를 보고싶으면 클릭하세요]" +
                                    "(https://cse.jbnu.ac.kr/cse/3589/subview.do?" +
                                    "enc=Zm5jdDF8QEB8JTJGYmJzJTJGY3NlJTJGOTI3JTJGYXJ0Y2xMaXN0LmRvJTNG)";

                            message.setText(ret);
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                    // 챗봇이 사용자로 부터 일반공지 수신 시
                    else if (i == 2) {
                        ret += "<최근 올라온 일반공지 5개를 보여드릴게요>\n\n";
                        try{
                            list = c.CrawlingPage("https://cse.jbnu.ac.kr/cse/3587/subview.do?" +
                                                    "enc=Zm5jdDF8QEB8JTJGYmJzJTJGY3NlJTJGOTI3JTJGYXJ0Y2xMaXN0LmRvJTNG");
                            for(String str : list){
                                ret += str + "\n\n";
                            }
                            ret += "[일반공지의 더 자세한 정보를 보고싶으면 클릭하세요]" +
                                    "(https://cse.jbnu.ac.kr/cse/3587/subview.do?" +
                                    "enc=Zm5jdDF8QEB8JTJGYmJzJTJGY3NlJTJGOTI3JTJGYXJ0Y2xMaXN0LmRvJTNG)";
                            message.setText(ret);
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                    // 챗봇이 사용자로 부터 연구실 정보 수신 시
                    else if(i == 3){
                        ret += "<연구실정보를 보여드릴게요>\n\n";
                        try {
                            list = cl.CrawlingPage("https://cse.jbnu.ac.kr/cse/3580/subview.do");
                            for(String str : list){
                                ret += str + "\n\n";
                            }
                            ret += "[연구실에 대해 더 자세한 정보를 보고싶으면 클릭하세요]" +
                                    "(https://cse.jbnu.ac.kr/cse/3580/subview.do)";

                            message.setText(ret);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    // 챗봇이 사용자에게 응답
                    try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    @Override
    public String getBotUsername() {
        return "Jbnu_Cse_bot";
    }

    @Override
    public String getBotToken() {
        return "1859120279:AAHqtv6BTkkYgDj0LcfIv5S2nfyED1KyQfM";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId().toString());
            message.enableMarkdown(true);
            // 챗봇이 처음 실행했을 때 사용자에게 사용법을 보여줌
            if(update.getMessage().getText().equals("/help")||update.getMessage().getText().equals("/start")){
                message.setText("반가워요! (^-^)b  \n" +
                                "<전북대학교 컴퓨터공학과 공지 알림이>입니다.\n\n" +
                                "궁금한 정보를 말하면 알려드릴게요!\n\n" +
                                "1. 학사 공지\n\n2. 취업 공지\n\n3. 일반 공지\n\n4. 연구실 정보\n\n" +
                                "ex) 학사공지 알려줘\nex) 취업정보 말해줘\nex) 연구실 뭐있어?\nex) 일반공지는 뭐가있니?\n\n\n" +
                                "\\( ^-^)/  \\( ^-^)/  \\( ^-^)/\n");
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            // 사용자의 입력을 받아 형태소 단위로 분리 후 분석
            else{
                String receive = update.getMessage().getText();
                Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
                KomoranResult analyzeResultList = komoran.analyze(update.getMessage().getText());
                List<Token> tokenList = analyzeResultList.getTokenList();

                // 학사, 취업, 일반, 연구실 형태소가 있는지 확인하는 Boolean 배열
                Boolean[] flag = new Boolean[4];
                // 유효 단어가 없는지 확인하는 Boolean 배열
                Boolean NoFlag = true;
                for(int i=0;i<4;i++){
                    flag[i] = false;
                }

                // 문장에서 형태소 단위로 분리한 string 값을 순회하며 유효 단어가 있는지 확인 후 체크
                for(Token token : tokenList){
                    if(token.getMorph().equals(Academic_info)){
                        flag[0] = true;
                        if(NoFlag) NoFlag = false;
                    }
                    else if(token.getMorph().equals(Job_announcement) ){
                        flag[1] = true;
                        if(NoFlag) NoFlag = false;
                    }
                    else if(token.getMorph().equals(General_info)){
                        flag[2] = true;
                        if(NoFlag) NoFlag = false;
                    }
                    else if(token.getMorph().equals(Lab)){
                        flag[3] = true;
                        if(NoFlag) NoFlag = false;
                    }
                }

                send_data(flag, NoFlag, message);
            }
        }
    }
}