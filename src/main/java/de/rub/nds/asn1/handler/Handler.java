package de.rub.nds.asn1.handler;

import de.rub.nds.asn1.context.AbstractChooser;

public abstract class Handler<Chooser extends AbstractChooser> {

    protected final Chooser chooser;

    public Handler(Chooser chooser) {
        this.chooser = chooser;
    }

    public abstract void adjustContext();
}
