# Encryption and Decryption using Hill Cipher and One-Time Pad

This repository contains Java and Python programs for encrypting and decrypting messages using the Hill Cipher and One-Time Pad encryption techniques.

## Table of Contents

- [Introduction](#introduction)
- [Prerequisites](#prerequisites)
- [Hill Cipher](#hill-cipher)
  - [Usage](#usage)
  - [File Descriptions](#file-descriptions)
- [One-Time Pad](#one-time-pad)
  - [Usage](#usage)
  - [File Descriptions](#file-descriptions)
- [License](#license)

## Introduction

The Hill Cipher is a polygraphic substitution cipher that is based on linear algebra. It uses a matrix as a key to encrypt and decrypt messages. Each character in the plaintext is transformed into its numerical equivalent, and then the entire message is divided into blocks and encrypted using the matrix key.

The One-Time Pad, on the other hand, is a symmetric key encryption algorithm that uses a random key as long as the message itself. This key is used only once, and the ciphertext is created by XORing the message with the key.

Both of these techniques are known for their security when used correctly, and this repository provides implementations for both.

## Prerequisites

Before using the programs in this repository, you will need to have the following installed:

- Java Development Kit (JDK) for running the Java programs.
- Python for running the Python programs.

## Hill Cipher

### Usage

1. **Hill Cipher Java Implementation:**

    1. Compile the Java program using the following command:

        ```shell
        javac HillCipherImp.java
        ```

    2. Run the program with the desired action (encrypt or decrypt) and provide the necessary inputs.

        - To encrypt a message:

            ```shell
            java HillCipherImp encrypt
            ```

        - To decrypt a message:

            ```shell
            java HillCipherImp decrypt
            ```

    3. Follow the prompts to enter the matrix key and the message you want to encrypt or decrypt.

2. **Hill Cipher Python Implementation:**

    - Run the Python program:

        ```shell
        python HillCipher.py
        ```

### File Descriptions

- `HillCipherImp.java`: Java implementation of the Hill Cipher algorithm.

- `HillCipher.py`: Python implementation of the Hill Cipher algorithm.

## One-Time Pad

### Usage

1. **One-Time Pad Java Implementation:**

    1. Compile the Java program using the following command:

        ```shell
        javac OneTimePad.java
        ```

    2. Run the program with the desired action (encrypt or decrypt) and provide the necessary inputs.

        - To encrypt a message:

            ```shell
            java OneTimePad encrypt
            ```

        - To decrypt a message:

            ```shell
            java OneTimePad decrypt
            ```

    3. Follow the prompts to enter the key (for encryption) or ciphertext (for decryption) and the message.

2. **One-Time Pad Python Implementation:**

    - Run the Python program:

        ```shell
        python OneTimePad.py
        ```

### File Descriptions

- `OneTimePad.java`: Java implementation of the One-Time Pad encryption algorithm.

- `OneTimePad.py`: Python implementation of the One-Time Pad encryption algorithm.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
