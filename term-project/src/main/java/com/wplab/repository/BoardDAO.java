package com.wplab.repository;

import java.util.ArrayList;

public interface BoardDAO {
	public void insert(BoardDTO boardDTO);
	public BoardDTO read();
	public void update(BoardDTO boardDTO);
	public void updateWriter(String prevName,String updateName);
	public void delete(BoardDTO boardDTO);
	public void deleteMathWriterAll(UserDTO userDTO);
	public ArrayList<BoardDTO> findAll();
	public BoardDTO findByBoard(String title);
	public ArrayList<BoardDTO> findByTitleBoard(String title);
	public ArrayList<BoardDTO> findByWriterBoard(String writer);
	}
