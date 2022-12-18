import me.looouiiis.config.SpringConfig;
import me.looouiiis.config.SpringMVCConfig;
import me.looouiiis.service.AnnounceService;
import me.looouiiis.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class ServiceTest {
    private MessageService messageService;
    private AnnounceService announceService;
    @Autowired
    public void setAnnounceService(AnnounceService announceService) {
        this.announceService = announceService;
    }

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }
    @Test
    public void anoMessageTest() throws IOException {
        String xczowf = "这是长度十个字的测试这是长度十个字的测试这是长度十个字的测试这是长度十个字的测试这是长度\n十个字的测试这是长度十个字的测试这是长度十个字的测试这是长度十个字的测试这是长度十个字的测试这是长度十个字的测试啊";
        messageService.commitAnoMessage("12:34:56:78:89:12",xczowf);
//        File dir = new File("D:/a/b/c");
//        dir.mkdirs();
//        BufferedWriter bw = new BufferedWriter(new FileWriter("D:/a/b/c/a.txt"));
//        bw.write("a");
//        bw.close();
    }
    @Test
    public void getAnoMessageTest() throws IOException {
        System.out.println(messageService.getAnoCommunicationByMac("12:34:56:78:89:12", null, null, true));
    }
    @Test
    public void messageTest() throws IOException {
        String xczowf = "asdf这是长度十个字的测试这是长度十个字的测试这是长度十个字的测试这是长度十个字的测试这是长度\n十个字的测试这是长度十个字的测试这是长度十个字的测试这是长度十个字的测试这是长度十个字的测试这是长度十个字的测试啊";
//        System.out.println(messageService.commitReply(2, xczowf));
//        File dir = new File("D:/a/b/c");
//        dir.mkdirs();
//        BufferedWriter bw = new BufferedWriter(new FileWriter("D:/a/b/c/a.txt"));
//        bw.write("a");
//        bw.close();
    }
    @Test
    public void getMessageTest() throws IOException {
        System.out.println(messageService.getCommunicationByUserId(2, null, null, true));
    }
    @Test
    public void transformTest(){
        messageService.transformToUser(4,2);
    }
    @Test
    public void insertTest(){
        announceService.insert("123", null);
    }
}
