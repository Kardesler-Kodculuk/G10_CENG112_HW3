from typing import List
from simulation.simulation_os import position_test_files, execute_java_code
from simulation.simulation_parts import load_transactions
from simulation.simulation_classes import Transaction, TransactionQueue


class Main:
    def __init__(self, file_name: str):
        self.transactions = []
        self.file_name = file_name

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
        self.transactions: List[TransactionQueue] = load_transactions(self.file_name)

    def test(self):
        NotImplemented


if __name__ == "__main__":
    tester = Main("CENG112_HW3_Transactions.txt")
    tester.ready_os()
    tester.populate_transactions()