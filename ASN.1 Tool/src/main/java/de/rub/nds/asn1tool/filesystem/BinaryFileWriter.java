package de.rub.nds.asn1tool.filesystem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BinaryFileWriter {

    private final String directory;

    private final String filename;

    private FileOutputStream fileOutputStream = null;

    public BinaryFileWriter(final String filename) throws IOException {
        this("", filename);
    }

    public BinaryFileWriter(final String directory, final String filename) throws IOException {
        this.directory = directory;
        this.filename = filename;
        this.initialize();
    }

    private void initialize() throws IOException {
        new File(this.directory).mkdirs();
        File file = new File(this.directory, this.filename);
        this.fileOutputStream = new FileOutputStream(file);
    }

    public void write(byte[] content) throws IOException {
        if(content != null) {
            this.write(content, 0, content.length);
        }
    }

    public void write(byte[] content, int offset, int length) throws IOException {
        fileOutputStream.write(content, offset, length);
        fileOutputStream.flush();
    }

    public void close() throws IOException {
        fileOutputStream.close();
    }
}
