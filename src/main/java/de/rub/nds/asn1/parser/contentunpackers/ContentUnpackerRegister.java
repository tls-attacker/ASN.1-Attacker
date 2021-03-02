package de.rub.nds.asn1.parser.contentunpackers;

import java.util.LinkedList;
import java.util.List;

public class ContentUnpackerRegister {

    private static ContentUnpackerRegister instance = null;

    private final List<Class<? extends ContentUnpacker>> contentUnpackerClasses = new LinkedList<>();

    private final List<ContentUnpacker> contentUnpackers = new LinkedList<>();

    private ContentUnpackerRegister() {

    }

    public static ContentUnpackerRegister getInstance() {
        if(instance == null) {
            instance = new ContentUnpackerRegister();
        }
        return instance;
    }

    @Override
    public Object clone() {
        return this;
    }

    public void registerContentUnpacker(final ContentUnpacker contentUnpacker) {
        if(this.contentUnpackerClasses.contains(contentUnpacker.getClass()) == false) {
            this.contentUnpackerClasses.add(contentUnpacker.getClass());
            this.contentUnpackers.add(contentUnpacker);
        }
    }

    public List<ContentUnpacker> getContentUnpackers() {
        return this.contentUnpackers;
    }
}
