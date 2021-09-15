import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;

//Classe TelaLocador onde o anfitri�o ir� acessar o menu do locador e vai escolher as op��es dispon�veis

public class TelaLocador implements Tela {

	private Scanner sc = new Scanner(System.in);
	private TelaUsuario telaUsuario;

	// Construtor
	public TelaLocador(TelaUsuario telaUsuario) {
		this.telaUsuario = telaUsuario;
	}

	// M�todo exibe o menu do Locador
	public void exibirTela() {
		int opcao = -1;

		do {
			System.out.println("\nOl� Bem vindo(a) ao Menu do Anfitri�o\n Digite a op��o que voc� deseja: \n");
			System.out.println("[1]Anunciar uma loca��o");
			System.out.println("[2]Visualizar todos os seus an�ncios");
			System.out.println("[3]Excluir an�ncio");
			System.out.println("[4]Visualizar suas Loca��es alugadas");
			System.out.println("[5]Editar an�ncio");
			System.out.println("[6]Voltar ao Menu Inicial");

			try {
				opcao = sc.nextInt();

				switch (opcao) {
				case 1:
					int tipo = 0;
					do {
						try {
							System.out.println("Digite a loca��o desejada: \n");
							System.out.println("[1]Apartamento");
							System.out.println("[2]Barco");
							System.out.println("[3]Quarto");
							System.out.println("[4]Voltar para o Menu do anfitri�o");

							tipo = sc.nextInt();
						} catch (InputMismatchException e) {
							System.out.println("\nVoc� deve digitar uma das op��es (Digite um n�mero!)\n\n");
							sc.nextLine();
						}

						try {
							switch (tipo) {
							case 1:
								// Anunciar um apartamento
								anunciar(new Apartamento());
								break;

							case 2:
								// Anunciar um Barco
								anunciar(new Barco());
								break;

							case 3:
								// Anunciar um Quarto
								anunciar(new Quarto());
								break;

							case 4:
								System.out.println("Obrigado por anunciar aqui!");
								break;

							default:
								System.out.println("Op��o inexistente, tente novamente!");
							}
						} catch (InputMismatchException | ParseException e) {
							System.out.println("\nVoc� deve digitar uma das op��es (Digite um n�mero!)\n\n");
							sc.nextLine();
						}

					} while (tipo != 4);
					break;
				case 2:
					// M�todo para visualizar an�ncios do locador
					visualizarAnuncios();
					break;
				case 3:
					// Excluir meus anuncios
					excluirAnuncios();
					break;

				case 4:
					// Visualiza as vendas do locador
					visualizarVendas();
					break;

				case 5:
					// Alterar an�ncios
					alterarAnuncios();
					break;

				case 6:
					// Voltar ao Menu Inicial
					telaUsuario.exibirTela();
					break;

				default:
					System.out.println("Op��o inexistente, tente novamente!");
				}

			} catch (InputMismatchException | ParseException e) {
				System.out.println("\nOp��o Inexistente. Digite um n�mero!\n\n");
				sc.nextLine();
			}
		} while (opcao != 6);
	}

