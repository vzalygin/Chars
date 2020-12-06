class NumEncoding:
    _chars = {'а': 0, 'a': 0, 'А': 32, 'A': 32, '0': 64,
              '.': 75, ',': 76, '!': 77, '?': 78, "'": 79, ' ': 80}
    _lang_change = 74

    @classmethod
    def encoding(cls, s):
        """Преобразует исходную строку в строку чисел в десятичной системе счисления"""
        n = ''
        lang_type = -1  # русский первоначально
        for c in s:
            if not cls._chars.get(c) is None:
                n += str(100+cls._chars.get(c))[1:]
                continue

            c_num = ord(c)
            c_begin = ''
            if ord(c.lower()) - ord('a') < 28:  # английские
                if lang_type != 1:
                    lang_type = -lang_type
                    n += str(cls._lang_change+100)[1:]
                if c.islower():
                    c_begin = 'a'
                else:
                    c_begin = 'A'
            elif ord(c.lower()) - ord('а') < 32:  # русские
                if lang_type != -1:
                    lang_type = -lang_type
                    n += cls._lang_change
                if c.islower():
                    c_begin = 'а'
                else:
                    c_begin = 'А'
            n += str(cls._chars[c_begin] + c_num - ord(c_begin) + 100)[1:]
        return n

    @classmethod
    def decoding(cls, n):
        """Преобразует исходную строку из чисел в нормальный вид"""
        return 'a'
