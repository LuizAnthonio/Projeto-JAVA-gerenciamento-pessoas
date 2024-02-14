package moldes;


import java.util.ArrayList;
import java.util.List;

public class Personagens {

    private static int proximoId = 1;

    private int id;
    private String nome;
    private int nivel;

    List<Ataques> habilidades = new ArrayList<>();

    private double energia;

    private double xp;

    private double forca;

    private double resistencia;

    private double velocidade;

    private int tipo;

    private int tecnica;

    //construct
    public Personagens(String nome, int nivel, double energia, double xp,double forca, double resistencia,double velocidade,int tipo,int tecnica) {

        this.id = proximoId++;
        setEnergia(energia);
        setNivel(nivel);
        setNome(nome);
        setXp(xp);
        setTipo(tipo);
        setForca(forca);
        setVelocidade(velocidade);
        setResistencia(resistencia);
        setTecnica(tecnica);
        //habilidades.add(new Ataques(nomeAtq,dano,custo));


    }
    public Personagens(int id, String nome, int nivel, double energia, double xp,double forca, double resistencia,double velocidade,int tipo, int tecnica) {

        this.id = id;
        setEnergia(energia);
        setNivel(nivel);
        setNome(nome);
        setXp(xp);
        setTipo(tipo);
        setForca(forca);
        setVelocidade(velocidade);
        setResistencia(resistencia);
        setTecnica(tecnica);
        //habilidades.add(new Ataques(nomeAtq,dano,custo));


    }

    //GETERS
    public String getNome() {

        return nome;
    }

    public int getTecnica() {
        return tecnica;
    }

    public int getTipo() {
        return tipo;
    }


    public double getForca() {
        return forca;
    }

    public double getResistencia() {
        return resistencia;
    }

    public double getVelocidade() {
        return velocidade;
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

    public List<Ataques> getHabilidades() {
        return habilidades;
    }

    //SETERS
    public void setHabilidades(List<Ataques> hab) {
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

    public void setForca(double forca) {
        this.forca = forca;
    }

    public void setResistencia(double resistencia) {
        this.resistencia = resistencia;
    }

    public void setTecnica(int tecnica) {
        this.tecnica = tecnica;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }




}
