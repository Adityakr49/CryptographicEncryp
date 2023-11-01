import numpy as np

def getKeyinv(matrix, keyinv):
    for i in range(3):
        for j in range(3):
            cofactor = calculateCofactor(matrix, i, j)
            keyinv[j][i] = cofactor % 26
            if keyinv[j][i] < 0:
                keyinv[j][i] += 26

    detinv = check(matrix)
    for i in range(3):
        for j in range(3):
            keyinv[i][j] = (keyinv[i][j] * detinv) % 26

def calculateCofactor(matrix, row, col):
    minorMatrix = np.zeros((2, 2), dtype=int)
    minorRow = 0

    for i in range(3):
        if i == row:
            continue
        minorCol = 0
        for j in range(3):
            if j == col:
                continue
            minorMatrix[minorRow][minorCol] = matrix[i][j]
            minorCol += 1
        minorRow += 1

    minorDeterminant = minorMatrix[0][0] * minorMatrix[1][1] - minorMatrix[0][1] * minorMatrix[1][0]
    cofactor = (-1) ** (row + col) * minorDeterminant

    return cofactor

def check(matrix):
    detinv = -1
    det = matrix[0][0] * (matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1]) - \
          matrix[0][1] * (matrix[1][0] * matrix[2][2] - matrix[1][2] * matrix[2][0]) + \
          matrix[0][2] * (matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0])

    det %= 26
    if det < 0:
        det += 26

    for i in range(25):
        if (det * i) % 26 == 1:
            detinv = i
            break

    if detinv == -1:
        print("Multiplicative inverse Does Not exist for determinant", det, ". Exiting")
        exit(0)

    return detinv

def getKeyMatrix(keyMatrix):
    print("Enter the Key Matrix")
    for i in range(3):
        for j in range(3):
            keyMatrix[i][j] = int(input()) % 26

    if check(keyMatrix) == -1:
        exit(0)

def encrypt(plaintext, keyMatrix):
    n = len(keyMatrix)
    encrypted = np.zeros(n, dtype=int)
    result = ""

    for i in range(0, len(plaintext), n):
        for j in range(n):
            encrypted[j] = 0
            for k in range(n):
                encrypted[j] += (ord(plaintext[i + k]) - ord('A')) * keyMatrix[k][j]
            encrypted[j] %= 26
            result += chr(encrypted[j] + ord('A'))

    return result

def decrypt(ciphertext, keyinv):
    return encrypt(ciphertext, keyinv)
print("---------------HILL CIPHER----------------")
keyMatrix = np.zeros((3, 3), dtype=int)
keyinv = np.zeros((3, 3), dtype=int)

getKeyMatrix(keyMatrix)
getKeyinv(keyMatrix, keyinv)

while True:
    choice = input("Enter '1' to encrypt or '2' to decrypt (0 to exit): ")
    if choice == '0':
        break
    elif choice == '1':
        plaintext = input("Enter the plaintext message (in uppercase): ")
        ciphertext = encrypt(plaintext, keyMatrix)
        print("Encrypted Message:", ciphertext)
    elif choice == '2':
        ciphertext = input("Enter the ciphertext message (in uppercase): ")
        decryptedMessage = decrypt(ciphertext, keyinv)
        print("Decrypted Message:", decryptedMessage)
    else:
        print("Invalid choice. Please enter '1' to encrypt or '2' to decrypt (0 to exit).")

print("Goodbye!")
