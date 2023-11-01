// Java code to implement Hill Cipher
import java.util.Scanner;
class HillCipherImp {
    static int det;
    static int detinv;
    static void getKeyinv(int[][] matrix,int[][] keyinv) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int cofactor = calculateCofactor(matrix, i, j);
                keyinv[j][i] = cofactor%26; // Transpose the cofactor matrix
                if(keyinv[j][i]<0){
                    keyinv[j][i]+=26;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                keyinv[i][j]=(keyinv[i][j]*detinv)%26;
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
        int cofactor = (int)Math.pow(-1, row + col) * minorDeterminant;
        
        return cofactor;
    }
    static int check(int[][] matrix){
        detinv=-1;
        det = matrix[0][0] * (matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1])
                   - matrix[0][1] * (matrix[1][0] * matrix[2][2] - matrix[1][2] * matrix[2][0])
                   + matrix[0][2] * (matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0]);
        det%=26;
        if(det<0)det+=26;
        //mul inv of det mod 26
        for(int i=0;i<25;i++){
            if((det*i)%26==1){
                detinv = i;
                break;
            }
        }
        if(detinv==-1){
            System.out.println("Multiplicative inverse Does Not exist for determinant "+det+" exitting");
        }
        return detinv;
    }
    
    // Following function generates the key matrix for the key string
    static void getKeyMatrix(int keyMatrix[][]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Key Matrix");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                keyMatrix[i][j] = sc.nextInt()%26;
            }
        }
        sc.close();
        if(check(keyMatrix)==-1){
            System.exit(0);
        }
    }
    
    // Following function encrypts the message
    static void encrypt(int cipherMatrix[],
            int keyMatrix[][],
            int msg[]) {
        int i, j;
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 1; j++) {
                cipherMatrix[i] = 0;
                cipherMatrix[i]+=msg[j]*keyMatrix[j][i];
            }
            cipherMatrix[i] = cipherMatrix[i] % 26;
        }
    }
    static void dencrypt(int cipherMatrix[][],
            int keyMatrix[][],
            int messageVector[][]) {
        int x, i, j;
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 1; j++) {
                cipherMatrix[i][j] = 0;

                for (x = 0; x < 3; x++) {
                    cipherMatrix[i][j] += keyMatrix[i][x] * messageVector[x][j];
                }

                cipherMatrix[i][j] = cipherMatrix[i][j] % 26;
            }
        }
    }
    static void printMat(int[][] mat){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(mat[i][j]+" ");
            }
            System.out.println();
        }
    }
    // Function to implement Hill Cipher
    static void HillCipher(String message, int[][] key) {
        int m = message.length();
        int n = m + 3-m%3;
        char[] ch = new char[n];
        int[] cipher = new int[n];
        for(int i=0;i<m;i++){
            ch[i]=message.charAt(i);
        }
        int[] arr = new int[3];
        int[] chr = new int[3];
        for(int i=0;i<n/3;i++){
            int row = i*3;
            arr[0]=(int)ch[row];
            arr[1]=(int)ch[row+1];
            arr[2]=(int)ch[row+2];
            encrypt(chr, key, arr);
            cipher[row]=chr[0];
            cipher[row+1]=chr[1];
            cipher[row+2]=chr[2];
        }    
        System.out.println("Cipher Text is:");
        for(int i=0;i<m;i++){
            System.out.print((char)(cipher[i]+'a'));
        }
    }

    // Driver code
    public static void main(String[] args) {
        String msg;
        Scanner sc = new Scanner(System.in);
        int[][] keyMatrix = new int[3][3];
        int[][] keyinv = new int[3][3];
        System.out.println("----------------------Hill Cipher-----------------------");
        getKeyMatrix(keyMatrix);
        getKeyinv(keyMatrix,keyinv);
        while (true) {
        System.out.print("Enter option:\n1.Encryption\n2.Decryption:");
        // int choice = sc.nextInt();
        int choice = 1;
        switch (choice) {
            case 1:
                System.out.println("Enter Plaintext for Encryption");
                msg = sc.nextLine();
                HillCipher(msg,keyMatrix);
                break;
            case 2:
                break;
            default:
                System.out.println("Enter correct option");
                break;
        }
        sc.close();
    }
}

