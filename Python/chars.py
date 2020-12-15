from num_encoding import NumEncoding  # перевод секрета в числа
from replace_encoding import ReplaceEncoding  # алгоритм замены
from insert_encoding import InsertEncoding  # алгоритм вставки
import ciphers  # дополнительные шифры


def encryption(container: str, mess: str, replace_rule=0, numbers_of_ciphers=[]):
    tmp = container
    for cipher in numbers_of_ciphers:
        # TODO добавить работу с шифрами
        pass

    try:
        mess = NumEncoding.encoding(mess)
        m_sum = _sum_of(mess)
    except Exception as e:
        print(f'Не удалось перевести сообщение в последовательность.\n {e}')
        return tmp

    try:
        container = InsertEncoding.encoding(container, mess)
    except Exception as e:
        print(f'Не удалось провести вставку в контейнере.\n {e}')
        return tmp

    try:
        container = ReplaceEncoding.encoding(container, m_sum, rule=replace_rule)
    except Exception as e:
        print(f'Не удалось провести замену в контейнере.\n {e}')
        return tmp

    return container


def decryption(container: str, replace_rule=0, numbers_of_ciphers=[]):
    mess = InsertEncoding.decoding(container)
    n_mess = ReplaceEncoding.decoding(container, rule=replace_rule)
    if n_mess != _sum_of(mess):
        print('Контрольная сумма неверна.')
    for i in range(len(numbers_of_ciphers)-1, -1, -1):
        cipher = numbers_of_ciphers[i]
        # TODO добавить работу с шифрами
        pass
    mess = NumEncoding.decoding(mess)
    return mess


def _sum_of(n: str):
    return str(sum([int(x) for x in n]))


def get_info_ciphers():
    pass


def get_info_replace_rules():
    pass


def set_settings():
    pass
