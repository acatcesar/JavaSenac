package au;



public class Ocorrencia {
private int id_ocorrencia;
private int id_cidadao;
private String dataa;
private String descricao;
private String loc;
private long cidadao;
public int getId_ocorrencia() {
	return id_ocorrencia;
}
public void setId_ocorrencia(int l) {
	this.id_ocorrencia = (int) l;
}
public int getId_cidadao() {
	return id_cidadao;
}
public void setId_cidadao(int id_cidadao) {
	this.id_cidadao = id_cidadao;
}
public String getDataa() {
	return dataa;
}
public void setDataa(String dataa) {
	this.dataa = dataa;
}
public String getDescricao() {
	return descricao;
}
public void setDescricao(String descricao) {
	this.descricao = descricao;
}
public String getLoc() {
	return loc;
}
public void setLoc(String loc) {
	this.loc = loc;
}
public long getCidadao() {
	return cidadao;
}
public void setCidadao(int cidadao) {
	this.cidadao = cidadao;
}
public long getId() {
	return id_ocorrencia;
}

	
}



