import crud.Crud;
import moldes.Ataques;
import moldes.EscritaDevagar;
import moldes.Personagens;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Batalha {


    public static int findIndexId(int id, List<Personagens> listaPerso) {

        int index = 0;

        for (int i = 0; i < listaPerso.size(); i++) {
            if (listaPerso.get(i).getId() == id) {
                index++;
            }
            break;
        }

        return index;
    }

    public static void acaoFinal(int idP1, int nivelP1, int nivelP2, int tecnicaP1, int tecnicaP2, int tipoP2, List<Ataques> ataque) throws IOException {

        Crud crud = new Crud();
        Scanner scanner = new Scanner(System.in);
        int cont = 0;
        int selecionado;
        int i;

        List<String> tecTodas = crud.listaTecnicas("tecnicas");


        if (tecnicaP1 == 2 && tipoP2 != 1) {
            System.out.println("escolha um ataque para copiar!");
            for (Ataques tec : ataque) {
                cont++;
                if (!tec.getNomeAtq().contains("Dominio") || !tec.getNomeAtq().contains("dominio")) {
                    System.out.println(cont + ") " + tec.getNomeAtq() + " - " + tec.getDano() + " - " + tec.getCustoEnergia());

                }

            }

            System.out.println("Não quero nenhuma -> " + (cont + 1) + "- sair.");
            selecionado = scanner.nextInt();
            if (selecionado == cont + 1) {

                System.out.println("Nenhuma tecnica adquirida!");

            } else {

                i = selecionado - 1;

                if (ataque.get(i).getNomeAtq().contains(tecTodas.get(tecnicaP2))) {

                    crud.adicionadorAtq(idP1, ataque.get(i).getNomeAtq(), ataque.get(i).getDano(), ataque.get(i).getCustoEnergia());
                } else {
                    String tecnicaNova = tecTodas.get(tecnicaP2) + ": " + ataque.get(i).getNomeAtq();


                    crud.adicionadorAtq(idP1, tecnicaNova, ataque.get(i).getDano(), ataque.get(i).getCustoEnergia());

                }

            }


        } else if (tecnicaP1 == 3 && tipoP2 == 1 && nivelP2 >= nivelP1) {


            System.out.println("Gostaria de Absorver esta maldição?\n 0 - Sim \n 1 - Não");
            selecionado = scanner.nextInt();

            String esp;


            if (selecionado == 0) {
                for (Ataques tec : ataque) {


                    // System.out.println(cont+") "+tec.getNomeAtq()+" - "+tec.getDano()+" - "+tec.getCustoEnergia());
                    if (nivelP2 != 0) {

                        crud.adicionadorAtq(idP1, ("Maldição de nivel " + nivelP2 + ": " + tec.getNomeAtq()), tec.getDano(), tec.getCustoEnergia());

                    } else {

                        crud.adicionadorAtq(idP1, ("Maldição de nivel Especial: " + tec.getNomeAtq()), tec.getDano(), tec.getCustoEnergia());

                    }

                }

            } else {
                System.out.println("Não absorvido!");
            }


        }


    }


    public static void resultado(int id, int nivelP2, int nivelP1, double xp) throws IOException {

        Crud crud = new Crud();

        int diferencaNIvel = nivelP1 - nivelP2;
        double novosPontos;
        double adquiridos;
        if (nivelP1 < nivelP2) {


            if (diferencaNIvel <= -1) {

                adquiridos = 12.0 * 200.0;

            } else {

                adquiridos = diferencaNIvel * 200.0;

            }

            novosPontos = adquiridos + xp;

            // p2.setXp(novosPontos);

            int novoNivel;

            String pont = Double.toString(novosPontos);
            //add pontos
            crud.editarLinha("arquivo2", id, 4, pont);

            //mudar de nivel com base nos pontos
            if (novosPontos >= 2000.0 && nivelP2 == 4) {
                novoNivel = nivelP2 - 1;

                crud.editarLinha("arquivo2", id, 2, Integer.toString(novoNivel));

                System.out.println("Pontos adquiritos: " + adquiridos);
                System.out.println("Total de pontos: " + novosPontos);
                System.out.println("Parabenss!!");
                System.out.println("Novo Nivel: " + novoNivel);


            } else if (novosPontos >= 6000.0 && nivelP2 == 3) {
                novoNivel = nivelP2 - 1;
                crud.editarLinha("arquivo2", id, 2, Integer.toString(novoNivel));


                System.out.println("Pontos adquiritos: " + adquiridos);
                System.out.println("Total de pontos: " + novosPontos);
                System.out.println("Parabenss!!");
                System.out.println("Novo Nivel: " + novoNivel);


            } else if (novosPontos >= 12000.0 && nivelP2 == 2) {
                novoNivel = nivelP2 - 1;
                crud.editarLinha("arquivo2", id, 2, Integer.toString(novoNivel));

                System.out.println("Pontos adquiritos: " + adquiridos);
                System.out.println("Total de pontos: " + novosPontos);
                System.out.println("Parabenss!!");
                System.out.println("Novo Nivel: " + novoNivel);


            } else if (novosPontos >= 30000.0 && nivelP2 == 1) {
                novoNivel = nivelP2 - 1;
                crud.editarLinha("arquivo2", id, 2, Integer.toString(novoNivel));

                System.out.println("Pontos adquiritos: " + adquiridos);
                System.out.println("Total de pontos: " + novosPontos);
                System.out.println("Parabenss!!");
                System.out.println("Novo Nivel: Especial");



            } else {
                System.out.println("Pontos adquiritos: " + adquiridos);
                System.out.println("Total de pontos: " + novosPontos);

            }


        } else if (nivelP1 == nivelP2) {

            adquiridos = 100.0;
            novosPontos = adquiridos + xp;
            crud.editarLinha("arquivo2", id, 4, Double.toString(novosPontos));

            System.out.println("Pontos adquiritos: " + adquiridos);
            System.out.println("Total de pontos: " + novosPontos);


        } else {
            adquiridos = 50.0;
            novosPontos = adquiridos + xp;
            crud.editarLinha("arquivo2", id, 4, Double.toString(novosPontos));

            System.out.println("Pontos adquiritos: " + adquiridos);
            System.out.println("Total de pontos: " + novosPontos);


        }

    }

    public static void escreva(String text) {
        EscritaDevagar escritaDevagar = new EscritaDevagar();


        String[] words = text.split(" ");
        //words.length;
        int cont = 0;

        for (String word : words) {
            escritaDevagar.printWithDelay(word, 100);// Tempo de atraso em milissegundos
            cont++;
        }

        if (cont == words.length) {
            System.out.println("\n");
        }


    }


    public static void olha() {
        System.out.println("Olhaaaaa");
    }


    public static boolean batalha(int id1,int id2) throws IOException {
        Crud crud = new Crud();
        Scanner scanner = new Scanner(System.in);

        List<Personagens> pers = crud.lerDoc("arquivo2");


        List<Personagens> lutadores = new ArrayList<>();
        //System.out.println("Selecione Player 1");
        //int selectPlayer2 = scanner.nextInt();
        int selectPlayer2 = id2;
        //System.out.println("Selecione Player 2");
        //int selectPlayer1 = scanner.nextInt();
        int selectPlayer1 = id1;

        for (int i = 0; i < pers.size(); i++) {
            Personagens all_persons = pers.get(i);

            if (all_persons.getId() == selectPlayer1 || all_persons.getId() == selectPlayer2) {
                lutadores.add(new Personagens(all_persons.getId(), all_persons.getNome(), all_persons.getNivel(), all_persons.getEnergia(), all_persons.getXp(), all_persons.getForca(), all_persons.getResistencia(), all_persons.getVelocidade(), all_persons.getTipo(), all_persons.getTecnica()));
            }
        }


        int contt = 0;
        for (Personagens jog : lutadores) {

            System.out.println("Jogador " + (contt++) + ": " + jog.getNome());


        }

        Random randomm = new Random();

        //Vida de cada Personagens
        double life_p1 = 2000.0f;
        double life_p2 = 2000.0f;
        //Selecionando os personagens

        int iP1 = findIndexId(selectPlayer1, lutadores);
        int iP2 = findIndexId(selectPlayer2, lutadores);


        Personagens l1 = lutadores.get(iP1);
        Personagens l2;
        l2 = lutadores.get(iP2);

        //Lista de Ataques
        List<Ataques> attqP1 = crud.listaAtq_pela_id(l1.getId(), "arquivo2");
        List<Ataques> attqP2 = crud.listaAtq_pela_id(l2.getId(), "arquivo2");

        float resistenciaAnteriorP1 = (float) l1.getResistencia();
        float resistenciaAnteriorP2 = (float) l2.getResistencia();

        int qtdExpP1 = 0;
        int qtdExpP2 = 0;

        do {
            int indexAttqP1;
            int indexAttqP2;

            boolean defesaAtivaP1 = false;
            boolean defesaAtivaP2 = false;

            System.out.println("-----------------");

            System.out.println("Selecione os ataques");
            System.out.println("Energia disponivel: " + l1.getEnergia());

            int contadorr = 0;
            for (Ataques atq : attqP1) {

                if (atq.getCustoEnergia() <= l1.getEnergia()) {
                    System.out.println(contadorr + ") " + atq.getNomeAtq() + " - Dano:" + atq.getDano() + " - energia: " + atq.getCustoEnergia());

                }


                contadorr++;

            }

            System.out.println((contadorr) + ") Carregar energia");

            if(l1.getEnergia() >= 100.0){
                System.out.println((contadorr + 1) + ") Defesa com energia");

            }



            System.out.println("---------Use o seu ataque:---------");
            indexAttqP1 = scanner.nextInt();

            if (indexAttqP1 == (contadorr)) {

                l1.setEnergia(l1.getEnergia() + 100.0);
                System.out.println(l1.getNome() + " carregou em 100.0");


            } else if (indexAttqP1 == (contadorr + 1)) {

                defesaAtivaP1 = true;


            } else {
                if (attqP1.get(indexAttqP1).getCustoEnergia() <= l1.getEnergia()) {

                    int possibilidadep2 = randomm.nextInt(5);

                    //ele pode se defender usando energia se a energia dor maior que 1 e
                    if (!defesaAtivaP2 && 100.0f <= life_p2 && life_p2 < 1000.0f && l2.getEnergia() > 100.0f && possibilidadep2 > 2 && !attqP1.get(indexAttqP1).getNomeAtq().contains("ominio")) {
                        // Ativa defesa
                        defesaAtivaP2 = true;
                        // converte energia em resistencia adicional
                        // float adicional = l2.getEnergia();
                        int quantidadeTotalAdicional = (int) l2.getEnergia();

                        int quantidadeAdd = randomm.nextInt(quantidadeTotalAdicional) + 10;

                        float vaiSerAddRes = (float) quantidadeAdd;

                        l2.setEnergia(l2.getEnergia() - vaiSerAddRes);

                        l2.setResistencia(l2.getResistencia() + vaiSerAddRes);


                        String defs = l2.getNome() + " se defendeu com concentrando energia contra o ataque de " + l1.getNome() + " resistencia agora: " + l2.getResistencia();
                        escreva(defs);
                        //l2.getEnergia()

                    }
                    //ele pode usar dominio simples se e somente se for em uma expansão de dominio


                    double danoAttq_P1 = (l1.getForca() * attqP1.get(indexAttqP1).getDano()) / l2.getResistencia();

                    String textoAtaque = l1.getNome() + " usou " + attqP1.get(indexAttqP1).getNomeAtq() + " em " + l2.getNome() + " Dano: " + danoAttq_P1;

                    //System.out.println(l1.getNome()+" usou "+attqP1.get(indexAttqP1).getNomeAtq()+" em "+l2.getNome()+" Dano: "+danoAttq_P1);
                    //System.out.println("\n");
                    escreva(textoAtaque);

                    // Setando a energia após o uso do ataque selecionado
                    l1.setEnergia(l1.getEnergia() - attqP1.get(indexAttqP1).getCustoEnergia());

                    // Setando o dano dado pelo ataque
                    life_p2 -= danoAttq_P1;

                    l1.setResistencia(resistenciaAnteriorP1);

                } else {

                    System.out.println("Energia insuficiente");
                }

            }


            if (defesaAtivaP1){



                l1.setEnergia(l1.getEnergia() - 50.0);

                l1.setResistencia(l1.getResistencia() + 100.0);


                String defs = l1.getNome() + " se defendeu com concentrando energia contra o ataque de " + l2.getNome() + " resistencia agora: " + l1.getResistencia();
                escreva(defs);

            }
            //Random rand = new Random();


            //indexAttqP2 = rand.nextInt(attqP2.size());


            int indexSelecAttq = -1;
            double maxDano = -1;


            for (int a = 0; a < attqP2.size(); a++) {


                if (attqP2.get(a).getDano() >= maxDano && attqP2.get(a).getCustoEnergia() <= l2.getEnergia() && qtdExpP2 < 3) {

                    maxDano = attqP2.get(a).getDano();
                    indexSelecAttq = a;

                    if (attqP2.get(a).getNomeAtq().contains("xpan")) {
                        qtdExpP2++;
                    }

                } else if (attqP2.get(a).getDano() >= maxDano && attqP2.get(a).getCustoEnergia() <= l2.getEnergia() && !attqP2.get(a).getNomeAtq().contains("xpan")) {

                    maxDano = attqP2.get(a).getDano();
                    indexSelecAttq = a;

                }


            }

            if (indexSelecAttq != -1) {
                String textoAtque2;


                double danoAttq_P2 = (l2.getForca() * attqP2.get(indexSelecAttq).getDano()) / l1.getResistencia();

                if (defesaAtivaP2) {
                    defesaAtivaP2 = false;
                    l2.setResistencia(resistenciaAnteriorP2);
                }

                textoAtque2 = l2.getNome() + " usou " + attqP2.get(indexSelecAttq).getNomeAtq() + " em " + l1.getNome() + " Dano: " + danoAttq_P2 + "";

                //System.out.println("\n");
                escreva(textoAtque2);

                //System.out.println(l2.getNome()+" usou "+attqP2.get(indexSelecAttq).getNomeAtq()+" em "+l1.getNome()+" Dano: "+danoAttq_P2);

                // Setando a energia após o uso do ataque selecionado
                l2.setEnergia(l2.getEnergia() - attqP2.get(indexSelecAttq).getCustoEnergia());

                // Setando o dano dado pelo ataque
                life_p1 -= danoAttq_P2;

                defesaAtivaP1 = false;


            }

            System.out.println("\n");


            System.out.println("Vida " + l1.getNome() + ": " + life_p1);
            System.out.println("Vida " + l2.getNome() + ": " + life_p2);


        } while (life_p2 > 0 && life_p1 > 0);

        if (life_p2 > life_p1) {
            System.out.println(l2.getNome() + " Venceuuu!");
            return false;


        } else {
            System.out.println(l1.getNome() + " Venceuuu!");
            return true;

        }





    }

}

