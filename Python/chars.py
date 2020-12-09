from num_encoding import NumEncoding  # перевод секрета в числа
import replace_encoding  # алгоритм замены
from insert_encoding import InsertEncoding  # алгоритм вставки
import ciphers  # дополнительные шифры


def encryption():
    print()
    for x in range(700, 1000):
        print('W' + chr(x), ' ' + chr(x))
