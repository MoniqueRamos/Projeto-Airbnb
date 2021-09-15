import java.util.InputMismatchException;
import java.util.Scanner;

//Classe TelaUsuario aparece para o usu�rio logo depois da Tela de login

public class TelaUsuario implements Tela {
	private TelaLogin telaLogin;
	private TelaLocatario telaLocatario;
	private TelaLocador telaLocador;
	private Scanner sc = new Scanner(System.in);

	// Construtor
	public TelaUsuario(TelaLogin telaLogin) {
		this.telaLogin = telaLogin;
	}

	// M�todo sobrescrito, onde o usu�rio escolhe entre ser anfitri�o ou h�spede
	public void exibirTela() {

		int opcao = -1;

		do {
			System.out.println("\nSeja bem vindo(a) ao Menu inicial Airbnb: \n");
			System.out.println("Entrar como: ");
			System.out.println("[1]Locat�rio");
			System.out.println("[2]Locador");
			System.out.println("[3]Sair");
			try {
				opcao = sc.nextInt();

				switch (opcao) {
				case 1:
					telaLocatario = new TelaLocatario(this);
					telaLocatario.exibirTela();
					break;

				case 2:
					telaLocador = new TelaLocador(this);
					telaLocador.exibirTela();
					break;
				case 3:
					System.out.println("Espero ter ajudado nas suas viagens. Volte sempre ;) \n\n\n");
					telaLogin.exibirTela();
					break;
				default:
					System.out.println("Op��o n�o existente, tente novamente");
					break;
				}

			} catch (InputMismatchException e) {
				System.out.println("Voc� deve digitar uma das op��es (um n�mero!)");
				sc.nextLine();
			}

		} while (opcao != 3);
	}

	// Retorna tela de login
	public TelaLogin getTelaLogin() {
		return telaLogin;
	}

	// Configura tela de login
	public void setTelaLogin(TelaLogin telaLogin) {
		this.telaLogin = telaLogin;
	}

}