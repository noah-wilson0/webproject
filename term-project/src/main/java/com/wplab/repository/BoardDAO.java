package com.wplab.repository;

import java.util.ArrayList;

public interface BoardDAO {
	public void insert(BoardDTO boardDTO);
	public BoardDTO read();
	public void update(BoardDTO boardDTO);
	public void delete(BoardDTO boardDTO);
	public ArrayList<BoardDTO> findAll();
	public BoardDTO findByBoard(String title);
}
