package moldes;

public class Ataques {

    private int idU;
    private String nomeAtq;
    private double dano;
    private double custoEnergia;

    //Constructor
    public Ataques(int id,String nomeAtq,double dano, double custoEnergia){
        this.idU = id;
        this.custoEnergia = custoEnergia;
        this.nomeAtq = nomeAtq;
        this.dano = dano;

    }

    //GETERS

    public double getCustoEnergia() {
        return custoEnergia;
    }

    public double getDano() {
        return dano;
    }

    public String getNomeAtq() {
        return nomeAtq;
    }

    public int getIdU() {
        return idU;
    }

    //SETERS


    public void setDano(double dano) {
        this.dano = dano;
    }

    public void setCustoEnergia(double custoEnergia) {
        this.custoEnergia = custoEnergia;
    }

    public void setNomeAtq(String nomeAtq) {
        this.nomeAtq = nomeAtq;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }
}
