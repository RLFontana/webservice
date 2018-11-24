package br.mp.mpes.vagas.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;

import br.mp.mpes.vagas.classe.Frota;
import br.mp.mpes.vagas.classe.Registro;
import br.mp.mpes.vagas.classe.Servidor;
import br.mp.mpes.vagas.classe.Visitante;

public class InfoVagasJDBCDAO {

	private Connection connection = null;
	private Statement stmt = null;
	private ResultSet resultSet = null;
	private String tabelaParticular = "";
	private String tabelaFrota = "";
	private String tabelaAgenda = "";
	public static boolean disponivelAgora, disponivelFuturo;
	public static String motivoAtual, motivoFuturo;
	public static Calendar dataFutura;


	public InfoVagasJDBCDAO(){
		this.tabelaParticular = "DB_estacionamento.controle.Particular";
		this.tabelaFrota = "DB_estacionamento.controle.Frota";
		this.tabelaAgenda = "DB_Estacionamento.controle.Agenda";
	}

	private Connection getConnection() throws SQLException{
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}

	public boolean salvaRegistro(Registro registro, int tipo){
		boolean retorno = false;
		String queryString;
		String veiculo;
		String horaEntrada = registro.getDataEntrada();
		String horaSaida = registro.getDataSaida();
		String kmEntrada;
		switch(tipo){
		//Particular
		case 0:
		case 1:
			if(registro.isCarro()){
				veiculo = "carro";
			}else{
				veiculo = "moto";
			}
			if(registro.getClass().getSimpleName().equalsIgnoreCase("Servidor")){
				queryString = "UPDATE "+tabelaParticular+" SET DataSaida = "+horaSaida+" WHERE Placa='"+registro.getPlaca()+"' AND DataEntrada="+horaEntrada+" IF @@ROWCOUNT=0 INSERT INTO "+tabelaParticular+" VALUES ("+horaEntrada+",'"+registro.getPlaca()+"',"+registro.getLocal()+","+((Servidor)registro).getMatricula()+",NULL,NULL,"+horaSaida+",'"+veiculo+"');";
			}else{
				queryString = "UPDATE "+tabelaParticular+" SET DataSaida = "+horaSaida+" WHERE Placa='"+registro.getPlaca()+"' AND DataEntrada="+horaEntrada+" IF @@ROWCOUNT=0 INSERT INTO "+tabelaParticular+" VALUES ("+horaEntrada+",'"+registro.getPlaca()+"',"+registro.getLocal()+",NULL,'"+((Visitante)registro).getNome()+"','"+((Visitante)registro).getDepto()+"',"+horaSaida+",'"+veiculo+"');";	
			}
			try{
				connection = getConnection();
				stmt = connection.createStatement();
				stmt.execute(queryString);
				retorno = true;
			}catch (SQLException e){
				e.printStackTrace();
			}
			break;
			//Frota
		case 2:
			if(((Frota)registro).getKmChegada() == 0L){
				kmEntrada = "NULL";
			}else{
				kmEntrada = ""+((Frota)registro).getKmChegada();
			}
			queryString = "UPDATE "+tabelaFrota+" SET DataEntrada="+horaEntrada+", KmEntrada="+((Frota)registro).getKmChegada()+" WHERE Placa='"+registro.getPlaca()+"' AND DataSaida="+horaSaida+" IF @@ROWCOUNT=0 INSERT INTO "+tabelaFrota+" VALUES ("+horaEntrada+",'"+registro.getPlaca()+"',"+kmEntrada+",'"+((Frota)registro).getNomeMotorista()+"',"+horaSaida+","+((Frota)registro).getKmSaida()+");";
			try{
				connection = getConnection();
				stmt = connection.createStatement();
				stmt.execute(queryString);
				retorno = true;
			}catch (SQLException e){
				e.printStackTrace();
			}
			break;
		}
		return retorno;
	}

	public boolean atualizaDisponibilidade(){
		boolean retorno = false;
		dataFutura = null;
		motivoFuturo = "";
		String queryString = "SELECT TOP 2 Data, Motivo FROM "+tabelaAgenda+" WHERE DATA BETWEEN CONVERT(VARCHAR(10),GETDATE(), 120) + ' 00:00:00' AND CONVERT(VARCHAR(10), DATEADD(d, 6, GETDATE()), 120) + ' 00:00:00' ORDER BY Data;";
		try{
			connection = getConnection();
			stmt = connection.createStatement();
			resultSet = stmt.executeQuery(queryString);
			if (resultSet.next()){
				final Calendar dataTemp = dateToCalendar(resultSet.getTimestamp("Data"));
				final Calendar dataAtual = Calendar.getInstance();
				dataAtual.set(Calendar.HOUR_OF_DAY, 0);
				dataAtual.set(Calendar.MINUTE, 0);
				dataAtual.set(Calendar.SECOND, 0);
				dataAtual.set(Calendar.MILLISECOND, 0);
				if(dataTemp.compareTo(dataAtual) == 0){
					motivoAtual = resultSet.getString("Motivo");
					disponivelAgora = false;
					if (resultSet.next()){
						disponivelFuturo = false;
						motivoFuturo = resultSet.getString("Motivo");
						final Calendar dataTemp2  = dateToCalendar(resultSet.getTimestamp("Data"));
						dataFutura = dataTemp2;
					}else{
						disponivelFuturo = true;
						motivoFuturo = "";
					}
				}else{
					disponivelAgora = true;
					motivoAtual = "";
					disponivelFuturo = false;
					motivoFuturo = resultSet.getString("Motivo");
					final Calendar dataTemp3  = dateToCalendar(resultSet.getTimestamp("Data"));
					dataFutura = dataTemp3;
				}
			}else{
				disponivelFuturo = true;
				disponivelAgora = true;
				motivoAtual = "";
				motivoFuturo = "";
			}
			retorno = true;
		}catch (SQLException e){
			e.printStackTrace();
		}
		return retorno;
	}

	public static Calendar dateToCalendar(Timestamp date){
		final Calendar cal = Calendar.getInstance();
		if(date != null){
			java.util.Date newDate = (java.util.Date)date;
			cal.setTime(newDate);
			return cal;
		}else{
			return null;
		}
	}
}