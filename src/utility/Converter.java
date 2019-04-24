package utility;

// A tool that reads the transaction.txt file and converts them to an array type of transaction

import java.io.*;

import simulation.Corporate;
import simulation.ICustomer;
import simulation.Individual;
import simulation.NonRegistered;
import simulation.Transaction;

public class Converter {

	private static Transaction[] transactions;

	/**
	 * @return the transactions
	 */
	public static Transaction[] getTransactions() {
		return transactions;
	}

	/**
	 * @param transactions the transactions to set
	 */
	public static void setTransactions(Transaction[] transactions) {
		Converter.transactions = transactions;
	}

	/**
	 * reads file with buffered reader
	 * 
	 * @param filename
	 * @return a file with type of BufferedReader
	 */
	private static BufferedReader readFile(String filename) {
		BufferedReader file = null;
		try {
			file = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return file;
	}

	/**
	 * counts line number of the file
	 * 
	 * @param fileName
	 * @return number of the lines
	 */
	private static int countLines(String fileName) {
		BufferedReader file = readFile(fileName);
		int lineCount = 0;
		try {
			while (file.readLine() != null) {
				lineCount++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lineCount;
	}

	/**
	 * main method for convert the transactions into our array
	 * 
	 * @param fileName
	 * @return converted transaction array
	 * @throws IOException
	 */
	public static Transaction[] toTransactions(String fileName) throws IOException {
		BufferedReader file = readFile(fileName);
		int lineCount = countLines(fileName);
		transactions = new Transaction[lineCount]; // line number means transaction number
		int waiting = 0; // waiting time initialize
		// loop for every transaction
		for (int i = 0; i < lineCount; i++) {
			String singleLine = file.readLine();
			String[] splitLine = singleLine.split(","); // splits by comma (by all attributes)
			String date = splitLine[0];
			int id = Integer.parseInt(splitLine[1]);
			String customerType = splitLine[2];
			ICustomer customer = null;
			// determine the customer type
			switch (customerType) {
			case "NON-REGISTERED":
				customer = new NonRegistered();
				break;
			case "CORPORATE":
				customer = new Corporate();
				break;
			case "INDIVIDUAL":
				customer = new Individual();
				break;
			}
			int occupation = Integer.parseInt(splitLine[3]);
			// sums the waiting time
			if (i == 0 || transactions[i - 1].getDate() != transactions[i].getDate()) { // TODO not sure about that
				waiting = 0;
			} else {
				waiting += transactions[i - 1].getOccupation();
			}
			// bind the transaction nodes
			if (i < lineCount) {
				transactions[i - 1].setNext(transactions[i]);
			}
			transactions[i] = new Transaction(date, id, customer, occupation, waiting);
		}
		return transactions;
	}

}
