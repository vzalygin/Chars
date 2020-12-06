from num_encoding import NumEncoding


def main():
    num = NumEncoding.encoding("323")
    print(num)
    text = NumEncoding.decoding(num)
    print(text)


main()
