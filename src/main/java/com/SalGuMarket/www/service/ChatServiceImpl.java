package com.SalGuMarket.www.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.SalGuMarket.www.domain.ChatMessage;
import com.SalGuMarket.www.domain.ChatRoom;
import com.SalGuMarket.www.repository.ChatRoomMapper;
import com.SalGuMarket.www.repository.ChattingLogMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatServiceImpl implements ChatService{
	
	// DB연결시
	private final ChatRoomMapper chatRoomMapper;
	
	private final ChattingLogMapper chattingLogMapper;
	
	@Override
	public void createRoom(ChatRoom chatRoom) {
	    log.info("ChatServiceImpl createRoom 진입"); 
	    log.info("chatRoom :"+chatRoom);
	    chatRoomMapper.createRoom(chatRoom);
	}

	@Override
	public ChatRoom findRoomById(long chatBno) {
		log.info("ChatServiceImpl findRoomById");
		log.info("chatName : "+chatBno);
		return chatRoomMapper.joinRoom(chatBno);
	}

	@Override
	public List<ChatRoom> findAllRoom() {
		log.info("ChatServiceImpl findAllRoom");
		return chatRoomMapper.findAllRoom();
	}

	@Override
	public ChatRoom findRoomByBno(long chatBno) {
		log.info("ChatSAerviceImpl findRoomById long chatBno");
		return chatRoomMapper.chattingSend(chatBno);
	}

	@Override
	public List<ChatMessage> getMessageList(long chatBno) {
		// TODO Auto-generated method stub
		return chattingLogMapper.getChatMessage(chatBno);
	}
}
