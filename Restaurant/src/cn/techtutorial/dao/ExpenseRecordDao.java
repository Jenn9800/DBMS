package cn.techtutorial.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.techtutorial.model.ExpenseRecord;
import cn.techtutorial.model.Record;

public class ExpenseRecordDao {
	private Connection con;

	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public ExpenseRecordDao(Connection con) {
		super();
		this.con = con;
	}

	public List<ExpenseRecord> getAllExpRcs(int userID) throws SQLException {
		List<ExpenseRecord> book = new ArrayList<>();
		
		try {
			query = "select * from er where userID=?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, userID);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				ExpenseRecord row = new ExpenseRecord();
				row.setUserID(rs.getInt("userID"));
				row.setDate(rs.getString("cDate"));
				row.setBudget(rs.getInt("budget"));
				row.setMoneySpent(rs.getInt("moneySpent"));
				row.setRemainingMoney(rs.getInt("remainingMoney"));

				book.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	
		return book;
	}
}
