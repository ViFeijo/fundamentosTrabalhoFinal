import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        investidor[] inv = new investidor[0];
        startup[] startup = new startup[0];
        pitch[] pitch = new pitch[0];
        int n = 1;
        while (n > 0) {
            System.out.println("Escolha uma das opções: " +
                    "\n1) Cadastrar Investidor" +
                    "\n2) Cadastrar Startup" +
                    "\n3) Adicionar Pitch");
            n = scSwitch();
            switch (n) {
                case 1: {
                    System.out.println("Digite o nome do investidor: ");
                    String nome = scString();
                    System.out.println("Digite o email do investidor: ");
                    String email = scString();
                    while (verEmail(email, inv)) {
                        System.out.println("Email já utilizado, tente novamente: ");
                        email = scString();
                    }
                    inv = novoInv(nome, email, inv);
                    System.out.println("Investidor cadastrado com sucesso!");
                    System.out.println("Investidor " + nome + " tem o ID "+ inv[inv.length-1].getId());
                    break;
                }
                case 2: {
                    System.out.println("Digite o nome da startup: ");
                    String nome = scString();
                    System.out.println("Digite o segmento da startup: ");
                    String seg = scString();
                    System.out.println("Digite a data de fundação da startup: ");
                    String data = scString();
                    System.out.println("Digite o ID do investidor relacionado: ");
                    int id = scIdInv(inv);
                    startup = novaStartup(startup.length, nome, seg, data, inv[id-1], startup);
                    System.out.println("Startup adicionada com sucesso!");
                    System.out.println("Startup " + nome + " tem o ID "+ startup[startup.length-1].getId());
                    break;
                }
                case 3: {
                    System.out.println("Digite o título do pitch: ");
                    String nome = scString();
                    System.out.println("Digite o ID da startup responsável pelo pitch: ");
                    int id = scIdStartup(startup);
                    System.out.println("Digite a nota dada ao pitch no tópico 'inovação': ");
                    double nota1 = scNota();
                    System.out.println("Digite a nota dada ao pitch no tópico 'potencial de mercado': ");
                    double nota2 = scNota();
                    pitch = novoPitch(pitch.length, nome, nota1, nota2, startup[id-1], pitch);
                    System.out.println("Pitch adicionado com sucesso!");
                    System.out.println("Pitch "+ nome + " tem o ID "+ pitch[pitch.length-1].getId());
                }
            }
        }
    }
    // Método de scanner para o switch principal
    public static int scSwitch(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        while (n>10 || n<0){
            System.out.println("Valor inválido");
            n = in.nextInt();
        }
        return n;
    }
    // Método de scanner quando necessárias Strings
    public static String scString(){
        Scanner in = new Scanner(System.in);
        String s = in.next();
        return s;
    }
    // Método verificador de email
    public static boolean verEmail (String email, investidor[] inv){
        for (int i=0;i<inv.length;i++){
            if (inv[i].getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }
    // Método que adiciona investidor
    public static investidor[] novoInv(String nome, String email, investidor[] inv) {
        investidor[] inv2 = new investidor[inv.length + 1];
        inv2[inv.length] = new investidor(inv.length + 1, nome, email);
        for (int i = 0; i < inv.length; i++) {
            inv2[i] = inv[i];
        }
        return inv2;
    }
    // Método scanner para seleção de ID de investidor
    public static int scIdInv (investidor[] inv){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        while (n<0 || n>inv.length){
            System.out.println("Valor inválido");
            n = in.nextInt();
        }
        return n;
    }
    // Método que adiciona startup
    public static startup[] novaStartup(int id, String nome, String setor, String data, investidor inv, startup[] startup) {
        startup[] startup2 = new startup[startup.length + 1];
        startup2[startup.length] = new startup(startup.length + 1, nome, setor, data, inv);
        for (int i = 0; i < startup.length; i++) {
            startup2[i] = startup[i];
        }
        return startup2;
    }
    // Método scanner para seleção de ID de startup
    public static int scIdStartup (startup[] startup){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        while (n<0 || n> startup.length){
            System.out.println("Valor inválido");
            n = in.nextInt();
        }
        return n;
    }
    // Método scanner para notas do pitch
    public static double scNota (){
        Scanner in = new Scanner(System.in);
        double nota = in.nextDouble();
        while (nota<0 || nota>10){
            System.out.println("Valor Inválido");
            nota = in.nextDouble();
        }
        return nota;
    }
    // Método que adiciona pitch
    public static pitch[] novoPitch(int id, String nome, double nota1, double nota2, startup startup, pitch[] pitch) {
        pitch[] pitch2 = new pitch[pitch.length + 1];
        pitch2[pitch.length] = new pitch(pitch.length + 1, nome, nota1, nota2, startup);
        for (int i = 0; i < pitch.length; i++) {
            pitch2[i] = pitch[i];
        }
        return pitch2;
    }
}
