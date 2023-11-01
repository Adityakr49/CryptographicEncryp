def main():
    plaintext = input("Enter the plaintext message: ")
    key = input("Enter the one-time pad key (binary): ")

    ciphertext = encrypt(plaintext, key)
    decrypted_text = decrypt(ciphertext, key)

    print("Plaintext:", plaintext)
    print("Ciphertext:", ciphertext)
    print("Decrypted Text:", decrypted_text)

# Encrypt the plaintext using the one-time pad key
def encrypt(plaintext, key):
    ciphertext = []
    for i in range(len(plaintext)):
        plain_char = plaintext[i]
        key_char = key[i % len(key)]  # Reuse key if it's shorter
        encrypted_char = chr(ord(plain_char) ^ ord(key_char))
        ciphertext.append(encrypted_char)
    return ''.join(ciphertext)

# Decrypt the ciphertext using the one-time pad key
def decrypt(ciphertext, key):
    return encrypt(ciphertext, key)  # XOR operation is its own inverse

if __name__ == "__main__":
    main()
