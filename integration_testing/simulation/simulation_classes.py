"""
A group of classes that will be useful in testing.
"""

from typing import List


class Transaction:
    def __init__(self, id: int, type: str, occupation: int, waiting_time: int = 0):
        type_converter = {
            "COR": "Corporate",
            "IND": "Individual",
            "NON": "Non-Registered"
        }
        self.type = type
        if self.type in type_converter:
            self.type = type_converter[self.type]
        else:
            self.type = type.title()
        self.occupation = occupation
        self.id = id
        priority_determinator = {
            "Corporate": 1,
            "Individual": 2,
            "Non-Registered": 3
        }
        self.priority: int = priority_determinator[self.type]
        self.waiting_time = waiting_time

    def __eq__(self, other):
        if self.type != other.type:
            return False
        elif self.occupation != other.occupation:
            return False
        elif self.id != other.id:
            return False
        elif self.waiting_time != other.waiting_time:
            return False
        else:
            return True


class TransactionQueue:
    """
    A priority queue like class to hold the transactions.
    """
    def __init__(self, day: str, transactions: List[Transaction] = []):
        """
        Constructor for transactionQueue class.
        :param day: Day the transactions take place.
        """
        self.queue: List[Transaction] = []
        self.total_waiting_time: int = 0
        self.day = day
        if transactions:
            for transaction in transactions:
                self.insert(transaction)

    @staticmethod
    def sort_key(transaction):
        """
        Provide a key for the sort function used in insert.
        :param transaction: Transaction to be processed
        :return: The keys for sorting.
        """
        return (transaction.priority, transaction.id)

    def insert(self, new_transaction: Transaction) -> None:
        """
        Insert a new element into the priority queue.
        :param new_transaction: New transaction to be inserted.
        :return: NONE.
        """
        self.queue.append(new_transaction)
        self.queue.sort(key=self.sort_key)
        self.total_waiting_time += new_transaction.occupation

    def remove(self) -> Transaction:
        """
        Remove the object at the start and return it.
        :return: Next transaction to be processed.
        """
        return self.queue.pop(0)

    def __eq__(self, other) -> bool:
        if type(self) != type(other):
            return False
        elif len(self.queue) != len(other.queue):
            return False
        else:
            for self_item, other_item in self.queue, other.queue:
                if self_item != other_item:
                    return False
        return True

