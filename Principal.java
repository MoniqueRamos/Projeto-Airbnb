import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//Classe Principal onde existe usu�rios previamente cadastrados e cont�m o "main"

public class Principal {

	public static void main(String[] args) throws ParseException {

		// Lista de usu�rios
		List<Usuario> listaUsuarios = criarUsuariosIniciais();

		// Lista de todos os an�ncios
		List<Anuncio> listaAnuncios = criarAnunciosDasLocacoesIniciais(listaUsuarios);

		// Instanciando bancoDeDados e configurando as listas
		BancoDeDados bancoDeDados = new BancoDeDados();

		bancoDeDados.setListaAnuncios(listaAnuncios);
		bancoDeDados.setListaUsuarios(listaUsuarios);

		// Chamando TelaLogin
		TelaLogin telaLogin = new TelaLogin(bancoDeDados);
		telaLogin.exibirTela();
	}

	// Usu�rios previamente cadastrados; E adicionando na listaUsuarios
	private static List<Usuario> criarUsuariosIniciais() {
		List<Usuario> listaUsuarios;

		listaUsuarios = new ArrayList<Usuario>();
		Usuario usuario1 = new Usuario("Lais", "Cardoso de Medeiros", 12345678910L, "Maresias", "yes",
				"lais@gmail.com");
		listaUsuarios.add(usuario1);

		Usuario usuario2 = new Usuario("Larissa", "dos Santos", 10987654321L, "Salvador", "no", "larissa@gmail.com");
		listaUsuarios.add(usuario2);

		Usuario usuario3 = new Usuario("Joao", "Pereira", 12349785605L, "Cruz das Almas", "almost", "joao@gmail.com");
		listaUsuarios.add(usuario3);

		return listaUsuarios;

	}

	// Primeiros an�ncios criados em que cada usu�rio tem 4 an�ncios de 3 classes
	// diferentes (apartamento, barco e quarto) adicionando-os na
	// listaAnunciosDasLocacoes e apontando para a listaUsuarios
	// Preenchido conforme cada construtor

