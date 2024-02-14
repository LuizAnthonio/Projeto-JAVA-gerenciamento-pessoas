package crud;

import moldes.Ataques;
import moldes.Personagens;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Crud {

    public void criar(String nomeArq,String msg) throws IOException{


        String caminhoArquivo = "dados\\"+nomeArq+".txt";
        String conteudo = msg;


        FileWriter escritor = new FileWriter(caminhoArquivo);
        escritor.write(conteudo);
        escritor.close();
        System.out.println("Feito");



    }

    public void adicionador(int id,String nomeArq,String nome, int nivel,double energia, double xp, double forca, double resistencia,double velocidade,int tipo,int tecnica) throws IOException{
            String caminhoArquivo = "dados\\"+nomeArq+".txt";



            FileWriter escritor = new FileWriter(caminhoArquivo, true);
            ///BufferedWriter escritor = new BufferedWriter(arquivoEscrita);

            escritor.write(id+","+nome+","+nivel+","+energia+","+xp+","+forca+","+resistencia+","+velocidade+","+tipo+","+tecnica+"\n");
            //escritor.newLine(); // Adiciona uma nova linha se necessário
            escritor.close();

            System.out.println("Informação adicionada ao arquivo com sucesso!");




    }

    public void adicionadorAtq(int id,String nomeAtq, double dano, double custoEnergia) throws IOException{
            String caminhoArquivo = "dados\\ataques.txt";


            FileWriter escritor = new FileWriter(caminhoArquivo, true);
            ///BufferedWriter escritor = new BufferedWriter(arquivoEscrita);

            escritor.write(id+","+nomeAtq+","+dano+","+custoEnergia+"\n");
            //escritor.newLine(); // Adiciona uma nova linha se necessário
            escritor.close();

            System.out.println("Informação adicionada ao arquivo com sucesso!");


    }






    public void ler(String nomeArq) throws IOException{

        String caminhoArquivo = "dados\\"+nomeArq+".txt";

        BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo));
        String linha;

        while ((linha = leitor.readLine()) != null) {
            System.out.println(linha);
        }

        leitor.close();

        //FileReader escritor = new FileReader(caminhoArquivo);
       // escritor.write(conteudo);
      //  escritor.close();
        System.out.println("Feito");

    }



    public List<Personagens> lerDoc(String nomeArq) throws IOException{

        List<Personagens> personagens = new ArrayList<>();
        List<Ataques> ataques = new ArrayList<>();


        String caminhoArquivo = "dados\\"+nomeArq+".txt";

        BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo));
        BufferedReader leitorAtq = new BufferedReader(new FileReader("dados\\ataques.txt"));
        String linha;
        String linha2;

        while ((linha = leitor.readLine()) != null) {

            //System.out.println(linha);
            String dados[] = linha.split(",");
            personagens.add(new Personagens(Integer.parseInt(dados[0]),dados[1],Integer.parseInt(dados[2]),Double.parseDouble(dados[3]),Double.parseDouble(dados[4]),Double.parseDouble(dados[5]),Double.parseDouble(dados[6]),Double.parseDouble(dados[7]),Integer.parseInt(dados[8]),Integer.parseInt(dados[9])));

        }

        while ((linha2 = leitorAtq.readLine()) != null) {

            //System.out.println(linha2);
            String dadosAtq[] = linha2.split(",");
            ataques.add(new Ataques(Integer.parseInt(dadosAtq[0]),dadosAtq[1],Double.parseDouble(dadosAtq[2]),Double.parseDouble(dadosAtq[3])));

        }

        leitor.close();
        leitorAtq.close();


        for (int i = 0; i < personagens.size(); i++) {
            Personagens person = personagens.get(i);
            List<Ataques> habilidadesPersonagem = person.getHabilidades();
            int idPersonagem = person.getId();

            for (int j = 0; j < ataques.size(); j++) {
                Ataques atq = ataques.get(j);

                if (atq.getIdU() == idPersonagem) {
                    boolean existeAtaque = false;

                    for (Ataques ataqueExistente : habilidadesPersonagem) {
                        if (atq.getNomeAtq().equals(ataqueExistente.getNomeAtq())) {
                            existeAtaque = true;
                            break;
                        }
                    }

                    if (!existeAtaque) {
                        person.setHabilidades(atq.getNomeAtq(),atq.getDano(),atq.getCustoEnergia());
                    }
                }
            }



        }

        return personagens;

    }


    public List<Ataques> listaAtq_pela_id(int idU, String nomeArq) throws IOException{

        List<Personagens> personagens = lerDoc(nomeArq);
        List<Ataques> atqSelec = new ArrayList<>();

        for(int i = 0; i < personagens.size(); i++){

            Personagens person = personagens.get(i);

            for (Ataques atq: person.getHabilidades()){
                if (atq.getIdU() == idU){
                    atqSelec.add(new Ataques(atq.getIdU(),atq.getNomeAtq(),atq.getDano(),atq.getCustoEnergia()));
                }

            }



        }



        return atqSelec;

    }


    public Personagens acharPersonaPorId(int idU) throws IOException{

        List<Personagens> personagens = lerDoc("arquivo2");


        for (int i = 0; i < personagens.size();i++){
            Personagens person = personagens.get(i);
            if (person.getId() == idU){
                return person;
            }
        }

        return null;

    }


    public List<String> listaTecnicas(String nomeArq) throws IOException{

        List<String> tecTudas = new ArrayList<>();


        String caminhoArquivo = "dados\\"+nomeArq+".txt";

        BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo));
        String linha;
        String linha2;

        while ((linha = leitor.readLine()) != null) {

            String dados[] = linha.split(",");
            tecTudas.add(dados[1]);

        }





        return tecTudas;

    }


    public List<String> listarHistoria(String nomeArq,String simb) throws IOException{

        List<String> historia = new ArrayList<>();
        String linha, linha2;
        String caminhhiArquivo = "dados\\"+nomeArq+".txt";

        BufferedReader leitor = new BufferedReader(new FileReader(caminhhiArquivo));
       int i = 0;
        while ((linha = leitor.readLine()) != null){
            String dados[] = linha.split(simb);

           // dados.length;

            for (String dad: dados){
                historia.add(dad);

            }

           //System.out.println(dados[0]);

            //historia = linha.split(simb);



            //System.out.println(dados[i]);
            //i++;



        }



        return historia;

    }





    public int contUsers(String nomeArq) throws IOException{

        String caminhoArquivo = "dados\\"+nomeArq+".txt";
        int cont = 0;

        BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo));
        String linha;

        while ((linha = leitor.readLine()) != null) {
            cont++;
        }

        leitor.close();

        return cont;

    }





    /*
    * ///Mostrar o que tá dentro arquivo e mostra indiviual
    * String caminhoArquivo = "dados\\"+arquivo+".txt";

        FileReader escritor = new FileReader(caminhoArquivo);

        BufferedReader leitor = new BufferedReader(escritor);

        String linha;

        while ((linha = leitor.readLine()) != null) {
            String[] elem = linha.split(",");

            System.out.println("-----------");

            for(String texto: elem){
                System.out.println(texto);

            }

        }

        leitor.close();

    *
    * */

    public void excluirLinha(String arquivo,String nome) throws IOException{

        String caminhoArquivo = "dados\\"+arquivo+".txt";
        String temporario = "dados\\temp.txt";

        File original = new File(caminhoArquivo);
        File temp = new File(temporario);

        BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo));
        BufferedWriter escreve = new BufferedWriter(new FileWriter(temporario));

        String linha;

        while((linha = leitor.readLine()) != null){
            if(!linha.startsWith(nome+",")){
                escreve.write(linha);
                escreve.newLine();
            }
        }

        leitor.close();
        escreve.close();

        original.delete();
        temp.renameTo(original);



    }


    public void editarLinha(String arquivo,int id,int posicao,String novaInfo) throws IOException{

        String caminhoArquivo = "dados\\"+arquivo+".txt";
        String temporario = "dados\\temp.txt";

        File original = new File(caminhoArquivo);
        File temp = new File(temporario);

        BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo));
        BufferedWriter escreve = new BufferedWriter(new FileWriter(temporario));

        String linha;

        while((linha = leitor.readLine()) != null){
            if(!linha.startsWith(id+",")){
                escreve.write(linha);
                escreve.newLine();
            }else{

                String partes[] = linha.split(",");

                partes[posicao] = novaInfo;

                String novaLinha = String.join(",",partes);

                escreve.write(novaLinha);
                escreve.newLine();


            }
        }

        leitor.close();
        escreve.close();

        original.delete();
        temp.renameTo(original);



    }












}
