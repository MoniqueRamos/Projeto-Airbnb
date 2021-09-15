import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//Classe Anuncio tem seus atributos e os métodos getters e setters de cada atributo

public class Anuncio {
	private Locacao locacao;
	private String titulo;
	private double preco;
	private String comentariosAdicionais;
	private long codigo;
	private Date[] datas;
	List<Date[]> listaDatas = new ArrayList<Date[]>();

	// Construtor
	public Anuncio(Locacao locacao, String titulo, double preco, String comentariosAdicionais, long codigo,
			List<Date[]> listaDatas) {
		this.setLocacao(locacao);
		this.setTitulo(titulo);
		this.setPreco(preco);
		this.setComentariosAdicionais(comentariosAdicionais);
		this.setCodigo(codigo);
		this.setListaDatas(listaDatas);
	}

	// Construtor padrão
	public Anuncio() {
		this.setLocacao(null);
		this.setTitulo("");
		this.setPreco(0);
		this.setComentariosAdicionais("");
		this.setCodigo(0);
	}

	public Locacao getLocacao() {
		return locacao;
	}

	public void setLocacao(Locacao locacao) {
		this.locacao = locacao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getComentariosAdicionais() {
		return comentariosAdicionais;
	}

	public void setComentariosAdicionais(String comentariosAdicionais) {
		this.comentariosAdicionais = comentariosAdicionais;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public List<Date[]> getListaDatas() {
		return listaDatas;
	}

	public void setListaDatas(List<Date[]> listaDatas) {
		this.listaDatas = listaDatas;
	}

	public void setData(Date[] datas) {
		this.listaDatas.add(datas);
		this.datas = datas;
	}

	public Date[] getData() {
		return datas;
	}

	// Método que retorna informações das locações

	public String toString() {
		if (getLocacao() instanceof Barco) {
			Barco barco = (Barco) getLocacao();
			return "\n Tipo da locação: " + getLocacao().getTipoDeLocacao() + "\n " + "Cidade da locação: "
					+ getLocacao().getCidadeDaLocacao() + "\n " + "Comodidades: " + getLocacao().getComodidades()
					+ "\n " + "Quantidade de hóspedes permitidos: " + getLocacao().getQuantidadeHospedes()
					+ " hóspedes \n " + "Valor do passeio: " + barco.getValorPasseio() + "\n Título: " + getTitulo()
					+ "\n Preço: " + getPreco() + "\n Comentários adicionais: " + getComentariosAdicionais()
					+ "\n Código: " + getCodigo();
		}

		if (getLocacao() instanceof Apartamento) {
			Apartamento apartamento = (Apartamento) getLocacao();
			return "\n Tipo da locação: " + getLocacao().getTipoDeLocacao() + "\n " + "Cidade da locação: "
					+ getLocacao().getCidadeDaLocacao() + "\n " + "Comodidades: " + getLocacao().getComodidades()
					+ "\n " + "Quantidade de hóspedes permitidos: " + getLocacao().getQuantidadeHospedes()
					+ " hóspedes \n " + "Quarto: " + apartamento.getQuartos() + " quartos\n" + " Banheiros: "
					+ apartamento.getBanheiros() + " banheiros \n" + " Colchões: " + apartamento.getColchoes()
					+ " colchões" + "\n Título: " + getTitulo() + "\n Preço: " + getPreco()
					+ "\n Comentários adicionais: " + getComentariosAdicionais() + "\n Código: " + getCodigo();
		}

		if (getLocacao() instanceof Quarto) {
			Quarto quarto = (Quarto) getLocacao();
			return "\n Tipo da locação: " + getLocacao().getTipoDeLocacao() + "\n " + "Cidade da locação: "
					+ getLocacao().getCidadeDaLocacao() + "\n " + "Comodidades: " + getLocacao().getComodidades()
					+ "\n " + "Quantidade de hóspedes permitidos: " + getLocacao().getQuantidadeHospedes()
					+ " hóspedes \n " + "Colchões: " + quarto.getColchoes() + " colchões" + "\n Título: " + getTitulo()
					+ "\n Preço: " + getPreco() + "\n Comentários adicionais: " + getComentariosAdicionais()
					+ "\n Código: " + getCodigo();
		}
		return null;
	}

	// Método criado para exibir as datas disponíveis para locação
	public void exibirDatasDisponiveis() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		System.out.println("\n--------------Períodos Disponíveis--------------\n");
		for (int x = 0; x < listaDatas.size(); x++) {
			System.out.printf(" [%d] Período:  %s  -  %s %n%n", x + 1, sdf.format(listaDatas.get(x)[0]),
					sdf.format(listaDatas.get(x)[1]));

		}
	}

}
