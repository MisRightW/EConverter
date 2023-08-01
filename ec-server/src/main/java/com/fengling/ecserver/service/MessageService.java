package com.fengling.ecserver.service;

import com.fengling.ecserver.netty.DataContent;

/**
 * @author Administrator
 */
public interface MessageService {

    void pushMsgToOne(DataContent dataContent);

}
