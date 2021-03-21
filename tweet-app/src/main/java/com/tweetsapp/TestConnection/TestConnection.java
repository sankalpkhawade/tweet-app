package com.tweetsapp.TestConnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class TestConnection {

	@SuppressWarnings("finally")
	public static Connection testConnection() throws IOException {

		Connection connection = null;
		Properties prop = new Properties();
		InputStream in = new FileInputStream("db.properties");
		prop.load(in);
		in.close();

		try {

			Class.forName(prop.getProperty("MYSQLJDBC.driver"));

			connection = DriverManager.getConnection(prop.getProperty("MYSQLJDBC.url"),
					prop.getProperty("MYSQLJDBC.username"), prop.getProperty("MYSQLJDBC.password"));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return connection;
		}

	}
}
