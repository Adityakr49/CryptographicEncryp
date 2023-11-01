import java.util.Scanner;

class HillCipherImp2 {
    static int det;
    static int detinv;

    static void getKeyinv(int[][] matrix, int[][] keyinv) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int cofactor = calculateCofactor(matrix, i, j);
                keyinv[j][i] = cofactor % 26; // Transpose the cofactor matrix
                if (keyinv[j][i] < 0) {
                    keyinv[j][i] += 26;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                keyinv[i][j] = (keyinv[i][j] * detinv) % 26;
            }
        }
    }

    static int calculateCofactor(int[][] matrix, int row, int col) {
        int[][] minorMatrix = new int[2][2];
        int minorRow = 0;
        int minorCol;

        for (int i = 0; i < 3; i++) {
            if (i == row) {
                continue;
            }
            minorCol = 0;
            for (int j = 0; j < 3; j++) {
                if (j == col) {
                    continue;
                }
                minorMatrix[minorRow][minorCol] = matrix[i][j];
                minorCol++;
            }
            minorRow++;
        }

        int minorDeterminant = minorMatrix[0][0] * minorMatrix[1][1] - minorMatrix[0][1] * minorMatrix[1][0];
        int cofactor = (int) Math.pow(-1, row + col) * minorDeterminant;

        return cofactor;
    }

    static int check(int[][] matrix) {
        detinv = -1;
        det = matrix[0][0] * (matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1])
                - matrix[0][1] * (matrix[1][0] * matrix[2][2] - matrix[1][2] * matrix[2][0])
                + matrix[0][2] * (matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0]);
        det %= 26;
        if (det < 0) det += 26;
        // Multiplicative inverse of det mod 26
        for (int i = 0; i < 25; i++) {
            if ((det * i) % 26 == 1) {
                detinv = i;
                break;
            }
        }
        if (detinv == -1) {
            System.out.println("Multiplicative inverse Does Not exist for determinant " + det + ". Exiting");
            System.exit(0);
        }
        return detinv;
    }

    // Following function generates the key matrix for the key string
    static void getKeyMatrix(int keyMatrix[][]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Key Matrix");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                keyMatrix[i][j] = sc.nextInt() % 26;
            }
        }
        sc.close();
        if (check(keyMatrix) == -1) {
            System.exit(0);
        }
    }

    // Encrypt a plaintext message using the provided key matrix
    //Matrix Multiplication
    static String encrypt(String plaintext, int[][] keyMatrix) {
        int n = keyMatrix.length;
        int[] encrypted = new int[n];
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i += n) {
            for (int j = 0; j < n; j++) {
                encrypted[j] = 0;
                for (int k = 0; k < n; k++) {
                    encrypted[j] += (plaintext.charAt(i + k) - 'A') * keyMatrix[k][j];
                }
                encrypted[j] %= 26;
                result.append((char) (encrypted[j] + 'A'));
            }
        }

        return result.toString();
    }
    static void printMat(int[][] mat){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(mat[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int keyMatrix[][] = new int[3][3];
        int keyinv[][] = new int[3][3];
        getKeyMatrix(keyMatrix);
        getKeyinv(keyMatrix,keyinv);
        System.out.println("Enter the plaintext message (in uppercase):");
        Scanner scanner = new Scanner(System.in);
        String plaintext = "PAYMOREMONEY";

        String ciphertext = encrypt(plaintext, keyMatrix);
        System.out.println("Encrypted Message: " + ciphertext);

        String decryptedMessage = encrypt(ciphertext, keyinv);
        System.out.println("Decrypted Message: " + decryptedMessage);

        scanner.close();
    }
}
