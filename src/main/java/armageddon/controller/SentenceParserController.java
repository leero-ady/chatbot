package armageddon.controller;

;
import armageddon.BarclayBotParser;
import armageddon.QueryResponse;
import armageddon.RuleEngine;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Junaid on 11-Jun-16.
 */
@RestController
//@EnableAutoConfiguration
public class SentenceParserController {

    static BarclayBotParser barclayBotParser=new BarclayBotParser();

    static  BarclayBotRepository barclayBotRepository = new BarclayBotRepository();

    @RequestMapping("/createSession/{userId}")
    @ResponseBody
    String createSession(@PathVariable String userId) {
        return getSessionId(userId);
    }

    public static String getSessionId(@PathVariable String userId) {
        return barclayBotRepository.createSessionForUser(userId);
    }

    @RequestMapping("/getGreetingMessage/{userId}")
    @ResponseBody
    String getGreetingMessage(@PathVariable String userId) {
        return "Welcome " + barclayBotRepository.getUserName(userId) + ". How can I help you";
    }

    @RequestMapping("/getAction/{sessionId}")
    @ResponseBody
    String getAction(@PathVariable String sessionId, @RequestBody String content) {
        return getActionString(sessionId, content);
    }

    public static String getActionString(String sessionId, String content) {
        try {
            content = content.toLowerCase();
            Communication lastCommunication = barclayBotRepository.getLastCommunication(sessionId);
            if(lastCommunication ==null){
                String action = barclayBotParser.predictAction(content).get(0);
                Communication communication = new Communication(action,content);
                communication.setPreviousCommunication(null);
                RuleEngine.populateRespose(communication);
                barclayBotRepository.addCommunicationDetailsForSession(sessionId, communication);
                QueryResponse.reset();
                return communication.getReply();
            }else{
                String action = barclayBotParser.predictAction(content).get(0);
                Communication communication = new Communication(action,content);
                communication.setPreviousCommunication(lastCommunication);
                RuleEngine.populateRespose(communication);
                barclayBotRepository.addCommunicationDetailsForSession(sessionId, communication);
                QueryResponse.reset();
                return communication.getReply();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Please try again";
    }

//    public static void main(String[] args) throws Exception {
//        SpringApplication.run(SentenceParserController.class, args);
//        RuleEngine.initiateEngine();
//        //SentenceDetectorTrainer("hi, good morning. change phone");
//    }
}

