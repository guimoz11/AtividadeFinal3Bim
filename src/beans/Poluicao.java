package beans;
/* @author guilherme.vcmoz*/
public class Poluicao {
    private int id;
    private String tipo;
    private int qtde;
    private String localocorrencia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public String getLocalocorrencia() {
        return localocorrencia;
    }

    public void setLocalocorrencia(String localocorrencia) {
        this.localocorrencia = localocorrencia;
    }
    
}
