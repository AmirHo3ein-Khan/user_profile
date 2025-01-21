package ir.maktabsharif.userprofile.security;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class Base64ImageEncoder {
    public static String imageEncoder(InputStream fileContent) {
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[16384];
            while (true) {
                if (!((nRead = fileContent.read(data, 0, data.length)) != -1)) break;
                buffer.write(data, 0, nRead);
            }
            buffer.flush();

            byte[] fileBytes = buffer.toByteArray();

            return Base64.getEncoder().encodeToString(fileBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
