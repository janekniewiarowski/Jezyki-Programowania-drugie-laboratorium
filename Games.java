import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/*	
 * Program: Operacje na obiektach klasy Games
 *	Plik: 	Games.java
 *		  	definicja typu wyliczeniowego GamesType
 *			definicja klasy GamesException
 *			definicja publicznej klasy Games
 *
 *	Autor programu: Jan Niewiarowski
 *	Numer indeksu: 	210828
 *	Data wykonania: Pazdziernik 2018r		 						
 * 			
 */


/*
 * Typ wyliczeniowy GamesType reprezentuje rodzaj gatunek gry,
 * ktora znajduje sie w naszym programie. 
 * 
 */
enum GamesType{
	
	UNKNOWN("-----"),
	MMORPG("RPG"),
	ACTION("Akcja"),
	RACING("Wyscigowa"),
	TOWERDEFENSE("Obrona Wiezy"),
	SHOOTER("Strzelanka"),
	SPORT("Sportowa"),
	FIGHTING("Bijatyka");
	
	String gamesType;
	
	private GamesType(String games_type) {
		gamesType = games_type;
	}
	
	@Override
	public String toString() {
		return gamesType;
	}
} // Koniec klasy enum GamesType


/*
 * Klasa GamesException jest klasa, ktora zglasza wyjatki przy operacjach
 * wykonywanych na obiektach klasy Games
 */
class GamesException extends Exception {

	private static final long serialVersionUID = 1L;

	public GamesException(String message) {
		super(message);
	}
} // Koniec klasy GameException

/*
 * Klasa Games reprezentuje gry, ktore opisane sa za pomoca szesciu
 * atrybutow, sa to: gatunek gry, data wydania gry, nazwa wytworni, nazwa gry, ocena uzytkownikow oraz popularnosc.
 * W ponizszej klasie przyjeto pewne ograniczenia:
 * - pole gameType musi zawierac tylko jeden gatunek gry, ktory zostal zdefiniowany,
 * - data wydania gry nie moze byc pozniejsza niz rok terazniejszy,
 * - nazwa wytworni oraz wymagania sprzetowe musza byc niepustym ciagiem znakow,
 * - ocena uzytkownikow oraz popularnosc musza byc z przedzialu [0,10].
 * 
 * Jezeli ktorys z wyjatkow wystapi podczas dzialania programu uzytkownik zostanie o tym powiadomiony 
 * i takie dzialania zostana odrzucone
 * 
 */
public class Games{
	// deklarowanie zmiennych
	private GamesType type;
	private int publicationYear;
	private String factoryName;
	private String gameName;
	private int usersRating;
	private int gamePopularity;
	
	public Games(String game_name, String factory_name) throws GamesException{
		setGameName(game_name);
		setFactoryName(factory_name);
		type = GamesType.UNKNOWN;
	}
	
	public String getGameName(){
		return gameName;
	}
	
	public void setGameName(String game_name) throws GamesException{
		if(game_name == null || game_name.equals(""))
			throw new GamesException("Pole z nazwa gry musi zostac wypelnione.");
		this.gameName = game_name;
	}
	
	public String getFactoryName(){
		return factoryName;
	}
	
	public void setFactoryName(String factory_name) throws GamesException{
		if(factory_name == null || factory_name.equals(""))
			throw new GamesException("Pole z nazwa wytworni musi zostac wypelnione.");
		this.factoryName = factory_name;
	}
	
	
	
	public int getPublicationYear() {
		return publicationYear;
	}
	
	public void setPublicationYear(int publication_year) throws GamesException{
		if(publication_year<0 || publication_year>2018)
			throw new GamesException("Wprowadza prawidlowa date wydania gry.");
		this.publicationYear = publication_year;
	}
	
	public void setPublicationYear(String publication_year) throws GamesException{
		if(publication_year==null || publication_year == " " ) {// wtedy data wydania jest nieznana
		setPublicationYear(0);
		return;
	}
	try {
		setPublicationYear(Integer.parseInt(publication_year));
	}catch (NumberFormatException e) {
		throw new GamesException ("Data wydania musi byc calkowita.");
	}}
	
	public double getUsersRating() {
		return usersRating;
	}
	
	public void setUsersRating(int users_rating) throws GamesException{
		if(users_rating<0 || users_rating>10)
			throw new GamesException("Wprowadz ocene w zakresie <0,10>");
		this.usersRating = users_rating;
	}
	
	public void setUsersRating(String users_rating) throws GamesException{
		if(users_rating == null || users_rating ==" ") {
			setUsersRating(0);
			return;
	}
	try {
		setUsersRating(Integer.parseInt(users_rating));
	}catch (NumberFormatException e) {
		throw new GamesException ("Ocena musi byc liczba calkowita.");
	}}
	
	public double getGamePopularity() {
		return gamePopularity;
	}
	
	public void setGamePopularity(int game_popularity) throws GamesException{
		if(game_popularity<0 || game_popularity>10)
			throw new GamesException("Wprowadz popularnosc w zakresie <0,10>");
		this.gamePopularity = game_popularity;
	}
	
	public void setGamePopularity(String game_popularity) throws GamesException{
		if(game_popularity == null || game_popularity ==" ") {
			setGamePopularity("Nieznana");
		return;
		}
		try {
			setGamePopularity(Integer.parseInt(game_popularity));
		}catch (NumberFormatException e) {
			throw new GamesException ("Popularnosc gry musi byc liczba calkowita.");
		}}
			
	public GamesType getType() {
		return type;
	}
	
	public void setType(GamesType type) {
		this.type = type;
	}
	
	public void setType(String game_type) throws GamesException{
		if(game_type == null || game_type.equals("")){
			this.type = GamesType.UNKNOWN;
			return;
		}
		for(GamesType type : GamesType.values()) {
			if(type.gamesType.equals(game_type)) {
				this.type = type;
				return;
			}
		}
	}
	
	
	@Override
	public String toString() {
		return gameName + " " + factoryName;
	}
	public static void printToFile(PrintWriter writer, Games game) {
		writer.println(game.gameName + " " + game.factoryName +
				" " + game.publicationYear + " " + game.usersRating +
				" " + game.gamePopularity + " " + game.type);
	}
	
	public static void printToFile(String file, Games game) throws GamesException{
		try(PrintWriter writer = new PrintWriter(file + ".txt")){
			printToFile(writer,game);
		}catch (FileNotFoundException e) {
			throw new GamesException("Nie znaleziono podanego pliku.");
		}
	}
	
	public static Games readFromFile(BufferedReader reader) throws GamesException{
		try {
			String line = reader.readLine();
			String[] txt = line.split(" ");
			Games game = new Games(txt[0],txt[1]);
			game.setPublicationYear(txt[2]);
			game.setUsersRating(txt[3]);
			game.setGamePopularity(txt[4]);
			game.setType(txt[5]);
			return game;
		}catch(IOException e) {
			throw new GamesException("Nie udalo sie wczytac danych z pliku.");
		}
	}
	
	public static Games readFromFile(String file_name) throws GamesException{
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(file_name)))) {
			return Games.readFromFile(reader);
		} catch (FileNotFoundException e){
			throw new GamesException("Nie odnaleziono pliku " + file_name);
		} catch(IOException e){
			throw new GamesException("Nie udalo sie odczytac pliku.");
		}	
	}
	
	
	
	
 
	
}
















