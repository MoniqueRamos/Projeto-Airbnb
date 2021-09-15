//Classe de Barco que é um tipo de locação, contém um atributo diferente das outras duas classes e seu método get e set.

public class Quarto extends Locacao {
	private int colchoes;

	// Construtor de Quarto
	public Quarto(Usuario locador, String tipoDeLocacao, String cidadeDaLocacao, String comodidades,
			int quantidadeHospedes, int colchoes) {
		super(locador, tipoDeLocacao, cidadeDaLocacao, comodidades, quantidadeHospedes);
		this.colchoes = colchoes;
	}

	// Construtor padrão
	public Quarto() {

	}

	public int getColchoes() {
		return colchoes;
	}

	public void setColchoes(int colchoes) {
		this.colchoes = colchoes;
	}

	// Método que retorna informações do quarto
	public String toString() {
		return "\n Tipo da locação: " + getTipoDeLocacao() + "\n " + "Cidade da locação: " + getCidadeDaLocacao()
				+ "\n " + "Comodidades: " + getComodidades() + "\n " + "Quantidade de hóspedes permitidos: "
				+ getQuantidadeHospedes() + " hóspedes \n " + "Quantidade de Quartos: " + "\n "
				+ "Quantidade de colchoes: " + getColchoes() + " colchões \n ";
	}
}
