package br.com.matheushajer.controleorcamento.entities;

public enum Categoria {

	ALIMENTAÇÃO("Alimentação"), SAÚDE("Saúde"), MORADIA("Moradia"), TRANSPORTE("Transporte"), EDUCAÇÃO("Educacao"),
	LAZER("Lazer"), IMPREVISTOS("Imprevistos"), OUTRAS("Outras");

	private final String descricao;

	Categoria(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

}
