public class DoubleHashingProbingHashTable<T> extends ProbingHashTable<T> {

	/*
	 * Denna metod ska skrivas klart. Den ska använda bokens förslag på andra
	 * hashalgoritm: f(i) = i * hash2(x), där hash2(x) = R - (x mod R) och R är
	 * ett primtal mindre än tabellstorleken.
	 */

	/**
	 * Beräknar och returnerar positionen för där element x finns eller ska placeras.
	 * Sonderar genom hashtable med hjälp av en ytterligare hashfunktion, pågår
	 * tills dess att ett likadant element eller null funnits.
	 * Den andra hashing algoritmen använder ett primtal mindre än kapaciteten av
	 * hashtable, en int offset, den första hashfunktionen och hashkoden av x
	 * fär att beräkna nästa position om en kollision uppstår.
	 *
	 * @param x föremålet att hitta en plats till eller hitta dess plats
	 * @return current, positionen där elementet hittas eller placerats om positionen var null
	 */
	@Override
	protected int findPos(T x) {
		int smallPrime = smallerPrimeThanCapacity(); //setting smallPrime to int generated from below method
		int current = myhash(x);	//current is retrieved from myhash with parameter x
		int offset = 1; //offset is set to 1 since
		while(continueProbing(current, x)){ //continue while continueprobing is true, see probingHashTable
			current = myhash(x) + offset * (smallPrime - (x.hashCode() % smallPrime));
			offset ++;
			if(current >= capacity()){
				current %= capacity();
			}
		}
		return current;
	}

	/*
	 * Denna metod ger ett primtal mindre än tabellens storlek.
	 * Detta primtal ska användas i metoden ovan.
	 */
	protected int smallerPrimeThanCapacity() {
		int n = capacity() - 2;
		while (!isPrime(n)) {
			n -= 2;
		}
		return n;
	}

}
