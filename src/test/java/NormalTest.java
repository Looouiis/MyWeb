import me.looouiiis.config.BaseConfig;
import me.looouiiis.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BaseConfig.class)
public class NormalTest {
    private MessageService messageService;
    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }
    @Test
    public void anoMessageTest() throws IOException {
        String a = null;
        Integer b = Integer.parseInt(a);
        System.out.println(b);
    }
    @Test
    public void bwTest() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("D:/a.txt"));
        bw.write("abc");
        bw.close();
    }
}
