package bsuir.pechuro.utils;

import org.apache.log4j.Logger;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * class Hasher created to reliable hash password
 */
public class Hasher {
    private static Logger logger = Logger.getLogger(Hasher.class);

    /**
     * @param input
     * @return String
     */
    public static String hashBySha1(String input) {
        String sha1 = null;
        try {
            MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
            msdDigest.update(input.getBytes("UTF-8"), 0, input.length());
            sha1 = DatatypeConverter.printHexBinary(msdDigest.digest());
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
        }
        return sha1;
    }
}
