package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.MyOfferManager;
import businessLogic.OfferManager;
import domain.Offer;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
//import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.awt.event.ActionEvent;
//import javax.swing.JList;

public class OfferBooking extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblCity;
	private JLabel lblYear;
	private JLabel lblMonth;
	private JLabel lblDay;
	private JLabel lblRoomType;
	private JLabel searchResult;
	
	private JTextField city;
	
	private JTextField day;
	private JComboBox<String> month;
	private DefaultComboBoxModel<String> monthNames = new DefaultComboBoxModel<String>();
	private JTextField year;
	
	private JRadioButton tripleRooms;
	private JRadioButton doubleRooms;
	private JRadioButton singleRooms;
	
	private ButtonGroup fareButtonGroup = new ButtonGroup();  
	
	private JButton lookforOffers;
	
	private DefaultComboBoxModel<Offer> offerInfo = new DefaultComboBoxModel<Offer>();
	private JComboBox<Offer> offerList = null;
	
	private JButton bookOffer = null;
	
	private OfferManager businessLogic;  

	private Collection<Offer> offerCollection;
	
//	private Offer selectedOffer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OfferBooking frame = new OfferBooking();
					frame.setBusinessLogic(new MyOfferManager());
					frame.setVisible(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Custom operations
	public void setBusinessLogic(OfferManager g) {businessLogic = g;}
	
	private Date newDate(int year,int month,int day) {

	     Calendar calendar = Calendar.getInstance();
	     calendar.setLenient(false); // To avoid use heuristics to parse inputs as dates
	     calendar.set(year, month, day,0,0,0);
	     calendar.set(Calendar.MILLISECOND, 0);

	     return calendar.getTime();
	}
	
	/**
	 * Create the frame.
	 */
	public OfferBooking() {
		setTitle("Rural Houses Offer Book");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCity = new JLabel("City:");
		lblCity.setBounds(21, 22, 61, 16);
		contentPane.add(lblCity);
		
		city = new JTextField();
		city.setText("Donostia");
		city.setBounds(64, 17, 243, 26);
		contentPane.add(city);
		city.setColumns(10);
		
		lblYear = new JLabel("Year:");
		lblYear.setBounds(21, 55, 33, 16);
		contentPane.add(lblYear);
		
		lblMonth = new JLabel("Month:");
		lblMonth.setBounds(117, 55, 50, 16);
		contentPane.add(lblMonth);
	    
		month = new JComboBox<String>();
		month.setBounds(163, 51, 116, 27);
		contentPane.add(month);
		month.setModel(monthNames);
		
		monthNames.addElement("January");
		monthNames.addElement("February");
		monthNames.addElement("March");
		monthNames.addElement("April");
		monthNames.addElement("May");
		monthNames.addElement("June");
		monthNames.addElement("July");
		monthNames.addElement("August");
		monthNames.addElement("September");
		monthNames.addElement("October");
		monthNames.addElement("November");
		monthNames.addElement("December");
		month.setSelectedIndex(0);
		
		lblDay = new JLabel("Day:");
		lblDay.setBounds(291, 55, 38, 16);
		contentPane.add(lblDay);
		
		day = new JTextField();
		day.setText("20");
		day.setBounds(331, 50, 50, 26);
		contentPane.add(day);
		day.setColumns(10);
		
		lblRoomType = new JLabel("Room Type:");
		lblRoomType.setBounds(21, 83, 84, 16);
		contentPane.add(lblRoomType);
		
		
		tripleRooms = new JRadioButton("Triple");
		tripleRooms.setSelected(true);
		fareButtonGroup.add(tripleRooms);
		tripleRooms.setBounds(99, 79, 68, 23);
		contentPane.add(tripleRooms);
		
		doubleRooms = new JRadioButton("Double");
		fareButtonGroup.add(doubleRooms);
		doubleRooms.setBounds(173, 79, 77, 23);
		contentPane.add(doubleRooms);
		
		singleRooms = new JRadioButton("Single");
		fareButtonGroup.add(singleRooms);
		singleRooms.setBounds(265, 79, 77, 23);
		contentPane.add(singleRooms);
		
		bookOffer = new JButton("Book an offer");
		bookOffer.setEnabled(false);
		
		lookforOffers = new JButton("Look for Concrete Offers");
		lookforOffers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) throws IllegalArgumentException {
				offerInfo.removeAllElements();
				try {
					Date date = newDate(Integer.parseInt(year.getText()),month.getSelectedIndex(),Integer.parseInt(day.getText()));
					Date actualDate = new Date();
					if (date.compareTo(actualDate) < 0) {
						throw new IllegalArgumentException();
					}
					offerCollection = businessLogic.getConcreteOffers(city.getText(),date);
					for (Offer v : offerCollection)  offerInfo.addElement(v); 
					if (offerCollection.isEmpty()) {
						searchResult.setText("No offers in that city in that date");
					} else { 
						searchResult.setText("Choose an available offer in this list:");
						bookOffer.setEnabled(true);
						offerList.setSelectedIndex(0);
					}
				} catch (IllegalArgumentException error) {
					searchResult.setText("The date introduced is wrong!");
				}
			}
		});
		
		lookforOffers.setBounds(91, 114, 261, 40);
		contentPane.add(lookforOffers);	
		
		searchResult = new JLabel("", SwingConstants.CENTER);
		searchResult.setBounds(99, 164, 243, 16);
		contentPane.add(searchResult);
		
		offerList = new JComboBox<Offer>();
		offerList.setBounds(57, 190, 336, 40);
		contentPane.add(offerList);
		offerList.setModel(offerInfo);
		
		offerList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) throws NullPointerException {
				if (bookOffer.isEnabled()) {
					try {
						Offer actualOffer = (Offer) offerList.getSelectedItem();
						bookOffer.setText("Book this offer '" + actualOffer.toString() + "'");
					} catch (NullPointerException error) {
						searchResult.setText("The date introduced has invalid format");
						bookOffer.setEnabled(false);
					}
					
				}
			}
		});	
		
		bookOffer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num=0;
				boolean error=false;
				if (tripleRooms.isSelected()) {
					Offer offer = (Offer) offerList.getSelectedItem();
					num = offer.getTripleNumber();
					if (num>0) offer.setTripleNumber(num-1); else error=true; 
				}
				else if (doubleRooms.isSelected()) {
					Offer offer = (Offer) offerList.getSelectedItem();
					num = offer.getDoubleNumber();
					if (num>0) offer.setDoubleNumber(num-1); else error=true;
				}
				else if (singleRooms.isSelected()) {
					Offer offer = (Offer) offerList.getSelectedItem();
					num = offer.getSingleNumber();
					if (num>0) { offer.setSingleNumber(num-1); businessLogic.updateOffer(offer); }else error=true;
				}
				if (error) bookOffer.setText("No available offers for that room type");
				else bookOffer.setText("Booked. #rooms left: "+(num-1));
				bookOffer.setEnabled(false);
			}
		});
		bookOffer.setBounds(21, 260, 399, 40);
		contentPane.add(bookOffer);

		year = new JTextField();
		year.setText("2022");
		year.setBounds(57, 50, 50, 26);
		contentPane.add(year);
		year.setColumns(10);
	}
}
