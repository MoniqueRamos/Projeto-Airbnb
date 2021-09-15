import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

//Classe onde o h�spede ir� acessar o menu do h�spede e vai escolher as op��es dispon�veis

public class TelaLocatario implements Tela {

	private Scanner sc = new Scanner(System.in);
	private TelaUsuario telaUsuario;

	// Construtor
	public TelaLocatario(TelaUsuario telaUsuario) {
		this.telaUsuario = telaUsuario;
	}

	// M�todo exibe o menu do H�spede
	@Override
	public void exibirTela() {
		int opcao = -1;

		do {
			System.out.println("\n\nOl�, Bem-vindo ao Menu do Locat�rio! \n\nDigite a op��o que voc� deseja: \n");
			System.out.println("[1]Visualizar Loca��es Dispon�veis");
			System.out.println("[2]Alugar Loca��o");
			System.out.println("[3]Listar loca��es alugadas por voc�");
			System.out.println("[4]Exibir detalhes de seus alugueis");
			System.out.println("[5]Exibir soma total das taxas sobre os alugu�is em Reais(R$)");
			System.out.println("[6]Voltar ao Menu Inicial");

			try {
				opcao = sc.nextInt();

				switch (opcao) {
				case 1:
					// Exibir todos os an�ncios
					visualizarTodosAnuncios();
					break;

				case 2:
					// Alugar loca��o
					try {
						alugar();
					} catch (ParseException e) {
						System.out.println("Erro na entrada.");
					}
					break;

				case 3:
					// Visualizar seus alugu�is
					visualizarAlugueis();

					break;

				case 4:
					// Visualizar detalhes do aluguel
					exibirDetalhes();
					break;

				case 5:
					// Taxa total Airbnb
					telaUsuario.getTelaLogin().getBancoDeDados().exibirTaxa();
					break;

				case 6:
					// Volta ao Menu Inicial
					telaUsuario.exibirTela();
					break;

				default:
					System.out.println("Op��o inexistente. Tente novamente!");
				}

			} catch (InputMismatchException e) {
				System.out.println("Voc� deve digitar um n�mero!");
				sc.nextLine();
			}

		} while (opcao != 6);
	}

	// M�todo que visualiza a lista de an�ncios existentes

	private void visualizarTodosAnuncios() {
		int contador = 0;
		String resposta = "";
		sc.nextLine();
		do {
			for (int i = contador; i < telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().size(); i++) {
				System.out.println("\n An�ncio " + (i + 1) + ": "
						+ telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(i).toString());
				telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(i).exibirDatasDisponiveis();
				contador++;
				if (contador % 10 == 0) {
					System.out.println("Continuar visualizando os an�ncios? (S/N)");
					resposta = sc.nextLine();
					do {
						if (contador % 10 == 0 && !resposta.equals("S") && !resposta.equals("N")) {
							System.out.println("Digite 'S' ou 'N' ");
							resposta = sc.nextLine();
						}
					} while (!resposta.equals("S") && !resposta.equals("N"));

				}

				if (contador % 10 == 0 && resposta.equals("N")) {
					break;
				}
			}
			break;
		} while (resposta.equals("S"));
	}

