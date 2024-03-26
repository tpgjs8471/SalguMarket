package com.SalGuMarket.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.SalGuMarket.www.domain.ChatMessage;
import com.SalGuMarket.www.domain.ChatRoom;

@Mapper
public interface ChatRoomMapper {

	void createRoom(ChatRoom chatRoom);

	ChatRoom joinRoom(long chatBno);

	List<ChatRoom> findAllRoom();

	ChatRoom chattingSend(long chatBno);
    
}