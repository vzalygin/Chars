from num_encoding import NumEncoding  # перевод секрета в числа
import replace_encoding  # алгоритм замены
import insert_encoding  # алгоритм вставки
import ciphers  # дополнительные шифры


def encryption():
    text = "абвгэюяАБВГЭЮЯ 0123456789 abcxyzABCXYZ .,!?'"
    enc = NumEncoding.encoding(text)
    dec = NumEncoding.decoding(enc)
    print(text)
    print(enc)
    print(dec)
