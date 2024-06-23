package com.wplab.repository;

import java.util.List;

public interface FileDAO {
	
	public void insertFile(FileDTO fileDTO);
	public void deleteFile(FileDTO fileDTO);
	public void deleteMathWriterAll(UserDTO userDTO);
//	public FileDTO getFile(FileDTO fileDTO);
	public List<FileDTO> getFileList(FileDTO fileDTO);
}