	// M�todo para anunciar uma loca��o
	public void anunciar(Locacao locacao) throws ParseException {

		Anuncio anuncio = new Anuncio();
		sc.nextLine();
		boolean conferirEntrada = true;
		do {
			anuncio.setLocacao(locacao);
			System.out.println("Espa�o inteiro ou Espa�o compartilhado? ");
			anuncio.getLocacao().setTipoDeLocacao(sc.nextLine());

			if (anuncio.getLocacao().getTipoDeLocacao().equals("Espa�o inteiro")
					|| anuncio.getLocacao().getTipoDeLocacao().equals("Espa�o compartilhado")) {
				conferirEntrada = false;
			}

			else {
				System.out.println("Informe corretamente!");
			}

		} while (conferirEntrada == true);

		do {

			System.out.println("Cidade da Loca��o: ");
			anuncio.getLocacao().setCidadeDaLocacao(sc.nextLine());

			if (!anuncio.getLocacao().getCidadeDaLocacao().equals("")) {
				conferirEntrada = true;
			}

			else {
				System.out.println("Campo deve ser preenchido!");
			}

		} while (conferirEntrada == false);

		do {

			System.out.println("T�tulo da Loca��o: ");
			anuncio.setTitulo(sc.nextLine());
			if (!anuncio.getTitulo().equals("")) {
				conferirEntrada = false;
			} else {
				System.out.println("Campo deve ser preenchido!");
			}

		} while (conferirEntrada == true);

		do {
			System.out.println("Comodidades:  ");
			anuncio.getLocacao().setComodidades(sc.nextLine());

			if (!anuncio.getLocacao().getComodidades().equals("")) {
				conferirEntrada = true;
			} else {
				System.out.println("Campo deve ser preenchido!");
			}

		} while (conferirEntrada == false);

		do {

			System.out.println("Observa��es sobre a Loca��o: ");
			anuncio.setComentariosAdicionais(sc.nextLine());

			if (!anuncio.getComentariosAdicionais().equals("")) {
				conferirEntrada = false;
			} else {
				System.out.println("Campo deve ser preenchido!");
			}

		} while (conferirEntrada == true);

		boolean seguir = true;
		do {
			try {
				System.out.println("Valor/noite(R$): ");
				anuncio.setPreco(sc.nextDouble());
				seguir = false;
			}

			catch (InputMismatchException e) {
				System.out.println("Voc� deve digitar um valor!");
				sc.nextLine();
			}

		} while (seguir == true);

		int verificarHospedes = 0;

		do {
			try {
				System.out.println("Quantidade m�xima de H�spedes: ");
				anuncio.getLocacao().setQuantidadeHospedes(sc.nextInt());
				verificarHospedes = 1;
			} catch (InputMismatchException e) {
				System.out.println("Voc� deve digitar um n�mero inteiro.");
				sc.nextLine();
			}
		} while (verificarHospedes == 0);

		String resposta = "";

		boolean continuar = true;
		do {
			try {
				System.out.println("C�digo da Loca��o: ");
				anuncio.setCodigo(sc.nextLong());
				DadosException.compararCodigo(telaUsuario.getTelaLogin().getBancoDeDados(), anuncio.getCodigo());
				continuar = false;
			}

			catch (InputMismatchException e) {
				System.out.println("Voc� deve digitar caracteres num�ricos!");
				sc.nextLine();
			} catch (DadosException e) {
				System.out.println(e.getMessage());
			}

		} while (continuar == true);

		sc.nextLine();
		do {

			SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
			Date checkIn = new Date();
			Date checkOut = new Date();
			boolean cont = true;
			do {
				try {

					System.out.println("\nDigite as datas que voc� deseja alugar no Formato DD/MM/YYYY\n ");
					System.out.print("Check-In:");
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

					System.out.println("\nDigite as datas que voc� deseja alugar no Formato DD/MM/YYYY\n ");
					System.out.print("Check-Out:");
					String saida = sc.nextLine();
					SymbolException.verificarData(saida);
					checkOut = sd.parse(saida);
					if (checkIn.compareTo(checkOut) > 0) {

						throw new SymbolException("A data do Check-In deve vir antes da Data do Check-Out ");
					}
					if (checkIn.compareTo(checkOut) == 0) {
						throw new SymbolException("Voc� est� digitando datas iguais! ");
					}

					cont = true;

				} catch (SymbolException e) {
					System.out.println(e.getMessage());

				}

			} while (cont == false);
			Date[] pegarDatar = { checkIn, checkOut };
			anuncio.setData(pegarDatar);

			System.out.println("Adicionar mais Datas? (S)/(N)");
			System.out.println("(S)-Continuar.\n(N)-Parar.\n");

			do {
				resposta = sc.nextLine();
				if (!resposta.equals("N") && !resposta.equals("S")) {
					System.out.println("Voc� deve digitar (S) ou (N)");

				}
			} while (!resposta.equals("N") && !resposta.equals("S"));

		} while (resposta.equals("S"));

		if (locacao instanceof Apartamento) {

			Apartamento apartamento = (Apartamento) locacao;

			do {
				try {
					System.out.println("Quantidade de Quartos: ");
					apartamento.setQuartos(sc.nextInt());
					continuar = true;
				} catch (InputMismatchException e) {
					System.out.println("Voc� deve digitar caracteres num�ricos inteiros!");
					sc.nextLine();
				}
			} while (continuar == false);

			do {

				try {
					System.out.println("Quantidade de Banheiros: ");
					apartamento.setBanheiros(sc.nextInt());
					continuar = false;
				} catch (InputMismatchException e) {
					System.out.println("Voc� deve digitar caracteres num�ricos inteiros!");
					sc.nextLine();

				}
			} while (continuar == true);

			do {
				try {
					System.out.println("Quantidade de Colch�es: ");
					apartamento.setColchoes(sc.nextInt());
					locacao = apartamento;
					continuar = true;
				} catch (InputMismatchException e) {
					System.out.println("Voc� deve digitar caracteres num�ricos inteiros!");
					sc.nextLine();
				}
			} while (continuar == false);

		}

		if (locacao instanceof Barco) {
			continuar = false;
			Barco barco = (Barco) locacao;
			do {
				try {
					System.out.println("Valor do passeio: ");
					barco.setValorPasseio(sc.nextDouble());
					locacao = barco;
					continuar = true;
				} catch (InputMismatchException e) {

					System.out.println("Voc� deve digitar caracteres num�ricos!");
					sc.nextLine();
				}

			} while (continuar == false);
		}

		if (locacao instanceof Quarto) {
			continuar = true;
			Quarto quarto = (Quarto) locacao;
			do {
				try {
					System.out.println("Quantidade de Colch�es: ");
					quarto.setColchoes(sc.nextInt());
					locacao = quarto;
					continuar = false;
				} catch (InputMismatchException e) {
					System.out.println("Voc� deve digitar caracteres num�ricos inteiros!");

				}
			} while (continuar == true);
		}

		anuncio.setLocacao(locacao);
		anuncio.getLocacao().setLocador(telaUsuario.getTelaLogin().getUsuarioAtual());
		telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().add(anuncio);
		telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().add(anuncio);

		System.out.println("An�ncio Cadastrado! \n");

	}

