package com.fengling.ecserver.netty;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
public class ChatMessage extends ChatMsg{

    private Integer action;
    @JsonProperty("sendName")
    private String sendName;
    @JsonProperty("receviceName")
    private String receviceName;
    @JsonProperty("sendText")
    private String sendText;
    @JsonProperty("createTime")
    private String createTime;
    @JsonProperty("updateTime")
    private String updateTime;
    @JsonProperty("chatmState")
    private Integer chatmState;
    @JsonProperty("TextType")
    private String textType;

}
