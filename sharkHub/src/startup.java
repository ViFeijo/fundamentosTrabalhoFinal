import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class startup {
    private int id;
    private String nome;
    private String segmento;
    private String dataFundacao;
    private investidor investidor;

    public startup(int id, String nome, String segmento, String dataFundacao, investidor investidor) {
        this.id = id;
        this.nome = nome;
        this.segmento = segmento;
        this.dataFundacao = dataFundacao;
        this.investidor = investidor;
    }
    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getSegmento() {
        return segmento;
    }
    public String getDataFundacao() {
        return dataFundacao;
    }
    public investidor getInvestidor() {
        return investidor;
    }
}
