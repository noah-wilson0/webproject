package com.wplab.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wplab.reserve.service.SeatsDO;
import com.wplab.service.DBconnectionInfo;

public class FileDAOImpl implements FileDAO{
	private DBconnectionInfo dbInfo;
	protected Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private final String INSERT_SQL="insert into FILE values (?,?,?,?)";
	//private final String GET_SQL="select * from BOARD   where BOARD_ID= ? ";
	private final String DELETE_SQL="delete FILE  where BOARD_ID=?;";
	private final String DELETE_MATH_WRITER_SQL="delete from FILE where USER_ID=?;";
	private final String LIST_SQL 	= 	"select * from FILE where BOARD_ID=?;";

	public FileDAOImpl() {}
	public FileDAOImpl(DBconnectionInfo dbInfo) {
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
	public void insertFile(FileDTO fileDTO) {
		try {
			connect();
			stmt = conn.prepareStatement(INSERT_SQL);
			stmt.setInt(1, fileDTO.getBoardId());
			stmt.setInt(2, fileDTO.getFileId());
			stmt.setString(3, fileDTO.getFileName());
			stmt.setString(4, fileDTO.getWriter());

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
	public void deleteFile(FileDTO fileDTO) {
		try {
			connect();
			stmt = conn.prepareStatement(DELETE_SQL);
			stmt.setInt(1, fileDTO.getBoardId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			disconnect();
		}
	}
	/**
	 * deleteMathWriterAll의 파라미터인 userDTO.getName()= name(id)로 받아야한다.
	 */
	@Override
	public void deleteMathWriterAll(UserDTO userDTO) {
		try {
			connect();
			stmt = conn.prepareStatement(DELETE_MATH_WRITER_SQL);
			stmt.setString(1, userDTO.getName());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			disconnect();
		}
	}
		
//	public FileDTO getFile(FileDTO fileDTO) {
//		FileDTO result = null;
//		connect();
//		
//		try {
//			stmt = conn.prepareStatement(GET_SQL);
//			stmt.setInt(1, fileDTO.getNumber());
//			
//			rs = stmt.executeQuery();
//			
//			if(rs.next()) {
//				result = new FileDTO();
//				result.setNumber(rs.getInt("seat_number"));
//				result.setType(rs.getString("seat_type").charAt(0));
//				result.setRsv(rs.getInt("rsv_seq"));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			disconnect();
//		}
//		
//		return result;
//	}
	
	public List<FileDTO> getFileList(FileDTO fileDTO) {
		List<FileDTO> list = null;
		connect();
		
		try {
			stmt = conn.prepareStatement(LIST_SQL);
			stmt.setInt(1, fileDTO.getBoardId());
			
			rs = stmt.executeQuery();
			
			if (rs.isBeforeFirst()) {
				list = new ArrayList<>();
				while (rs.next()) {
					FileDTO file = new FileDTO();
					file.setBoardId(rs.getInt("BOARD_ID"));
					file.setFileId(rs.getInt("FILE_ID"));
					file.setFileName(rs.getString("FILE_NAME"));
					file.setWriter(rs.getString("USER_ID"));
					list.add(file);
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
