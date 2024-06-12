package com.wplab.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wplab.service.DBconnectionInfo;

public class BoardDAOImpl implements BoardDAO{
	private DBconnectionInfo dbInfo;
	protected Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private final String INSERT_SQL="insert into BOARD  (WRITER,TITLE,CONTENT,REGDATE) values (?,?,?,?)";
	//private final String GET_SQL="select * from BOARD   where BOARD_ID= ? ";
	private final String UPDATE_SQL="update BOARD set TITLE=?,CONTENT=?, REGDATE=? where WRITER=? and BOARD_ID=?";
	private final String DELETE_SQL="delete BOARD  where TITLE=?;";
	private final String FIND_ALL_SQL="select * from  BOARD;";
	private final String FIND_BOARD_SQL="select * from  BOARD where TITLE=?;"; 
	private final String FIND_TITLE_BOARD_SQL="select * from  BOARD where TITLE=?;";
	private final String FIND_WRITER_BOARD_SQL="select * from  BOARD where WRITER=?;";

	public BoardDAOImpl() {}
	public BoardDAOImpl(DBconnectionInfo dbInfo) {
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
	public void insert(BoardDTO boardDTO) {
		try {
			connect();
			stmt = conn.prepareStatement(INSERT_SQL);
			stmt.setString(1, boardDTO.getWriter());
			stmt.setString(2, boardDTO.getTitle());
			stmt.setString(3, boardDTO.getContent());
			stmt.setString(4, boardDTO.getRegdate());

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
	public BoardDTO read() {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(BoardDTO boardDTO) {
		try {
			connect();
			stmt = conn.prepareStatement(UPDATE_SQL);
			stmt.setString(1, boardDTO.getTitle());
			stmt.setString(2, boardDTO.getContent());
			stmt.setString(3, boardDTO.getRegdate());
			stmt.setString(4, boardDTO.getWriter());
			stmt.setInt(5, boardDTO.getBoardId());
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
	public void delete(BoardDTO boardDTO) {
		try {
			connect();
			stmt = conn.prepareStatement(DELETE_SQL);
			stmt.setString(1, boardDTO.getTitle());
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
	public ArrayList<BoardDTO> findAll() {
		ArrayList<BoardDTO> result=new ArrayList<BoardDTO>(); 
		try {
			connect();
			stmt = conn.prepareStatement(FIND_ALL_SQL);
			rs=stmt.executeQuery();
			while(rs.next()) {
				BoardDTO boardDTO=new BoardDTO();
				boardDTO.setBoardId(Integer.parseInt(rs.getString("BOARD_ID")));
				boardDTO.setWriter(rs.getString("WRITER"));
				boardDTO.setTitle(rs.getString("TITLE"));
				boardDTO.setContent(rs.getString("CONTENT"));
				boardDTO.setRegdate(rs.getString("REGDATE"));
				result.add(boardDTO);
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
	public BoardDTO findByBoard(String title) {
		BoardDTO result=new BoardDTO();
		try {
			connect();
			stmt = conn.prepareStatement(FIND_BOARD_SQL);
			stmt.setString(1, title);
			rs=stmt.executeQuery();
			if(rs.next()) {
				result.setBoardId(Integer.parseInt(rs.getString("BOARD_ID")));
				result.setWriter(rs.getString("WRITER"));
				result.setTitle(rs.getString("TITLE"));
				result.setContent(rs.getString("CONTENT"));
				result.setRegdate(rs.getString("REGDATE"));
				
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
	public ArrayList<BoardDTO> findByTitleBoard(String title) {
		ArrayList<BoardDTO> result=new ArrayList<BoardDTO>(); 
		try {
			connect();
			stmt = conn.prepareStatement(FIND_TITLE_BOARD_SQL);
			stmt.setString(1, title);
			rs=stmt.executeQuery();
			while(rs.next()) {
				BoardDTO boardDTO=new BoardDTO();
				boardDTO.setBoardId(Integer.parseInt(rs.getString("BOARD_ID")));
				boardDTO.setWriter(rs.getString("WRITER"));
				boardDTO.setTitle(rs.getString("TITLE"));
				boardDTO.setContent(rs.getString("CONTENT"));
				boardDTO.setRegdate(rs.getString("REGDATE"));
				result.add(boardDTO);
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
	public ArrayList<BoardDTO> findByWriterBoard(String writer) {
		ArrayList<BoardDTO> result=new ArrayList<BoardDTO>(); 

		try {
			connect();
			stmt = conn.prepareStatement(FIND_WRITER_BOARD_SQL);
			stmt.setString(1, writer);
			rs=stmt.executeQuery();
			while(rs.next()) {
				BoardDTO boardDTO=new BoardDTO();
				boardDTO.setBoardId(Integer.parseInt(rs.getString("BOARD_ID")));
				boardDTO.setWriter(rs.getString("WRITER"));
				boardDTO.setTitle(rs.getString("TITLE"));
				boardDTO.setContent(rs.getString("CONTENT"));
				boardDTO.setRegdate(rs.getString("REGDATE"));
				result.add(boardDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			disconnect();
		}	
		return  result;
	}


}
