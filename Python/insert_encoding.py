import random


class InsertEncoding:
    _el = ('̾', '̽', '̷', '̴', '̶', '̳', '͓')
    _l_el = len(_el)

    @classmethod
    def _sys10toN(cls, n):
        """Перевод из десятичной системы в N-ную"""
        s = ''
        while n > 0:
            s = str(n % cls._l_el + s)
            n //= cls._l_el
        if len(s) == 1:
            s = '0' + s
        return s

    @classmethod
    def _sysNto10(cls, num):
        """Перевод из N-ной системы в десятичную"""
        sym = 0
        while len(num) > 0:
            sym += int(num[0]) * (cls._l_el ** (len(num) - 1))
            num = num[1:]
        return sym

    @classmethod
    def encryption(cls, container, mess: int, cluster=-1):
        """Алгоритм вставки сообщения в контейнер. В параметрах: контейнер, сообщение и максимальное количество символов
          между вставками (по умолчанию вставки растягиваются на длинну контейнера). Возвращает заполненный контейнер.
          В случае, если сообщение невозможно полностью вставить в текст, возвращается строка, сигнализирующая об этом"""
        enc_mess = ''
        int_mess = ''
        for i in range(0, len(mess), 2):
            int_mess += cls._sys10toN(mess[i]+mess[i+1])
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
                enc_mess += curr_syms[:n] + cls._el[int(mess[0])] + curr_syms[n:]
                mess = mess[1:]
                curr_syms = ''
        if len(mess) != 0:
            return 'Insufficient length or clusters are too large'
        return enc_mess

    @classmethod
    def decryption(cls, enc_mess):
        mess_int = ''
        mess = ''
        for sym in enc_mess:
            if cls._el.count(sym) > 0:
                mess_int += str(cls._el.index(sym))
        for i in range(0, len(mess_int), 2):
            mess += chr(cls._sysNto10(mess_int[i:i + 2]) + ord('а') - 1)
        return mess

    @classmethod
    def caesar(cls, s, k):
        a = ''
        for c in s:
            a += chr((ord(c) - ord('а') + k) % 32 + ord('а'))
        return a