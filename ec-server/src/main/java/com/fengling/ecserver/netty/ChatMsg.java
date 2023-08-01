package com.fengling.ecserver.netty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMsg {

    private String senderId;
    private String receiverId;
    private String msg;
    private String msgId;

}
