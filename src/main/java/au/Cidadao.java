package au;

public class Cidadao {
	  private int id_cidadao;
	    private String nome;
	    private String numdoc;
	    private String endereco;
	    private String telefone;
	    private String email;

	    public int getid_cidadao() {
	        return id_cidadao;
	    }

	    public void setid_cidadao(int id_cidadao) {
	        this.id_cidadao = id_cidadao;
	    }

	    public String getnome() {
	        return nome;
	    }

	    public void setnome(String nome) {
	        this.nome = nome;
	    }

	    public String getnumdoc() {
	        return numdoc;
	    }

	    public void setnumdoc(String numdoc) {
	        this.numdoc = numdoc;
	    }

	    public String getendereco() {
	        return endereco;
	    }

	    public void setendereco(String endereco) {
	        this.endereco = endereco;
	    }

	    public String gettelefone() {
	        return telefone;
	    }

	    public void settelefone(String telefone) {
	        this.telefone = telefone;
	    }

	    public String getemail() {
	        return email;
	    }

	    public void setemail(String email) {
	        this.email = email;
	    }
}
