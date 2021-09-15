import java.util.InputMismatchException;
import java.util.Scanner;

//Classe TelaLogin primeira tela exibida ao usuário para cadastrar ou fazer login no sistema

public class TelaLogin implements Tela {

	private Scanner entrada = new Scanner(System.in);
	private Usuario usuarioAtual;
	private BancoDeDados bancoDeDados;

	// Construtor
	public TelaLogin(BancoDeDados bancoDeDados) {
		this.bancoDeDados = bancoDeDados;
	}

	// Método compara as strings do login(email e senha)
	public Usuario solicitarLogin() {
		String emailDigitado = "";
		boolean continuar = true;

		int i = 0;
		int cont = 0;
		entrada.nextLine();
		if (i != 10) {
			System.out.println("--------Tela de LOGIN-------- \n");
			System.out.println("[0] Voltar \n");
			while (continuar == true) {
				try {

					System.out.println("Digite o seu email: ");
					emailDigitado = entrada.nextLine().toLowerCase();

					if (emailDigitado.equals("0")) {
						i = 10;
						System.out.println("Obrigado pela visita!");
						continuar = false;
					}
					if (cont == 3) {
						System.out.println("Você excedeu o número de tentativas!");
						i = 10;
					}

					SymbolException.verificarEmail(emailDigitado);
					continuar = false;
				} catch (SymbolException e) {
					System.out.println(e.getMessage());
					cont++;
				}

				catch (ArrayIndexOutOfBoundsException e) {
					if (!emailDigitado.equals("0")) {
						System.out.println("Formato de Email incorreto!");
					}
				}

			}

			for (Usuario usuario : bancoDeDados.getListaUsuarios()) {
				if (usuario.getEmail().equals(emailDigitado)) {
					System.out.println("Usuário encontrado!\n");

					System.out.println("Digite sua senha: ");

					String senhaDigitada = entrada.nextLine();

					if (senhaDigitada.equals(usuario.getSenha())) {
						System.out.println("Login efetuado!");
						usuarioAtual = usuario;
						i = 10;
					} else {
						for (int j = 0; j < 3; j++) {
							System.out.println("Senha incorreta.\nDigite sua senha: ");
							senhaDigitada = entrada.nextLine();

							if (senhaDigitada.equals(usuario.getSenha())) {
								System.out.println("Login Efetuado!");
								usuarioAtual = usuario;
								i = 10;
								j = 3;
							}
						}
					}
				}
			}
			if (i != 10) {
				System.out.println("Dados incorretos!");

				System.out.println("Você deseja ir para a Tela de Cadastro?(S/N)");
				String resposta = "";
				do {

					resposta = entrada.next();
					System.out.println("Digite (S) ou (N) ");
				} while (!resposta.equals("S") && !resposta.equals("N"));

				if (resposta.equals("N")) {
					System.out.println("\nObrigado pela visita!\n");
				} else {
					System.out.println("\n");
					cadastrar();
				}
			}
		}
		return usuarioAtual;
	}

	// Método sobrescrito exibirtela, onde o usuário escolhe fazer login ou fazer
	// o cadastro
	@Override
	public void exibirTela() {
		int opcao = -1;

		do {
			TelaUsuario telaUsuario;

			System.out.println("Bem vindo(a) ao Airbnb! \n");
			System.out.println("[0] Sair.");
			System.out.println("[1] Realizar Login.");
			System.out.println("[2] Realizar Cadastro.");

			try {

				opcao = entrada.nextInt();

				switch (opcao) {
				case 1:
					// Login
					Usuario usuario = solicitarLogin();
					if (usuario != null) {
						telaUsuario = new TelaUsuario(this);
						telaUsuario.exibirTela();
					}
					break;

				case 2:
					// Cadastro do usuário
					String pegaValor = cadastrar();
					if (!pegaValor.equals("0")) {
						telaUsuario = new TelaUsuario(this);
						telaUsuario.exibirTela();
					}
					break;

				case 0:
					System.out.println("Obrigado pela visita");
					break;
				default:
					System.out.println("Opção Inexistente!\nTente novamente!\n");
				}

			} catch (InputMismatchException e) {
				System.out.println("Você deve digitar uma das opções (um número!)");
				entrada.nextLine();
			}
		} while (opcao != 0);
	}

