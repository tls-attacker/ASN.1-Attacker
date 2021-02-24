/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2021 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

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
