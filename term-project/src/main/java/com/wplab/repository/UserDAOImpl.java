package com.wplab.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wplab.service.DBconnectionInfo;

public class UserDAOImpl implements UserDAO{

	private DBconnectionInfo dbInfo;
	protected Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private final String INSERT_SQL="insert into POSTUSER   (ID ,PASSWORD, NAME, AUTHORITY ) values (?,?,?,?)";
	private final String UPDATE_SQL="update POSTUSER  set PASSWORD=?, NAME=? where ID=?";
	private final String DELETE_SQL="delete POSTUSER   where ID=?;";
	private final String FIND_USER_SQL="select * from POSTUSER  where ID= ? ";
	private final String GET_USER_LIST_SQL="select * from POSTUSER";

	public UserDAOImpl() {
	}
	public UserDAOImpl(DBconnectionInfo dbInfo) {
		this.dbInfo=dbInfo;
	}
	
	protected void connect() {
		try {
			Class.forName(dbInfo.getDiverName());
			conn =DriverManager.getConnection(
				dbInfo.getUrl(),dbInfo.getUsername(),dbInfo.getPassword());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	private void disconnect() {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null)stmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	
	@Override
	public void insert(UserDTO userDTO) {
		try {
			connect();
			stmt = conn.prepareStatement(INSERT_SQL);
			stmt.setString(1, userDTO.getId());
			stmt.setString(2, userDTO.getPassword());
			stmt.setString(3, userDTO.getName());
			stmt.setString(4, userDTO.getAuthority());

			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			disconnect();
		}		
	}

	@Override
	public void update(UserDTO userDTO) {
		try {
			connect();
			stmt = conn.prepareStatement(UPDATE_SQL);
			stmt.setString(1, userDTO.getPassword());
			stmt.setString(2, userDTO.getName());
			stmt.setString(3, userDTO.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			disconnect();
		}		
	}

	@Override
	public void delete(UserDTO userDTO) {
		try {
			connect();
			stmt = conn.prepareStatement(DELETE_SQL);
			stmt.setString(1, userDTO.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			disconnect();
		}		
	}

	@Override
	public UserDTO findUser(UserDTO userDTO) {
		UserDTO result=new UserDTO();
		try {
			connect();
			stmt = conn.prepareStatement(FIND_USER_SQL);
			stmt.setString(1, userDTO.getId());
			rs=stmt.executeQuery();
			if(rs.next()) {
				result.setId(rs.getString("ID"));
				result.setPassword(rs.getString("PASSWORD"));
				result.setName(rs.getString("NAME"));
				result.setAuthority(rs.getString("AUTHORITY"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			disconnect();
		}	
		return  result;		
	}
	
	@Override
	public List<UserDTO> getUserList() {
		List<UserDTO> list = null;
		connect();
		
		try {
			stmt = conn.prepareStatement(GET_USER_LIST_SQL);
			
			rs = stmt.executeQuery();
			if(rs.isBeforeFirst()) {
				list = new ArrayList<>();
				while(rs.next()) {
					if(rs.getString("Authority").equals("manager")) {
						UserDTO user = new UserDTO();
						user.setId(rs.getString("ID"));
						user.setPassword(rs.getString("PASSWORD"));
						user.setName(rs.getString("NAME"));
						user.setAuthority(rs.getString("Authority"));
						list.add(user);						
					}
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return list;
	}

}
