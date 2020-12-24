import chars
from num_encoding import NumEncoding  # перевод секрета в числа
from replace_encoding import ReplaceEncoding  # алгоритм замены
from insert_encoding import InsertEncoding  # алгоритм вставки
from ciphers import Des # дополнительные шифры
# TODO различные версии работы алгоритма
# TODO 2. Шифрование начала сообщения заменой, а потом вставкой
# TODO 3. Шифрование только вставкой
# TODO 4. Шифрование только заменой


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


def main():
    #         c = "«Мой дядя самых честных правил,\n\
# Когда не в шутку занемог,\n\
# Он уважать себя заставил\n\
# И лучше выдумать не мог.\n\
# Его пример другим наука;\n\
# Но, боже мой, какая скука\n\
# С больным сидеть и день и ночь,\n\
# Не отходя ни шагу прочь!\n\
# Какое низкое коварство\n\
# Полуживого забавлять,\n\
# Ему подушки поправлять,\n\
# Печально подносить лекарство,\n\
# Вздыхать и думать про себя:\n\
# Когда же черт возьмет тебя!»"
#         m = '1гл Евгения Онегина.'
#         print(len(c), len(m))
#         e_m = chars.encryption(c, m, encoding_rule=3, replace_rule=0)
#         d_m = chars.decryption(e_m, encoding_rule=3, replace_rule=0)
#         print(m)
#         print(e_m)
#         print(d_m)
    ui()


main()
