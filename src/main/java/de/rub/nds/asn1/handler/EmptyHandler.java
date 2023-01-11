package de.rub.nds.asn1.handler;

import de.rub.nds.asn1.context.AbstractChooser;

public class EmptyHandler<Chooser extends AbstractChooser> extends Handler<Chooser>{

    public EmptyHandler(Chooser chooser) {
        super(chooser);
    }

    @Override
    public void adjustContext() {
    }
    
}
