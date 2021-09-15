//Classe de exce��o para tratar erro em tempo de execu��o

public class DadosException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DadosException(String msg) {
		super(msg);
	}

	// M�todo criado para tratar erro no CPF
	public static void compararCPF(BancoDeDados bancoDeDados, String pegarCPF, long cPF) {

		if (pegarCPF.length() != 11) {
			throw new DadosException("CPF inv�lido");
		}

		for (int i = 0; i < bancoDeDados.getListaUsuarios().size(); i++) {
			if (cPF == (bancoDeDados.getListaUsuarios().get(i).getCPF())) {
				throw new DadosException("Este CPF j� est� cadastrado!");
			}
		}
	}

	// M�todo criado para verificar se o e-mail est� cadastrado
	public static void compararEmail(BancoDeDados bancoDeDados, String email) {
		for (int i = 0; i < bancoDeDados.getListaUsuarios().size(); i++) {
			if (email.equals(bancoDeDados.getListaUsuarios().get(i).getEmail())) {
				throw new DadosException("Este email j� est� cadastrado!");
			}
		}
	}

	// M�todo criado para verificar se o c�digo est� cadastrado
	public static void compararCodigo(BancoDeDados bancoDeDados, long codigo) {
		for (int i = 0; i < bancoDeDados.getListaAnuncios().size(); i++) {
			if (codigo == bancoDeDados.getListaAnuncios().get(i).getCodigo()) {
				throw new DadosException("Este c�digo j� est� cadastrado!");
			}
		}
	}
}
