package com.wplab.service;

import com.wplab.entity.BoardDO;
import com.wplab.repository.BoardDTO;

public class BoardDoByBoardDtoConverter {
	public BoardDO convertBoardDTOtoBoardDO(BoardDTO boardDTO){
		return new BoardDO(boardDTO.getBoardId(),boardDTO.getWriter(),
				boardDTO.getTitle(),boardDTO.getContent(),boardDTO.getRegdate());
	}
}
