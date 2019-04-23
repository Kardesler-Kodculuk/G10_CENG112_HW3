"""
Parses the results to find what exactly is going on.
"""

from typing import Tuple, List, Dict
from simulation.simulation_classes import Transaction, TransactionQueue


class Parser:
    def __init__(self, file_name: str):
        self.file_name = file_name
        with open(file_name, "r") as file:
            self.data = file.read()
        self.transaction_queue_log: List[str] = []
        self.days: List[str] = []
        self.days_parsed: Dict[str, Tuple[Dict, str]] = {}
        self.transaction_queues: List[TransactionQueue] = []

    @staticmethod
    def return_arrow_value(string: str) -> str:
        """
        Return the value part of a statement of form Key → Value
        :param string: String containing →
        :return: Value
        """
        keywords = string.split("→")
        return keywords[1]

    @staticmethod
    def parse_times(second_part_report: str) -> Dict[str, Tuple[float, float, float]]:
        """
        Return the transaction counts, waiting times and average waiting times of total, customer etc.
        :param second_part_report: Second part of the daily report
        :return: Dictionary containing these values.
        """
        dictionary_times = {
            "Total": (),
            "Corporate": (),
            "Individual": (),
            "Non-Registered": ()
        }
        lines = second_part_report.splitlines()
        dictionary_keys = ["Total", "Corporate", "Individual", "Non-Registered"]
        for i, j in range(0, 13, 3), range(0, 4):
            dictionary_times[dictionary_keys[j]] = (
                float(Parser.return_arrow_value(lines[i])),
                float(Parser.return_arrow_value(lines[i + 1])),
                float(Parser.return_arrow_value(lines[i + 2]))
            )
        return dictionary_times

    @staticmethod
    def return_highest_date(second_part_report: str) -> str:
        """
        Highest date up to now.
        :param second_part_report: Second part of the daily report
        :return: Highest date until then.
        """
        lines = second_part_report.split("\n")
        return Parser.return_arrow_value(lines[-1])

    @staticmethod
    def return_date(second_part_report: str) -> str:
        """
        Return the date of day
        :param second_part_report:
        :return: The date of the day
        """
        return second_part_report.splitlines()[0].split("→")[0].split(" ")[-1]

    @staticmethod
    def convert_into_transaction_queue(date: str, transactions: List) -> TransactionQueue:
        """
        Convert a list of transaction infos into a transaction queue.
        :param transactions: Transactions list
        :return: TransactionQueue
        """
        transactions_list: List[Transaction] = []
        for transaction in transactions:
            parsed_transaction = transaction.split("|")
            transactions_list.append(Transaction(*parsed_transaction))
        return TransactionQueue(date, transactions_list)

    def split_report_parts(self) -> None:
        """
        Split the report into respective blocs.
        :return: None.
        """
        blocs: List[str] = self.data.split("\n\n")
        self.transaction_queue_log = blocs[0].splitlines
        self.days = blocs[1:]

    def parse_report(self) -> None:
        """
        Parse the report to prepare the objects
        :return: None.
        """
        for transaction in self.transaction_queue_log:
            transaction_split = transaction.split("→")
            day, transactions = transaction_split[0], transaction_split[1:]
            self.transaction_queues.append(Parser.convert_into_transaction_queue(day, transactions))
        for day in self.days:
            self.days_parsed[Parser.return_date(day)] = (Parser.parse_times(day),
                                                         Parser.return_highest_date(day))

    @property
    def output(self) -> Tuple[List, Dict]:
        """
        Output
        :return: the transaction queues and days parsed.
        """
        return self.transaction_queues, self.days_parsed
