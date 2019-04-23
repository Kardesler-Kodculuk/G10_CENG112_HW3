"""
A group of functions that will ready the operating system to do the testing.
"""

from os import system
from platform import platform


def position_test_files(file_name: str) -> None:
    """
    Position the test files into appropriate places so the testing can take place.
    :param file_name: File holding the transactions
    :return: None.
    """
    system(f"cp ../{file_name} {file_name} && cp ../bin/output.txt output.txt")


def execute_java_code() -> None:
    """
    Execute java code to test.
    :return: None
    """
    command_key = {
        "Windows": "|",
        "Linux": ">"
    }
    system(f"cd ../bin && java Main {command_key[platform()]} output.txt")