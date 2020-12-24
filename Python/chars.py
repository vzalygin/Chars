from num_encoding import NumEncoding  # перевод секрета в числа
from replace_encoding import ReplaceEncoding  # алгоритм замены
from insert_encoding import InsertEncoding  # алгоритм вставки
from ciphers import Des  # дополнительные шифры


def __sum_of(n: str):
    return str(sum([int(x) for x in n]))


def encryption(container: str, mess: str, key: str, encoding_rule=0, replace_rule=0, ):
    """container - текст-контейнер
    mess - секретное сообщение
    encoding_rule - правило шифрования
    (0: замена - контрольная сумма, вставка - секрет (самое "стабильное" правило)
    1: замена - секрет, вставка - секрет (самое вместимое правило))
    replace_rule - правило замены
    (0: стандартное правило
    1: замена только внутри букв)
    key - ключ для шифрования des"""
    try:
        mess = NumEncoding.encoding(mess)
        mess = str(Des.enc(int(mess), bytes(key, 'utf-8')))
        if encoding_rule == 0:
            m_sum = __sum_of(mess)
            container = InsertEncoding.encoding(container, mess)
            container = ReplaceEncoding.encoding(container, m_sum, rule=replace_rule)
        elif encoding_rule == 1:
            for i in range(len(mess), -1, -2):
                rep_mess, ins_mess = mess[:i], mess[i:]
                try:
                    container_tmp = InsertEncoding.encoding(container, ins_mess)
                    container_tmp = ReplaceEncoding.encoding(container_tmp, rep_mess, rule=replace_rule)
                    container = container_tmp
                    break
                except:
                    pass
        elif encoding_rule == 2:
            container = InsertEncoding.encoding(container, mess)
        elif encoding_rule == 3:
            container = ReplaceEncoding.encoding(container, mess, rule=replace_rule)
        else:
            raise Exception('Unknown encoding rule')
    except Exception as e:
        print(f'Не удалось провести кодирование.\n {e}')
        return
    return container


def decryption(container: str, key: str, encoding_rule=0, replace_rule=0):
    """container - заполненный текст-контейнер
    encoding_rule - правило шифрования
    (0: замена - контрольная сумма, вставка - секрет)
    replace_rule - правило замены
    (0: стандартное правило
    1: замена только внутри слов)
    key - ключ для дешифрования des"""
    try:
        if encoding_rule == 0:
            mess = InsertEncoding.decoding(container)
            n_mess = ReplaceEncoding.decoding(container, rule=replace_rule)
            if n_mess != __sum_of(mess):
                print('Контрольная сумма неверна.')
        elif encoding_rule == 1:
            rep_mess = ReplaceEncoding.decoding(container, rule=replace_rule)
            ins_mess = InsertEncoding.decoding(container)
            mess = rep_mess + ins_mess
        elif encoding_rule == 2:
            mess = InsertEncoding.decoding(container)
        elif encoding_rule == 3:
            mess = ReplaceEncoding.decoding(container, rule=replace_rule)
        else:
            raise Exception('Unknown encoding rule')
        mess = str(Des.dec(int(mess), bytes(key, 'utf-8')))
        mess = NumEncoding.decoding(mess)
    except Exception as e:
        print(f'Не удалось провести декодирование.\n {e}')
        mess = ''
    return mess


def get_info_replace_rules():
    return '0. Стандартное кодирование.'


def get_info_encoding_rules():
    return '0. Проверка целостности.\n' \
           '1. Максимальная вместимость.\n' \
           '2. Только вставка.\n' \
           '3. Только замена.'
