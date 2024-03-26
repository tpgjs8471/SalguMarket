package com.SalGuMarket.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.SalGuMarket.www.domain.ChatMessage;

@Mapper
public interface ChattingLogMapper {

	void saveChattingLog(ChatMessage chatMessage);

	List<ChatMessage> getChatMessage(long chatBno);

}
