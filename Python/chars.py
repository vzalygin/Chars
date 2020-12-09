from num_encoding import Num  # перевод секрета в числа
import replace_encoding  # алгоритм замены
import insert_encoding  # алгоритм вставки
import ciphers  # дополнительные шифры


def encryption():
    text = "Привет"
    enc = Num.encoding(text)
    dec = Num.decoding(enc)
    print(text)
    print(enc)
    print(dec)
