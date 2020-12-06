class NumEncoding:
    _chars = {'а': 0, 'a': 0, 'А': 32, 'A': 32, '0': 64,
              '.': 75, ',': 76, '!': 77, '?': 78, "'": 79, ' ': 80}
    _chars_reverse = {0: ['а', 'a'], 32: ['А', 'A'], 64: ['0'],
                      75: ['.'], 76: [','], 77: ['!'], 78: ['?'], 79: ["'"], 80: [' ']}
    _lang_change = 74
    _default_lang_type = -1

    @classmethod
    def encoding(cls, s: str):
        """Преобразует исходную строку в строку чисел в десятичной системе счисления"""
        n = ''
        lang_type = cls._default_lang_type  # русский первоначально
        for c in s:
            if not cls._chars.get(c) is None:
                n += str(100+cls._chars.get(c))[1:]
                continue

            if ord(c.lower()) - ord('a') < 28:  # английские
                if lang_type != 1:
                    lang_type = -lang_type
                    n += str(cls._lang_change+100)[1:]
                if c.islower():
                    c_begin = 'a'
                else:
                    c_begin = 'A'
            else:  # ord(c.lower()) - ord('а') < 32:  # русские
                if lang_type != -1:
                    lang_type = -lang_type
                    n += cls._lang_change
                if c.islower():
                    c_begin = 'а'
                else:
                    c_begin = 'А'
            n += str(cls._chars[c_begin] + ord(c) - ord(c_begin) + 100)[1:]
        return n

    @classmethod
    def decoding(cls, n):
        """Преобразует исходную строку из чисел в нормальный вид"""
        s = ''
        lang_type = cls._default_lang_type
        while len(n) > 1:
            c = int(n[0] + n[1])
            n = n[2:]
            if c == cls._lang_change:
                lang_type = -lang_type
                continue

            if not cls._chars_reverse.get(c) is None:
                a = cls._chars_reverse.get(c)
                if len(a) == 1:
                    s += a[0]
                else:
                    if lang_type == -1: s += a[0]
                    else: s += a[1]
                continue

            if c < cls._chars['A']:  # буква маленькая
                if lang_type == -1:
                    c_begin = 0
                else:
                    c_begin = 1
                s += chr(c + ord(cls._chars_reverse[0][c_begin]))
            elif c < cls._chars['0']:
                if lang_type == -1:
                    c_begin = 0
                else:
                    c_begin = 1
                s += chr(c + ord(cls._chars_reverse[32][c_begin]) - 32)
            else:  # if c >= cls._chars['0']:  # цифра
                s += chr(c - cls._chars['0'])
        return s
