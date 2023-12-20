package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import model.Engineer;

public class ControllerEngineer {
	private static final String DATABASE = "staff_database";
    private static final String TABLE = "table_engineers";
    private static Connection connection = DatabaseConnection.getConnection(DATABASE);

	public static ArrayList<Engineer> getEngineerList() {
		ArrayList<Engineer> engineerList = new ArrayList<Engineer>();
		try {
			String query = "SELECT * FROM " + TABLE;
			PreparedStatement preparedstatement = connection.prepareStatement(query);
			ResultSet resultset = preparedstatement.executeQuery();

			while (resultset.next()) {
				String currentId = resultset.getString("id");
				String currentFirst = resultset.getString("first");
				String currentMiddle = resultset.getString("middle");
				String currentLast = resultset.getString("last");
				LocalDate currentStartDate = resultset.getDate("startDate").toLocalDate();
				double currentSalary = resultset.getDouble("salary");
				String currentEmail = resultset.getString("email");

				Engineer currentEngineer = new Engineer(
					currentId, currentFirst, currentMiddle, currentLast, currentStartDate, currentSalary, currentEmail
				);
				
				engineerList.add(currentEngineer);
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return engineerList;
	}

    public static void addEngineer(String first, String middle, String last, LocalDate startDate, double salary) {
        try {
			Random rand = new Random();
			String id = String.valueOf(
				first.substring(0, 1).toLowerCase()
				+ middle.substring(0, 1).toLowerCase()
				+ last.toLowerCase()
				+ String.valueOf(rand.nextInt(99))
			);
			
            String query;
            Statement statement = connection.createStatement();
            query = "INSERT INTO `" + DATABASE + "`.`" + TABLE
					+ "` (`id`, `first`, `middle`, `last`, `startDate`, `salary`, `email`) VALUES ('" 
					+ id + "','"
					+ (first.substring(0, 1).toUpperCase() + first.substring(1)) + "','"
					+ (middle.substring(0, 1).toUpperCase() + middle.substring(1)) + "','"
					+ (last.substring(0, 1).toUpperCase() + last.substring(1)) + "','"
					+ startDate + "','"
					+ salary + "','"
					+ (id+"@staff.fx") + "')";
			statement.executeUpdate(query);
			statement.close();
			System.out.println(first + " added to " + TABLE + " table");

        } catch
            (SQLException SQLException) {
                SQLException.printStackTrace();
            }
    }

    public static void removeEngineer(Engineer engineer) {
        try {
			String query = "DELETE FROM  " + TABLE + " WHERE `id` = '" + engineer.getId() + "'";
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			statement.close();
			System.out.println(engineer.getId() + " removed from " + TABLE + " table");
		} 
        catch (SQLException sqlException) {
			sqlException.printStackTrace();
			// close();
		}
    }

	public static void giveRaise(Engineer engineer) {
		try {
			engineer.giveRaise();
			String query = "UPDATE `" + TABLE + "` SET `salary` = '" + engineer.getSalary() + "' WHERE (`id` = '" + engineer.getId() + "')";
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			statement.close();
			System.out.println(engineer.getId() + " salary updated to " + engineer.getSalary() + " in " + TABLE);
		} 
        catch (SQLException sqlException) {
			sqlException.printStackTrace();
			// close();
		}
	}



}