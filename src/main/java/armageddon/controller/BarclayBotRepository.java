package armageddon.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Junaid on 12-Jun-16.
 */
public class BarclayBotRepository {
    Map<String, List<Communication>> sessionCommunicationDetails = new HashMap<String, List<Communication>>();
    Map<String, String> sessionAccountDetails= new HashMap<String, String>();
    Map<String, String> accountSessionDetails= new HashMap<String, String>();
    Map<String, String> userDetails= new HashMap<String, String>();

    public BarclayBotRepository(){
        userDetails.put("User1", "Mike");
        userDetails.put("User1", "Tom");
        sessionCommunicationDetails.put("123", new ArrayList<Communication>() );
        accountSessionDetails.put("User1", "123");
        sessionAccountDetails.put("123", "User1");
    }

    public String getUserName(String userId){
        return userDetails.get(userId);
    }
    public String getUserId(String sessionId){
        return sessionAccountDetails.get(sessionId);
    }

    public String getSessioId(String userId){
        return accountSessionDetails.get(userId);
    }
    public String createSessionForUser(String userId){
        String sessionId = "" + System.currentTimeMillis();
        accountSessionDetails.put(userId, sessionId);
        sessionAccountDetails.put(sessionId, userId);
        sessionCommunicationDetails.put(sessionId, new ArrayList<Communication>());
        return sessionId;
    }

    public void addCommunicationDetailsForSession(String sessionId, Communication communication){
        sessionCommunicationDetails.get(sessionId).add(communication);
    }

    public Communication getLastCommunication(String sessionId)
    {
        if(null!=sessionCommunicationDetails.get(sessionId) && !sessionCommunicationDetails.get(sessionId).isEmpty()) {
            return sessionCommunicationDetails.get(sessionId).get(sessionCommunicationDetails.get(sessionId).size() - 1);
        }
        return null;
    }
}
