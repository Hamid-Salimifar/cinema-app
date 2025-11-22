package ir.maktabsharif.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class ImageUtil {

    public static String encodeImage(InputStream imageInputStream, String mimeType) throws IOException {
        byte[] fileContent = imageInputStream.readAllBytes();
        String encodedImage = Base64.getEncoder().encodeToString(fileContent);
        return "data:" + mimeType + ";Base64," + encodedImage;

    }
}
