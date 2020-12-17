from num_encoding import NumEncoding  # перевод секрета в числа
from replace_encoding import ReplaceEncoding  # алгоритм замены
from insert_encoding import InsertEncoding  # алгоритм вставки
import ciphers  # дополнительные шифры


def encryption(container: str, mess: str, encoding_rule=0, replace_rule=0, numbers_of_ciphers=[]):
    """container - текст-контейнер
    mess - секретное сообщение
    encoding_rule - правило шифрования
    (0: замена - контрольная сумма, вставка - секрет)
    replace_rule - правило замены
    (0: стандартное правило)
    numbers_of_ciphers - инфо об используемых шфирах"""
    try:
        mess = NumEncoding.encoding(mess)
        m_sum = _sum_of(mess)
    except Exception as e:
        print(f'Не удалось перевести сообщение в последовательность.\n {e}')
        return

    try:
        for cipher in numbers_of_ciphers:
            # TODO добавить работу с шифрами
            pass
    except Exception as e:
        print(f'Не удалось применять шифры к сообщению.\n {e}')
        return

    try:
        container = InsertEncoding.encoding(container, mess)
    except Exception as e:
        print(f'Не удалось провести вставку в контейнере.\n {e}')
        return

    try:
        container = ReplaceEncoding.encoding(container, m_sum, rule=replace_rule)
    except Exception as e:
        print(f'Не удалось провести замену в контейнере.\n {e}')
        return

    return container


def decryption(container: str, replace_rule=0, numbers_of_ciphers=[]):
    try:
        mess = InsertEncoding.decoding(container)
    except Exception as e:
        print(f'Не удалось вытащить сообщение из контейнера.\n {e}')

    try:
        n_mess = ReplaceEncoding.decoding(container, rule=replace_rule)
    except Exception as e:
        print(f'Не удалось считать сообщение из контейнера.\n {e}')

    if n_mess != _sum_of(mess):
        print('Контрольная сумма неверна.')

    try:
        for i in range(len(numbers_of_ciphers)-1, -1, -1):
            cipher = numbers_of_ciphers[i]
            # TODO добавить работу с шифрами
            pass
    except Exception as e:
        print(f'Не удалось применять шифры к сообщению.\n {e}')
        return
    try:
        mess = NumEncoding.decoding(mess)
    except Exception as e:
        print(f'Не удалось собрать сообщение из последовательности.\n {e}')
        return

    return mess


def _sum_of(n: str):
    return str(sum([int(x) for x in n]))


def get_info_ciphers():
    pass


def get_info_replace_rules():
    pass


def set_settings():
    pass
