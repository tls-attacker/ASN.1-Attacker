/*
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2020 Ruhr University Bochum, Paderborn University,
 * and Hackmanit GmbH
 *
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
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
