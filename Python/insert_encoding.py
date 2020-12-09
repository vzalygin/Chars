import random


class InsertEncoding:
    _el = ('ͯ', 'ͮ', 'ͭ', 'ͬ', 'ͫ', 'ͪ', 'ͩ', 'ͨ', 'ͧ', 'ͦ', 'ͥ', 'ͤ', 'ͣ', '͘', '',
           '͗', '͕', '͔', '͓', '͑', '͐', '͏', '͉', '̾', '̽', '̻', '̹', '̩', '̨', '̦',
           '̥', '̣', '̠', '̟', '̞', '̝', '̛', '̚', '̕', '̔', '', '̓', '̑', '', '̏',
           '̉', '̶')
    _l_el = len(_el)
    _super_secret = '͏'

    # @classmethod
    # def _sys10toN(cls, n):
    #     """Перевод из десятичной системы в N-ную"""
    #     s = ''
    #     while n > 0:
    #         s = str(n % cls._l_el + s)
    #         n //= cls._l_el
    #     if len(s) == 1:
    #         s = '0' + s
    #     return s
    #
    # @classmethod
    # def _sysNto10(cls, num):
    #     """Перевод из N-ной системы в десятичную"""
    #     sym = 0
    #     while len(num) > 0:
    #         sym += int(num[0]) * (cls._l_el ** (len(num) - 1))
    #         num = num[1:]
    #     return sym

    @classmethod
    def _sys10toN(cls, n: int):
        sys = cls._l_el
        sys_n = []
        while n > 0:
            sys_n.append(n % sys)
            n //= sys
        return sys_n

    @classmethod
    def _sysNto10(cls, sys_n):
        sys = cls._l_el
        n = 0
        for i in range(len(sys_n)):
            n += sys_n[i] * (sys ** i)
        return n

    @classmethod
    def encryption(cls, container: str, mess: int, cluster=-1):
        """Алгоритм вставки сообщения в контейнер. В параметрах: контейнер, сообщение и максимальное количество символов
          между вставками (по умолчанию вставки растягиваются на длинну контейнера). Возвращает заполненный контейнер.
          В случае, если сообщение невозможно полностью вставить в текст, возвращается строка, сигнализирующая об этом"""
        enc_mess = ''
        arr_mess = cls._sys10toN(mess)
        if cluster == -1:
            cluster = len(container) // len(str(mess))
        curr_syms = ''
        for sym in container:
            if len(arr_mess) > 0:
                curr_syms += sym
            else:
                enc_mess += sym
            if len(curr_syms) == cluster:
                n = random.randrange(0, cluster)
                enc_mess += curr_syms[:n] + cls._el[arr_mess[0]] + curr_syms[n:]
                arr_mess = arr_mess[1:]
                curr_syms = ''
        return enc_mess

    @classmethod
    def decryption(cls, enc_mess):
        arr_mess = []
        for sym in enc_mess:
            if cls._el.count(sym) > 0:
                arr_mess.append(cls._el.index(sym))
        mess = cls._sysNto10(arr_mess)
        return mess

    @classmethod
    def caesar(cls, s, k):
        a = ''
        for c in s:
            a += chr((ord(c) - ord('а') + k) % 32 + ord('а'))
        return a