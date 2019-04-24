"""
A group of functions that will be useful in testing.
"""

from typing import List, Dict, Tuple
from simulation.simulation_classes import Transaction, TransactionQueue


def read_transactions(file_name: str) -> List[str]:
    """
    Read transactions from file
    :param file_name: Name of the file where transactions are loggeed.
    :return: A list of lines in the file.
    """
    with open(file_name) as file:
        data: str = file.read()
    data_list: List[str] = data.split("\n")
    return data_list


def split_days(lines: List[str]) -> Dict[str, List[Tuple]]:
    """
    Split the transaction file's raw format to days
    :param lines: List containing lines in the txt.
    :return: Day - Info pairs
    """
    days: Dict[str, List[Tuple]] = {}
    for line in lines:
        tr_proto_tuple = line.split(",")
        day = tr_proto_tuple[0]
        specs = tuple(tr_proto_tuple[1:])
        if day not in days:
            days[day] = [specs]
        else:
            days[day].append(specs)
    return days


def parse_transactions(days: Dict[str, List[Tuple[int, str, int]]]) -> Dict[str, List[Transaction]]:
    """
    Convert the info to Transaction class
    :param days: Dictionary containing day-info pairs
    :return: Dictionary containing day-Transaction pairs.
    """
    days_transaction = {}
    for day in days:
        days_transaction[day] = []
        waiting_time = 0
        for i, transaction in enumerate(days[day]):
            days_transaction[day].append(Transaction(transaction[0], transaction[1], transaction[2], waiting_time))
            waiting_time += transaction[2]
    return days_transaction


def create_queues(days: Dict[str, List[Transaction]]) -> List[TransactionQueue]:
    """
    Convert list of transactions into a transaction_queue
    :param days: The days dictionary.
    :return: Days to TransactionQueue s dictionary
    """
    transaction_queues: List[TransactionQueue] = []
    for day in days:
        transaction_queues.append(TransactionQueue(day, days[day]))
    return transaction_queues


def load_transactions(file_name: str) -> List[TransactionQueue]:
    """
    Do the above
    :param file_name: File the transactions are kept
    :return: List of transaction queues.
    """
    transactions_lines = read_transactions(file_name)
    days = split_days(transactions_lines)
    transactions = parse_transactions(days)
    transaction_queues = create_queues(transactions)
    return transaction_queues