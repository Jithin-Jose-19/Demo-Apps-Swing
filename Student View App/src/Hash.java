import java.util.HashMap;
import java.util.Map;

public class Hash {

    public static String hashing(final String password) {
        String hashedValue="";
        final Map<Character, String> map = new HashMap<>();
        
        final String hSrc = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890@";
        for (int i = 0; i < hSrc.length(); i++) {
            int ascii = hSrc.charAt(i);
            ascii += 4;
            char value = (char) ascii;
            String hashed = String.valueOf((ascii * 3) - 9 * 3)+value;
            map.put(hSrc.charAt(i), hashed);
        }
        for (int i = 0; i < password.length(); i++) {
            for (Map.Entry<Character, String> mp : map.entrySet()) {
                if(password.charAt(i)==mp.getKey())
                    hashedValue += mp.getValue();
            }
        }
        return hashedValue;
    }
}