from pyDes import *

class Des:
    @classmethod
    def __num_to_bytes(cls, num):
        length = 1
        while True:
            try:
                res = int.to_bytes(num, length, 'little')
                break
            except:
                length *= 2
        return res

    @classmethod
    def __pair_nums(cls, a, b):
        a_bytes = cls.__num_to_bytes(a)
        b_bytes = cls.__num_to_bytes(b)
        result_bytes = []
        for i in range(max(len(a_bytes), len(b_bytes))):
            result_bytes.append(a_bytes[i] if i < len(a_bytes) else 0)
            result_bytes.append(b_bytes[i] if i < len(b_bytes) else 0)
        return int.from_bytes(result_bytes, 'little')

    @classmethod
    def __depair_num(cls, x):
        x_bytes = cls.__num_to_bytes(x)
        a_bytes = []
        b_bytes = []
        parity = 0
        for x_byte in x_bytes:
            if parity == 0:
                a_bytes.append(x_byte)
            else:
                b_bytes.append(x_byte)
            parity += 1
            parity %= 2
        return int.from_bytes(a_bytes, 'little'), int.from_bytes(b_bytes, 'little')

    @classmethod
    def enc(cls, data, key):
        k = des(key, CBC, b"\0\0\0\0\0\0\0\1", pad=None, padmode=PAD_PKCS5)
        to_encrypt = cls.__num_to_bytes(data)
        d = k.encrypt(to_encrypt)
        old_length = len(d)
        to_num = int.from_bytes(d, 'little')
        return cls.__pair_nums(to_num, old_length)

    @classmethod
    def dec(cls, to_num, key):
        to_num, old_length = cls.__depair_num(to_num)
        to_bytes = int.to_bytes(to_num, old_length, 'little')
        k = des(key, CBC, b"\0\0\0\0\0\0\0\1", pad=None, padmode=PAD_PKCS5)
        decrypted = k.decrypt(to_bytes)
        return int.from_bytes(decrypted, 'little')
