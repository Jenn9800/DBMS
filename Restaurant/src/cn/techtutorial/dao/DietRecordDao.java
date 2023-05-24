package cn.techtutorial.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.techtutorial.model.DietRecord;
import cn.techtutorial.model.ExpenseRecord;
import cn.techtutorial.model.Record;

public class DietRecordDao {
	private Connection con;

	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public DietRecordDao(Connection con) {
		super();
		this.con = con;
	}

	public List<DietRecord> getAllDRs(int userID, String cDate) throws SQLException {
		List<DietRecord> book = new ArrayList<>();

		try {
			query = "select * from dr where userID=?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, userID);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				DietRecord row = new DietRecord();
				row.setUserID(rs.getInt("userID"));
				row.setDate(rs.getString("cDate"));
				row.setCalLimit(rs.getDouble("calLimit"));
				row.setCalIngested(rs.getDouble("calIngested"));
				row.setRemainingCal(rs.getDouble("remainingCal"));

				book.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	
		return book;
	}
}
