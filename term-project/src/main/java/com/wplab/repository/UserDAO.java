package com.wplab.repository;

import java.util.List;

public interface UserDAO {
	public void insert(UserDTO userDTO);
	public void update(UserDTO userDTO);
	public void delete(UserDTO userDTO);
	public UserDTO findUser(UserDTO userDTO);
	public List<UserDTO> getUserList(); 
}
