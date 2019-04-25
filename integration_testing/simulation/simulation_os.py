"""
A group of functions that will ready the operating system to do the testing.
"""

from os import system, remove
from platform import system as systemname


def delete_file(file_name: str) -> None:
    """
    Remove the file
    :param file_name: Name of the file to be removed
    :return: None
    """
    remove(file_name)

def position_test_files(file_name: str) -> None:
    """
    Position the test files into appropriate places so the testing can take place.
    :param file_name: File holding the transactions
    :return: None.
    """
    system(f"cp ../{file_name} {file_name}")