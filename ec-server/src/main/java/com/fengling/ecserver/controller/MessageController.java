package com.fengling.ecserver.controller;

import com.fengling.ecserver.netty.DataContent;
import com.fengling.ecserver.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@Slf4j
@RestController("/chat")
public class MessageController {

    /**
     * 同时为了方便操作，我们还可以提取出
     * Controller
     * */

    @Autowired
    MessageService messageService;

    @RequestMapping("/send")
    public void pushOne(DataContent dataContent){
        messageService.pushMsgToOne(dataContent);
    }

}
