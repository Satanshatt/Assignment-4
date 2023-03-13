import java.util.Arrays;

public class ISBN10 {

	private char[] isbn;

	public ISBN10(String isbn) {
		if (isbn.length() != 10)
			throw new IllegalArgumentException("Wrong length, must be 10");
		if (!checkDigit(isbn))
			throw new IllegalArgumentException("Not a valid isbn 10");
		this.isbn = isbn.toCharArray();
	}

	private boolean checkDigit(String isbn) {
		int sum = 0;
		for (int i = 0; i < 9; i++) {
			sum += Character.getNumericValue(isbn.charAt(i)) * (10 - i);
		}
		int checkDigit = (11 - (sum % 11)) % 11;

		return isbn.endsWith(checkDigit == 10 ? "X" : "" + checkDigit);
	}

	/**
	 * Genererar ISBN-delen av den slutgiltiga hashkoden. Itererar
	 * genom varje element i ISBN arrayen. Adderar det numeriska
	 * värdet av varje char i arrayen till tidigare summa multiplicerad
	 * med ett primtal. Multiplicerar med primtal för att särskilja
	 * ISBN som har samma tecken i olika ordning så att summan i
	 * dessa fall blir olika samt ge bättre spridning.
	 *
	 * @return hCode, ISBN delen till slutgiltiga hashkoden
	 */
	public int hashCode(){
		int hCode = 0;
		for(char a : isbn){
			hCode += hCode * 23 + Character.getNumericValue(a);
		}

		return hCode;
	}

	/**
	 * Equals metod för ISBN som jämför två objekt och
	 * kontrollerar om deras states är desamma (deras värden).
	 * Använder Arrays.equals för att kontrollera om objekten
	 * är lika. Används för att upptäcka kollision.
	 *
	 * @param o objektet som ska jämföras
	 * @return true om elementen är likadana
	 */
	@Override
	public boolean equals(Object o){
		if(o instanceof ISBN10 isbn10){
			return Arrays.equals(isbn10.isbn, this.isbn);
		}
		return false;
	}

	@Override
	public String toString() {
		return new String(isbn);
	}
}
