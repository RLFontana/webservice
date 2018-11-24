package br.mp.mpes.vagas.classe;

import java.util.Calendar;

/**
 * Created by rfontana on 05/10/2015.
 */
public class Visitante extends Registro {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7357409799892210138L;
	private String nome, depto;
    private long celular;

    public Visitante(String placa, boolean carro, int local, String nome, String depto, long celular) {
        super(placa, carro, local);
        this.nome = nome;
        this.depto = depto;
        this.celular = celular;
        super.setEntrada();
    }

    public String getNome() {
        return nome;
    }

    public String getDepto() {
        return depto;
    }

    public long getCelular() {
        return celular;
    }

    @Override
    public String toString(){
        String retorno = "";
        retorno = super.getEntrada().get(Calendar.YEAR)+"-"+(super.getEntrada().get(Calendar.MONTH)+1)+"-"+super.getEntrada().get(Calendar.DAY_OF_MONTH)+" "+super.getEntrada().get(Calendar.HOUR_OF_DAY)+":"+super.getEntrada().get(Calendar.MINUTE)+":"+super.getEntrada().get(Calendar.SECOND)+";"
                +super.getPlaca()+";"
                +super.getLocal()+";"
                +"NULL;"
                +this.getNome()+";"
                +this.getDepto()+";";
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
