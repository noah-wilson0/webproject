package com.wplab.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.wplab.repository.BoardDTO;
/**
 * regdate는 현재 시각으로 set된다.
 */

public class FileDO {
	private int boardId;
	private int fileId;
	private String fileName;
	private String writer;
	
	public FileDO() {}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	@Override
	public String toString() {
		return "FileDO [boardId=" + boardId + ", fileId=" + fileId + ", fileName=" + fileName + ", writer=" + writer
				+ "]";
	}

	
}
