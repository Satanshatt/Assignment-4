public class LinearProbingHashTable<T> extends ProbingHashTable<T> {

	/*
	 * Denna metod ska skrivas klart. Den ska använda linjär sondering och hela tiden öka med ett.
	 */

	/**
	 * Beräknar och returnerar positionen för där element x finns eller ska placeras.
	 * Sonderar genom hashtable linjärt, alltså ett steg i taget. Fortsätter så funna
	 * elementet är detsamma som x eller null. Om positionen går utanför kapaciteten
	 * så subtraheras kapaciteten och sökningen fortgår.
	 *
	 * @param x föremålet att hitta en plats till eller hitta dess plats
	 * @return currentPos, positionen där elementet hittas elelr placerats om positionen var null
	 */
	@Override
	protected int findPos(T x) {
		int currentPos = myhash(x);
		while(continueProbing(currentPos, x)){
			currentPos++;
			if(currentPos >= capacity()){
				currentPos -= capacity();
			}
		}
		return currentPos;
	}

}


