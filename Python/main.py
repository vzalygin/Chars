from num_encoding import NumEncoding


def main():
    num = NumEncoding.encoding("абвгэюяАБВГЭЮЯ 0123456789 abcxyzABCXYZ .,!?'")
    print(num)
    text = NumEncoding.decoding(num)
    print(text)


main()
