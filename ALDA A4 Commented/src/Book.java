import java.util.Random;

/*
 * Denna klass ska förberedas för att kunna användas som nyckel i en hashtabell. 
 * Du får göra nödvändiga ändringar även i klasserna MyString och ISBN10.
 * 
 * Hashkoden ska räknas ut på ett effektivt sätt och följa de regler och 
 * rekommendationer som finns för hur en hashkod ska konstrueras. Notera i en 
 * kommentar i koden hur du har tänkt när du konstruerat din hashkod.
 */
public class Book {
	private MyString title;
	private MyString author;
	private ISBN10 isbn;
	private MyString content;
	private int price;

	public Book(String title, String author, String isbn, String content, int price) {
		this.title = new MyString(title);
		this.author = new MyString(author);
		this.isbn = new ISBN10(isbn);
		this.content = new MyString(content);
		this.price = price;
	}

	public MyString getTitle() {
		return title;
	}

	public MyString getAuthor() {
		return author;
	}

	public ISBN10 getIsbn() {
		return isbn;
	}

	public MyString getContent() {
		return content;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * Jag har valt att basera hashkoden på bokens ISBN och titel. Även om ISBN är unikt för varje bok så
	 * finns det en risk att alla böcker inte har ett ISBN. Därför beräknas hashkoden med hjälp av ISBN
	 * och titeln av boken så att även böcker utan ISBN kan få en hashkod. Bara ISBN hade egentligen räckt
	 * för hash koden och skapandet av hashkoden med titel gör också att processen kan kräva lite mer tid.
	 * När vi använder titeln så får vi dock ett extra "skydd" för de böcker som inte har ISBN och vi kan
	 * välja att cacha hash koden för att bemöta tidsproblemet. Jag har dock valt att inte använda cachning
	 * i detta fall då titlar på böcker för det mesta inte är oerhört långa och därför inte tar onödigt lång
	 * tid att bearbeta. När delarna för hashkoden genereras i ISBN-klassen och MyString-klassen multipliceras
	 * även dessa med primtal för att ytterligare stärka kollisionshanteringen. Då flera titlar och isbn kan
	 * innehålla samma karaktärer utförs en multiplikation innan varje nytt tillägg. Detta då risken att samma
	 * värde skulle uppkomma minskar drastiskt.Detta är inte nödvändigt men det kostar oss inget extra att
	 * implementera detta och det kan i vissa fall behövas.
	 *
	 * @return hCode, the generated hashCode for the book
	 */

	@Override
	public int hashCode(){
		int hCode = 0;
		hCode += title.hashCode() * isbn.hashCode();
		return hCode;
	}

	public boolean equals(Object comp){
		if(comp instanceof Book book)
			return book.getIsbn().equals(isbn) && book.getTitle().equals(title);
		return false;
	}

	@Override
	public String toString() {
		return String.format("\"%s\" by %s Price: %d ISBN: %s lenght: %s", title, author, price, isbn,
				content.length());
	}

}
