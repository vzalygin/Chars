from num_encoding import NumEncoding


def main():
    text = "абвгэюяАБВГЭЮЯ 0123456789 abcxyzABCXYZ .,!?'"
    enc = NumEncoding.encoding(text)
    dec = NumEncoding.decoding(enc)
    print(text)
    print(enc)
    print(dec)


main()