	// M�todo que aluga uma loca��o
	public void alugar() throws ParseException {
		long codigo = 0;
		boolean continuar = true;
		do {
			try {
				System.out.println("\nDigite o c�digo da loca��o :");

				codigo = sc.nextLong();
				continuar = false;
			}

			catch (InputMismatchException e) {
				System.out.println("Voc� deve digitar um numero!");
				sc.nextLine();
			}

		} while (continuar == true);

		int verificarDados = 0;
		int verificarLista = 0;
		int variavelDeControle = 0;

		// Compara o c�digo digitado ao c�digo de uma das loca��es
		for (int i = 0; i < telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().size(); i++) {

			if (telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(i).getCodigo() == codigo) {
				verificarDados = 1;
				if (telaUsuario.getTelaLogin().getUsuarioAtual().getEmail().equals(telaUsuario.getTelaLogin()
						.getBancoDeDados().getListaAnuncios().get(i).getLocacao().getLocador().getEmail())) {
					System.out.println("Voc� n�o pode alugar uma Loca��o que � sua!");
					variavelDeControle = 1;
				}

				if (telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(i).listaDatas.size() == 0) {
					System.out.println("\nDatas Indispon�veis");
				}

				else if (variavelDeControle == 0
						&& telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(i).listaDatas
								.size() != 0) {
					telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(i).exibirDatasDisponiveis();

					sc.nextLine();
					System.out.println("\nDeseja alugar em alguma dessas datas?(S/N)");
					String resposta = "";

					do {
						resposta = sc.nextLine();
						if (!resposta.equals("N") && !resposta.equals("S")) {
							System.out.println("Voc� deve digitar (S) ou (N)");

						}
					} while (!resposta.equals("N") && !resposta.equals("S"));

					if (resposta.equals("S")) {

						SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
						Date checkIn = new Date();
						Date checkOut = new Date();
						boolean cont = true;
						do {
							try {

								System.out.println("\nDigite as datas que voc� deseja alugar no Formato dd/MM/yyyy\n ");
								System.out.print("Check In:");
								String entrada = sc.nextLine();
								SymbolException.verificarData(entrada);
								checkIn = sd.parse(entrada);

								cont = false;

							} catch (SymbolException e) {
								System.out.println(e.getMessage());

							}

						} while (cont == true);

						do {
							try {
								System.out.println("\nDigite as datas que voc� deseja alugar no Formato dd/MM/yyyy\n ");
								System.out.print("Check Out:");
								String saida = sc.nextLine();
								SymbolException.verificarData(saida);
								checkOut = sd.parse(saida);

								cont = true;

							} catch (SymbolException e) {
								System.out.println(e.getMessage());

							} catch (ParseException e) {
								System.out.println("Voc� digitou no formato incorreto!");
							}

						} while (cont == false);

						for (int x = 0; x < telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(i)
								.getListaDatas().size(); x++) {

							if (telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(i).getListaDatas()
									.get(x)[0].compareTo(checkIn) == 0
									&& telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(i)
											.getListaDatas().get(x)[1].compareTo(checkOut) == 0) {

								System.out.println("\nLoca��o alugada! Aproveite! \n");

								telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(i).getLocacao()
										.getLocador().getListaMinhasVendas()
										.add(telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(i));

								telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(i).getLocacao()
										.setLocatario(telaUsuario.getTelaLogin().getUsuarioAtual());

								telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(i).getLocacao()
										.getLocatario().getListaMeusAlugueis()
										.add(telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(i));

								telaUsuario.getTelaLogin().getBancoDeDados().getListaVendas()
										.add(telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(i));
								telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(i).getListaDatas()
										.remove(x);

								verificarLista = 1;
							}

						}
						if (verificarLista == 0) {
							System.out.println("Loca��o indispon�vel nesta Data.\n");

						}
					}
				}

			}
		}
		if (verificarDados == 0) {
			System.out.println("N�o existe An�ncio com este c�digo!\nVeja todos os an�ncios abaixo! \n");
			visualizarTodosAnuncios();
		}

	}

	// M�todo que exibe todos os alugu�is
	public void visualizarAlugueis() {
		int contador = 0;
		String resposta = "";
		do {
			for (int i = contador; i < telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAlugueis()
					.size(); i++) {
				System.out.println("\nLoca��o: " + (i + 1) + " "
						+ telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAlugueis().get(i)
						+ "\n Datas Reservadas");
				contador++;
				if (contador % 10 == 0) {
					System.out.println("Continuar visualizando os an�ncios? (S/N)");
					resposta = sc.nextLine();
					do {
						if (contador % 10 == 0 && !resposta.equals("S") && !resposta.equals("N")) {
							System.out.println("Digite 'S' ou 'N' ");
							resposta = sc.nextLine();
						}
					} while (!resposta.equals("S") && !resposta.equals("N"));
					break;
				}

				if (contador % 10 == 0 && resposta.equals("N")) {
					break;
				}
			}
		} while (resposta.equals("S"));
	}

	// M�todo que exibe detalhes de um aluguel
	public void exibirDetalhes() {
		int verificarDados = 0;

		long codigo = 0;
		boolean continuar = true;
		do {
			try {
				System.out.printf("Digite o c�digo da Loca��o que deseja ver detalhes: ");

				codigo = sc.nextLong();
				continuar = false;
			}

			catch (InputMismatchException e) {
				System.out.println("Voc� deve digitar um valor!");
				sc.nextLine();
			}

		} while (continuar == true);

		for (int i = 0; i < telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAlugueis().size(); i++) {
			if (telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAlugueis().get(i).getCodigo() == codigo) {

				System.out.println("Detalhes sobre a loca��o: \n " + "\n Informa��es sobre o anfitri�o: \n "
						+ telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAlugueis().get(i).getLocacao()
								.getLocador()
						+ "\n Informa��es sobre a loca��o: \n "
						+ telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAlugueis().get(i).toString()
						+ "\n Datas Reservadas");

				verificarDados = 1;

			}
		}
		if (verificarDados == 0) {
			System.out.println("Voc� n�o tem alugu�is com esse c�digo! \n");
		}
	}

}