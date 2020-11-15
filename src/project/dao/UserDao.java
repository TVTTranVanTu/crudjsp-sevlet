package project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.model.User;

public class UserDao {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/projectcrud?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";
	
	
	private static final String INSERT_USERS_SQL = "INSERT INTO users" + " (name,email,country,isadmin) VALUES" + " (?,?,?,?);";
	private static final String SELECT_USER_BY_ID = "select id,name,email,country,isadmin from users where id=?";
	private static final String SELECT_ALL_USERS = "select * from users";
	private static final String SELECT_ALL_CUSTOMERS = "select * from users where isadmin =0";
	private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
	private static final String UPDATE_USERS_SQL = "update users set name=?,email=?,country=?,isadmin=? where id=?;";

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	//create or insert user
	
	public void insertUser(User user) throws SQLException {
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getCountry());
			preparedStatement.setInt(4, user.getIsadmin());
			preparedStatement.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//update user
	
	public boolean updateUser(User user) throws SQLException {
		boolean rowUpdated;
		try(Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL)) {
			statement.setString(1, user.getName());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getCountry());
			statement.setInt(4, user.getIsadmin());
			statement.setInt(5, user.getId());
			
			rowUpdated = statement.executeUpdate() >0 ;
		}
		return rowUpdated;
	}
	//select user by id
	
	public User selectUser(int id) {
		User user = null;
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);){
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			
			ResultSet rs =  preparedStatement.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				int isadmin = rs.getInt("isadmin");
				user = new User(id,name,email,country,isadmin);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	//select users
	
	public List<User> selectAllUsers() {
		List<User> users = new ArrayList<>();
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);){
			System.out.println(preparedStatement);
			
			ResultSet rs =  preparedStatement.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				int isadmin = rs.getInt("isadmin");
				users.add(new User(id,name, email, country,isadmin));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return users;

	}
	
	//select customer
	
	public List<User> selectAllCustomers() {
		List<User> users = new ArrayList<>();
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERS);){
			System.out.println(preparedStatement);
			
			ResultSet rs =  preparedStatement.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				int isadmin = rs.getInt("isadmin");
				users.add(new User(id,name, email, country,isadmin));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return users;

	}
	//delete user
	
	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try(Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL)){
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
}