import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.*;
import java.io.File;
import javax.swing.JFileChooser;


/*
 * Program: Aplikacja okienkowa GUI, umozliwiajaca testowanie metod
 * zawartych w klasie Games. Ponadto zostaly wykonane dwa dodatkowe zadania.
 * 
 * Plik: GamesWindowsApp.java
 * 
 * Autor: Jan Niewiarowski
 * Nr indeksu: 210828
 * Data: Pazdziernik 2018
 *  
 */

public class GamesWindowsApp extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;

	
	// powitalny napis
	
	private static final String MESSAGE = 
			"Program: Games - wersja okienkowa   \n" + 
			"Autor: Jan Niewiarowski           \n" + 
			"Nr indeks: 225948                    \n" +		
			"Data:  pazdziernik 2018 r.        \n";
	
	
	// mozliwe jest otworzenie kilku okien aplikacji, niezaleznych od siebie
	
	public static void main(String[] args) {
		
		new GamesWindowsApp(); // reprezentuje glowne okno obiektu

	
	}
	
	
	
	private Games currentGame; // referencja do obiektu, ktorym jest obecna gra
	
		
	
        
     
		// Ustawienie fontu dla etykiet
		Font font = new Font("MonoSpaced", Font.ITALIC | Font.BOLD , 14);
		// Ustawienie fontu dla napisow na przyciskach
		Font font1 = new Font("Dialog", Font.BOLD  | Font.PLAIN, 15);
		
		
		// Etykiety wyswietlane w glownym oknie aplikacji okienkowej
		JLabel gameNameLabel = new JLabel("Nazwa gry: ");
		JLabel factoryNameLabel = new JLabel("Nazwa wytworni: ");
		JLabel publicationYearLabel = new JLabel("Data wydania: ");
		JLabel usersRatingLabel = new JLabel("Ocena uzytkownikow: ");
		JLabel gamePopularityLabel = new JLabel("Popularnosc gry: ");
		JLabel gameTypeLabel = new JLabel("Gatunek gry: ");

		// Pola tekstowe
		JTextField gameNameField = new JTextField(10);
		JTextField factoryNameField = new JTextField(10);
		JTextField publicationYearField = new JTextField(10);
		JTextField usersRatingField = new JTextField(10);
		JTextField gamePopularityField = new JTextField(10);
		JTextField gameTypeField = new JTextField(10);

		// Przyciski
		JButton newGameButton    = new JButton("Dodaj nowa gre");
		JButton editGameButton   = new JButton("Zmien dane gry");
		JButton saveGameButton   = new JButton("Zapisz do pliku");
		JButton loadGameButton   = new JButton("Wczytaj z pliku");
		JButton deleteGameButton = new JButton("Usun gre z bazy");
		JButton programInfoButton   = new JButton("O programie");
		JButton programExitButton   = new JButton("Zakoncz aplikacje");

		// utworzenie menu
		 JMenu[] menu = { new JMenu("Opcje"),
				new JMenu("O programie")
				};
		// utworzenie obiektow w menu
		 JMenuItem[] items = { new JMenuItem("Dodaj nowa gre"),
				new JMenuItem("Zmien dane gry"),
				new JMenuItem("Zapisz do pliku"),
				new JMenuItem("Wczytaj z pliku"),
				new JMenuItem("Usun gre z bazy"),
				new JMenuItem("Zakoncz aplikacje"),
				new JMenuItem("Informacje o autorze")
				
		};
		
		// Utworzenie glownego okna programu
		public GamesWindowsApp(){
			
			// Konfiguracja okna
			setTitle("Aplikacja Games - Jan Niewiarowski");  
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
			// rozmiar okna
			setSize(210, 640);
			// uniemozliwienie zmiany rozmiaru
			setResizable(false);
			// obiekty w oknie nie zmieniaja polozenia 
			setLocationRelativeTo(null);

			// Zmiana domyslnego fontu dla wszystkich etykiet
			gameNameLabel.setFont(font);
			factoryNameLabel.setFont(font);
			publicationYearLabel.setFont(font);
			usersRatingLabel.setFont(font);
			gamePopularityLabel.setFont(font);
			gameTypeLabel.setFont(font);
			
			
			// ustawienie rodzaju czcionki na przyciskach
			newGameButton.setFont(font1); 
			editGameButton.setFont(font1); 
			saveGameButton.setFont(font1); 
			loadGameButton.setFont(font1); 
			deleteGameButton.setFont(font1); 
			programInfoButton.setFont(font1); 
			programExitButton.setFont(font1); 
			
			// Zablokowanie edytowania tekst we wszystkich polach programu
			gameNameField.setEditable(false);
			factoryNameField.setEditable(false);
			publicationYearField.setEditable(false);
			usersRatingField.setEditable(false);
			gamePopularityField.setEditable(false);
			gameTypeField.setEditable(false);
			
			// Dodanie sluchaczy zdarzen do wszystkich przyciskow
			newGameButton.addActionListener(this);
			editGameButton.addActionListener(this);
			saveGameButton.addActionListener(this);
			loadGameButton.addActionListener(this);
			deleteGameButton.addActionListener(this);
			programInfoButton.addActionListener(this);
			programExitButton.addActionListener(this);
			
			
		          
		        
		   
			
			
			// Dodanie sluchaczy zdarzen do wszystkich obiektow w menu
			for (int i = 0; i < items.length; i++)
	        	items[i].addActionListener(this);

			// Utworzenie panelu glownego dla okna aplikacji
			
			JPanel panel = new JPanel();
			
			// zmienne potrzebne przy ustalaniu koloru
			int a=200,b=200,c=255;
			
			// zmiana koloru okna
			panel.setBackground(new Color(a,b,c));
			int d=110,e=20,f=255;
			
			// Nadanie kolorow napisow na przyciskach
			newGameButton.setForeground(new Color(d,e,f)); 
			editGameButton.setForeground(new Color(d,e,f)); 
			saveGameButton.setForeground(new Color(d,e,f)); 
			loadGameButton.setForeground(new Color(d,e,f)); 
			deleteGameButton.setForeground(new Color(d,e,f)); 
			programInfoButton.setForeground(new Color(d,e,f)); 
			programExitButton.setForeground(new Color(d,e,f)); 
			
			
		// Dodanie i rozmieszczenie na panelu wszystkich komponentow
			
		// Ustawienie ilosci obiektow w menu
			menu[0].add(items[0]); 
		    menu[0].add(items[1]);
		    menu[0].add(items[2]);
		    menu[0].add(items[3]);
		    menu[0].add(items[4]);
		    menu[0].addSeparator();
		    menu[0].add(items[5]);
		    menu[1].add(items[6]);
		    
		// pokazanie menu
		    JMenuBar menubar = new JMenuBar();
		    for (int i = 0; i < menu.length; i++)
		    menubar.add(menu[i]);
		    setJMenuBar(menubar);
		      	
			panel.add(newGameButton);
			panel.add(deleteGameButton);
			panel.add(saveGameButton);
			panel.add(loadGameButton);
			panel.add(editGameButton);
			
			panel.add(gameNameLabel);
			panel.add(gameNameField);
			
			panel.add(factoryNameLabel);
			panel.add(factoryNameField);
			
			panel.add(publicationYearLabel);
			panel.add(publicationYearField);
			panel.add(usersRatingLabel);
			panel.add(usersRatingField);
			
			panel.add(gamePopularityLabel);
			panel.add(gamePopularityField);
			
			panel.add(gameTypeLabel);
			panel.add(gameTypeField);
			
			panel.add(programInfoButton);
			panel.add(programExitButton);
			
			// Umieszczenie Panelu w glownym oknie aplikacji.
			setContentPane(panel);
			
			// Wypelnienie pol informacjami o aktualnej grze.
			showCurrentGame();
			
			// Pokazanie na ekranie glownego okna aplikacji
	      setVisible(true);
		}
	
			// Metoda, ktora pokazuje dane o aktualnej grze
		void showCurrentGame() {
			if (currentGame == null) {
				gameNameField.setText("");
				factoryNameField.setText("");
				publicationYearField.setText("");
				usersRatingField.setText("");
				gamePopularityField.setText("");
				gameTypeField.setText("");
			} else {
				gameNameField.setText(currentGame.getGameName());
				factoryNameField.setText(currentGame.getFactoryName());
				publicationYearField.setText("" + currentGame.getPublicationYear());
				usersRatingField.setText(""+currentGame.getUsersRating());
				gamePopularityField.setText("" + currentGame.getGamePopularity());
				gameTypeField.setText("" +currentGame.getType());
				
			}
		}
	
		@Override
		public void actionPerformed(ActionEvent event) {
			
			
			// Odczytanie referencji do obiektu, który wygenerowal zdarzenie w aplikacji
			Object eventSource = event.getSource();
		
			try { // gdy nacisniety przycisk nowej gry lub zostal wybrany z menu
				if (eventSource == newGameButton || eventSource == items[0] ) { 
					currentGame = GamesWindowsDialog.createNewGame(this);
				}
					// gdy nacisniety przycisk usuwanie gry lub opcja wybrana zostala w menu
				if (eventSource == deleteGameButton || eventSource == items[4]) {
					currentGame = null;
				}
					// gdy nacisniety przycisk zapisu danych o grze lub opcja zostala wybrana z menu
					// wykorzystujemy biblioteke JFileChooser
				if (eventSource == saveGameButton || eventSource == items[2]) {
					JFileChooser fileChooser1 = new JFileChooser();
			        int returnValue1 = fileChooser1.showOpenDialog(null);
			        if (returnValue1 == JFileChooser.APPROVE_OPTION) {
			            File selectedFile = fileChooser1.getSelectedFile();
			            String fileName = selectedFile.getName();
					Games.printToFile(fileName, currentGame);
			        	}
			        }
				// gdy nacisniety przycisk odczytu danych o grze lub opcja zostala wybrana z menu
				// wykorzystujemy biblioteke JFileChooser
				if (eventSource == loadGameButton || eventSource == items[3]) {
					
					JFileChooser fileChooser = new JFileChooser();
			        int returnValue = fileChooser.showOpenDialog(null);
			        if (returnValue == JFileChooser.APPROVE_OPTION) {
			            File selectedFile = fileChooser.getSelectedFile();
			            String fileName = selectedFile.getName();
			            
			            currentGame = Games.readFromFile(fileName);
			        }  
					
					
				}// gdy nacisniety przycisk edycji danych o grze lub opcja zostala wybrana z menu
				
				if (eventSource == editGameButton || eventSource == items[1]) {
					if (currentGame == null) throw new GamesException("Nie udalo sie zmienic danych"); 
					GamesWindowsDialog.changeGameData(this, currentGame);
				} // informacje o programie
				if (eventSource == programInfoButton || eventSource == items[6] ) {
					JOptionPane.showMessageDialog(this, MESSAGE);
				} // wyjscie z aplikacji
				if (eventSource == programExitButton || eventSource == items[5]) {
					System.exit(0);
				}
				
				// zglasza wyjatki 
			} catch (GamesException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			// aktualizuj pola tekstowe
			showCurrentGame();
		}	
	
}
