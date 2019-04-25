import simulation.*;
import java.io.IOException;
import internals.*;
import utility.*;

public class Main {

	/**
	 * Determine the count of days.
	 * @param transactions An array of transactions
	 * @return Number of unique days.
	 */
	private static int determineDateCount(Transaction[] transactions) {
		int numberOfDates = 0;
		String current_date = "";
		for (Transaction transaction : transactions) {
			if (!transaction.getDate().equals(current_date)) {
				current_date = transaction.getDate();
				numberOfDates++;
			}
		}
		return numberOfDates;
	}

	/**
	 * Determine the names of the unique dates
	 * @param transactions An array of transactions
	 * @param dates An empty array for days.
	 */
	private static void determineDates(Transaction[] transactions, String[] dates) {
		String current_date = "";
		int i = 0;
		for (Transaction transaction : transactions) {
			if (!transaction.getDate().equals(current_date)) {
				current_date = transaction.getDate();
				dates[i] = current_date;
			}
		}
	}
	
	/**
	 * Initilise the transactionQueues as empty.
	 * @param transactionQueues - An array of transaction queues.
	 * @param dates An array of dates.
	 */
	private static void initialiseTransactionQueues(TransactionQueue[] transactionQueues, String[] dates) {
		for (int i = 0; i < dates.length; i++) {
			transactionQueues[i] = new TransactionQueue(dates[i]);
		}
	}

	/**
	 * Put the transaction to the right transactionQueue with apporiprate date.
	 * @param transactionQueues An array of transaction queues.
	 * @param transaction A transaction.
	 */
	private static void distribute(TransactionQueue[] transactionQueues, Transaction transaction) {
		String date = transaction.getDate();
		for (TransactionQueue transactionQueue : transactionQueues) {
			if (date.equals(transactionQueue.getDate())) {
				transactionQueue.insert(transaction);
				break;
			}
		}
	}

	public static void main(String[] args) {
		
		TQL transactionQueueList = new TQL();
		Transaction[] transactions = null;
		try {
			transactions = Converter.toTransactions("CENG112_HW3_Transactions");
		} catch (IOException e) {
			System.out.println("There is an error in IO.");
		}
		int numberOfDays = determineDateCount(transactions);
		TransactionQueue[] transactionQueues = new TransactionQueue[numberOfDays];
		String[] dates = new String[numberOfDays];
		determineDates(transactions, dates);
		initialiseTransactionQueues(transactionQueues, dates);
		for (Transaction transaction : transactions) {
			distribute(transactionQueues, transaction);
		}
		for (TransactionQueue transactionQueue: transactionQueues) {
			transactionQueueList.add(transactionQueue);
		}
		
		transactionQueues = null;
		transactions = null;
		dates = null;
		
		System.out.println(transactionQueueList);

	}

}
