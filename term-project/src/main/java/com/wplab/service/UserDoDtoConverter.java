package com.wplab.service;

import com.wplab.entity.UserDO;
import com.wplab.repository.UserDTO;

public class UserDoDtoConverter {
	
	public UserDO convertUserDTOtoDO(UserDTO userDTO){
		return new UserDO(userDTO.getId(),userDTO.getName(),userDTO.getAuthority());
	}
	
//	public BoardDO(BoardDTO boardDTO) {
//	    this.boardId = boardDTO.getBoardId();
//	    this.writer = boardDTO.getWriter();
//	    this.title = boardDTO.getTitle();
//	    this.content = boardDTO.getContent();
//	    this.regdate = boardDTO.getRegdate();
//	}
	
}
