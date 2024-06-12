package com.wplab.service;

import java.util.ArrayList;

import com.wplab.entity.BoardDO;
import com.wplab.repository.BoardDTO;

public class FindMathContenter {
	
	
	public ArrayList<BoardDO> getMathContentByWord(ArrayList<BoardDTO> list,String word) {
		ArrayList<BoardDO> result=new ArrayList<BoardDO>();
		BoardDoByBoardDtoConverter converter=new BoardDoByBoardDtoConverter();
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getContent().contains(word)) {
				result.add(converter.convertBoardDTOtoBoardDO(list.get(i)));
			}
		}
		
		return result;
	}
}
