public class investimento {
    private int id;
    private double valor;
    private double percentualParticipacao;
    private startup startup;

    public investimento(int id, double valor, double percentualParticipacao, startup startup) {
        this.id = id;
        this.valor = valor;
        this.percentualParticipacao = percentualParticipacao;
        this.startup = startup;
    }
}
