import java.util.Arrays;

public class MyString {

	private char[] data;
	
	public MyString(String title) {
		data = title.toCharArray();
	}

	public Object length() {
		return data.length;
	}

	/**
	 * Genererar MyString-delen av den slutgiltiga hashkoden. Itererar
	 * genom varje element i MyString arrayen. Adderar det numeriska
	 * värdet av varje char i arrayen till tidigare summa som multipliceras
	 * med ett primtal. Multiplicerar med primtal för att särskilja
	 * titlar som har samma tecken i olika ordning så att summan i
	 * dessa fall blir olika samt ge bättre spridning.
	 *
	 * @return hCode, titel delen till slutgiltiga hashkoden
	 */
	@Override
	public int hashCode(){
		int hCode = 0;
		for (char a : data){
			hCode += hCode * 13 + Character.getNumericValue(a);
		}
		return hCode;
	}

	/**
	 * Equals metod för MyString som jämför två objekt och
	 * kontrollerar om deras states är desamma (deras värden).
	 * Använder Arrays.equals för att kontrollera om objekten
	 * är lika. Används för att upptäcka kollision.
	 *
	 * @param o objektet som ska jämföras
	 * @return true om elementen är likadana
	 */
	@Override
	public boolean equals(Object o){
		if(o instanceof MyString myString){
			return Arrays.equals(myString.data, data);
		}
		return false;
	}

	@Override
	public String toString() {
		return new String(data);
	}

}