	private static List<Anuncio> criarAnunciosDasLocacoesIniciais(List<Usuario> listaUsuarios) throws ParseException {
		List<Anuncio> listaAnunciosDasLocacoes;

		listaAnunciosDasLocacoes = new ArrayList<Anuncio>();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Apartamento apartamento;
		Barco barco;
		Quarto quarto;

		Date checkIn = sdf.parse("24/05/2021");
		Date checkOut = sdf.parse("25/05/2021");
		Date[] datas = { checkIn, checkOut };

		List<Date[]> listaDatas;

		// An�ncios do primeiro Usu�rio:

		apartamento = new Apartamento(listaUsuarios.get(0), "Espa�o inteiro", "Cruz das Almas", "Sala de jantar", 5, 1,
				5, 5);

		listaDatas = new ArrayList<Date[]>();
		listaDatas.add(datas);

		Anuncio anuncio1 = new Anuncio(apartamento, "Apartamento bem localizado", 350.00, "Uma bela vista para a pra�a",
				1111, listaDatas);
		listaAnunciosDasLocacoes.add(anuncio1);

		listaUsuarios.get(0).getListaMeusAnuncios().add(anuncio1);

		apartamento = new Apartamento(listaUsuarios.get(0), "Espa�o inteiro", "S�o Paulo", "Sala, varanda, piscina", 10,
				2, 3, 7);

		listaDatas = new ArrayList<Date[]>();
		listaDatas.add(datas);
		Anuncio anuncio2 = new Anuncio(apartamento, "Apartamento espa�oso", 700.00, "Pr�ximo a Av. Paulista", 1211,
				listaDatas);
		listaAnunciosDasLocacoes.add(anuncio2);

		listaUsuarios.get(0).getListaMeusAnuncios().add(anuncio2);

		apartamento = new Apartamento(listaUsuarios.get(0), "Espa�o compartilhado", "Salvador", "Sala espa�osa", 6, 1,
				3, 6);

		listaDatas = new ArrayList<Date[]>();
		listaDatas.add(datas);

		Anuncio anuncio3 = new Anuncio(apartamento, "Apartamento limpo", 250.00, "Pr�ximo a praia da Barra", 1311,
				listaDatas);
		listaAnunciosDasLocacoes.add(anuncio3);

		listaUsuarios.get(0).getListaMeusAnuncios().add(anuncio3);

		apartamento = new Apartamento(listaUsuarios.get(0), "Espa�o compartilhado", "Rio de Janeiro", "Varanda", 8, 2,
				3, 15);

		listaDatas = new ArrayList<Date[]>();
		listaDatas.add(datas);

		Anuncio anuncio4 = new Anuncio(apartamento, "Apartamento bem localizado", 80.00,
				"Localizado em condom�nio pr�ximo a praia", 1411, listaDatas);
		listaAnunciosDasLocacoes.add(anuncio4);

		listaUsuarios.get(0).getListaMeusAnuncios().add(anuncio4);

		// An�ncios do segundo Usu�rio:

		barco = new Barco(listaUsuarios.get(1), "Espa�o inteiro", "Fortaleza", "2 andares", 10, 300.00);

		listaDatas = new ArrayList<Date[]>();
		listaDatas.add(datas);

		Anuncio anuncio5 = new Anuncio(barco, "Lancha de luxo", 1000.00, "Orla de Copacabana", 1511, listaDatas);
		listaAnunciosDasLocacoes.add(anuncio5);

		listaUsuarios.get(1).getListaMeusAnuncios().add(anuncio5);

		barco = new Barco(listaUsuarios.get(1), "Espa�o compartilhado", "Ilha de Itaparica", "Sala de estar", 15,
				50.00);

		listaDatas = new ArrayList<Date[]>();
		listaDatas.add(datas);

		Anuncio anuncio6 = new Anuncio(barco, "Apartamento bem localizado", 80.00,
				"Localizado em condom�nio pr�ximo a praia", 1611, listaDatas);
		listaAnunciosDasLocacoes.add(anuncio6);

		listaUsuarios.get(1).getListaMeusAnuncios().add(anuncio6);

		barco = new Barco(listaUsuarios.get(1), "Espa�o compartilhado", "Morro de S�o Paulo",
				"�rea para festas e 2 andares", 8, 90.00);
		listaDatas = new ArrayList<Date[]>();
		listaDatas.add(datas);

		Anuncio anuncio7 = new Anuncio(barco, "Apartamento bem localizado", 80.00,
				"Localizado em condom�nio pr�ximo a praia", 1711, listaDatas);
		listaAnunciosDasLocacoes.add(anuncio7);

		listaUsuarios.get(1).getListaMeusAnuncios().add(anuncio7);

		barco = new Barco(listaUsuarios.get(1), "Espa�o inteiro", "Barra Grande", "Sala de estar", 7, 250.00);
		listaDatas = new ArrayList<Date[]>();
		listaDatas.add(datas);

		Anuncio anuncio8 = new Anuncio(barco, "Iate", 1500.00, "Espa�o para festas", 1811, listaDatas);
		listaAnunciosDasLocacoes.add(anuncio8);

		listaUsuarios.get(1).getListaMeusAnuncios().add(anuncio8);

		// An�ncios do terceiro usu�rio:

		quarto = new Quarto(listaUsuarios.get(2), "Espa�o inteiro", "Vale do cap�o",
				"Acesso a cozinha, o frigobar e suite", 4, 4);

		listaDatas = new ArrayList<Date[]>();
		listaDatas.add(datas);

		Anuncio anuncio9 = new Anuncio(quarto, "Quarto bem localizado", 30.00, "�timo descanso ap�s as trilhas", 1911,
				listaDatas);
		listaAnunciosDasLocacoes.add(anuncio9);

		listaUsuarios.get(2).getListaMeusAnuncios().add(anuncio9);

		quarto = new Quarto(listaUsuarios.get(2), "Espa�o compartilhado", "Vit�ria", "Acesso a cozinha e banheiro", 4,
				4);

		listaDatas = new ArrayList<Date[]>();
		listaDatas.add(datas);

		Anuncio anuncio10 = new Anuncio(quarto, "Quarto espa�oso", 80.00, "�tima localiza��o", 1912, listaDatas);
		listaAnunciosDasLocacoes.add(anuncio10);

		listaUsuarios.get(2).getListaMeusAnuncios().add(anuncio10);

		quarto = new Quarto(listaUsuarios.get(2), "Espa�o inteiro", "Morro de S�o Paulo", "Acesso a Cozinha", 8, 10);
		listaDatas = new ArrayList<Date[]>();
		listaDatas.add(datas);

		Anuncio anuncio11 = new Anuncio(quarto, "Um morador local oferecendo um quarto aconchegante", 50.00,
				"Anfitri�o guiando os passeios", 1221, listaDatas);
		listaAnunciosDasLocacoes.add(anuncio11);

		listaUsuarios.get(2).getListaMeusAnuncios().add(anuncio11);

		quarto = new Quarto(listaUsuarios.get(2), "Espa�o compartilhado", "Feira de santana",
				"Quarto, banheiro, acesso a cozinha e lavanderia da casa", 3, 3);

		listaDatas = new ArrayList<Date[]>();
		listaDatas.add(datas);
		Anuncio anuncio12 = new Anuncio(quarto, "Quarto para estudantes", 50.00, "Pr�xima a UEFS", 1222, listaDatas);
		listaAnunciosDasLocacoes.add(anuncio12);

		listaUsuarios.get(2).getListaMeusAnuncios().add(anuncio12);

		return listaAnunciosDasLocacoes;

	}
}
