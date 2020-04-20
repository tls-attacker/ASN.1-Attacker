package de.rub.nds.asn1tool.filesystem;

import java.io.IOException;

public class TextFileWriter extends BinaryFileWriter {

    public TextFileWriter(final String filename) throws IOException {
        this("", filename);
    }

    public TextFileWriter(final String directory, final String filename) throws IOException {
        super(directory, filename);
    }

    public void write(String text) throws IOException {
        super.write(text.getBytes());
    }
}
