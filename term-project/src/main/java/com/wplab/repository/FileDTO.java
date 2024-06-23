package com.wplab.repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileDTO {
	private int boardId;
	private int fileId;
	private String fileName;
	private String writer;
	
	public FileDTO() {}

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
}
