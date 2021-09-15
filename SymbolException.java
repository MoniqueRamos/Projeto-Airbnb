//Classe de exce��o para tratar erro em tempo de execu��o tomando como base s�mbolos 

public class SymbolException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SymbolException(String msg) {
		super(msg);
	}

	// M�todo utilizado para confirmar se o e-mail est� em sua forma correta
	public static void verificarEmail(String email) {
		String[] vet = email.split("@");
		if (vet.length != 2 && !email.equals("0")) {
			throw new SymbolException("Formato de E-mail incorreto!");

		} else if (!vet[1].equals("gmail.com") && !vet[1].equals("hotmail.com") && !vet[1].equals("yahoo.com")
				&& !vet[1].equals("outlook.com")) {
			throw new SymbolException("Formato de E-mail incorreto");
		}
	}

	// M�todo utilizado para verificar o formato da data est� correto
	public static void verificarData(String data) {
		String[] vet = data.split("/");
		Integer[] vet2 = new Integer[3];
		if ((vet[0].length() != 2 || vet[1].length() != 2 || vet[2].length() != 4)) {
			throw new SymbolException("Formato de Data Invalida!");

		} else {

			for (int i = 0; i < 3; i++) {

				vet2[i] = Integer.parseInt(vet[i]);
			}

			if (vet2[1] <= 12 && vet2[1] > 0 && vet2[0] < 32) {
				switch (vet2[1]) {

				case 1:
					if (vet2[0] > 31) {
						throw new SymbolException("Voc� digitou um dia que n�o existe nesse m�s!");
					}
				case 2:

					if ((vet2[2] % 4) == 0 && vet2[0] > 29) {

						throw new SymbolException("Em anos bissextos Fevereiro vai at� o dia 29");
					}

					else if (vet2[0] > 28 && (vet2[2] % 4) != 0) {

						throw new SymbolException("Em anos n�o bissextos Fevereiro vai at� o dia 28!");
					}
				case 3:

					if (vet2[0] > 31) {
						throw new SymbolException("Voc� digitou um dia que n�o existe nesse m�s!");
					}

				case 4:
					if (vet2[0] > 30) {
						throw new SymbolException("Voc� digitou um dia que n�o existe nesse m�s!");
					}

				case 5:
					if (vet2[0] > 31) {
						throw new SymbolException("Voc� digitou um dia que n�o existe nesse m�s!");
					}

				case 6:
					if (vet2[0] > 30) {
						throw new SymbolException("Voc� digitou um dia que n�o existe nesse m�s!");
					}

				case 7:
					if (vet2[0] > 31) {
						throw new SymbolException("Voc� digitou um dia que n�o existe nesse m�s!");
					}
				case 8:
					if (vet2[0] > 31) {
						throw new SymbolException("Voc� digitou um dia que n�o existe nesse m�s!");
					}
				case 9:
					if (vet2[0] > 30) {
						throw new SymbolException("Voc� digitou um dia que n�o existe nesse m�s!");
					}

				case 10:
					if (vet2[0] > 31) {
						throw new SymbolException("Voc� digitou um dia que n�o existe nesse m�s!");
					}

				case 11:
					if (vet2[0] > 30) {
						throw new SymbolException("Voc� digitou um dia que n�o existe nesse m�s!");
					}
				case 12:
					if (vet2[0] > 31) {
						throw new SymbolException("Voc� digitou um dia que n�o existe nesse m�s!");
					}

				}

			} else {
				throw new SymbolException("Essa Data n�o existe!");
			}

		}

	}
}
