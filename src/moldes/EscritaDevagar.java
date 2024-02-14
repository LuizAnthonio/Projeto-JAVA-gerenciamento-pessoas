package moldes;

public class EscritaDevagar {

        public static void main(String[] args) {
            String text = "Olá, mundo! Este é um exemplo de efeito no terminal.";
            String[] words = text.split(" ");

            for (String word : words) {
                printWithDelay(word, 200); // Tempo de atraso em milissegundos
            }
        }

        public static void printWithDelay(String word, int delay) {
            for (char c : word.toCharArray()) {
                System.out.print(c);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print(" "); // Adicione um espaço entre as palavras
        }


}
