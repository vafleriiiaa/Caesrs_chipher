import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static String rus_upper_alpha = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    public static String rus_lower_alpha = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    public static String en_upper_alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String en_lower_alpha = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) throws IOException {
        while(true) {
            Scanner in = new Scanner(System.in);
            System.out.println("Выберите операцию:");
            System.out.println("Шифрование");
            System.out.println("Дешифрование");

            String op = in.nextLine();

            Scanner in2 = new Scanner(System.in);
            System.out.println("Выберите сдвиг:");

            int shift = in2.nextInt();

            File inputFile;
            File outputFile;
            BufferedReader reader;
            BufferedWriter writer;

            String line;
            if (op.toLowerCase().contains("дешифрование")) {
                if (shift <= 0) {
                    System.out.println("Неверное значение сдвига");
                } else {
                    inputFile = new File("src\\files\\file.txt");
                    outputFile = new File("src\\files\\new_file.txt");
                    reader = new BufferedReader(new FileReader(inputFile));
                    writer = new BufferedWriter(new FileWriter(outputFile));

                    while((line = reader.readLine()) != null) {
                        writer.write(decrypt(line, shift));
                    }

                    reader.close();
                    writer.close();
                    System.out.println("Расшифровано");
                }
            } else if (op.toLowerCase().contains("шифрование")) {
                inputFile = new File("src\\files\\file.txt");
                outputFile = new File("src\\files\\new_file.txt");
                reader = new BufferedReader(new FileReader(inputFile));
                writer = new BufferedWriter(new FileWriter(outputFile));

                while((line = reader.readLine()) != null) {
                    writer.write(encrypt(line, shift));
                }

                reader.close();
                writer.close();
                System.out.println("Зашифровано");

            } else if (shift <= 0) {
                System.out.println("Неверное значение сдвига");
            } else {
                System.out.println("Неверная операция");
            }
        }
    }

    public static String encrypt(String word, int shift) {
        String result = "";
        char[] temp = new char[0];
        char[] wordCharArray = word.toCharArray();

        for(int i = 0; i < wordCharArray.length; ++i) {
            char ch = wordCharArray[i];
            if (rus_upper_alpha.contains("" + ch)) {
                result = result + rus_upper_alpha.toCharArray()[(rus_upper_alpha.indexOf("" + ch) + shift) % 33];
            } else if (rus_lower_alpha.contains("" + ch)) {
                result = result + rus_lower_alpha.toCharArray()[(rus_lower_alpha.indexOf("" + ch) + shift) % 33];
            } else if (en_upper_alpha.contains("" + ch)) {
                result = result + en_upper_alpha.toCharArray()[(en_upper_alpha.indexOf("" + ch) + shift) % 26];
            } else if (en_lower_alpha.contains("" + ch)) {
                result = result + en_lower_alpha.toCharArray()[(en_lower_alpha.indexOf("" + ch) + shift) % 26];
            } else {
                result = result + ch;
            }
        }

        return result;
    }

    public static String decrypt(String word, int shift) {
        String result = "";
        char[] wordCharArray = word.toCharArray();

        for(int i = 0; i < wordCharArray.length; ++i) {
            char ch = wordCharArray[i];
            if (rus_upper_alpha.contains("" + ch)) {
                result = result + rus_upper_alpha.toCharArray()[(rus_upper_alpha.indexOf("" + ch) + 33 - shift % 33) % 33];
            } else if (rus_lower_alpha.contains("" + ch)) {
                result = result + rus_lower_alpha.toCharArray()[(rus_lower_alpha.indexOf("" + ch) + 33 - shift % 33) % 33];
            } else if (en_upper_alpha.contains("" + ch)) {
                result = result + en_upper_alpha.toCharArray()[(en_upper_alpha.indexOf("" + ch) + 26 - shift % 26) % 26];
            } else if (en_lower_alpha.contains("" + ch)) {
                result = result + en_lower_alpha.toCharArray()[(en_lower_alpha.indexOf("" + ch) + 26 - shift % 26) % 26];
            } else {
                result = result + ch;
            }
        }

        return result;
    }
}
