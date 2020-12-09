from num_encoding import NumEncoding  # перевод секрета в числа
import replace_encoding  # алгоритм замены
from insert_encoding import InsertEncoding  # алгоритм вставки
import ciphers  # дополнительные шифры


def encryption():
    s = "Кодируемся в Евгении Онегине"
    print(s)
    n_s = '1' + NumEncoding.encoding(s)
    print(n_s)
    e_s = InsertEncoding.encryption("«Мой дядя самых честных правил,\n \
Когда не в шутку занемог,\n \
Он уважать себя заставил\n \
И лучше выдумать не мог.\n \
Его пример другим наука;\n \
Но, боже мой, какая скука\n \
С больным сидеть и день и ночь,\n \
Не отходя ни шагу прочь!\n \
Какое низкое коварство\n \
Полуживого забавлять,\n \
Ему подушки поправлять,\n \
Печально подносить лекарство,\n \
Вздыхать и думать про себя:\n \
Когда же черт возьмет тебя!»", int(n_s))
    print(e_s)
    d_s = InsertEncoding.decryption(e_s)
    print(d_s)
    d_s = str(d_s)[1:]  # TODO стнадартизировать вывод и ввод (int или str)
    dn_s = NumEncoding.decoding(d_s)
    print(dn_s)
