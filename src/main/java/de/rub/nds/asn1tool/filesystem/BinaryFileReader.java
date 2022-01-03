/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

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
