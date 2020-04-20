package de.rub.nds.asn1tool.filesystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class BinaryFileReader {

    private final String directory;

    private final String filename;

    public BinaryFileReader(final String filename) {
        this("", filename);
    }

    public BinaryFileReader(final String directory, final String filename) {
        this.directory = directory;
        this.filename = filename;
    }

    public byte[] read() throws IOException {
        File file = new File(this.filename);
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        fileInputStream.read(buffer);
        fileInputStream.close();
        return buffer;
    }
}
