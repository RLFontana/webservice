package br.mp.mpes.vagas.classe;

import java.util.Calendar;

/**
 * Created by rfontana on 26/05/2015.
 */
public abstract class Registro implements java.io.Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 4370609420723170755L;
	private String placa;
    private boolean carro;
    private int local;
    private Calendar entrada;
    private Calendar saida;

    public Registro(String placa, boolean carro, int local) {
        this.placa = placa;
        this.carro = carro;
        this.local = local;
        this.saida = null;
    }

    public Calendar getEntrada() {
        return entrada;
    }

    public Calendar getSaida(){
        return saida;
    }

    public boolean isCarro(){
        return carro;
    }

    public String getPlaca() {
        return placa;
    }

    public void setEntrada() {
        final Calendar temp = Calendar.getInstance();
        this.entrada = temp;
    }

    public void setSaida(){
        final Calendar temp = Calendar.getInstance();
        this.saida = temp;
    }

    public String horaEntrada(){
        String hora = "";
        if(this.entrada==null){
            hora = "-";
        }else {
            hora += entrada.get(Calendar.DAY_OF_MONTH) + "/" + (entrada.get(Calendar.MONTH) + 1) + "/" + entrada.get(Calendar.YEAR);
            hora += " ";
            if (entrada.get(Calendar.AM_PM) == 1) {
                hora += (entrada.get(Calendar.HOUR) + 12) + ":";
            } else {
                hora += entrada.get(Calendar.HOUR) + ":";
            }
            if (entrada.get(Calendar.MINUTE) < 10) {
                hora += "0" + entrada.get(Calendar.MINUTE);
            } else {
                hora += entrada.get(Calendar.MINUTE);
            }
        }
        return hora;
    }

    public String horaSaida(){
        String hora ="";
        if(this.saida==null){
            hora = "-";
        }else{
            hora += saida.get(Calendar.DAY_OF_MONTH) + "/" + (saida.get(Calendar.MONTH)+1) + "/" + saida.get(Calendar.YEAR);
            hora += " ";
            if(saida.get(Calendar.AM_PM) == 1) {
                hora += (saida.get(Calendar.HOUR)+12) + ":";
            }else{
                hora += saida.get(Calendar.HOUR) + ":";
            }
            if(saida.get(Calendar.MINUTE) < 10){
                hora += "0"+saida.get(Calendar.MINUTE);
            }else{
                hora += saida.get(Calendar.MINUTE);
            }
        }
        return hora;
    }
    
    public String getDataEntrada(){
    	String retorno = "";
    	if(this.entrada != null){
    		retorno = "'"+entrada.get(Calendar.YEAR)+"-"+
  				  	  (entrada.get(Calendar.MONTH)+1)+"-"+
  				  	  entrada.get(Calendar.DAY_OF_MONTH)+" "+
  				  	  entrada.get(Calendar.HOUR_OF_DAY)+":"+
  				  	  entrada.get(Calendar.MINUTE)+":"+
  				  	  entrada.get(Calendar.SECOND)+"'";
    	}else{
    		retorno = "NULL";
    	}
    	return retorno;
    }
    
    public String getDataSaida(){
    	String retorno = "";
    	if(this.saida != null){
    		retorno = "'"+saida.get(Calendar.YEAR)+"-"+
    				  (saida.get(Calendar.MONTH)+1)+"-"+
    				  saida.get(Calendar.DAY_OF_MONTH)+" "+
    				  saida.get(Calendar.HOUR_OF_DAY)+":"+
    				  saida.get(Calendar.MINUTE)+":"+
    				  saida.get(Calendar.SECOND)+"'";
    	}else{
    		retorno = "NULL";
    	}
    	return retorno;
    }

    public String getL1(){
        return placa.substring(0,1).toLowerCase();
    }

    public String getL2(){
        return placa.substring(1,2).toLowerCase();
    }

    public String getL3(){
        return placa.substring(2,3).toLowerCase();
    }

    public String getN1(){
        return placa.substring(4, 5);
    }

    public String getN2(){
        return placa.substring(5, 6);
    }

    public String getN3(){
        return placa.substring(6,7);
    }

    public String getN4(){
        return placa.substring(7);
    }

    public int getLocal() {
        return local;
    }

    public boolean isPresente(){
        if(saida == null){
            return true;
        }else{
            return false;
        }
    }

    public boolean isAusente(){
        if(entrada == null){
            return true;
        }
        else{
            return false;
        }
    }
}