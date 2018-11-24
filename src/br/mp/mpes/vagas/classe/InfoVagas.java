package br.mp.mpes.vagas.classe;

import java.util.Calendar;

public class InfoVagas {
	
	private int qtdVagasMoto;
	private int qtdVagasCarro;
	private Calendar data;
	private boolean disponivel;
	private String motivo;
	
	public InfoVagas() {
		this.qtdVagasMoto = -1;
		this.qtdVagasCarro = -1;
		this.data = Calendar.getInstance();
		this.disponivel = true;
		this.motivo = "";
	
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public int getQtdVagasMoto() {
		return qtdVagasMoto;
	}

	public void setQtdVagasMoto(int qtdVagasMoto) {
		this.qtdVagasMoto = qtdVagasMoto;
	}

	public int getQtdVagasCarro() {
		return qtdVagasCarro;
	}

	public void setQtdVagasCarro(int qtdVagasCarro) {
		this.qtdVagasCarro = qtdVagasCarro;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}
	
}
