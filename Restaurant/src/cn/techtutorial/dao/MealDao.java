package cn.techtutorial.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.techtutorial.model.Meal;
import cn.techtutorial.model.Rest;

public class MealDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/ecommerce_cart?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "jenn980047";

	private static final String INSERT_MEAL_SQL = "INSERT INTO louisa_menu" + "  (name, r_name, price, calorie, category, image) VALUES "
			+ " (?, ?, ?, ?, ?, ?);";

	private static final String SELECT_MEAL_BY_ID = "select id,name,r_name,price,calorie, category, image from louisa_menu where id =?";
	private static final String SELECT_ALL_MEALS = "select * from louisa_menu";
	private static final String DELETE_MEALS_SQL = "delete from louisa_menu where id = ?;";
	private static final String UPDATE_MEALS_SQL = "update louisa_menu set name = ?, r_name=? price= ?, calorie =?, category=?, image=? where id = ?;";

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
	
	public void insertMeal(Meal meal) throws SQLException {
		System.out.println(INSERT_MEAL_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MEAL_SQL)) {
			preparedStatement.setString(1, meal.getName());
			preparedStatement.setString(2, meal.getR_name());

			preparedStatement.setDouble(3, meal.getPrice());
			preparedStatement.setInt(4, meal.getCalorie());
			preparedStatement.setString(5, meal.getCategory());
			preparedStatement.setString(6, meal.getImage());

			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public Meal selectMeal(int id) {
		Meal meal = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MEAL_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String r_name = rs.getString("r_name");

				Double price = Double.parseDouble(rs.getString("price"));
				Integer calorie = Integer.parseInt(rs.getString("calorie"));
				String category = rs.getString("category");
				String image = rs.getString("image");

				meal = new Meal(id, name, r_name, price, calorie, category, image);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return meal;
	}
	
	public List<Meal> selectAllMeals() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Meal> meals = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MEALS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String r_name = rs.getString("r_name");
				Double price = Double.parseDouble(rs.getString("price"));
				Integer calorie = Integer.parseInt(rs.getString("calorie"));
				String category = rs.getString("category");
				String image = rs.getString("image");
				meals.add(new Meal(id, name, r_name, price, calorie, category, image));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return meals;
	}
	
	public boolean deleteMeal(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_MEALS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	public boolean updateMeal(Meal meal) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_MEALS_SQL);) {
			System.out.println("Updated Meal:"+statement);
			statement.setString(1, meal.getName());
			statement.setString(2, meal.getR_name());

			statement.setDouble(3, meal.getPrice());
			statement.setInt(4, meal.getCalorie());
			statement.setString(5, meal.getCategory());
			statement.setString(6, meal.getImage());
			statement.setInt(7, meal.getId());

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
