package com.fengling.ecserver.netty;

import com.fengling.ecserver.netty.ChatMsg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataContent implements Serializable {
    private Integer action;
    private ChatMsg chatMsg;
    private String extend;
}
