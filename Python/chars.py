from num_encoding import NumEncoding  # перевод секрета в числа
from replace_encoding import ReplaceEncoding  # алгоритм замены
from insert_encoding import InsertEncoding  # алгоритм вставки
import ciphers  # дополнительные шифры


def func():
    s = "Кодируемся в Евгении Онегине"
    print(s)
    n_s = NumEncoding.encoding(s)
    print(n_s)
    e_s = InsertEncoding.encoding("«Мой дядя самых честных правил,\n\
    Когда не в шутку занемог,\n\
    Он уважать себя заставил\n\
    И лучше выдумать не мог.\n\
    Его пример другим наука;\n\
    Но, боже мой, какая скука\n\
    С больным сидеть и день и ночь,\n\
    Не отходя ни шагу прочь!\n\
    Какое низкое коварство\n\
    Полуживого забавлять,\n\
    Ему подушки поправлять,\n\
    Печально подносить лекарство,\n\
    Вздыхать и думать про себя:\n\
    Когда же черт возьмет тебя!»", n_s)[0]
    print(e_s)
    d_s = InsertEncoding.decoding(e_s)
    print(d_s)
    dn_s = NumEncoding.decoding(d_s)
    print(dn_s)


def encryption():
    m = '123'
    e_m = ReplaceEncoding.encoding('Съешь этих мягких французских булок да выпей чаю.', m)
    d_m = ReplaceEncoding.decoding(e_m)
    print(m)
    print(e_m)
    print(d_m)
