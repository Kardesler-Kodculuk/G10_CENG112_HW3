from typing import List, Dict, Tuple
from simulation.simulation_os import position_test_files, execute_java_code
from simulation.simulation_parts import load_transactions
from simulation.simulation_classes import Transaction, TransactionQueue
from simulation.simulation_parser import  Parser

class Main:
    def __init__(self, file_name: str):
        self.transactions: List[TransactionQueue] = []
        self.file_name: str = file_name
        self.parsed_output: Tuple[List, Dict] = ([], {})

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

    def ready(self) -> None:
        """
        Wrapper method for helper methods of tester.
        :return: None
        """
        self.ready_os()
        self.populate_transactions()
        self.parse_results()

    def test_queue_equality(self) -> int:
        """
        Test if queue inside the program and reported queues are the same
        :return: number of the line error ocured or none.
        """
        transaction_queues: List[TransactionQueue] = self.parsed_output[0]
        for i, self_q, other_q in enumerate(self.transactions), transaction_queues:
            if self_q != other_q:
                return i
        return -1

    def test(self) -> str:
        self.ready()
        queue_equality = self.test_queue_equality()
        if queue_equality >= 0:
            return "Mismatch between reported TransactionQueue and" + \
                   f"calculated TransactionQueue at day {queue_equality + 1}"
        return "Test Successful"


if __name__ == "__main__":
    tester = Main("CENG112_HW3_Transactions.txt")
    tester.test()