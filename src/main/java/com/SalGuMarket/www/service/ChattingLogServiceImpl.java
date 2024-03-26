package com.SalGuMarket.www.service;

import org.springframework.stereotype.Service;

import com.SalGuMarket.www.domain.ChatMessage;
import com.SalGuMarket.www.repository.ChattingLogMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChattingLogServiceImpl implements ChattingLogService {

    private final ChattingLogMapper chattingLogMapper;

    @Override
    public void saveChattingLog(ChatMessage chatMessage) {
    	log.info("ChattingLogMapper saveChattingLogsaveChattingLog insert");
    	log.info("chatMessage sender"+chatMessage.getSenderNick());
    	log.info("chatMessage Message"+chatMessage.getChatContent());
    	log.info("chatMessage RegAt"+chatMessage.getRegAt());
    	log.info("chatMessage ChatBno"+chatMessage.getChatBno());

        chattingLogMapper.saveChattingLog(chatMessage);
    }
}