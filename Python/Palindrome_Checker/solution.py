def is_palindrome(text):
    cleaned = [ch.lower() for ch in text if ch.isalnum()]
    return cleaned == cleaned[::-1]


def main():
    text = input()
    if is_palindrome(text):
        print('Palindrome')
    else:
        print('Not a palindrome')


if __name__ == '__main__':
    main()
