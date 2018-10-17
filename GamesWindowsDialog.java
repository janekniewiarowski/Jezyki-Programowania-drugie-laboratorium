import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GamesWindowsDialog extends JDialog implements ActionListener {


	private static final long serialVersionUID = 1L;
	
	private Games game; //referencja do obiektu klasy Games
	
	Font font = new Font("MonoSpaced", Font.ITALIC | Font.BOLD, 14);


	// Utworzenie i inicjalizacja komponentów 
	JLabel gameNameLabel = new JLabel("Nazwa gry: ");
	JLabel factoryNameLabel = new JLabel("Nazwa wytworni: ");
	JLabel publicationYearLabel = new JLabel("Data wydania: ");
	JLabel usersRatingLabel = new JLabel("Ocena uzytkownikow: ");
	JLabel gamePopularityLabel = new JLabel("Popularnosc gry: ");
	JLabel gameTypeLabel = new JLabel("Gatunek gry: ");

	// Etykietowanie
	JTextField gameNameField = new JTextField(10);
	JTextField factoryNameField = new JTextField(10);
	JTextField publicationYearField = new JTextField(10);
	JTextField usersRatingField = new JTextField(10);
	JTextField gamePopularityField = new JTextField(10);
	JTextField gameTypeField = new JTextField(10);
	JComboBox<GamesType> typeBox = new JComboBox<GamesType>(GamesType.values());
	

	// Przyciski pomocnicze wyswietlane na panelu
	JButton okButton = new JButton("OK");
	JButton cancelButton = new JButton("Cancel");
	
	
	
	
	private GamesWindowsDialog(Window parent, Games game) {

		super(parent, Dialog.ModalityType.DOCUMENT_MODAL);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);// Konfiguracja parametrów tworzonego okna dialogowego
		setSize(270, 240); // ustawienie wymiarow
		
		setLocationRelativeTo(parent);
		
		this.game = game;
		
		// ustawienie tytulu okna, wypelnienie pol tekstowych 
		if (game==null){
			setTitle("Nowa gra");
		} else{
			setTitle(game.toString());
			gameNameField.setText(game.getGameName());
			factoryNameField.setText(game.getFactoryName());
			publicationYearField.setText(""+game.getPublicationYear());
			usersRatingField.setText(""+game.getUsersRating());
			gamePopularityField.setText(""+game.getUsersRating());
			typeBox.setSelectedItem(game.getType());
		}
		
		// dodanie dwoch przyciskow OK oraz Cancel do sluchacza zdarzen
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		JPanel panel = new JPanel(); // utworzenie panelu
		
		
		// zmienne potrzebne przy ustalaniu koloru
			int a=200,b=200,c=255;
					
		// zmiana koloru okna
			panel.setBackground(new Color(a,b,c));

		// Dodanie i rozmieszczenie na panelu wszystkich komponentów
		
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
		panel.add(typeBox);

		panel.add(okButton);
		panel.add(cancelButton);
		
		setContentPane(panel);// Umieszczenie Panelu w oknie dialogowym
		
		setVisible(true);		// Pokazanie na ekranie okna dialogowego
	
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {

		// Odczytanie referencji do obiektu, który wygenerowal zdarzenie
		Object source = event.getSource();
		
		if (source == okButton) {
			try {
				if (game == null) { 
					game = new Games(gameNameField.getText(), factoryNameField.getText());// Utworzenie nowej gry
				} else { 
					game.setGameName(gameNameField.getText());// Zmiana danych o nazwie gry
					game.setFactoryName(factoryNameField.getText());// Zmiana danych o nazwie wytworni
				}
				game.setPublicationYear(publicationYearField.getText());// Zmiana danych o roku publikacji gry
				game.setUsersRating(usersRatingField.getText());// Zmiana danych o ocenie gry
				game.setGamePopularity(gamePopularityField.getText()); // Zmiana danych o popularnosci gry
				game.setType((GamesType) typeBox.getSelectedItem());// Zmiana danych o gatunku gry
				
				dispose();// Zamkniecie okna aplikacji
			} catch (GamesException e) { // wychwytuje wyjatki
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
			// jezeli klikniemy cancel 
		if (source == cancelButton) {
			// Zamkniecie okna aplikacji
			dispose();
		}
			
	}
	public static Games createNewGame(Window parent) {
		GamesWindowsDialog dialog = new GamesWindowsDialog(parent, null);
		return dialog.game;
	}
	
	public static void changeGameData(Window parent, Games game) {
		new GamesWindowsDialog(parent, game);
	}
}