import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        investidor[] inv = new investidor[0];
        startup[] startup = new startup[0];
        pitch[] pitch = new pitch[0];
        investimento[] investimento = new investimento[0];
        valuation[] val = new valuation[0];
        int n = 1;
        while (n > 0) {
            System.out.println("Escolha uma das opções: " +
                    "\n1) Cadastrar Investidor" +
                    "\n2) Cadastrar Startup" +
                    "\n3) Adicionar Pitch" +
                    "\n4) Adicionar Investimento" +
                    "\n9) Exibir Ranking de Startups" +
                    "\n10) Exibir Índice de Unicornização" +
                    "\n0) Sair");
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
                    if (inv.length == 0) {
                        System.out.println("Cadastre um investidor antes de cadastrar uma startup.");
                        break;
                    }
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
                    if (startup.length == 0) {
                        System.out.println("Cadastre uma startup antes de adicionar pitch.");
                        break;
                    }
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
                    break;
                }
                case 4: {
                    if (startup.length == 0) {
                        System.out.println("Cadastre uma startup antes de adicionar investimento.");
                        break;
                    }
                    System.out.println("Digite o valor do investimento: ");
                    double valor = scValor();
                    System.out.println("Digite o percentual participação do investimento: ");
                    double percentual = scPercentual();
                    System.out.println("Digite o ID da startup investida: ");
                    int id = scIdStartup(startup);
                    investimento = novoInvestimento(valor, percentual, startup[id-1], investimento);
                    System.out.println("Investimento adicionado com sucesso!");
                    break;
                }
                case 9: {
                    rankingStartup(startup, pitch, investimento);
                    break;
                }
                case 10: {
                    indiceUnicornizacao(startup, inv, pitch, investimento);
                    break;
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
        while (n<=0 || n>inv.length){
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
        while (n<=0 || n> startup.length){
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
    // Método scanner para valor de dinheiro
    public static double scValor (){
        Scanner in = new Scanner(System.in);
        double valor = in.nextDouble();
        while (valor<0){
            System.out.println("Valor Inválido");
            valor = in.nextDouble();
        }
        return valor;
    }
    // Método scanner para porcentagem
    public static double scPercentual (){
        Scanner in = new Scanner(System.in);
        double percentual = in.nextDouble();
        while (percentual <0 || percentual >100){
            System.out.println("Valor Inválido");
            percentual = in.nextDouble();
        }
        return percentual;
    }
    // Método que adiciona investimento
    public static investimento[] novoInvestimento(double valor, double percentual, startup startup, investimento[] investimento) {
        investimento[] investimento2 = new investimento[investimento.length + 1];
        investimento2[investimento.length] = new investimento(investimento.length + 1, valor, percentual, startup);
        for (int i = 0; i < investimento.length; i++) {
            investimento2[i] = investimento[i];
        }
        return investimento2;
    }
    // Método que calcula total investido em uma startup
    public static double totalStartup(startup startup, investimento[] investimento) {
        double total = 0;
        for (int i = 0; i < investimento.length; i++) {
            if (investimento[i].getStartup().getId() == startup.getId()) {
                total = total + investimento[i].getValor();
            }
        }
        return total;
    }
    // Método que calcula média dos pitches de uma startup
    public static double mediaPitchStartup(startup startup, pitch[] pitch) {
        double total = 0;
        int qtd = 0;
        for (int i = 0; i < pitch.length; i++) {
            if (pitch[i].getStartup().getId() == startup.getId()) {
                total = total + pitch[i].media();
                qtd++;
            }
        }
        if (qtd == 0) {
            return 0;
        }
        return total / qtd;
    }
    // Método que calcula pontuação simples do IPU
    public static double pontuacaoStartup(startup startup, pitch[] pitch, investimento[] investimento) {
        return mediaPitchStartup(startup, pitch) * 20 + totalStartup(startup, investimento) / 1000;
    }
    // Método que mostra classificação pelo IPU
    public static String classificacaoStartup(double pontos) {
        if (pontos <= 100) {
            return "Startup Inicial";
        }
        if (pontos <= 250) {
            return "Startup Promissora";
        }
        if (pontos <= 500) {
            return "Startup em Crescimento";
        }
        return "Potencial Unicórnio";
    }
    // Método que exibe ranking das startups cadastradas
    public static void rankingStartup(startup[] startup, pitch[] pitch, investimento[] investimento) {
        if (startup.length == 0) {
            System.out.println("Nenhuma startup cadastrada.");
            return;
        }
        boolean[] usada = new boolean[startup.length];
        for (int pos = 0; pos < startup.length; pos++) {
            int maior = -1;
            for (int i = 0; i < startup.length; i++) {
                if (!usada[i]) {
                    if (maior == -1 || pontuacaoStartup(startup[i], pitch, investimento) > pontuacaoStartup(startup[maior], pitch, investimento)) {
                        maior = i;
                    }
                }
            }
            usada[maior] = true;
            double pontos = pontuacaoStartup(startup[maior], pitch, investimento);
            System.out.println((pos + 1) + ") " + startup[maior].getNome() + " - " + pontos + " pontos - " + classificacaoStartup(pontos));
        }
    }
    // Método que exibe o índice de potencial de unicornização
    public static void indiceUnicornizacao(startup[] startup, investidor[] inv, pitch[] pitch, investimento[] investimento) {
        if (startup.length == 0) {
            System.out.println("Nenhuma startup cadastrada.");
            return;
        }
        int startupPromissora = 0;
        int startupMaiorInvestimento = 0;
        for (int i = 1; i < startup.length; i++) {
            if (pontuacaoStartup(startup[i], pitch, investimento) > pontuacaoStartup(startup[startupPromissora], pitch, investimento)) {
                startupPromissora = i;
            }
            if (totalStartup(startup[i], investimento) > totalStartup(startup[startupMaiorInvestimento], investimento)) {
                startupMaiorInvestimento = i;
            }
        }
        System.out.println("Startup mais promissora: " + startup[startupPromissora].getNome());
        System.out.println("Startup com maior volume de investimentos: " + startup[startupMaiorInvestimento].getNome());
        investidorMaiorInvestimento(inv, investimento);
        System.out.println("Classificação das startups:");
        for (int i = 0; i < startup.length; i++) {
            double pontos = pontuacaoStartup(startup[i], pitch, investimento);
            System.out.println(startup[i].getNome() + " - " + pontos + " pontos - " + classificacaoStartup(pontos));
        }
    }
    // Método que exibe investidor com maior valor investido
    public static void investidorMaiorInvestimento(investidor[] inv, investimento[] investimento) {
        if (inv.length == 0 || investimento.length == 0) {
            System.out.println("Investidor com maior valor investido: nenhum investimento cadastrado.");
            return;
        }
        int maior = 0;
        double maiorTotal = 0;
        for (int i = 0; i < inv.length; i++) {
            double total = 0;
            for (int j = 0; j < investimento.length; j++) {
                if (investimento[j].getStartup().getInvestidor().getId() == inv[i].getId()) {
                    total = total + investimento[j].getValor();
                }
            }
            if (total > maiorTotal) {
                maiorTotal = total;
                maior = i;
            }
        }
        System.out.println("Investidor com maior valor investido: " + inv[maior].getNome() + " - " + maiorTotal);
    }
}
