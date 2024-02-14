//import crud.Create;
import crud.Crud;
import moldes.Ataques;
import moldes.EscritaDevagar;
import moldes.Personagens;


import java.awt.*;
import java.nio.file.Files;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class App {


    public static void imprimirPalavras(String texto) {
        EscritaDevagar escritaDevagar = new EscritaDevagar();

        String[] palavras = texto.split("\\s+"); // Divide a string em palavras
        int contador = 0;

        for (String palavra : palavras) {
            escritaDevagar.printWithDelay(palavra, 100); // Imprime a palavra com atraso
            contador++;
            if (contador % 10 == 0) {
                escritaDevagar.printWithDelay("\n", 100); // Quebra a linha a cada 10 palavras
            }
        }

        /*
        String[] palavras = texto.split("\\s+"); // Divide a string em palavras
        int contador = 0;
        for (String palavra : palavras) {
            System.out.print(palavra + " ");
            contador++;
            if (contador % 10 == 0) {
                System.out.println(); // Quebra a linha a cada 10 palavras
            }
        }

         */
    }


    public static int findIndexId(int id,List<Personagens> listaPerso){

        int index = 0;

        for (int i = 0; i < listaPerso.size(); i++){
            if (listaPerso.get(i).getId() == id){
                index++;
            }
            break;
        }

        return index;
    }
    public static void acaoFinal(int idP1, int nivelP1, int nivelP2,int tecnicaP1,int tecnicaP2, int tipoP2,List<Ataques> ataque) throws IOException{

        Crud crud = new Crud();
        Scanner scanner = new Scanner(System.in);
        int cont = 0;
        int selecionado;
        int i;

        List<String> tecTodas = crud.listaTecnicas("tecnicas");


        if(tecnicaP1 == 2 && tipoP2 != 1){
            System.out.println("escolha um ataque para copiar!");
            for (Ataques tec: ataque){
                cont++;
                if (!tec.getNomeAtq().contains("Dominio") || !tec.getNomeAtq().contains("dominio")){
                    System.out.println(cont+") "+tec.getNomeAtq()+" - "+tec.getDano()+" - "+tec.getCustoEnergia());

                }

            }

            System.out.println("Não quero nenhuma -> "+(cont+1)+"- sair.");
            selecionado = scanner.nextInt();
            if(selecionado == cont +1){

                System.out.println("Nenhuma tecnica adquirida!");

            }else{

                i = selecionado - 1;

                if(ataque.get(i).getNomeAtq().contains(tecTodas.get(tecnicaP2))){

                    crud.adicionadorAtq(idP1,ataque.get(i).getNomeAtq(),ataque.get(i).getDano(),ataque.get(i).getCustoEnergia());
                }else {
                    String tecnicaNova = tecTodas.get(tecnicaP2)+": "+ataque.get(i).getNomeAtq();


                    crud.adicionadorAtq(idP1,tecnicaNova,ataque.get(i).getDano(),ataque.get(i).getCustoEnergia());

                }

            }







        } else if (tecnicaP1 == 3 && tipoP2 == 1 && nivelP2 >= nivelP1) {



         System.out.println("Gostaria de Absorver esta maldição?\n 0 - Sim \n 1 - Não");
         selecionado = scanner.nextInt();

         String esp;



         if(selecionado == 0){
                for (Ataques tec: ataque){


                       // System.out.println(cont+") "+tec.getNomeAtq()+" - "+tec.getDano()+" - "+tec.getCustoEnergia());
                        if (nivelP2 != 0) {

                            crud.adicionadorAtq(idP1,("Maldição de nivel "+ nivelP2+": "+tec.getNomeAtq()),tec.getDano(),tec.getCustoEnergia());

                        }else{

                            crud.adicionadorAtq(idP1,("Maldição de nivel Especial: "+tec.getNomeAtq()),tec.getDano(),tec.getCustoEnergia());

                    }

                }

         }else {
             System.out.println("Não absorvido!");
         }




        }


    }


    public static void resultado(int id,int nivelP2, int nivelP1,double xp) throws IOException{

            Crud crud = new Crud();

            int diferencaNIvel = nivelP1 - nivelP2;
            double novosPontos;
            double adquiridos;
        if (nivelP1 < nivelP2){


            if (diferencaNIvel <= -1){

                adquiridos = 12.0 * 200.0;

            }else {

                adquiridos = diferencaNIvel * 200.0;

            }

            novosPontos = adquiridos + xp;

            // p2.setXp(novosPontos);

            int novoNivel;

            String pont = Double.toString(novosPontos);
            //add pontos
            crud.editarLinha("arquivo2",id,4,pont);

            //mudar de nivel com base nos pontos
            if(novosPontos >= 2000.0 && nivelP2 == 4){
                novoNivel = nivelP2 - 1;

                crud.editarLinha("arquivo2",id,2,Integer.toString(novoNivel));

                System.out.println("Pontos adquiritos: "+adquiridos);
                System.out.println("Total de pontos: "+novosPontos);
                System.out.println("Parabenss!!");
                System.out.println("Novo Nivel: "+novoNivel);



            }else if (novosPontos >= 6000.0 && nivelP2 == 3){
                novoNivel = nivelP2 - 1;
                crud.editarLinha("arquivo2",id,2,Integer.toString(novoNivel));


                System.out.println("Pontos adquiritos: "+adquiridos);
                System.out.println("Total de pontos: "+novosPontos);
                System.out.println("Parabenss!!");
                System.out.println("Novo Nivel: "+novoNivel);



            }else if (novosPontos >= 12000.0 && nivelP2 == 2){
                novoNivel = nivelP2 - 1;
                crud.editarLinha("arquivo2",id,2,Integer.toString(novoNivel));

                System.out.println("Pontos adquiritos: "+adquiridos);
                System.out.println("Total de pontos: "+novosPontos);
                System.out.println("Parabenss!!");
                System.out.println("Novo Nivel: "+novoNivel);


            }else if (novosPontos >= 30000.0 && nivelP2 == 1){
                novoNivel = nivelP2 - 1;
                crud.editarLinha("arquivo2",id,2,Integer.toString(novoNivel));

                System.out.println("Pontos adquiritos: "+adquiridos);
                System.out.println("Total de pontos: "+novosPontos);
                System.out.println("Parabenss!!");
                System.out.println("Novo Nivel: Especial");


            }else{
                System.out.println("Pontos adquiritos: "+adquiridos);
                System.out.println("Total de pontos: "+novosPontos);

            }




        }else if (nivelP1 == nivelP2) {

            adquiridos = 100.0;
            novosPontos = adquiridos + xp;
            crud.editarLinha("arquivo2",id,4,Double.toString(novosPontos));

            System.out.println("Pontos adquiritos: "+adquiridos);
            System.out.println("Total de pontos: "+novosPontos);


        }else{
            adquiridos = 50.0;
            novosPontos = adquiridos + xp;
            crud.editarLinha("arquivo2",id,4,Double.toString(novosPontos));

            System.out.println("Pontos adquiritos: "+adquiridos);
            System.out.println("Total de pontos: "+novosPontos);


        }

    }

    public static void escreva(String text){
        EscritaDevagar escritaDevagar = new EscritaDevagar();


        String[] words = text.split(" ");
        //words.length;
        int cont = 0;

        for (String word : words) {
            escritaDevagar.printWithDelay(word, 100);// Tempo de atraso em milissegundos
            cont++;
        }

        if (cont == words.length){
            System.out.println("\n");
        }


    }



    public static void main(String[] args) throws IOException {

        Crud crud = new Crud();

        int selec;
       // int tot = 0;


        do {
            List<Personagens> pers = crud.lerDoc("arquivo2");

            System.out.println("ID | Nome | Nivel | Xp");
            for(Personagens nossaLista: pers){

                System.out.println(nossaLista.getId()+" - "+nossaLista.getNome()+" | "+nossaLista.getNivel()+" | "+nossaLista.getXp());

            }

            Scanner scanner = new Scanner(System.in);
            System.out.println("Selecione uma opção:\n 1 - Cadastrar personagem\n 2 - Cadastrar ataque\n 3 - Ver ataques\n 4 - Editar personagem\n 5 - Luta!\n 6 - Sair");
            int selec1 = scanner.nextInt();
            int sair;
            selec = selec1;



            switch (selec){
                case 1:
                   // System.out.println("selec 1");

                    System.out.println("Nome do personagem:");
                    String nome = scanner.next();
                    scanner.nextLine();

                    System.out.println("Nivel do personagem:");
                    int nivel = scanner.nextInt();

                    System.out.println("Quantidade de Energia do personagem:");
                    String energia = scanner.next();

                    System.out.println("XP do personagem:");
                    String xp = scanner.next();

                    System.out.println("Força do personagem:");
                    String forca = scanner.next();

                    System.out.println("Resistência do personagem:");
                    String resistencia = scanner.next();

                    System.out.println("Velocidade do personagem:");
                    String velocidade = scanner.next();


                    System.out.println("Tipo ou classe do personagem:");
                    System.out.println("0 - Xamã\n 1 - Maldição\n 2 - Hibrido");
                    int tipoPerso = scanner.nextInt();

                    int contador = 0;

                    System.out.println("Tipo ou classe do personagem:");
                    for (String tecnicasI: crud.listaTecnicas("tecnicas")){

                        System.out.println(contador+")"+tecnicasI);
                        contador++;
                    }

                    int tecnicaInata = scanner.nextInt();

                    System.out.println("Nome do ataque do personagem:");
                    String nomeAtq = scanner.next();


                    System.out.println("Dano do Ataque:");
                    String dano = scanner.next();

                    System.out.println("Quantidade energia usada pelo ataque:");
                    String custo = scanner.next();

                    int id = pers.size()+1;

                    crud.adicionador(id,"arquivo2",nome,nivel,Double.parseDouble(energia),Double.parseDouble(xp),Double.parseDouble(forca),Double.parseDouble(resistencia),Double.parseDouble(velocidade),tipoPerso,tecnicaInata);

                    crud.adicionadorAtq(id,nomeAtq,Double.parseDouble(dano),Double.parseDouble(custo));

                    selec = 0;


                    break;


                case 2:
                    System.out.println("ID do personagem:");
                    String idSelec = scanner.next();
                    scanner.nextLine();

                    System.out.println("Nome do ataque do personagem:");
                    String nome_Atq = scanner.nextLine();



                    System.out.println("Dano do Ataque:");
                    String danoAtq = scanner.next();

                    System.out.println("Quantidade energia usada pelo ataque:");
                    String custo_energia = scanner.next();

                    crud.adicionadorAtq(Integer.parseInt(idSelec),nome_Atq,Double.parseDouble(danoAtq),Double.parseDouble(custo_energia));
                    System.out.println("Adicionado com sucesso!");
                    selec = 0;
                    break;

                case 3:
                    System.out.println("ID do personagem:");
                    int idUSelec = scanner.nextInt();


                    List<Ataques> todos_Atq = crud.listaAtq_pela_id(idUSelec,"arquivo2");

                    System.out.println("Ataque | Dano | Custo de energia");
                    for (Ataques atq: todos_Atq){
                        System.out.println(atq.getNomeAtq()+" | "+atq.getDano()+" | "+atq.getCustoEnergia());


                    }


                    System.out.println("Deseja sair?");
                    sair = scanner.nextInt();
                    if (sair == 1){
                        selec = 0;
                    }

                    break;

                case 4:

                    System.out.println("ID do personagem:");
                    int idUS = scanner.nextInt();
                    System.out.println("Posição da inforamção:\n 1- nome\n 2 - Nivel\n 3 - Energia\n 4 - Xp\n");
                    int posicao = scanner.nextInt();
                    System.out.println("Mude agora:");
                    String mudar = scanner.next();

                    crud.editarLinha("arquivo2",idUS,posicao,mudar);

                    System.out.println("Deseja sair?");
                    sair = scanner.nextInt();
                    if (sair == 1){
                        selec = 0;
                    }

                    break;

                case 8:

                   //Modo historia

                    Batalha.olha();

                    List<String> historiaMode = crud.listarHistoria("modoHistoria","&");



                    List<String> jogadoresPart1 = new ArrayList<>();

                    for(String jog: historiaMode){
                        String coiso[] = jog.split("@");

                        jogadoresPart1.add(coiso[1]);

                    }



                    for(String test: jogadoresPart1){
                        System.out.println(test);
                    }

                    int modEstoria = 0;


                    do {

                        System.out.println("\n");
                        imprimirPalavras(historiaMode.get(modEstoria).split("@")[0]);
                        System.out.println("\n");



                        String jogadoresFinal[] = jogadoresPart1.get(modEstoria).split(",");

                        if(Batalha.batalha(Integer.parseInt(jogadoresFinal[1]),Integer.parseInt(jogadoresFinal[0]))){
                            modEstoria++;

                            Personagens persoEnco1 = crud.acharPersonaPorId(Integer.parseInt(jogadoresFinal[0]));
                            Personagens persoEnco2 = crud.acharPersonaPorId(Integer.parseInt(jogadoresFinal[1]));

                            resultado(persoEnco1.getId(),persoEnco1.getNivel(),persoEnco2.getNivel(),persoEnco1.getXp());

                        }

                    }while (modEstoria < jogadoresPart1.size());





                    /*

                    for (String osJog: jogadoresPart1){

                        jogadoresPart1.get(modEstoria);

                        String jogadoresPart2[] = osJog.split(",");



                    }
*/



                    //Batalha.batalha(1,2);




                    System.out.println("Deseja sair?");
                    sair = scanner.nextInt();
                    if (sair == 1){
                        selec = 0;
                    }

                    break;

                case 10:
                    System.out.println("saindo...");
                    selec = 5;

                    break;

                case 5:

                    System.out.println("ID do personagem:");
                    int id_persona = scanner.nextInt();

                    List<Personagens> personagemSelec = new ArrayList<>();

                    //Mostra o perfil do personagem
                    for (int i = 0; i < pers.size(); i++){

                        if (pers.get(i).getId() == id_persona){

                            System.out.println("Nome: "+pers.get(i).getNome());
                            System.out.println("Nível: "+pers.get(i).getNivel());
                            System.out.println("Energia Amaldiçoada: "+pers.get(i).getEnergia());
                            System.out.println("Força: "+pers.get(i).getForca());
                            System.out.println("Velocidade: "+pers.get(i).getVelocidade());
                            System.out.println("Resistência: "+pers.get(i).getResistencia());
                            System.out.println("============================================");
                                System.out.println("Nome do Ataque |  Dano do Ataque | Custo de energia do Ataque: ");
                            for (Ataques atq: pers.get(i).getHabilidades()){
                                System.out.println(atq.getNomeAtq()+" - "+atq.getDano()+" - "+atq.getCustoEnergia());

                            }

                            personagemSelec.add(new Personagens(pers.get(i).getId(),pers.get(i).getNome(),pers.get(i).getNivel(),pers.get(i).getEnergia(),pers.get(i).getXp(),pers.get(i).getForca(),pers.get(i).getResistencia(),pers.get(i).getVelocidade(),pers.get(i).getTipo(),pers.get(i).getTecnica()));
                        }

                    }

                    System.out.println("\n");
                    System.out.println("=======================================================");
                    System.out.println("XP disponivel: "+personagemSelec.get(0).getXp());

                    System.out.println("selecione onde quer gastar");
                    System.out.println("1 - Energia Amaldiçoada\n 2 - Força\n 3 - Velocidade\n 4 - Resistência");
                    int opcao_selec = scanner.nextInt();
                    double qtdXp_selec;
                    double qtdXp_nova;
                    double xp_antes;
                    double conversao;

                    switch (opcao_selec){


                        case 1:
                            if (personagemSelec.get(0).getEnergia() <= 1500.0){
                                xp_antes = personagemSelec.get(0).getXp();
                                System.out.println("3pts = 25xp");
                                qtdXp_selec = scanner.nextDouble();
                                qtdXp_nova = xp_antes - qtdXp_selec;
                                if(qtdXp_selec >= 25.0){
                                    conversao = ((qtdXp_selec * 3) / 25.0)+personagemSelec.get(0).getEnergia();
                                    crud.editarLinha("arquivo2",personagemSelec.get(0).getId(),3,Double.toString(conversao));
                                    crud.editarLinha("arquivo2",personagemSelec.get(0).getId(),4,Double.toString(qtdXp_nova));
                                    System.out.println("Energia aumentou para "+conversao+"!");

                                }else {
                                    System.out.println("selecione outra quantidade");
                                }
                            } else if (personagemSelec.get(0).getEnergia() > 1500.1 && personagemSelec.get(0).getEnergia() < 3500.0) {

                                System.out.println("10pts = 57xp");
                                xp_antes = personagemSelec.get(0).getXp();
                                qtdXp_selec = scanner.nextDouble();
                                qtdXp_nova = xp_antes - qtdXp_selec;
                                if(qtdXp_selec >= 57.0){
                                    conversao = ((qtdXp_selec * 10.0) / 57.0)+personagemSelec.get(0).getEnergia();
                                    crud.editarLinha("arquivo2",personagemSelec.get(0).getId(),3,Double.toString(conversao));
                                    crud.editarLinha("arquivo2",personagemSelec.get(0).getId(),4,Double.toString(qtdXp_nova));
                                    System.out.println("Energia aumentou "+conversao+"!");



                                }else {
                                    System.out.println("selecione outra quantidade");
                                }

                            } else if (personagemSelec.get(0).getEnergia() > 3500.0 && personagemSelec.get(0).getEnergia() < 8500.0) {

                                System.out.println("15pts = 132xp");
                                xp_antes = personagemSelec.get(0).getXp();
                                qtdXp_selec = scanner.nextDouble();
                                qtdXp_nova = xp_antes - qtdXp_selec;
                                if(qtdXp_selec >= 132.0){
                                    conversao = ((qtdXp_selec * 15.0) / 132.0)+personagemSelec.get(0).getEnergia();
                                    crud.editarLinha("arquivo2",personagemSelec.get(0).getId(),3,Double.toString(conversao));
                                    crud.editarLinha("arquivo2",personagemSelec.get(0).getId(),4,Double.toString(qtdXp_nova));
                                    System.out.println("Energia aumentou "+conversao+"!");


                                }else {
                                    System.out.println("selecione outra quantidade");
                                }

                            } else if (personagemSelec.get(0).getEnergia() >= 8500.1) {

                                System.out.println("34pts = 200xp");
                                xp_antes = personagemSelec.get(0).getXp();
                                qtdXp_selec = scanner.nextDouble();
                                qtdXp_nova = xp_antes - qtdXp_selec;
                                if(qtdXp_selec >= 200.0){
                                    conversao = ((qtdXp_selec * 34.0) / 200.0)+personagemSelec.get(0).getEnergia();
                                    crud.editarLinha("arquivo2",personagemSelec.get(0).getId(),3,Double.toString(conversao));
                                    crud.editarLinha("arquivo2",personagemSelec.get(0).getId(),4,Double.toString(qtdXp_nova));
                                    System.out.println("Energia aumentou "+conversao+"!");

                                }else {
                                    System.out.println("selecione outra quantidade");
                                }



                            }
                            break;


                        case 2:
                            if (personagemSelec.get(0).getForca() <= 1.0){
                                xp_antes = personagemSelec.get(0).getXp();
                                System.out.println("3pts = 25xp");
                                qtdXp_selec = scanner.nextDouble();
                                qtdXp_nova = xp_antes - qtdXp_selec;
                                if(qtdXp_selec >= 25.0){
                                    conversao = ((qtdXp_selec * 3) / 25.0)+personagemSelec.get(0).getForca();
                                    crud.editarLinha("arquivo2",personagemSelec.get(0).getId(),5,Double.toString(conversao));
                                    crud.editarLinha("arquivo2",personagemSelec.get(0).getId(),4,Double.toString(qtdXp_nova));
                                    System.out.println("Força agora é aumentou "+conversao+"!");

                                }else {
                                    System.out.println("selecione outra quantidade");
                                }
                            } else if (personagemSelec.get(0).getForca() > 32.0 && personagemSelec.get(0).getForca() < 50.0) {

                                System.out.println("10pts = 57xp");
                                xp_antes = personagemSelec.get(0).getXp();
                                qtdXp_selec = scanner.nextDouble();
                                qtdXp_nova = xp_antes - qtdXp_selec;
                                if(qtdXp_selec >= 57.0){
                                    conversao = ((qtdXp_selec * 10.0) / 57.0)+personagemSelec.get(0).getForca();
                                    crud.editarLinha("arquivo2",personagemSelec.get(0).getId(),5,Double.toString(conversao));
                                    crud.editarLinha("arquivo2",personagemSelec.get(0).getId(),4,Double.toString(qtdXp_nova));
                                    System.out.println("Força agora é aumentou "+conversao+"!");



                                }else {
                                    System.out.println("selecione outra quantidade");
                                }

                            } else if (personagemSelec.get(0).getForca() >= 50.0 && personagemSelec.get(0).getForca() < 70.0) {

                                System.out.println("15pts = 132xp");
                                xp_antes = personagemSelec.get(0).getXp();
                                qtdXp_selec = scanner.nextDouble();
                                qtdXp_nova = xp_antes - qtdXp_selec;
                                if(qtdXp_selec >= 132.0){
                                    conversao = ((qtdXp_selec * 15.0) / 132.0)+personagemSelec.get(0).getForca();
                                    crud.editarLinha("arquivo2",personagemSelec.get(0).getId(),5,Double.toString(conversao));
                                    crud.editarLinha("arquivo2",personagemSelec.get(0).getId(),4,Double.toString(qtdXp_nova));
                                    System.out.println("Força agora é aumentou "+conversao+"!");


                                }else {
                                    System.out.println("selecione outra quantidade");
                                }

                            } else if (personagemSelec.get(0).getForca() >= 70.0) {

                                System.out.println("34pts = 200xp");
                                xp_antes = personagemSelec.get(0).getXp();
                                qtdXp_selec = scanner.nextDouble();
                                qtdXp_nova = xp_antes - qtdXp_selec;
                                if(qtdXp_selec >= 200.0){
                                    conversao = ((qtdXp_selec * 34.0) / 200.0)+personagemSelec.get(0).getForca();
                                    crud.editarLinha("arquivo2",personagemSelec.get(0).getId(),5,Double.toString(conversao));
                                    crud.editarLinha("arquivo2",personagemSelec.get(0).getId(),4,Double.toString(qtdXp_nova));
                                    System.out.println("Força agora é aumentou "+conversao+"!");

                                }else {
                                    System.out.println("selecione outra quantidade");
                                }



                            }
                            break;


                        case 3:
                            if (personagemSelec.get(0).getVelocidade() >= 1.0){
                                xp_antes = personagemSelec.get(0).getXp();
                                System.out.println("3pts = 25xp");
                                qtdXp_selec = scanner.nextDouble();
                                qtdXp_nova = xp_antes - qtdXp_selec;
                                if(qtdXp_selec >= 25.0){
                                    conversao = ((qtdXp_selec * 3) / 25.0)+personagemSelec.get(0).getVelocidade();
                                    crud.editarLinha("arquivo2",personagemSelec.get(0).getId(),7,Double.toString(conversao));
                                    crud.editarLinha("arquivo2",personagemSelec.get(0).getId(),4,Double.toString(qtdXp_nova));
                                    System.out.println("Energia aumentou "+conversao+"!");

                                }else {
                                    System.out.println("selecione outra quantidade");
                                }
                            } else if (personagemSelec.get(0).getVelocidade() > 20.0 && personagemSelec.get(0).getVelocidade() < 40.0) {

                                System.out.println("10pts = 57xp");
                                xp_antes = personagemSelec.get(0).getXp();
                                qtdXp_selec = scanner.nextDouble();
                                qtdXp_nova = xp_antes - qtdXp_selec;
                                if(qtdXp_selec >= 57.0){
                                    conversao = ((qtdXp_selec * 10.0) / 57.0)+personagemSelec.get(0).getVelocidade();
                                    crud.editarLinha("arquivo2",personagemSelec.get(0).getId(),7,Double.toString(conversao));
                                    crud.editarLinha("arquivo2",personagemSelec.get(0).getId(),4,Double.toString(qtdXp_nova));
                                    System.out.println("Velocidade aumentou "+conversao+"!");



                                }else {
                                    System.out.println("selecione outra quantidade");
                                }

                            } else if (personagemSelec.get(0).getVelocidade() > 56.0 && personagemSelec.get(0).getVelocidade() < 85.0) {

                                System.out.println("15pts = 132xp");
                                xp_antes = personagemSelec.get(0).getXp();
                                qtdXp_selec = scanner.nextDouble();
                                qtdXp_nova = xp_antes - qtdXp_selec;
                                if(qtdXp_selec >= 132.0){
                                    conversao = ((qtdXp_selec * 15.0) / 132.0)+personagemSelec.get(0).getVelocidade();
                                    crud.editarLinha("arquivo2",personagemSelec.get(0).getId(),7,Double.toString(conversao));
                                    crud.editarLinha("arquivo2",personagemSelec.get(0).getId(),4,Double.toString(qtdXp_nova));
                                    System.out.println("Energia aumentou "+conversao+"!");


                                }else {
                                    System.out.println("selecione outra quantidade");
                                }

                            } else if (personagemSelec.get(0).getVelocidade() >= 85.1) {

                                System.out.println("34pts = 200xp");
                                xp_antes = personagemSelec.get(0).getXp();
                                qtdXp_selec = scanner.nextDouble();
                                qtdXp_nova = xp_antes - qtdXp_selec;
                                if(qtdXp_selec >= 200.0){
                                    conversao = ((qtdXp_selec * 34.0) / 200.0)+personagemSelec.get(0).getVelocidade();
                                    crud.editarLinha("arquivo2",personagemSelec.get(0).getId(),7,Double.toString(conversao));
                                    crud.editarLinha("arquivo2",personagemSelec.get(0).getId(),4,Double.toString(qtdXp_nova));
                                    System.out.println("Energia aumentou "+conversao+"!");

                                }else {
                                    System.out.println("selecione outra quantidade");
                                }



                            }
                            break;


                        case 4:
                            if (personagemSelec.get(0).getResistencia() >= 1.0){
                                xp_antes = personagemSelec.get(0).getXp();
                                System.out.println("3pts = 25xp");
                                qtdXp_selec = scanner.nextDouble();
                                qtdXp_nova = xp_antes - qtdXp_selec;
                                if(qtdXp_selec >= 25.0){
                                    conversao = ((qtdXp_selec * 3) / 25.0)+personagemSelec.get(0).getResistencia();
                                    crud.editarLinha("arquivo2",personagemSelec.get(0).getId(),6,Double.toString(conversao));
                                    crud.editarLinha("arquivo2",personagemSelec.get(0).getId(),4,Double.toString(qtdXp_nova));
                                    System.out.println("Energia aumentou "+conversao+"!");

                                }else {
                                    System.out.println("selecione outra quantidade");
                                }
                            } else if (personagemSelec.get(0).getResistencia() > 20.0 && personagemSelec.get(0).getResistencia() < 40.0) {

                                System.out.println("10pts = 57xp");
                                xp_antes = personagemSelec.get(0).getXp();
                                qtdXp_selec = scanner.nextDouble();
                                qtdXp_nova = xp_antes - qtdXp_selec;
                                if(qtdXp_selec >= 57.0){
                                    conversao = ((qtdXp_selec * 10.0) / 57.0)+personagemSelec.get(0).getResistencia();
                                    crud.editarLinha("arquivo2",personagemSelec.get(0).getId(),6,Double.toString(conversao));
                                    crud.editarLinha("arquivo2",personagemSelec.get(0).getId(),4,Double.toString(qtdXp_nova));
                                    System.out.println("Resistencia aumentou "+conversao+"!");



                                }else {
                                    System.out.println("selecione outra quantidade");
                                }

                            } else if (personagemSelec.get(0).getEnergia() > 40.0 && personagemSelec.get(0).getEnergia() < 80.0) {

                                System.out.println("15pts = 132xp");
                                xp_antes = personagemSelec.get(0).getXp();
                                qtdXp_selec = scanner.nextDouble();
                                qtdXp_nova = xp_antes - qtdXp_selec;
                                if(qtdXp_selec >= 132.0){
                                    conversao = ((qtdXp_selec * 15.0) / 132.0)+personagemSelec.get(0).getResistencia();
                                    crud.editarLinha("arquivo2",personagemSelec.get(0).getId(),6,Double.toString(conversao));
                                    crud.editarLinha("arquivo2",personagemSelec.get(0).getId(),4,Double.toString(qtdXp_nova));
                                    System.out.println("Energia aumentou "+conversao+"!");


                                }else {
                                    System.out.println("selecione outra quantidade");
                                }

                            } else if (personagemSelec.get(0).getResistencia() >= 80.0) {

                                System.out.println("34pts = 200xp");
                                xp_antes = personagemSelec.get(0).getXp();
                                qtdXp_selec = scanner.nextDouble();
                                qtdXp_nova = xp_antes - qtdXp_selec;
                                if(qtdXp_selec >= 200.0){
                                    conversao = ((qtdXp_selec * 34.0) / 200.0)+personagemSelec.get(0).getResistencia();
                                    crud.editarLinha("arquivo2",personagemSelec.get(0).getId(),6,Double.toString(conversao));
                                    crud.editarLinha("arquivo2",personagemSelec.get(0).getId(),4,Double.toString(qtdXp_nova));
                                    System.out.println("Energia aumentou "+conversao+"!");

                                }else {
                                    System.out.println("selecione outra quantidade");
                                }



                            }
                            break;


                        case 5:
                            System.out.println("Voltando");
                            break;


                    }





                    System.out.println("Deseja sair?");
                    sair = scanner.nextInt();
                    if (sair == 1){
                        selec = 0;
                    }

                    //System.out.println("Posição da inforamção:\n 1- nome\n 2 - Nivel\n 3 - Energia\n 4 - Xp\n");
                    //int posicao = scanner.nextInt();
                    //System.out.println("Mude agora:");
                    //String mudar = scanner.next();





                    break;

                case 7:



                    System.out.println("Batalha");


                    List<Personagens> lutadores = new ArrayList<>();
                    System.out.println("Selecione Player 1");
                    int selectPlayer2 = scanner.nextInt();
                    System.out.println("Selecione Player 2");
                    int selectPlayer1 = scanner.nextInt();

                    for(int i = 0; i < pers.size(); i++){
                        Personagens all_persons = pers.get(i);

                        if (all_persons.getId() == selectPlayer1 || all_persons.getId() == selectPlayer2){
                            lutadores.add(new Personagens(all_persons.getId(),all_persons.getNome(),all_persons.getNivel(),all_persons.getEnergia(),all_persons.getXp(),all_persons.getForca(),all_persons.getResistencia(),all_persons.getVelocidade(),all_persons.getTipo(),all_persons.getTecnica()));
                        }
                    }


                    int contt = 0;
                    for (Personagens jog: lutadores){

                        System.out.println("Jogador "+(contt++)+": "+jog.getNome());




                    }

                    Random randomm = new Random();

                    //Vida de cada Personagens
                    double life_p1 = 2000.0f;
                    double life_p2 = 2000.0f;
                    //Selecionando os personagens

                    int iP1 = findIndexId(selectPlayer1,lutadores);
                    int iP2 = findIndexId(selectPlayer2,lutadores);


                    Personagens l1 = lutadores.get(iP1);
                    Personagens l2;
                    l2 = lutadores.get(iP2);

                    //Lista de Ataques
                    List<Ataques> attqP1 = crud.listaAtq_pela_id(l1.getId(),"arquivo2");
                    List<Ataques> attqP2 = crud.listaAtq_pela_id(l2.getId(),"arquivo2");

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
                        System.out.println("Energia disponivel: "+l1.getEnergia());

                        int contadorr = 0;
                        for (Ataques atq: attqP1){

                            if (atq.getCustoEnergia() <= l1.getEnergia()){
                                System.out.println(contadorr+") "+atq.getNomeAtq()+" - Dano:"+atq.getDano()+" - energia: "+atq.getCustoEnergia());

                            }




                            contadorr++;

                        }

                        System.out.println((contadorr)+") Carregar energia");


                        System.out.println("---------Use o seu ataque:---------");
                        indexAttqP1 = scanner.nextInt();

                        if (indexAttqP1 == (contadorr)){

                                l1.setEnergia(l1.getEnergia() + 20.0);
                                System.out.println(l1.getNome()+" carregou em 20.0");


                        }else {
                            if (attqP1.get(indexAttqP1).getCustoEnergia() <= l1.getEnergia()){

                                int possibilidadep2 = randomm.nextInt(5);

                                //ele pode se defender usando energia se a energia dor maior que 1 e
                                if(!defesaAtivaP2 && 100.0f <= life_p2 && life_p2  < 1000.0f && l2.getEnergia() > 100.0f && possibilidadep2 > 2 && !attqP1.get(indexAttqP1).getNomeAtq().contains("ominio")){
                                    // Ativa defesa
                                    defesaAtivaP2 = true;
                                    // converte energia em resistencia adicional
                                   // float adicional = l2.getEnergia();
                                    int quantidadeTotalAdicional = (int) l2.getEnergia();

                                    int quantidadeAdd = randomm.nextInt(quantidadeTotalAdicional) + 10;

                                    float vaiSerAddRes = (float) quantidadeAdd;

                                    l2.setEnergia( l2.getEnergia() - vaiSerAddRes);

                                    l2.setResistencia(l2.getResistencia() + vaiSerAddRes);


                                    String defs = l2.getNome()+" se defendeu com concentrando energia contra o ataque de "+l1.getNome()+" resistencia agora: "+l2.getResistencia();
                                    escreva(defs);
                                    //l2.getEnergia()

                                }
                                //ele pode usar dominio simples se e somente se for em uma expansão de dominio



                                double danoAttq_P1 = (l1.getForca() * attqP1.get(indexAttqP1).getDano()) / l2.getResistencia();

                                String textoAtaque = l1.getNome()+" usou "+attqP1.get(indexAttqP1).getNomeAtq()+" em "+l2.getNome()+" Dano: "+danoAttq_P1;

                                //System.out.println(l1.getNome()+" usou "+attqP1.get(indexAttqP1).getNomeAtq()+" em "+l2.getNome()+" Dano: "+danoAttq_P1);
                                //System.out.println("\n");
                                escreva(textoAtaque);

                                // Setando a energia após o uso do ataque selecionado
                                l1.setEnergia(l1.getEnergia() - attqP1.get(indexAttqP1).getCustoEnergia());

                                // Setando o dano dado pelo ataque
                                life_p2 -= danoAttq_P1;


                            }else{

                                System.out.println("Energia insuficiente");
                            }

                        }



                        //Random rand = new Random();


                        //indexAttqP2 = rand.nextInt(attqP2.size());


                        int indexSelecAttq = -1;
                        double maxDano = -1;



                        for(int a = 0; a < attqP2.size(); a++){


                               if (attqP2.get(a).getDano() >= maxDano && attqP2.get(a).getCustoEnergia() <= l2.getEnergia() && qtdExpP2 < 3){

                                   maxDano = attqP2.get(a).getDano();
                                   indexSelecAttq = a;

                                   if(attqP2.get(a).getNomeAtq().contains("xpan")){
                                       qtdExpP2++;
                                   }

                               }else  if (attqP2.get(a).getDano() >= maxDano && attqP2.get(a).getCustoEnergia() <= l2.getEnergia() && !attqP2.get(a).getNomeAtq().contains("xpan")){

                                   maxDano = attqP2.get(a).getDano();
                                   indexSelecAttq = a;

                               }


                        }

                        if (indexSelecAttq != -1){
                            String textoAtque2;



                            double danoAttq_P2 = (l2.getForca() * attqP2.get(indexSelecAttq).getDano()) / l1.getResistencia();

                            if (defesaAtivaP2){
                                defesaAtivaP2 = false;
                                l2.setResistencia(resistenciaAnteriorP2);
                            }

                            textoAtque2 = l2.getNome()+" usou "+attqP2.get(indexSelecAttq).getNomeAtq()+" em "+l1.getNome()+" Dano: "+danoAttq_P2+"";

                            //System.out.println("\n");
                            escreva(textoAtque2);

                            //System.out.println(l2.getNome()+" usou "+attqP2.get(indexSelecAttq).getNomeAtq()+" em "+l1.getNome()+" Dano: "+danoAttq_P2);

                            // Setando a energia após o uso do ataque selecionado
                            l2.setEnergia(l2.getEnergia() - attqP2.get(indexSelecAttq).getCustoEnergia());

                            // Setando o dano dado pelo ataque
                            life_p1 -= danoAttq_P2;

                        }

                        System.out.println("\n");


                        System.out.println("Vida "+l1.getNome()+": "+life_p1);
                        System.out.println("Vida "+l2.getNome()+": "+life_p2);











                    }while (life_p2 > 0 && life_p1 > 0);

                    if (life_p2 > life_p1){
                        System.out.println(l2.getNome()+" Venceuuu!");

                    }else {
                        System.out.println(l1.getNome()+" Venceuuu!");

                    }


                    System.out.println("Deseja sair?");
                    sair = scanner.nextInt();
                    if (sair == 1){
                        selec = 0;
                    }




                    break;

                case 6:
                    System.out.println("Luta !!!!!");
                    List<Personagens> players = new ArrayList<>();

                    System.out.println("Selecione Player 1");
                    int player1 = scanner.nextInt();
                    System.out.println("Selecione Player 2");
                    int player2 = scanner.nextInt();




                    for(int i = 0; i < pers.size(); i++){

                        Personagens todos_os_persons = pers.get(i);

                        if (todos_os_persons.getId() == player2 || todos_os_persons.getId() == player1){
                            players.add(new Personagens(todos_os_persons.getId(),todos_os_persons.getNome(),todos_os_persons.getNivel(),todos_os_persons.getEnergia(),todos_os_persons.getXp(),todos_os_persons.getForca(),todos_os_persons.getResistencia(),todos_os_persons.getVelocidade(),todos_os_persons.getTipo(),todos_os_persons.getTecnica()));

                        }



                    }



                    int cont = 0;
                    for (Personagens jog: players){

                        System.out.println("Jogador "+(cont+1)+": "+jog.getNome());
                        cont++;



                    }

                    //Vida de cada Personagens
                    double vida_p1 = 2000.0;
                    double vida_p2 = 2000.0;
                    //Selecionando os personagens

                    int indexIdP1 = findIndexId(player1,players);
                    int indexIdP2 = findIndexId(player2,players);


                    Personagens p1 = players.get(indexIdP1);
                    Personagens p2 = players.get(indexIdP2);

                    //Lista de Ataques
                    List<Ataques> atqP1 = crud.listaAtq_pela_id(p1.getId(),"arquivo2");
                    List<Ataques> atqP2 = crud.listaAtq_pela_id(p2.getId(),"arquivo2");


                    do {




                    System.out.println("-------------------------------------------------");
                    //Atque disponiveis sendo usados
                    Random random = new Random();
                    int indexP1 = random.nextInt(atqP1.size());
                    int indexP2 = random.nextInt(atqP2.size());






                        if(atqP1.get(indexP1).getCustoEnergia() <= p1.getEnergia()){
                            // Ataque e resistência e velocidade

                            double danoRecebidoP2 = (p1.getForca() * atqP1.get(indexP1).getDano()) / p2.getResistencia();

                            // Falando o ataque
                            System.out.println(p1.getNome() + " usou " + atqP1.get(indexP1).getNomeAtq() + " em " + p2.getNome() + " DANO: " + danoRecebidoP2);

                            // Setando a energia após o uso do ataque selecionado
                            p1.setEnergia(p1.getEnergia() - atqP1.get(indexP1).getCustoEnergia());

                            // Setando o dano dado pelo ataque
                            vida_p2 -= danoRecebidoP2;
                        }

// ...

                        if(atqP2.get(indexP2).getCustoEnergia() <= p2.getEnergia()){
                            // Ataque e resistência e velocidade
                            double danoRecebidoP1 = (p2.getForca() * atqP2.get(indexP2).getDano()) / p1.getResistencia();

                            // Falando o ataque
                            System.out.println(p2.getNome() + " usou " + atqP2.get(indexP2).getNomeAtq() + " em " + p1.getNome() + " DANO: " + danoRecebidoP1);

                            // Setando a energia após o uso do ataque selecionado
                            p2.setEnergia(p2.getEnergia() - atqP2.get(indexP2).getCustoEnergia());

                            // Setando o dano dado pelo ataque
                            vida_p1 -= danoRecebidoP1;
                        }



                   // System.out.println("teste p1 "+p1.getEnergia());
                   // System.out.println("teste p2 "+p2.getEnergia());

                    System.out.println("Vida "+p1.getNome()+": "+vida_p1);
                    System.out.println("Vida "+p2.getNome()+": "+vida_p2);



                   // System.out.println("teste atqs p1 "+atqP1.size());

                    }while(vida_p1 > 0 && vida_p2 > 0);

                    if (vida_p2 > vida_p1){



                        System.out.println("\n");
                        System.out.println(p2.getNome()+" Venceuu");
                        resultado(p2.getId(),p2.getNivel(),p1.getNivel(),p2.getXp());
                        acaoFinal(p2.getId(), p2.getNivel(), p1.getNivel(),p2.getTecnica(),p1.getTecnica(), p1.getTipo(),atqP1);
                        System.out.println("\n");


                    }else {
                        System.out.println("\n");
                        System.out.println(p1.getNome()+" Venceuu");
                        resultado(p1.getId(),p1.getNivel(),p2.getNivel(),p1.getXp());
                        acaoFinal(p1.getId(), p1.getNivel(), p2.getNivel(),p1.getTecnica(),p2.getTecnica(), p2.getTipo(),atqP2);
                        System.out.println("\n");

                    }









                    System.out.println("Deseja sair?");
                    sair = scanner.nextInt();
                    if (sair == 1){
                        selec = 0;
                    }

                    break;





                default:
                    System.out.println("pia só");

            }


        } while (selec < 5);



    }

}
