import java.util.ArrayList;
import java.util.List;

//Classe de BancoDeDados com três atributos listas com seus getters e setters

public class BancoDeDados {

	private List<Usuario> listaUsuarios;
	private List<Anuncio> listaAnuncios;
	private List<Anuncio> listaVendas = new ArrayList<Anuncio>();

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public List<Anuncio> getListaAnuncios() {
		return listaAnuncios;
	}

	public void setListaAnuncios(List<Anuncio> listaAnuncios) {
		this.listaAnuncios = listaAnuncios;
	}

	public List<Anuncio> getListaVendas() {
		return listaVendas;
	}

	public void setListaVendas(List<Anuncio> listaVendas) {
		this.listaVendas = listaVendas;
	}

	// Método que exibe a taxa de serviço sobre os aluguéis
	public void exibirTaxa() {
		double soma = 0;
		for (int i = 0; i < listaVendas.size(); i++) {
			soma += listaVendas.get(i).getPreco();
		}

		soma = soma * 0.05;

		System.out.printf("A taxa total sobre os alguéis é: R$%.2f%n", soma);
	}
}