package de.rub.nds.asn1tool.filesystem;

import java.io.IOException;
import java.util.Base64;

public class HexFileWriter extends BinaryFileWriter {

    public HexFileWriter(final String filename) throws IOException {
        super(filename);
    }

    public HexFileWriter(final String directory, final String filename) throws IOException {
        super(directory, filename);
    }

    @Override
    public void write(byte[] content, int offset, int length) throws IOException {
        byte[] contentSlice = new byte[length];
        for(int i = 0; i < length; i++) {
            contentSlice[i] = content[offset + i];
        }
        byte[] base64Encoded = Base64.getEncoder().encode(contentSlice);
        super.write(base64Encoded, 0, base64Encoded.length);
    }
}
