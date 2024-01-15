//import crud.Create;
import crud.Crud;
import moldes.Ataques;
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




    public static void main(String[] args) throws IOException {

        Crud crud = new Crud();
        //crud.adicionador("arquivo2","Yuta4",0,200.0,1000.0);
        //crud.adicionador("arquivo10","Yuta5",0,200.0,1000.0);
        //crud.ler("arquivo2");
        //crud.excluirLinha("arquivo2","Getou");
        List<Ataques> todosAtq = crud.listaAtq_pela_id(1,"arquivo2");

        for (Ataques atq: todosAtq){
            System.out.println("NomeAtq: "+atq.getNomeAtq());
            System.out.println("\n");

        }

        /*
        //array para colocar os participantes
        ArrayList<Personagens> personagens = new ArrayList<>();
        ArrayList<Ataques> ataques = new ArrayList<>();

        personagens.add(new Personagens("Getou",0,1000.0,400.0));
        personagens.add(new Personagens("Satoru",0,3000.0,400.0));
        personagens.add(new Personagens("Yuta",0,6000.0,100.0));
        personagens.add(new Personagens("Nanami",1,850.0,700.0));

        ataques.add(new Ataques(1,"Uzumaki",200.5,100.0));
        ataques.add(new Ataques(2,"Roxo",460.0,430.0));
        ataques.add(new Ataques(3,"Rika",600.0,500.0));
        ataques.add(new Ataques(4,"Cocusem",395.2,100.0));


 personagens.add(new Personagens("Getou",0,1000.0,400.0,"Uzumaki",400.0,240.0));
        personagens.add(new Personagens("Satoru",0,3000.0,400.0,"Roxo",460.0,430.0));
        personagens.add(new Personagens("Yuta",0,6000.0,100.0,"Rika",600.0,500.0));
        personagens.add(new Personagens("Nanami",1,850.0,700.0,"Cocusem",395.2,100.0));

*/
        int selec;
       // int tot = 0;


        do {
            List<Personagens> pers = crud.lerDoc("arquivo2");

            System.out.println("ID | Nome | Nivel | Xp");
            for(Personagens nossaLista: pers){


                System.out.println(nossaLista.getId()+" - "+nossaLista.getNome()+" | "+nossaLista.getNivel()+" | "+nossaLista.getXp());



            }

            Scanner scanner = new Scanner(System.in);
            System.out.println("Selecione uma opção:");
            int selec1 = scanner.nextInt();
            int sair;
            selec = selec1;

            switch (selec){
                case 1:
                   // System.out.println("selec 1");

                    System.out.println("Nome do personagem:");
                    String nome = scanner.next();

                    System.out.println("Nivel do personagem:");
                    int nivel = scanner.nextInt();

                    System.out.println("Quantidade de Energia do personagem:");
                    String energia = scanner.next();

                    System.out.println("XP do personagem:");
                    String xp = scanner.next();


                    System.out.println("Nome do ataque do personagem:");
                    String nomeAtq = scanner.next();


                    System.out.println("Dano do Ataque:");
                    String dano = scanner.next();

                    System.out.println("Quantidade energia usada pelo ataque:");
                    String custo = scanner.next();

                    int id = pers.size()+1;

                    crud.adicionador(id,"arquivo2",nome,nivel,Double.parseDouble(energia),Double.parseDouble(xp));

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
                    System.out.println("Luta !!!!!");
                    List<Personagens> players = new ArrayList<>();

                    System.out.println("Selecione Player 1");
                    int player1 = scanner.nextInt();
                    System.out.println("Selecione Player 2");
                    int player2 = scanner.nextInt();

                    //Lista de Ataques
                    List<Ataques> atqP1 = crud.listaAtq_pela_id(player1,"arquivo2");
                    List<Ataques> atqP2 = crud.listaAtq_pela_id(player2,"arquivo2");









                    for(int i = 0; i < pers.size(); i++){

                        Personagens todos_os_persons = pers.get(i);

                        if (todos_os_persons.getId() == player2 || todos_os_persons.getId() == player1){
                            players.add(new Personagens(todos_os_persons.getId(),todos_os_persons.getNome(),todos_os_persons.getNivel(),todos_os_persons.getEnergia(),todos_os_persons.getXp()));

                        }


                    }
                    int cont = 0;
                    for (Personagens jog: players){

                        System.out.println("Jogador "+(cont+1)+": "+jog.getNome());
                        cont++;



                    }

                    //Vida de cada Personagens
                    double vida_p1 = 1200.0;
                    double vida_p2 = 1200.0;
                    //Selecionando os personagens
                    Personagens p1 = players.get(0);
                    Personagens p2 = players.get(1);

                    do {









                   // System.out.println("teste p1 "+p1.getEnergia());
                    //System.out.println("teste p2 "+p2.getEnergia());



                        System.out.println("-------------------------------------------------");
                    //Atque disponiveis sendo usados
                    Random random = new Random();
                    int indexP1 = random.nextInt(atqP1.size());
                    int indexP2 = random.nextInt(atqP2.size());


                    if(atqP1.get(indexP1).getCustoEnergia() <= p1.getEnergia()){
                        //Falando o ataque
                        System.out.println(p1.getNome()+" usou "+atqP1.get(indexP1).getNomeAtq()+" em "+p2.getNome()+" DANO: "+atqP1.get(indexP1).getDano());
                        //Setando a energia após o uso do atque selecionado
                        p1.setEnergia(p1.getEnergia() - atqP1.get(indexP1).getCustoEnergia());
                        //Setando o dano dado pelo ataque
                        vida_p2 -= atqP1.get(indexP1).getDano();

                    }



                    if(atqP2.get(indexP2).getCustoEnergia() <= p2.getEnergia()){
                        //Falando o ataque
                        System.out.println(p2.getNome()+" usou "+atqP2.get(indexP2).getNomeAtq()+" em "+p1.getNome()+" DANO: "+atqP2.get(indexP2).getDano());
                        //Setando a energia após o uso do atque selecionado
                        p2.setEnergia(p2.getEnergia() - atqP2.get(indexP2).getCustoEnergia());
                        //Setando o dano dado pelo ataque
                        vida_p1 -= atqP2.get(indexP2).getDano();

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
                    System.out.println("\n");

                    }else {
                    System.out.println("\n");
                    System.out.println(p1.getNome()+" Venceuu");
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




        /*

        do {


        for(int i = 0; i < personagens.size(); i++){
            Personagens person = personagens.get(i);
            ArrayList<Ataques> ataquesHab = person.getHabilidades();

            for (int j = 0; j < ataques.size(); j++){
                Ataques atq = ataques.get(j);
                //Ataques atqExistentes = ataquesHab.get(j);


                if(atq.getIdU() == person.getId()){
                    person.setHabilidades(atq.getNomeAtq(),atq.getDano(),atq.getCustoEnergia());

                }

            }

            System.out.println("Id: "+person.getId());
            System.out.println("Nome: "+person.getNome());
            for(Ataques at: ataquesHab){
                System.out.println("Nome atq: "+at.getNomeAtq());

            }

            tot++;

        }

            Scanner scanner = new Scanner(System.in);
            System.out.println("selecione opcao");
            int selec1 = scanner.nextInt();
            selec = selec1;


        if(selec == 1){

            System.out.println("Nome do personagem:");
            String nome = scanner.next();
            System.out.println("Nivel do personagem:");
            int nivel = scanner.nextInt();
            System.out.println("Quantidade de Energia do personagem:");
            double energia = scanner.nextDouble();
            System.out.println("XP do personagem:");
            double xp = scanner.nextDouble();


            System.out.println("Nome do ataque do personagem:");
            String nomeAtq = scanner.next();

            int id = personagens.size();
            System.out.println("Dano do Ataque:");
            double dano = scanner.nextDouble();
            System.out.println("Quantidade energia usada pelo ataque:");
            double custo = scanner.nextDouble();


            personagens.add(new Personagens(nome,nivel,energia,xp));

            ataques.add(new Ataques(id,nomeAtq,dano,custo));

            selec = 0;

        }


        }while (selec < 5);



*/





       // Path path = Path.of("D:\\javaIde\\java projetos\\projeto1\\dados");
        //Files.createFile('D://javaIde//java projetos//projeto1//dados');

        //String texto = "Vacalo ";
       // Files.writeString(path, texto);
    }

}
