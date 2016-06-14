package com.chatbot.model.core;

import armageddon.controller.SentenceParserController;
;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Getter
public class Chatbot {

    private Logger log = LoggerFactory.getLogger(Chatbot.class);

    private String chatbotName = "Andrew";
    private Conversation conversation = new Conversation("Aditya");
    private String response="Please try agian";

    public boolean isUserTurn() {
        return conversation.isUserTurn();
    }

    public String getLastAnswer() {
        return conversation.getLastAnswer();
    }

    public void addUserAnswer(String s) {
        conversation.addChatbotAnswerToCourse(s);

        response=  SentenceParserController.getActionString("123", s);
    }

    public void answer() throws Exception {
        conversation.addChatbotAnswerToCourse(response);

        conversation.chatLevelUp();
    }

    public List<String> getConversationCourse() {
        return conversation.getCourse();
    }


}
