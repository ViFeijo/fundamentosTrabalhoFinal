import org.w3c.dom.ls.LSOutput;

public class pitch {
    private int id;
    private String titulo;
    private double notaInovacao;
    private double notaMercado;
    private startup startup;

    public pitch(int id, String titulo, double notaInovacao, double notaMercado, startup startup) {
        this.id = id;
        this.titulo = titulo;
        this.notaInovacao = notaInovacao;
        this.notaMercado = notaMercado;
        this.startup = startup;
    }

    public double media (){
        return (notaInovacao+notaMercado)/2;
    }
    public int getId (){ return id; }
}
