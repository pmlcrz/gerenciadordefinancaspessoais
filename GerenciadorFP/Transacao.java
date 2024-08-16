import java.time.LocalDate;

public class Transacao {
    private String descricao;
    private double valor;
    private LocalDate data;
    private String categoria;

    public Transacao(String descricao, double valor, LocalDate data, String categoria) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return String.format("%s - R$ %.2f - %s - %s", descricao, valor, categoria, data);
    }
}
