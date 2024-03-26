package com.SalGuMarket.www.service;

import org.springframework.web.socket.WebSocketSession;

public interface MessageService {

	void findRoomById(long chatBno);

	<T> Object sendMessage(WebSocketSession session, T message);

}
