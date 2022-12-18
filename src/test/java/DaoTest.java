import me.looouiiis.config.SpringConfig;
import me.looouiiis.config.SpringMVCConfig;
import me.looouiiis.dao.MessageDao;
import me.looouiiis.pojo.AnonymousMessage;
import me.looouiiis.pojo.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class DaoTest {
    private MessageDao messageDao;
    @Autowired
    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Test
    public void commitMessageTest() {
        Message message = new Message();
        message.setDate(new Date());
        message.setContent("MessageTest");
        message.setLocal(false);
        message.setUserId(2);
        messageDao.commitMessage(message);
    }
    @Test
    public void commitReplyTest() {
        Message message = new Message();
        message.setDate(new Date());
        message.setContent("ReplyTest");
        message.setLocal(false);
        message.setUserId(2);
        messageDao.commitReply(message);
    }
    @Test
    public void selectMessageTest(){
        List<Message> messages = messageDao.selectMessageById(2,1,1);
        System.out.println(messages);
    }
    @Test
    public void createAnoTest(){
        messageDao.createAnoAccount("12:34:56:78:89:12");
    }
    @Test
    public void commitAnoMessageTest() {
        AnonymousMessage message = new AnonymousMessage();
        message.setDate(new Date());
        message.setContent("AnoMessageTest");
        message.setLocal(false);
        message.setAnoId(1);
        messageDao.commitAnoMessage(message);
    }
    public void getAnoIdTest(){
        System.out.println(messageDao.getAnoIdByMac("12:34:56:78:89:12"));
    }
    @Test
    public void selectAnoMessageTest(){
        List<AnonymousMessage> messages = messageDao.selectAnoMessageById(1,null,null);
        System.out.println(messages);
    }
    @Test
    public void commitAnoReplyTest() {
        AnonymousMessage message = new AnonymousMessage();
        message.setDate(new Date());
        message.setContent("AnoReplyTest");
        message.setLocal(false);
        message.setAnoId(messageDao.getAnoIdByMac("12:34:56:78:89:12"));
        messageDao.commitAnoReply(message);
    }
    @Test
    public void transformTest(){
        List<AnonymousMessage> messages = messageDao.selectAnoMessageById(messageDao.getAnoIdByMac("12:34:56:78:89:12"),null,null);
        messageDao.insertFromAno(messages,2);
    }
    @Test
    public void deleteAnoTest(){
        messageDao.deleteAnoUsr(messageDao.getAnoIdByMac("12:34:56:78:89:12"));
    }
    @Test
    public void deleteTest(){
        messageDao.deleteMsgById(2);
    }
    @Test
    public void nullMacTest(){
        System.out.println(messageDao.getAnoIdByMac("123"));
    }
    @Test
    public void checkIsNull(){
        System.out.println(messageDao.checkAnoExist(2));
    }

    @Test
    public void getCount(){
        System.out.println(messageDao.getAnoMessageTotalNum(0));
    }
}