	// Método cadastrar
	private String cadastrar() {
		usuarioAtual = new Usuario();
		entrada.nextLine();
		boolean conferirEntrada = true;
		String sair = "";

		do {

			System.out.println("Digite seu nome: ");
			usuarioAtual.setNome(entrada.nextLine());
			sair = usuarioAtual.getNome();
			if (!usuarioAtual.getNome().equals("")) {
				conferirEntrada = false;
			} else {
				System.out.println("Campo deve ser preenchido!");
			}

		} while (conferirEntrada == true);

		conferirEntrada = true;
		if (!sair.equals("0")) {
			do {
				System.out.println("Digite seu sobrenome: ");
				usuarioAtual.setSobrenome(entrada.nextLine());
				sair = usuarioAtual.getSobrenome();
				if (!usuarioAtual.getSobrenome().equals("")) {
					conferirEntrada = false;
				} else {
					System.out.println("Campo deve ser preenchido!");
				}

			} while (conferirEntrada == true);

		}
		boolean continuar = true;
		if (!sair.equals("0")) {

			while (continuar == true) {
				try {

					System.out.println("Digite seu email: ");
					usuarioAtual.setEmail(entrada.nextLine().toLowerCase());
					sair = usuarioAtual.getEmail();
					if (sair.equals("0")) {
						continuar = false;
					}
					SymbolException.verificarEmail(usuarioAtual.getEmail());
					DadosException.compararEmail(bancoDeDados, usuarioAtual.getEmail());
					continuar = false;

				} catch (SymbolException e) {
					System.out.println(e.getMessage());
					System.out.println(
							"\nUtilize um dos formatos a seguir:\n@gmail.com\n@hotmail.com\n@outlook.com\n@yahoo.com\n\nEx:lais15@gmail.com\n");

				} catch (DadosException a) {
					System.out.println(a.getMessage());
				} catch (ArrayIndexOutOfBoundsException e) {
					if (!sair.equals("0")) {
						System.out.println("Formato de Email Incorreto!");
					}
				}

			}
		}

		boolean seguir = true;

		if (!sair.equals("0")) {
			while (seguir == true) {
				try {
					System.out.println("Digite seu CPF: ");

					String pegarCPF = entrada.nextLine();
					sair = pegarCPF;
					if (pegarCPF.equals("0")) {
						seguir = false;
					} else {
						usuarioAtual.setCPF(Long.valueOf(pegarCPF).longValue());
						DadosException.compararCPF(bancoDeDados, pegarCPF, usuarioAtual.getCPF());
						seguir = false;
					}
				} catch (NumberFormatException e) {
					System.out.println("Digite apenas números!");
				}

				catch (DadosException a) {
					System.out.println(a.getMessage());
				} catch (InputMismatchException e) {
					System.out.println("Voce deve digitar caracteres numéricos!");
					entrada.nextLine();
				}

			}
		}
		seguir = true;
		if (!sair.equals("0")) {
			do {
				System.out.println("Digite sua cidade: ");
				usuarioAtual.setCidade(entrada.nextLine());
				sair = usuarioAtual.getCidade();
				if (!usuarioAtual.getCidade().equals("")) {
					seguir = false;
				} else {
					System.out.println("Campo deve ser preenchido!");
				}

			} while (seguir == true);
			seguir = true;
		}
		if (!sair.equals("0")) {
			do {
				System.out.println("Digite sua senha: ");
				usuarioAtual.setSenha(entrada.nextLine());
				sair = usuarioAtual.getSenha();
				if (!usuarioAtual.getSenha().equals("")) {
					seguir = false;
				} else {
					System.out.println("Campo deve ser preenchido!");
				}

			} while (seguir == true);
		} else if (!sair.equals("0")) {
			bancoDeDados.getListaUsuarios().add(usuarioAtual);

			System.out.println("Cadastro realizado com sucesso. Veja seus dados:");

			usuarioAtual.mostrarCadastro();
		}
		if (sair.equals("0")) {
			System.out.println("\nObrigado pela visita!\n");

		}
		return sair;

	}

	// Métodos getters e setters
	public Usuario getUsuarioAtual() {
		return usuarioAtual;
	}

	public void setUsuarioAtual(Usuario usuarioAtual) {
		this.usuarioAtual = usuarioAtual;
	}

	public BancoDeDados getBancoDeDados() {
		return bancoDeDados;
	}

	public void setBancoDeDados(BancoDeDados bancoDeDados) {
		this.bancoDeDados = bancoDeDados;
	}

}
