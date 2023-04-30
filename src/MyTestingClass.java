public class MyTestingClass {
    public String firstString;
    public String secondString;

    public MyTestingClass(String firstString, String secondString) {
        this.firstString = firstString;
        this.secondString = secondString;
    }

    @Override
    public int hashCode() {
        int firstHash = 0;
        for(int i = 0; i < firstString.length(); i++) {
            firstHash = 31 * firstHash + firstString.charAt(i);
        }
        int secondHash = 0;
        for(int i = 0; i < secondString.length(); i++) {
            secondHash = 31 * secondHash + secondString.charAt(i);
        }
        return Math.abs(secondHash + firstHash);
    }
}
