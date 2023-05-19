package cn.techtutorial.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

 
import cn.techtutorial.model.Rest;

//CHANGED TO PRODUCTDAO
public class RestDao {

	private String jdbcURL = "jdbc:mysql://localhost:3306/ecommerce_cart?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "jenn980047";

	private static final String INSERT_RESTS_SQL = "INSERT INTO products" + "  (name, location, price, image) VALUES "
			+ " (?, ?, ?, ?);";

	private static final String SELECT_REST_BY_ID = "select id,name,location,price, image from products where id =?";
	private static final String SELECT_ALL_RESTS = "select * from products";
	private static final String DELETE_RESTS_SQL = "delete from products where id = ?;";
	private static final String UPDATE_RESTS_SQL = "update products set name = ?,location= ?, price =?, image=? where id = ?;";

	public RestDao() {
	}
	
	

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertRest(Rest rest) throws SQLException {
		System.out.println(INSERT_RESTS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RESTS_SQL)) {
			preparedStatement.setString(1, rest.getName());
			preparedStatement.setString(2, rest.getLocation());
			preparedStatement.setDouble(3, rest.getPrice());
			preparedStatement.setString(4, rest.getImage());

			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Rest selectRest(int id) {
		Rest rest = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REST_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String location = rs.getString("location");
				Double price = Double.parseDouble(rs.getString("price"));
				String image = rs.getString("image");
				rest = new Rest(id, name, location, price, image);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return rest;
	}

	public List<Rest> selectAllRests() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Rest> rests = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_RESTS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String location = rs.getString("location");
				Double price = Double.parseDouble(rs.getString("price"));
				String image = rs.getString("image");
				rests.add(new Rest(id, name, location, price, image));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return rests;
	}

	public boolean deleteRest(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_RESTS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateRest(Rest rest) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_RESTS_SQL);) {
			System.out.println("Updated Rest:"+statement);
			statement.setString(1, rest.getName());
			statement.setString(2, rest.getLocation());
			statement.setDouble(3, rest.getPrice());
			statement.setString(4, rest.getImage());
			statement.setInt(5, rest.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}