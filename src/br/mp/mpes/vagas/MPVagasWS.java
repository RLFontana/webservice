package br.mp.mpes.vagas;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.mp.mpes.vagas.classe.Frota;
import br.mp.mpes.vagas.classe.InfoVagas;
import br.mp.mpes.vagas.classe.Registro;
import br.mp.mpes.vagas.classe.Servidor;
import br.mp.mpes.vagas.classe.Visitante;
import br.mp.mpes.vagas.dao.InfoVagasJDBCDAO;

@Path("/")
public class MPVagasWS {

	public static final InfoVagas[] info = new InfoVagas[2];

	@GET
	@Produces("text/html")
	public Response isRunning() {
		return Response.status(200).entity("<H1>The server is running!</H1>").build();
	}
	
	@GET
	@Path("/consulta")
	@Produces("application/json")
	public Response getVagasDisponiveis() throws UnsupportedEncodingException{
		Gson gson = new Gson();
		String retorno = gson.toJson(info, InfoVagas[].class);
		byte[] stream = retorno.getBytes("UTF-8");
		retorno = new String (stream, "ISO-8859-1");
		return Response.status(200).entity(retorno).build();
	}

	@POST
	@Path("/atualiza/{localidade}/{carro}/{moto}")
	public Response atualizaVagas(@PathParam("localidade") int local, @PathParam("carro") int carro, @PathParam("moto") int moto){
		if(info[local] == null){
			info[local] = new InfoVagas();
		}
		info[local].setData(Calendar.getInstance());
		info[local].setQtdVagasCarro(carro);
		info[local].setQtdVagasMoto(moto);
		return Response.status(200).build();
	}

	@PUT
	@Path("/salvar/{tipo}")
	@Consumes("application/json")
	public Response salvaRegistro(@PathParam("tipo") int tipo, String body){
		Gson gson = new Gson();
		Registro temp;
		InfoVagasJDBCDAO salvar = new InfoVagasJDBCDAO();
		switch (tipo){
		case 1:
			temp = gson.fromJson(body, Visitante.class);
			break;
		case 2:
			temp = gson.fromJson(body, Frota.class);
			break;
		case 0:
		default:
			temp = gson.fromJson(body, Servidor.class);
			break;
		}
		if(salvar.salvaRegistro(temp, tipo)){
			return Response.status(200).build();	
		}else{
			return Response.status(400).build();
		}
	}

	@GET
	@Path("/agenda")
	@Produces("application/json")
	public Response verificaAgenda() throws UnsupportedEncodingException{
		String json = "null";
		boolean consultou;
		InfoVagasJDBCDAO consulta = new InfoVagasJDBCDAO();
		consultou = consulta.atualizaDisponibilidade();
		if(info[0] == null){
			info[0] = new InfoVagas();
			info[0].setQtdVagasCarro(0);
			info[0].setQtdVagasMoto(0);
		}
		info[0].setDisponivel(false);
		info[0].setMotivo("Indisponível, tente novamente mais tarde");
		if (consultou){
			info[0].setDisponivel(InfoVagasJDBCDAO.disponivelAgora);
			info[0].setMotivo(InfoVagasJDBCDAO.motivoAtual);
		}

		if (!InfoVagasJDBCDAO.disponivelFuturo){
			json = "{\"data\":{"+getDataFormatada(InfoVagasJDBCDAO.dataFutura)+"},\"motivo\":\""+InfoVagasJDBCDAO.motivoFuturo+"\"}";
		}
		byte[] stream = json.getBytes("UTF-8");
		json = new String (stream, "ISO-8859-1");
		return Response.status(200).entity(json).build();
	}

	public String getDataFormatada(Calendar data){
		String retorno = "\"year\":"+data.get(Calendar.YEAR)+",\"month\":"+data.get(Calendar.MONTH)+",\"dayOfMonth\":"+data.get(Calendar.DAY_OF_MONTH)+",\"hourOfDay\":0,\"minute\":0,\"second\":0";
		return retorno;
	}

}
