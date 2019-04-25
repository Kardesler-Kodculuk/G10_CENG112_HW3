import simulation.*;
import java.io.IOException;
import internals.*;
import utility.*;

public class BankApp {

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
				i++;
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

	/**
	 * Updates the stats according to transaction.
	 * @param stats Stats array
	 * @param transaction Current transaction.
	 */
	private static void updateStats(String[] stats, Transaction transaction) {
		stats[0] = String.valueOf(Integer.parseInt(stats[0]) + 1);
		stats[1] = String.valueOf(Integer.valueOf(stats[1]) + transaction.getWaiting());
		String typeof = transaction.getCustomer().getType();
		switch (typeof) {
		case "Corporate":
			stats[3] = String.valueOf(Integer.parseInt(stats[3]) + 1);
			stats[4] = String.valueOf(Integer.valueOf(stats[4]) + transaction.getWaiting());
			break;
		case "Individual":
			stats[6] = String.valueOf(Integer.parseInt(stats[6]) + 1);
			stats[7] = String.valueOf(Integer.valueOf(stats[7]) + transaction.getWaiting());
			break;
		case "Non-Registered":
			stats[9] = String.valueOf(Integer.parseInt(stats[9]) + 1);
			stats[10] = String.valueOf(Integer.valueOf(stats[10]) + transaction.getWaiting());
			break;
		default:
			System.err.println("Error occured, type undetermined.");
			break;
		}
	}
	
	/**
	 * Calculate the avarages of the stats
	 * @param stats Stats from which the avarages will be calculated.
	 */
	private static void finaliseStats(String[] stats) {
		int[] indices = {2, 5, 8, 11};
		for (int index : indices) {
			try {
				stats[index] = ((Float) (Float.valueOf(stats[index - 1]) / Float.valueOf(stats[index - 2]))).toString();
			} catch (ArithmeticException e) {
				continue;
			}
		}
	}

	/**
	 * Iteratively dequeue transactions one by one, while keeping statistics.
	 * @param transactionQueue Current transaction queue.
	 * @return Statistics generated for the day.
	 */
	private static String[] unravelTQ(TransactionQueue transactionQueue) {
		String[] stats = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
		Transaction current_transaction;
		while (!transactionQueue.isEmpty()) {
			current_transaction = transactionQueue.remove();
			updateStats(stats, current_transaction);
		}
		finaliseStats(stats);
		return stats;
	}
	
	/**
	 * Print the daily report.
	 * @param date Date of which the daily report will be produced for
	 * @param stats Statistics from which it will be produced.
	 */
	private static void printPeriodicOutput(String date, String[] stats) {
		String template = "\nTotal transaction count in " + date + " → %s \n" +
						  "Total waiting time in " + date + " → %s \n" +
						  "Average waiting time in " + date + " →  %s \n" +
						  "Total transaction count for CORPORATE customer in " + date +
						  " → %s \n" +
						  "Total waiting time for CORPORATE customer in " + date +
						  " → %s \n" +
						  "Average waiting time for CORPORATE customer in " + date +
						  " → %s \n" +
						  "Total transaction count for INDIVIDUAL customer in " + date +
						  " → %s \n" +
						  "Total waiting time for INDIVIDUAL customer in " + date +
						  " → %s \n" +
						  "Average waiting time for INDIVIDUAL customer in " + date +
						  " → %s \n" +
						  "Total transaction count for NON-REGISTERED customer in " + date +
						  " → %s \n" +
						  "Total waiting time for NON-REGISTERED customer in " + date +
						  " → %s \n" +
						  "Average waiting time for NON-REGISTERED customer in " + date +
						  " → %s \n" +
						  "The date with highest workload (max total waiting time) → %s \n";
		String output = String.format(template, (Object[]) stats);
		System.out.println(output);
	}

	/**
	 * Unravel the tql, taking the transaction queues out one by one.
	 * @param transactionQueueList
	 */
	private static void unravelTQL(TQL transactionQueueList) {
		TransactionQueue currentTQ;
		String maximum_day = "";
		float maximum_workload = 0; 
		for (int i = 1; i <= transactionQueueList.getLength(); i++) {
			currentTQ = transactionQueueList.getTQ(i);
			String[] stats = unravelTQ(currentTQ);
			if (maximum_workload <= Float.parseFloat(stats[1])) {
				maximum_day = currentTQ.getDate();
				maximum_workload = Float.parseFloat(stats[1]);
			}
			stats[12] = maximum_day;
			printPeriodicOutput(currentTQ.getDate(), stats);
		}
	}

	public static void main(String[] args) {
		
		TQL transactionQueueList = new TQL();
		Transaction[] transactions = null;
		try {
			transactions = Converter.toTransactions("CENG112_HW3_Transactions.txt");
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

		System.out.println(transactionQueueList);
		unravelTQL(transactionQueueList);

	}

}
