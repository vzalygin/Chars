import random

el = ('̾', '̽', '̷', '̴', '̶', '̳', '͓')


def sys10toN(sym: str):
    """Перевод из десятичной системы в N-ную"""
    s = ''
    n = ord(sym) - ord('а') + 1
    while n > 0:
        s = str(n % len(el)) + s
        n //= len(el)
    if len(s) == 1:
        s = '0' + s
    return s


def sysNto10(num):
    """Перевод из N-ной системы в десятичную"""
    sym = 0
    while len(num) > 0:
        sym += int(num[0]) * (len(el) ** (len(num) - 1))
        num = num[1:]
    return sym


def encryption(container, mess, cluster=-1, shift=0):
    """Алгоритм вставки сообщения в контейнер. В параметрах: контейнер, сообщение и максимальное количество символов
      между вставками (по умолчанию вставки растягиваются на длинну контейнера). Возвращает заполненный контейнер.
      В случае, если сообщение невозможно полностью вставить в текст, возвращается строка, сигнализирующая об этом"""
    enc_mess = ''
    int_mess = ''
    #mess = caesar(mess, shift)
    for x in mess:
        int_mess += sys10toN(x)
    mess = int_mess
    if cluster == -1:
        cluster = len(container) // len(mess)
    curr_syms = ''
    for sym in container:
        if len(mess) > 0:
            curr_syms += sym
        else:
            enc_mess += sym
        if len(curr_syms) == cluster:
            n = random.randrange(0, cluster)
            enc_mess += curr_syms[:n] + el[int(mess[0])] + curr_syms[n:]
            mess = mess[1:]
            curr_syms = ''
    if len(mess) != 0:
        return 'Insufficient length or clusters are too large'
    return enc_mess


def decryption(enc_mess, shift = 0):
    mess_int = ''
    mess = ''
    for sym in enc_mess:
        if el.count(sym) > 0:
            mess_int += str(el.index(sym))
    for i in range(0, len(mess_int), 2):
        mess += chr(sysNto10(mess_int[i:i + 2]) + ord('а') - 1)
    #mess = caesar(mess, 32-shift)
    return mess


def caesar(s, k):
    a = ''
    for c in s:
        a += chr((ord(c) - ord('а') + k) % 32 + ord('а'))
    return a


def main():
    """Алгоритм доставания сообщения из контейнера. На параметрах только заполненный контейнер, возращает вставленный
      секрет."""
    while True:
        print('Введите номер действия, которое хотите выполнить '
              '\n1. Вставить секрет в контейнер.'
              '\n2. Вытащить секрет из заполненного контейнера.')
        op_t = input()
        if op_t == '1':
            print('Введите текст-контейнер')
            container = input()
            print('Введите текст-секрет')
            mess = input()
            print('Введите ключ шифрования (целое число)')
            shift = int(input())
            print('Введите длинну кластера (если не хотите это делать, от введите пустую команду)')
            cluster = input()
            if cluster.isalnum():
                cluster = int(cluster)
            else:
                cluster = -1
            print('Заполненный контейнер: ', encryption(container, mess, cluster, shift))
        else:
            print('Введите заполненный контейнер')
            container = input()
            print('Введите ключ шифрования (целое число)')
            shift = int(input())
            mess = input()
            print('Тайное сообщение: ', decryption(container, shift))
        print('Введите любую команду, чтобы продолжить')
        _ = input()


if __name__ == '__main__':
    main()
