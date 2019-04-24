from typing import List, Dict, Tuple
from simulation.simulation_os import position_test_files, execute_java_code
from simulation.simulation_parts import load_transactions
from simulation.simulation_classes import Transaction, TransactionQueue
from simulation.simulation_parser import Parser


class Main:
    def __init__(self, file_name: str):
        self.transactions: List[TransactionQueue] = []
        self.file_name: str = file_name
        self.parsed_output: Tuple[List, Dict] = ([], {})
        self.stats = {}

    def ready_os(self) -> None:
        """
        Ready the operating system for testing purposes.
        :return: None
        """
        execute_java_code()
        position_test_files(self.file_name)

    def populate_transactions(self) -> None:
        """
        Parse the transactions
        :return: None
        """
        self.transactions = load_transactions(self.file_name)

    def parse_results(self) -> None:
        """
        Parse the output.
        :return: None
        """
        parser = Parser(self.file_name)
        parser.split_report_parts()
        parser.parse_report()
        self.parsed_output = parser.output

    def initialise_stats(self) -> None:
        """
        Initialise the dictionary holding the stats
        :return: None
        """
        dates_keys = []
        inner_template: Dict[str, int] = {
            "Transaction Count": 0,
            "Total Waiting Time": 0,
            "Average Waiting Time": 0
        }

        template: Dict[str, Dict[str, int]] = {
            "General": inner_template.copy(),
            "Corporate": inner_template.copy(),
            "Individual": inner_template.copy(),
            "Non-Registered": inner_template.copy()
        }

        for transaction_queue in self.transactions:
            dates_keys.append(transaction_queue.day)

        for date in dates_keys:
            self.stats[date] = template.copy()

    def ready(self) -> None:
        """
        Wrapper method for helper methods of tester.
        :return: None
        """
        self.ready_os()
        self.populate_transactions()
        self.parse_results()
        self.initialise_stats()

    def test_queue_equality(self) -> int:
        """
        Test if queue inside the program and reported queues are the same
        :return: number of the line error ocured or none.
        """
        transaction_queues: List[TransactionQueue] = self.parsed_output[0]
        for i in range(len(self.transactions)):
            if self.transactions[i] != transaction_queues[i]:
                return i
        return -1

    def test(self) -> str:
        self.ready()
        queue_equality = self.test_queue_equality()
        if queue_equality >= 0:
            return "Mismatch between reported TransactionQueue and" + \
                   f"calculated TransactionQueue at day {queue_equality + 1}"
        for transaction_queue in self.transactions:
            current_date: str = transaction_queue.day
            while len(transaction_queue.queue) != 0:
                current_transaction: Transaction = transaction_queue.remove()
                type_of: str = current_transaction.type
                self.stats[current_date]["General"]["Transaction Count"] += 1
                self.stats[current_date]["General"]["Total Waiting Time"] += current_transaction.waiting_time
                self.stats[current_date][type_of]["Transaction Count"] += 1
                self.stats[current_date][type_of]["Total Waiting Time"] += current_transaction.waiting_time
            for key in self.stats[current_date]:
                cur = current_date  # To shorten the lin length below
                self.stats[cur][key]["Average Waiting Time"] = self.stats[cur][key]["Total Waiting Time"] / \
                                                               self.stats[current_date][key]["Transaction Count"]

        for date in self.stats:
            if self.stats[date] != self.parsed_output[1][date]:
                return f"Test Unsucessful, Error Occured at date {date}"

        return "Test Successful"


if __name__ == "__main__":
    tester = Main("CENG112_HW3_Transactions.txt")
    result = tester.test()
    print(result)