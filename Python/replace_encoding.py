class ReplaceEncoding:
    _el = (('а', 'a'), ('с', 'c'), ('е', 'e'), ('к', 'k'), ('о', 'o'), ('р', 'p'), ('г', 'r'), ('х', 'x'), ('у', 'y'),
           ('А', 'A'), ('В', 'B'), ('С', 'C'), ('Е', 'E'), ('Н', 'H'), ('К', 'K'), ('М', 'M'), ('О', 'O'), ('Р', 'P'),
           ('Т', 'T'), ('Х', 'X'), ('У', 'Y'))

    @classmethod
    def _check_usability(cls, c):
        for i in range(len(cls._el)):
            e = cls._el[i]
            if c == e[0]:
                return True, 0, i
            elif c == e[1]:
                return True, 1, i
        return False, -1, i

    @classmethod
    def _check_lang(cls, c: str):
        if ord('а') <= ord(c.lower()) < ord('а') + 32:
            return 0
        elif ord('a') <= ord(c.lower()) < ord('a') + 28:
            return 1
        else:
            return -1

    @classmethod
    def _rule(cls, prev_c: str, next_c: str, lang, rule_number):
        if rule_number == 0:
            return ((prev_c.isalpha() and (cls._check_lang(prev_c) == lang or lang == -1)) or not prev_c.isalpha()) and \
                   ((next_c.isalpha() and (cls._check_lang(next_c) == lang or lang == -1)) or not next_c.isalpha())
        else:
            return False

    @classmethod
    def _sys10to2(cls, n):  # инверсия, дабы в конце можно было добавить незначащие нули
        sys = 2
        sys_n = ''
        while n > 0:
            sys_n += str(n % sys)
            n //= sys
        return sys_n

    @classmethod
    def _sys2to10(cls, sys_n):
        sys = 2
        n = 0
        for i in range(len(sys_n)):
            n += int(sys_n[i]) * (sys ** i)
        return n

    @classmethod
    def encoding(cls, container: str, mess: str):
        mess = cls._sys10to2(int(mess))
        res = ''
        for i in range(len(container) - 1):
            c = container[i]
            usable, lang, n = cls._check_usability(c)
            if usable:
                if cls._rule(container[i - 1], container[i + 1], lang, 0):
                    if len(mess) > 0:
                        if mess[0] == '0': res += cls._el[n][0]  # замена на русскую букву
                        else: res += cls._el[n][1]  # замена на английскую букву
                        mess = mess[1:]
                    else:
                        res += cls._el[n][0]  # иначе по нулям
            else:
                res += c
        return res

    @classmethod
    def decoding(cls, container: str):
        res = ''
        for i in range(len(container)-1):
            usable, lang, n = cls._check_usability(container[i])
            if usable:
                if cls._rule(container[i-1], container[i+1], -1, 0):
                    res += str(lang)
        last = res.rfind('1')
        res = res[0:last+1]
        res = cls._sys2to10(res)
        return str(res)
