package moldes;


import java.util.ArrayList;
import java.util.List;

public class Personagens {

    private static int proximoId = 1;

    private int id;
    private String nome;
    private int nivel;

    ArrayList<Ataques> habilidades = new ArrayList<>();


    private double energia;

    private double xp;

    //construct
    public Personagens(String nome, int nivel, double energia, double xp) {

        this.id = proximoId++;
        setEnergia(energia);
        setNivel(nivel);
        setNome(nome);
        setXp(xp);
        //habilidades.add(new Ataques(nomeAtq,dano,custo));


    }
    public Personagens(int id, String nome, int nivel, double energia, double xp) {

        this.id = id;
        setEnergia(energia);
        setNivel(nivel);
        setNome(nome);
        setXp(xp);
        //habilidades.add(new Ataques(nomeAtq,dano,custo));


    }

    //GETERS
    public String getNome() {
        return nome;
    }

    public int getNivel(){
        return nivel;

    }

    public int getId() {
        return id;
    }

    public double getEnergia() {
        return energia;
    }

    public double getXp() {
        return xp;
    }

    public ArrayList<Ataques> getHabilidades() {
        return habilidades;
    }

    //SETERS
    public void setHabilidades(ArrayList<Ataques> hab) {
        //return habilidades;
        this.habilidades = hab;
    }
    public void setEnergia(double energia) {
        this.energia = energia;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setHabilidades(String nomeAtq,double dano, double custoEnergia) {

        habilidades.add(new Ataques(this.id,nomeAtq,dano,custoEnergia));
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setXp(double xp) {
        this.xp = xp;
    }
    public void setPontos(double xp) {
       double antigoXp = this.xp;
       double novo = antigoXp+xp;
       setXp(novo);

    }


}
