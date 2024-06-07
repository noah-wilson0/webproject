package com.wplab.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.wplab.repository.BoardDTO;
/**
 * regdate는 현재 시각으로 set된다.
 */

public class BoardDO {
	private int boardId;
	private String writer;
	private String title;
	private String content;
	private String regdate;
	
	public BoardDO() {}
	public BoardDO(int boardId, String writer, String title, String content) {
		super();
		this.boardId = boardId;
		this.writer = writer;
		this.title = title;
		this.content = content;
		setRegdateNow();
	}
	public BoardDO( String writer, String title, String content) {
		super();
		this.writer = writer;
		this.title = title;
		this.content = content;
		setRegdateNow();
	}
	
	public BoardDO(BoardDTO boardDTO) {
	    this.boardId = boardDTO.getBoardId();
	    this.writer = boardDTO.getWriter();
	    this.title = boardDTO.getTitle();
	    this.content = boardDTO.getContent();
	    this.regdate = boardDTO.getRegdate();
	}

	
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setContent() {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	private void setRegdateNow() {
		LocalDateTime now = LocalDateTime.now();
	   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.regdate = now.format(formatter);
	}
	@Override
	public String toString() {
		return "BoardDO [boardId=" + boardId + ", writer=" + writer + ", title=" + title + ", content=" + content
				+ ", regdate=" + regdate + "]";
	}
}
