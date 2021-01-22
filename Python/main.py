from num_encoding import NumEncoding  # перевод секрета в числа
from replace_encoding import ReplaceEncoding  # алгоритм замены
from insert_encoding import InsertEncoding  # алгоритм вставки


def ui():
    print('CharS v1 console version')
    while True:
        print('Выберете номер операции: \n1. Шифрование \n2. Дешифрование')
        oper = input('Номер операции: ')
        if oper == '1':
            print('Введите текст-контейнер:')
            container = input()
            print('Введите сообщение секрет:')
            mess = input()
            print('Варианты работы алгоритма вставки.')
            print(chars.get_info_replace_rules())
            print('Введите номер правила (по умолчанию 0)')
            replace_rule = int(input('Номер правила:'))
            print('Варианты работы алгоритма шифрования.')
            print(chars.get_info_encoding_rules())
            print('Введите номер правила (по умолчания 0)')
            encoding_rule = int(input('Номер правила:'))
            key = input('Введите ключ шифрования (8 цифр):')
            encrypted = chars.encryption(container, mess, key, encoding_rule=encoding_rule, replace_rule=replace_rule)
            if encrypted is not None:
                print(f'Заполненный контейнер: \n{encrypted}')
            else:
                print('Что-то пошло не так.')
        elif oper == '2':
            print('Введите заполненный контейнер:')
            container = input()
            print('Варианты работы алгоритма вставки.')
            print(chars.get_info_replace_rules())
            print('Введите номер правила (по умолчанию 0)')
            replace_rule = int(input('Номер правила:'))
            print('Варианты работы алгоритма шифрования.')
            print(chars.get_info_encoding_rules())
            print('Введите номер правила (по умолчания 0)')
            encoding_rule = int(input('Номер правила:'))
            key = input('Введите ключ шифрования (8 цифр):')
            decrypted = chars.decryption(container, key, encoding_rule=encoding_rule, replace_rule=replace_rule)
            if decrypted is not None:
                print(f'Секретное сообщение: \n{decrypted}')
            else:
                print('Что-то пошло не так.')
        else:
            print('Неверный номер операции. Повторите попытку.')
        print('Для продолжения введите любую команду.')
        _ = input()


print(NumEncoding.decoding("1471608020518"))
#ui()
