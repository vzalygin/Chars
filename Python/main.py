from num_encoding import NumEncoding


def main():
    text = "Привет, Андрей. Теперь я могу писать секретные сообщения таким образом."
    enc = NumEncoding.encoding(text)
    dec = NumEncoding.decoding(enc)
    print(text)
    print(enc)
    print(dec)


main()
