package br.mp.mpes.vagas.classe;

import java.util.Calendar;

/**
 * Created by rfontana on 05/10/2015.
 */
public class Frota extends Registro {

    /**
	 * 
	 */
	private static final long serialVersionUID = 552105503513605254L;
	private String nomeMotorista;
    private long kmSaida, kmChegada;

    public Frota(String placa, boolean carro, int local, String nomeMotorista, long kmSaida) {
        super(placa, carro, local);
        this.nomeMotorista = nomeMotorista;
        this.kmSaida = kmSaida;
        this.kmChegada = 0;
        super.setSaida();
    }

    public String getNomeMotorista() {
        return nomeMotorista;
    }

    public long getKmSaida() {
        return kmSaida;
    }

    public long getKmChegada() {
        return kmChegada;
    }

    public void setKmChegada(long kmChegada) {
        this.kmChegada = kmChegada;
    }

    @Override
    public String toString(){
        String retorno = "";
        if(super.getEntrada() == null){
            retorno = "NULL;";
        }else{
            retorno = super.getEntrada().get(Calendar.YEAR)+"-"+(super.getEntrada().get(Calendar.MONTH)+1)+"-"+super.getEntrada().get(Calendar.DAY_OF_MONTH)+" "+super.getEntrada().get(Calendar.HOUR_OF_DAY)+":"+super.getEntrada().get(Calendar.MINUTE)+":"+super.getEntrada().get(Calendar.SECOND)+";";
        }
        retorno += super.getPlaca()+";";
        if(this.getKmChegada() == 0){
            retorno += "NULL;";
        }else{
            retorno += this.getKmChegada()+";";
        }
        retorno += this.getNomeMotorista()+";"
                +super.getSaida().get(Calendar.YEAR)+"-"+(super.getSaida().get(Calendar.MONTH)+1)+"-"+super.getSaida().get(Calendar.DAY_OF_MONTH)+" "+super.getSaida().get(Calendar.HOUR_OF_DAY)+":"+super.getSaida().get(Calendar.MINUTE)+":"+super.getSaida().get(Calendar.SECOND)+";"
                +this.getKmSaida()+";";
        return retorno;
    }
}
