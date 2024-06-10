package com.wplab.service;

import com.wplab.entity.UserDO;
import com.wplab.repository.UserDTO;

public class UserDoDtoConverter {
	
	public UserDO convertUserDTOtoDO(UserDTO userDTO){
		return new UserDO(userDTO.getId(),userDTO.getName(),userDTO.getAuthority());
	}
}
