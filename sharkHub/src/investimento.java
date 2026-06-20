public class investimento {
    private int id;
    private String titulo;
    private double notaInovacao;
    private double notaMercado;
    private startup startup;

    public investimento(int id, String titulo, double notaInovacao, double notaMercado, startup startup) {
        this.id = id;
        this.titulo = titulo;
        this.notaInovacao = notaInovacao;
        this.notaMercado = notaMercado;
        this.startup = startup;
    }
}
