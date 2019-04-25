from typing import List, Dict, Tuple
from simulation.simulation_os import position_test_files, delete_file
from simulation.simulation_parts import load_transactions
from simulation.simulation_classes import Transaction, TransactionQueue
from json import dumps
from copy import deepcopy as copy

class Main:
    def __init__(self, file_name: str):
        self.transactions: List[TransactionQueue] = []
        self.file_name: str = file_name
        self.stats = {}

    def ready_os(self) -> None:
        """
        Ready the operating system for testing purposes.
        :return: None
        """
        position_test_files(self.file_name)

    def populate_transactions(self) -> None:
        """
        Parse the transactions
        :return: None
        """
        self.transactions = load_transactions(self.file_name)

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
            "General": copy(inner_template),
            "Corporate": copy(inner_template),
            "Individual": copy(inner_template),
            "Non-Registered": copy(inner_template)
        }

        for transaction_queue in self.transactions:
            dates_keys.append(transaction_queue.day)

        for date in dates_keys:
            if date == "":
                break
            self.stats[date] = copy(template)

    def ready(self) -> None:
        """
        Wrapper method for helper methods of tester.
        :return: None
        """
        self.ready_os()
        self.populate_transactions()
        self.initialise_stats()

    @staticmethod
    def finalise(files: List[str]) -> None:
        """
        Remove the files from the file system that was generated during testing.
        :param files: Names of the files to be removed
        :return: None
        """
        for file in files:
            delete_file(file)

    def test(self) -> str:
        self.ready()
        """
        if queue_equality >= 0:
            return "Mismatch between reported TransactionQueue and" + \
                   f"calculated TransactionQueue at day {queue_equality + 1}"
        """
        for transaction_queue in self.transactions:
            if not transaction_queue.queue:
                break
            current_date: str = transaction_queue.day
            for current_transaction in transaction_queue.queue:
                type_of: str = current_transaction.type
                self.stats[current_date]["General"]["Transaction Count"] += 1
                self.stats[current_date]["General"]["Total Waiting Time"] += current_transaction.waiting_time
                self.stats[current_date][type_of]["Transaction Count"] += 1
                self.stats[current_date][type_of]["Total Waiting Time"] += current_transaction.waiting_time
            for key in self.stats[current_date]:
                cur = current_date  # To shorten the lin length below
                try:
                    self.stats[cur][key]["Average Waiting Time"] = self.stats[cur][key]["Total Waiting Time"] / self.stats[current_date][key]["Transaction Count"]
                except ZeroDivisionError:
                    pass
        generated_result = ""
        for transaction_queue in self.transactions:
            generated_result += str(transaction_queue) + "\n"
        generated_result += "\n\n" + dumps(self.stats, indent=4)
        return generated_result

if __name__ == "__main__":
    tester = Main("CENG112_HW3_Transactions.txt")
    result = tester.test()
    Main.finalise(["CENG112_HW3_Transactions.txt"])
    with open("output.txt", "w") as file:
        print(result, file= file)