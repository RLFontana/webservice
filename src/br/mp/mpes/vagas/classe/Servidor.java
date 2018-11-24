package br.mp.mpes.vagas.classe;

import java.util.Calendar;

/**
 * Created by rfontana on 05/10/2015.
 */
public class Servidor extends Registro {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6146367340719822963L;
	private int matricula, ramal;

    public Servidor(String placa, boolean carro, int local, int matricula, int ramal) {
        super(placa, carro, local);
        this.matricula = matricula;
        this.ramal = ramal;
        super.setEntrada();
    }

    public int getMatricula() {
        return matricula;
    }

    public int getRamal() {
        return ramal;
    }

    @Override
    public String toString(){
        String retorno = "";
        retorno = super.getEntrada().get(Calendar.YEAR)+"-"+(super.getEntrada().get(Calendar.MONTH)+1)+"-"+super.getEntrada().get(Calendar.DAY_OF_MONTH)+" "+super.getEntrada().get(Calendar.HOUR_OF_DAY)+":"+super.getEntrada().get(Calendar.MINUTE)+":"+super.getEntrada().get(Calendar.SECOND)+";"
                +super.getPlaca()+";"
                +super.getLocal()+";"
                +this.getMatricula()+";"
                +"NULL;"
                +"NULL;";
        if(super.getSaida() == null){
            retorno += "NULL;";
        }else{
            retorno += super.getSaida().get(Calendar.YEAR)+"-"+(super.getSaida().get(Calendar.MONTH)+1)+"-"+super.getSaida().get(Calendar.DAY_OF_MONTH)+" "+super.getSaida().get(Calendar.HOUR_OF_DAY)+":"+super.getSaida().get(Calendar.MINUTE)+":"+super.getSaida().get(Calendar.SECOND)+";";
        }
        if(super.isCarro()){
            retorno += "carro;";
        }else{
            retorno += "moto;";
        }
        return retorno;
    }
}
