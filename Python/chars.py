from num_encoding import NumEncoding  # перевод секрета в числа
import replace_encoding  # алгоритм замены
from insert_encoding import InsertEncoding  # алгоритм вставки
import ciphers  # дополнительные шифры


def encryption():
    el = ('̾', '̽', '̷', '̴', '̶', '̳', '͓')
    for x in el:
        print(ord(x), end=' ')
    print()
    for x in range(700, 1000):
        print(chr(x), end='+')
