package com.greenart.mybatis.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greenart.mybatis.model.User;
@Repository
public class UserDAOImpl implements UserDAO {
	@Autowired
	DataSource ds;
	
	@Override
	public int insertUser(User user) {
		String sql = "INSERT INTO member VALUES(?,?,?,?,?,?,default)";
		try(
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPwd());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getEmail());
			pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
			pstmt.setString(6, user.getSns());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("UserDAO > insertUser 에러");
			e.printStackTrace();
		}
		return -1;
	}
	
	@Override
	public int updateUser(User user) {
		String sql = "UPDATE member pwd=?, name=?, email=?, birth=?, sns=? WHERE id=?";
		try( 
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, user.getPwd());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getEmail());
			pstmt.setDate(4, new java.sql.Date(user.getBirth().getTime()));
			pstmt.setString(5, user.getSns());
			pstmt.setString(6, user.getId());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("UserDAO > updateUser 오류");
			e.printStackTrace();
		}
		return -1;
	}
	
	@Override
	public int deleteUser(String id) {
		String sql = "DELETE FROM member WHERE id=?";
		try (
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, id);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("UserDAO > deleteUser 오류");
			e.printStackTrace();
		}
		return -1;
	}
	
	@Override
	public User selectUser(String id) {
		User user = null;
		String sql = "SELECT * FROM member WHERE id=?";
		try (
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setId(rs.getString("id"));
				user.setPwd(rs.getString("pwd"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setBirth(new Date(rs.getDate("birth").getTime()));
				user.setSns(rs.getString("sns"));
				user.setReg_date(rs.getTimestamp("reg_date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("UserDAO > selectUser 오류");
			e.printStackTrace();
		}
		return user;
	}
	
	//전체 회원 조회
	@Override
	public List<User> selectUserAll() {
		List<User> users = new ArrayList<>();
		String sql = "SELECT * FROM member";
		try (
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()){
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getString("id"));
				user.setPwd(rs.getString("pwd"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setBirth(new Date(rs.getDate("birth").getTime()));
				user.setSns(rs.getString("sns"));
				user.setReg_date(rs.getTimestamp("reg_date"));
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("UserDAO > selectUserAll 오류");
			e.printStackTrace();
		}
		return users;
	}
	
}









































