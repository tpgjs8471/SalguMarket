package com.SalGuMarket.www.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.SalGuMarket.www.domain.ChatMessage;
import com.SalGuMarket.www.domain.ChatRoom;
import com.SalGuMarket.www.security.AuthMember;
import com.SalGuMarket.www.service.ChatService;

import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@RequestMapping("/chat/*")
@Slf4j
public class ChatController {
	private final ChatService chatService;
	
	static List<Session> sessionUsers = Collections.synchronizedList(new ArrayList<Session>());
	static boolean runCheck = false;

    
    // 채팅방 목록
    @GetMapping("/chatList")
    public String chatList(Model model){
        List<ChatRoom> roomList = chatService.findAllRoom();
        model.addAttribute("roomList",roomList);
        return "chat/chatList";
    }

    // 방 만들기
    @PostMapping("/createRoom")
    public String createRoom(Model model,ChatRoom chatRoom) {
    	log.info("chatRoom : "+chatRoom);
        // 방 생성 및 정보 가져오기
    	chatService.createRoom(chatRoom);
    	
        log.info("chatBno 확인: "+chatRoom.getChatBno());
		return "redirect:/";
    }

    // 방 들어가기
    @GetMapping("/chatRoom")
    public String chatRoom(Model model,
    		@RequestParam("chatBno") long chatBno
    		) {
    	// @AuthenticationPrincipal AuthMember authMember
    	log.info("chatName : "+chatBno);
        // 채팅방 정보 가져오기
        ChatRoom room = chatService.findRoomById(chatBno);
     // 사용자 이메일 가져오기
     //   String email = authMember.getEmail();
        
        model.addAttribute("room", room);
     //   model.addAttribute("email",email);
        return "chat/chatRoom";
    }
    
    @GetMapping("/chatRoom/{chatBno}")
	@ResponseBody
	public List<ChatMessage> list(@PathVariable("chatBno")long chatBno) {
		log.info(">>>> chatName >> " +chatBno);
		//비동기 => 한 객제만 전송 가능
		List<ChatMessage> list = chatService.getMessageList(chatBno);
		return list;
	}
}
