import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Integer> hashTable = new MyHashTable<>();
        for(int i = 0; i < 10000; i++) {
            String firstString = generateRandomString(3, 10);
            String secondString = generateRandomString(3, 10);
            MyTestingClass myTestingClass = new MyTestingClass(firstString, secondString);
            hashTable.put(myTestingClass, i);
        }
        hashTable.printNumberOfElementsInEachBucket();
    }

    public static String generateRandomString(int minLen, int maxLen) {
        int len = (int) (Math.random() * (maxLen - minLen + 1)) + minLen;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < len; i++) {
            int rand = (int) (Math.random() * 26);
            sb.append((char) ('a' + rand));
        }
        return sb.toString();
    }
}
