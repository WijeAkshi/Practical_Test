package model;

import java.sql.*;

public class PaymentDetails {
	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/healthcare", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertPaymentDetails(String app_Id, String cardType, String nameOnCard, String cardno, String phone,
			String expdate, String amount, String status) {
		String output = "";
		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into paymentdetails (`id`,`app_Id`,`cardType`,`nameOnCard`,`cardno`,`phone`,`expdate`,`amount`,`status`)"
					+ " values (?,?, ?, ?, ?, ?, ?, ?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, app_Id);
			preparedStmt.setString(3, cardType);
			preparedStmt.setString(4, nameOnCard);
			preparedStmt.setString(5, cardno);
			preparedStmt.setString(6, phone);
			preparedStmt.setDate(7, java.sql.Date.valueOf(expdate));
			preparedStmt.setDouble(8, Double.parseDouble(amount));
			preparedStmt.setString(9, status);

			// execute the statement
			preparedStmt.execute();
			con.close();
			String newPaymentDetails  = readPaymentDetails();
			output = "{\"status\":\"success\", \"data\": \"" + newPaymentDetails + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting payment details.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

		public String readPaymentDetails() {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for reading.";
				}
				// Prepare the html table to be displayed
				output = "<table border=\"1\"><tr> <th>AppoID</th> <th>CarsType</th> <th>Name</th> <th>cardno</th> <th>Phone</th ><th>Exp_date</th> <th>Amount</th> <th>Status</th><th>Update</th><th>Remove</th> </tr>";
				String query = "select * from paymentdetails";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				// iterate through the rows in the result set
				while (rs.next()) {
					String id = Integer.toString(rs.getInt("id"));
					String app_Id = rs.getString("app_Id");
					String cardType = rs.getString("cardType");
					String nameOnCard = rs.getString("nameOnCard");
					String cardno = rs.getString("cardno");
					String phone = Integer.toString(rs.getInt("phone"));
					Date expdate = rs.getDate("expdate");
					String amount = Double.toString(rs.getDouble("amount"));
					String status = rs.getString("status");
					// Add into the html table
					output += "<tr><td><input id='hidIDUpdate' name='hidIDUpdate' type='hidden' value='" + id + "'>"
					 + app_Id + "</td>";
					output += "<td>" + cardType + "</td>";
					output += "<td>" + nameOnCard + "</td>";
					output += "<td>" + cardno + "</td>";
					output += "<td>" + phone + "</td>";
					output += "<td>" + expdate + "</td>";
					output += "<td>" + amount + "</td>";
					output += "<td>" + status + "</td>";
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
							+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-paymentid='"
							+ id + "'>" + "</td></tr>";

				}
				con.close();
				// Complete the html table
				output += "</table>";
			} catch (Exception e) {
				output = "Error while reading the card details.";
				System.err.println(e.getMessage());
			}
			return output;
	}

		public String updatePaymentDetails(String id, String app_Id, String cardType, String nameOnCard, String cardno, String phone, String expdate, String amount, String status) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for updating.";
				}
				// create a prepared statement
				String query = "UPDATE paymentdetails SET app_Id=?,cardType=?,nameOnCard=?,cardno=?,phone=?,expdate=?,amount=?,status=? WHERE id=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, app_Id);
				preparedStmt.setString(2, cardType);
				preparedStmt.setString(3, nameOnCard);
				preparedStmt.setString(4, cardno);
				preparedStmt.setInt(5, Integer.parseInt(phone));
				preparedStmt.setDate(6, java.sql.Date.valueOf(expdate));
				preparedStmt.setDouble(7, Double.parseDouble(amount));
				preparedStmt.setString(8, status);
				preparedStmt.setInt(9, Integer.parseInt(id));

				// execute the statement
				preparedStmt.execute();
				con.close();
				String newPaymentDetails = readPaymentDetails();
				output = "{\"status\":\"success\", \"data\": \"" + newPaymentDetails + "\"}";
			} catch (Exception e) {
				output = "{\"status\":\"error\", \"data\": \"Error while updating the payment details.\"}";
				System.err.println(e.getMessage());
			}
			return output;
	}

		public String deletePaymentDetails(String id) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}
				// create a prepared statement
				String query = "delete from paymentdetails where id=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(id));
				// execute the statement
				preparedStmt.execute();
				con.close();
				String newPaymentDetails = readPaymentDetails();
				output = "{\"status\":\"success\", \"data\": \"" + newPaymentDetails + "\"}";
			} catch (Exception e) {
				output = "Error while deleting the card details.";
				System.err.println(e.getMessage());
			}
			return output;
	}
}