	// M�todo que exibe todos os an�ncios cadastrados
	private void visualizarAnuncios() {
		String resposta = "";
		int contador = 0;
		sc.nextLine();
		do {
			for (int i = contador; i < telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios()
					.size(); i++) {
				System.out.println("\n\nAn�ncio " + (i + 1) + ": "
						+ telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(i).toString());
				telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(i).exibirDatasDisponiveis();
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
		if (telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().size() == 0) {
			System.out.println("Voc� n�o tem an�ncios cadastrados!");
		}

	}

	// M�todo exclui os an�ncios
	private void excluirAnuncios() {
		int verificarCodigo = 0;
		long excluido = 0;
		boolean continuar = true;
		do {
			try {
				System.out.println("Digite o c�digo do an�ncio: ");

				excluido = sc.nextLong();
				continuar = false;
			}

			catch (InputMismatchException e) {
				System.out.println("Voc� deve digitar um valor!");
				sc.nextLine();
			}

		} while (continuar == true);

		for (int i = 0; i < telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().size(); i++) {
			if (telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(i).getCodigo() == excluido) {
				telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().remove(i);
				for (int x = 0; x < telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().size(); x++) {
					if (telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(x)
							.getCodigo() == excluido) {
						telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().remove(x);

						System.out.println("Anuncio removido!");
					}
				}
				verificarCodigo = 1;
			}
		}
		if (verificarCodigo == 0) {
			System.out.println("An�ncio n�o existente! \n");
		}
	}

	// M�todo para visualizar as vendas
	private void visualizarVendas() {
		for (int i = 0; i < telaUsuario.getTelaLogin().getUsuarioAtual().getListaMinhasVendas().size(); i++) {
			System.out.println("Minhas vendas: "
					+ telaUsuario.getTelaLogin().getUsuarioAtual().getListaMinhasVendas().get(i).toString()
					+ "\nDatas Reservadas");
			System.out.println("Inquilino:\n" + telaUsuario.getTelaLogin().getUsuarioAtual().getListaMinhasVendas()
					.get(i).getLocacao().getLocatario());

		}

	}

	// M�todo que altera os an�ncios
	private void alterarAnuncios() throws ParseException {
		long codigo = 0;
		boolean continuar = true;
		boolean conferirEntrada = true;
		do {
			try {
				System.out.println("Digite o c�digo da Loca��o que deseja editar: ");

				codigo = sc.nextLong();
				continuar = false;
			}

			catch (InputMismatchException e) {
				System.out.println("Voc� deve digitar um valor!");
				sc.nextLine();
			}

		} while (continuar == true);
		sc.nextLine();
		String resposta = "S";
		int posicao = 0;
		int verificarEntrada = 0;
		do {
			for (int i = 0; i < telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().size(); i++) {
				if (telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(i).getCodigo() == codigo) {
					System.out.println("An�ncio: "
							+ telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(i).toString()
							+ "\n");
					verificarEntrada = 1;
					posicao = i;

					int editar = 0;
					if (telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
							.getLocacao() instanceof Apartamento) {

						boolean seguir = true;
						do {

							try {

								Apartamento apartamento = (Apartamento) telaUsuario.getTelaLogin().getUsuarioAtual()
										.getListaMeusAnuncios().get(posicao).getLocacao();
								System.out.println(
										"\nQual dos campos voc� deseja editar?\n \n[1] Tipo De Locacao: \n[2] Cidade Da Locacao:  \n[3] T�tulo:  \n[4] Comodidades: \n[5] Coment�rios adicionais: \n[6] Pre�o: \n[7] Quantidade de H�spedes: \n[8] C�digo: \n[9] Datas:  ");

								System.out.println(
										"[10] Quantidade de quartos: \n[11] Quantidade de banheiros:  \n[12] Quantidade de colch�es: ");
								editar = sc.nextInt();
								sc.nextLine();

								telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
										.setLocacao(apartamento);

								seguir = false;
							} catch (InputMismatchException e) {
								System.out.println("Voce deve digitar n�meros!");
								sc.nextLine();

							}
						} while (seguir == true);

					}

					if (telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
							.getLocacao() instanceof Barco) {
						boolean seguir = true;
						do {

							try {
								Barco barco = (Barco) telaUsuario.getTelaLogin().getUsuarioAtual()
										.getListaMeusAnuncios().get(posicao).getLocacao();
								System.out.println(
										"\nQual dos campos voc� deseja editar?\n \n[1] Tipo De Locacao: \n[2] Cidade Da Locacao:  \n[3] T�tulo:  \n[4] Comodidades: \n[5] Coment�rios adicionais: \n[6] Pre�o: \n[7] Quantidade de H�spedes: \n[8] C�digo: \n[9] Datas:  ");

								System.out.println("\n[10] Valor do passeio: ");
								editar = sc.nextInt();
								sc.nextLine();
								seguir = false;
								telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
										.setLocacao(barco);
							} catch (InputMismatchException e) {
								System.out.println("Voc� deve digitar um valor!");
								sc.nextLine();

							}
						} while (seguir == true);
					}

					if (telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
							.getLocacao() instanceof Quarto) {

						boolean seguir = true;

						do {

							try {

								Quarto quarto = (Quarto) telaUsuario.getTelaLogin().getUsuarioAtual()
										.getListaMeusAnuncios().get(posicao).getLocacao();
								System.out.println(
										"\nQual dos campos voc� deseja editar?\n \n[1] Tipo De Locacao: \n[2] Cidade Da Locacao:  \n[3] T�tulo:  \n[4] Comodidades: \n[5] Coment�rios adicionais: \n[6] Pre�o: \n[7] Quantidade de H�spedes: \n[8] C�digo: \n[9] Datas:  ");

								System.out.println("\n[10] Quantidade de colch�es: ");
								editar = sc.nextInt();
								sc.nextLine();

								telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
										.setLocacao(quarto);
								seguir = false;

							} catch (InputMismatchException e) {
								System.out.println("Voce deve digitar n�meros!");
								sc.nextLine();
							}
						} while (seguir == true);

					}

					switch (editar) {

					case 1:
						conferirEntrada = true;
						do {
							System.out.print("Est� oferencendo um espa�o inteiro ou espa�o compartilhado?");
							telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
									.getLocacao().setTipoDeLocacao(sc.nextLine());

							if (telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
									.getLocacao().getTipoDeLocacao().equals("Espa�o inteiro")
									|| telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
											.getLocacao().getTipoDeLocacao().equals("Espa�o compartilhado")) {
								conferirEntrada = false;
							}

							else {
								System.out.println("Informe corretamente!");
							}
						} while (conferirEntrada == true);
						break;

					case 2:
						conferirEntrada = true;
						do {
							System.out.print("Qual a cidade em que a loca��o se encontra? ");
							telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
									.getLocacao().setCidadeDaLocacao(sc.nextLine());
							if (!telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
									.getLocacao().getCidadeDaLocacao().equals("")) {
								conferirEntrada = false;
							}
						} while (conferirEntrada == true);
						break;

					case 3:
						conferirEntrada = true;
						do {
							System.out.print("T�tulo da loca��o: ");
							telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
									.setTitulo(sc.nextLine());
							if (!telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
									.getTitulo().equals("")) {
								conferirEntrada = false;
							}
						} while (conferirEntrada == true);
						break;

					case 4:
						conferirEntrada = true;
						do {
							System.out.print("Comodidades:  ");
							telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
									.getLocacao().setComodidades(sc.nextLine());
							if (!telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
									.getLocacao().getComodidades().equals("")) {
								conferirEntrada = false;
							}
						} while (conferirEntrada == true);
						break;

					case 5:
						conferirEntrada = true;
						do {
							System.out.print("Informa��es adicionais ");
							telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
									.setComentariosAdicionais(sc.nextLine());
							if (!telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
									.getComentariosAdicionais().equals("")) {
								conferirEntrada = false;
							}
						} while (conferirEntrada == true);
						break;

					case 6:
						conferirEntrada = true;
						do {
							try {
								System.out.print("Valor por noite? ");
								telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
										.setPreco(sc.nextDouble());
								sc.nextLine();
								conferirEntrada = false;
							} catch (InputMismatchException e) {
								System.out.println("Voce deve digitar n�meros!");
								sc.nextLine();
							}
						} while (conferirEntrada == true);
						break;

					case 7:
						conferirEntrada = true;
						do {
							try {
								System.out.print("Quantidade m�xima de H�spedes: ");
								telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
										.getLocacao().setQuantidadeHospedes(sc.nextInt());
								sc.nextLine();
								conferirEntrada = false;
							} catch (InputMismatchException e) {
								System.out.println("Voce deve digitar n�meros!");
								sc.nextLine();
							}
						} while (conferirEntrada == true);
						break;

					case 8:
						do {
							try {
								conferirEntrada = true;
								System.out.print("Digite o novo c�digo que deseja para sua loca��o: ");
								long pegaCodigo = sc.nextLong();
								sc.nextLine();

								DadosException.compararCodigo(telaUsuario.getTelaLogin().getBancoDeDados(), pegaCodigo);
								telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
										.setCodigo(pegaCodigo);
								codigo = pegaCodigo;
								conferirEntrada = false;
							} catch (InputMismatchException e) {
								System.out.println("Voce deve digitar n�meros!");
								sc.nextLine();
							}

							catch (DadosException e) {
								System.out.println(e.getMessage());
							}
						} while (conferirEntrada == true);
						break;

					case 9:
						conferirEntrada = true;
						Date checkIn = new Date();
						Date checkOut = new Date();
						int verificarEdicao = 0;
						int verificarAlteracao = 0;
						SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
						do {
							do {
								try {
									telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(posicao)
											.exibirDatasDisponiveis();
									System.out.print("Digite a data de Check-In que deseja editar:");
									String entrada = sc.nextLine();
									checkIn = sd.parse(entrada);
									SymbolException.verificarData(entrada);
									conferirEntrada = false;
								} catch (SymbolException e) {
									System.out.println(e.getMessage());
								} catch (ParseException e) {
									System.out.println("Voc� digitou no formato incorreto!");
								}

							} while (conferirEntrada == true);
							conferirEntrada = true;
							do {
								try {
									telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(posicao)
											.exibirDatasDisponiveis();
									System.out.print("\nDigite a data de Check-Out que deseja editar:");
									String saida = sc.nextLine();
									checkOut = sd.parse(saida);
									SymbolException.verificarData(saida);

									if (checkIn.compareTo(checkOut) > 0) {

										throw new SymbolException(
												"A data do Check-In deve vir antes da Data do Check-Out ");
									}
									if (checkIn.compareTo(checkOut) == 0) {
										throw new SymbolException("Voc� est� digitando datas iguais! ");
									}
									conferirEntrada = false;
								} catch (SymbolException e) {
									System.out.println(e.getMessage());
								} catch (ParseException e) {
									System.out.println("\nVoc� digitou no formato incorreto!");
								}

							} while (conferirEntrada == true);

							conferirEntrada = true;

							for (int x = 0; x < telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios()
									.get(posicao).getListaDatas().size(); x++) {
								if (telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(posicao)
										.getListaDatas().get(x)[0].compareTo(checkIn) == 0
										&& telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(posicao)
												.getListaDatas().get(x)[1].compareTo(checkOut) == 0) {
									telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(posicao)
											.getListaDatas().remove(x);

									verificarEdicao = 1;

									System.out.println("\nDigite a nova data no Formato DD/MM/YYYY ");
									do {
										try {
											System.out.print("\nDigite a nova data de Check-In:");
											String entrada = sc.nextLine();
											checkIn = sd.parse(entrada);
											SymbolException.verificarData(entrada);
											conferirEntrada = false;
										} catch (SymbolException e) {
											System.out.println(e.getMessage());
										} catch (ParseException e) {
											System.out.println("\nVoc� digitou no formato incorreto!");
										}

									} while (conferirEntrada == true);

									conferirEntrada = true;

									do {
										try {
											System.out.print("\nDigite a nova data de Check-Out:");
											String saida = sc.nextLine();
											checkOut = sd.parse(saida);
											SymbolException.verificarData(saida);

											if (checkIn.compareTo(checkOut) > 0) {

												throw new SymbolException(
														"A data do Check-In deve vir antes da Data do Check-Out ");
											}
											if (checkIn.compareTo(checkOut) == 0) {
												throw new SymbolException("Voc� est� digitando datas iguais! ");
											}
											conferirEntrada = false;
										} catch (SymbolException e) {
											System.out.println(e.getMessage());
										} catch (ParseException e) {
											System.out.println("\nVoc� digitou no formato incorreto!");
										}

									} while (conferirEntrada == true);

									Date[] datas = { checkIn, checkOut };
									telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(posicao)
											.setData(datas);
								}
							}
							if (verificarEdicao == 0) {
								System.out.println("\nEste periodo n�o est� dispon�vel");
							} else {
								verificarAlteracao = 1;
							}
						} while (verificarAlteracao == 0);
						break;
					}

					if (telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
							.getLocacao() instanceof Apartamento) {
						Apartamento apartamento = (Apartamento) telaUsuario.getTelaLogin().getUsuarioAtual()
								.getListaMeusAnuncios().get(posicao).getLocacao();

						if (editar == 10) {
							conferirEntrada = true;
							do {
								try {
									System.out.println("Quartos: ");
									apartamento.setQuartos(sc.nextInt());
									sc.nextLine();
									conferirEntrada = false;
								} catch (InputMismatchException e) {
									System.out.println("Voce deve digitar n�meros inteiros!");
									sc.nextLine();
								}
							} while (conferirEntrada == true);
						}

						if (editar == 11) {
							conferirEntrada = true;
							do {
								try {
									System.out.println("Banheiros: ");
									apartamento.setBanheiros(sc.nextInt());
									sc.nextLine();
									conferirEntrada = false;
								} catch (InputMismatchException e) {
									System.out.println("Voce deve digitar n�meros inteiros!");
									sc.nextLine();
								}
							} while (conferirEntrada == true);
						}
						if (editar == 12) {
							conferirEntrada = true;
							do {
								try {
									System.out.println("Colch�es: ");
									apartamento.setColchoes(sc.nextInt());
									sc.nextLine();
									conferirEntrada = false;
								} catch (InputMismatchException e) {
									System.out.println("Voce deve digitar n�meros inteiros!");
									sc.nextLine();
								}
							} while (conferirEntrada == true);

						}
						telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
								.setLocacao(apartamento);
					}

					if (telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
							.getLocacao() instanceof Barco) {

						if (editar == 10) {
							Barco barco = (Barco) telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios()
									.get(posicao).getLocacao();
							conferirEntrada = true;
							do {
								try {
									System.out.println("Valor do passeio: ");
									barco.setValorPasseio(sc.nextDouble());
									sc.nextLine();
									conferirEntrada = false;
								} catch (InputMismatchException e) {
									System.out.println("Voce deve digitar n�meros inteiros!");
									sc.nextLine();
								}
							} while (conferirEntrada == true);

							telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
									.setLocacao(barco);

						}
					}

					if (telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
							.getLocacao() instanceof Quarto) {
						Quarto quarto = (Quarto) telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios()
								.get(posicao).getLocacao();

						if (editar == 10) {
							conferirEntrada = true;
							do {
								try {
									System.out.println("Colch�es: ");
									quarto.setColchoes(sc.nextInt());
									sc.nextLine();

									conferirEntrada = false;
								} catch (InputMismatchException e) {
									System.out.println("Voce deve digitar n�meros inteiros!");
									sc.nextLine();
								}
							} while (conferirEntrada == true);
						}

						telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
								.setLocacao(quarto);
					}

					System.out.println("An�ncio Editado! \n" + telaUsuario.getTelaLogin().getUsuarioAtual()
							.getListaMeusAnuncios().get(posicao).toString());
					telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(posicao)
							.exibirDatasDisponiveis();
					System.out.println("Deseja continuar editando? (S/N) \n");
					resposta = sc.nextLine();

					do {

						if (!resposta.equals("N") && !resposta.equals("S")) {
							System.out.println("Voc� deve digitar (S) ou (N)");
							resposta = sc.nextLine();
						}
					} while (!resposta.equals("N") && !resposta.equals("S"));

				}

			}
			if (verificarEntrada == 0) {
				System.out.println("Voc� n�o tem loca��es anunciadas com esse c�digo!");
				resposta = "N";
			}

		} while (resposta.equals("S"));
	}
}
