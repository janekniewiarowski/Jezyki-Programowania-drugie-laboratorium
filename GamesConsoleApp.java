import java.util.Arrays;


/*
 * Program: Aplikacja konsolowa, dzieki ktorej testujemy w widoczny sposob
 * 			dzialania na obiektach klasy Games.
 * 
 * Plik: GamesConsoleApp.java
 * 
 * Autor: Jan Niewiarowski
 * Numer indeksu: 210828
 * Data: pazdziernik 2018r.
 * 
 * 
 */

public class GamesConsoleApp{
	
	
	// Menu powitalne aplikacji
	private static final String MESSAGE =
			"Aplikacja Games \n" +
			"Jan Niewiarowski, 210828 \n" +
			"Pazdziernik 2018 r. \n" ;
	
	// Menu glowne naszej aplikacji
	private static final String MAIN_MENU =
			"MENU GLOWNE APLIKACJI \n" +
			"1. Wprowadz nowa gre. \n" +
			"2. Usun gre z bazy gier. \n" +
			"3. Modyfikuj informacje o grze. \n" +
			"4. Zapisz informacje o grze do pliku. \n" +
			"5. Wczytaj informacje o grze z pliku. \n" +
			"0. Wyjdz z aplikacji. \n" ;
	
	// Menu modyfikacji danych w aplikacji 
	private static final String CHANGE_MENU =
			"MODYFIKACJA DANYCH GRY\n" +
			"1. Zmien nazwe gry. \n" +
			"2. Zmien nazwe wytworni. \n" +
			"3. Zmien rok wydania. \n" +
			"4. Zmien gatunek gry. \n" +
			"5. Zmien ocene uzytkownikow. \n" +
			"6. Zmien popularnosc gry. \n" +
			"0. Wroc do MENU GLOWNEGO APLIKACJI. \n" ;
	
	
	/*
	 * ConsoleUserDialog jest to klasa pomocnicza, ktora zawiera metody
	 * pomocnicze podczas dialogu z uzytkownikiem w konsoli tekstowej.
	 */
	private static ConsoleUserDialog UI = new ConsoleUserDialog();
	
	public static void main(String[] args) {
		
		// utworzenie obiektu aplikacji konsolowej
		GamesConsoleApp application = new GamesConsoleApp();
		// uruchomienie glownej petli aplikacji
		application.runMainLoop();
		}
	
		// na poczatku wyzerowanie informacji o osobie
	private Games currentGame = null;
	
	private void runMainLoop(){
		
		// wyswietlnie powitalnych informacji
		UI.printMessage(MESSAGE);
		
		while(true) {
			UI.clearConsole();
			showCurrentGame();
			
			try {
				switch (UI.enterInt(MAIN_MENU + "===> ")) {
				
						// utworzenie nowej gry
				case 1: 
						currentGame = createNewGame();
						break;
				
						// usuniecie danych  wybranej grze
				case 2: 
						currentGame = null ;
						UI.printInfoMessage("Dane wybranej gry zostaly usuniete.");
						break;
						
						// zamiana danych aktualnej gry
				case 3:
						if(currentGame == null) throw new GamesException("W bazie nie ma zadnej gry.");
						changeGameData(currentGame);
						break;
						
						// zapisanie informacji o grze z pliku
				case 4:{
						String file = UI.enterString("Podaj nazwe pliku: ");
						Games.printToFile(file, currentGame);
						UI.printInfoMessage("Dane gry zostaly zapisane do podanego pliku.");
						}
						break;
						// wczytanie informacji o grze z pliku
				case 5: 
						{String file_name = UI.enterString("Podaj nazwe pliku: ");
						currentGame = Games.readFromFile(file_name);
						UI.printInfoMessage("Dane gry zostaly wczytane z podanego pliku.");
						}
						break;
						// wyjscie z programu
				case 0: 
						UI.printInfoMessage("\nProgram zakoñczy³ dzia³anie!");
						System.exit(0);
						
				} // koniec switch
			}
			catch (GamesException e) {
					UI.printErrorMessage(e.getMessage());
				}
				
		 } // koniec petli while
	}// koniec runMainLoop()
	
	// wyswietla aktualna gre
	void showCurrentGame(){
		showGame(currentGame);
	}
	
	// wyswietla gre
	static void showGame(Games game) {
		
		StringBuilder sb = new StringBuilder();
		
		if(game != null) {
			sb.append("Aktualna gra komputerowa: \n");
			sb.append( "Nazwa gry: " + game.getGameName() + "\n" );
			sb.append( "Nazwa wytworni: " + game.getFactoryName() + "\n" );
			sb.append( "Data wydania: " + game.getPublicationYear() + "\n" );
			sb.append( "Ocena gry: " + game.getUsersRating() + "\n" );
			sb.append( "Popularnosc: " + game.getGamePopularity() + "\n");
			sb.append( "Gatunek gry: " + game.getType() + "\n");
		}else
			sb.append("Brak danych o grze. ");
			UI.printMessage(sb.toString());
			}
	
	// metoda wczytuje dane o nowej grze, tworzy nowy obiekt, gettery i settery dodaja dane do obiektu
	static Games createNewGame(){
		String game_name = UI.enterString("Podaj nazwe gry:  ");
		String factory_name = UI.enterString("Podaj nazwe wytworni: ");
		String publication_year = UI.enterString("Podaj rok wydania gry: ");
		String users_rating = UI.enterString("Podaj ocene gry: ");
		String game_popularity = UI.enterString("Podaj popularnosc gry: ");
		UI.printMessage("Mozliwe gatunki gry:" + Arrays.deepToString(GamesType.values()));
		String game_type = UI.enterString("Podaj gatunek gry: ");
		Games game;
		try { 
			// Utworzenie nowego obiektu klasy Games oraz
			// ustawienie wartoœci wszystkich atrybutów.
			game = new Games(game_name,factory_name);
			game.setPublicationYear(publication_year);
			game.setUsersRating(users_rating);
			game.setGamePopularity(game_popularity);
			game.setType(game_type);
		} catch (GamesException e) {    
			// Tu s¹ wychwytywane wyj¹tki zg³aszane przez metody klasy Games
			// gdy nie s¹ spe³nione ograniczenia na³o¿one na dopuszczelne wartoœci 
			// poszczególnych atrybutów.
			// Drukowanie komunikatu o b³êdzie zg³oszonym za pomoc¹ wyj¹tku GamesException.
			UI.printErrorMessage(e.getMessage());
			return null;
		}
		return game;
	}
	
	static void changeGameData(Games game)
	{
		while (true) {
			UI.clearConsole();
			showGame(game);

			try {		
				switch (UI.enterInt(CHANGE_MENU + "==>> ")) {
				case 1:
					game.setGameName(UI.enterString("Podaj nazwe gry: "));
					break;
				case 2:
					game.setFactoryName(UI.enterString("Podaj nazwe wytworni: "));
					break;
				case 3:
					game.setPublicationYear(UI.enterString("Podaj date wydania: "));
					break;
				case 4:
					UI.printMessage("Mozliwe gatunki gry:" + Arrays.deepToString(GamesType.values()));
					game.setType(UI.enterString("Podaj gatunek gry: "));
					break;
				case 5:
					game.setUsersRating(UI.enterString("Podaj ocene gry: "));
					break;
				case 6:
					game.setGamePopularity(UI.enterString("Podaj popularnosc gry: "));
					break;
				case 0: return;
				}  // koniec instrukcji switch
			} catch (GamesException e) {     
				// Wyjatki klasy Games
				
				UI.printErrorMessage(e.getMessage());
			}
		}
	}
	
	
	
}


