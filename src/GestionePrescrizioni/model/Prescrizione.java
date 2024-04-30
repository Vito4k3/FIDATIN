package GestionePrescrizioni.model;


public class Prescrizione {
    protected String nomeDottore;
    protected String diagnosi;
    protected String nomeCliente;
    protected String cognomeCliente;

    public Prescrizione(String nomeDottore, String diagnosi, String nomeCliente, String cognomeCliente) {
        this.nomeDottore = nomeDottore;
        this.diagnosi = diagnosi;
        this.nomeCliente = nomeCliente;
        this.cognomeCliente = cognomeCliente;
    }

    public String getNomeDottore() {
        return nomeDottore;
    }

    public void setNomeDottore(String nomeDottore) {
        this.nomeDottore = nomeDottore;
    }

    public String getDiagnosi() {
        return diagnosi;
    }

    public void setDiagnosi(String diagnosi) {
        this.diagnosi = diagnosi;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCognomeCliente() {
        return cognomeCliente;
    }

    public void setCognomeCliente(String cognomeCliente) {
        this.cognomeCliente = cognomeCliente;
    }

    @Override
    public String toString() {
        return "Prescrizione{" + "nomeDottore=" + nomeDottore + ", diagnosi=" + diagnosi + ", nomeCliente=" + nomeCliente + ", cognomeCliente=" + cognomeCliente + '}';
    }

    
    
    
}
