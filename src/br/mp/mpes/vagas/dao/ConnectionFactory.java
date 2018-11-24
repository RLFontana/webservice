package br.mp.mpes.vagas.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private String driverClassName = "com.mysql.cj.jdbc.Driver";
	private static ConnectionFactory connectionFactory = null;
	//Homologação
	private String connectionUrl = "jdbc:mysql://localhost:3306/?useTimezone=true&serverTimezone=UTC";
	//Produção
	//private String connectionUrl = "jdbc:sqlserver://mpsql001\\gampes;databaseName=DB_Estacionamento;user=user_estacionamento;password=mp3s_3st@ci0n@m3nt0!@#;";
	
	private ConnectionFactory(){
		try{
			Class.forName(driverClassName);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(NullPointerException np){
			np.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection(connectionUrl, "root", "117066");
		return conn;
	}

	public static ConnectionFactory getInstance(){
		if(connectionFactory == null){
			connectionFactory = new ConnectionFactory();
		}
		return connectionFactory;
	}
}
