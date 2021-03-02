package de.rub.nds.asn1tool.filesystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TextFileReader {

    private final String directory;

    private final String filename;

    public TextFileReader(final String filename) {
        this("", filename);
    }

    public TextFileReader(final String directory, final String filename) {
        this.directory = directory;
        this.filename = filename;
    }

    public String read() throws IOException  {
        StringBuilder content = new StringBuilder();
        File file = new File(this.filename);
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] buffer = new byte[2048];
        int bytesRead = 0;
        do {
            bytesRead = fileInputStream.read(buffer);
            if(bytesRead > 0) {
                content.append(new String(buffer, 0, bytesRead));
            }
        } while(bytesRead > 0);
        fileInputStream.close();
        return content.toString();
    }
}
