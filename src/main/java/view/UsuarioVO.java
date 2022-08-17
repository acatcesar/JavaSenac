package view;
public class UsuarioVO {

	private int codUsuario;
	private String nome;
	private String email;
	private String login;
	private String senha;

	public UsuarioVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsuarioVO(int codUsuario, String nome, String email, String login, String senha) {
		super();
		this.codUsuario = codUsuario;
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
	}

	public int getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
