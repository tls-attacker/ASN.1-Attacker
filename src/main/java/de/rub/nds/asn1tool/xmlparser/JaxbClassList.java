package de.rub.nds.asn1tool.xmlparser;

import java.util.LinkedList;
import java.util.List;

public class JaxbClassList {

    private static JaxbClassList instance = null;

    private final List<Class> classList = new LinkedList<>();

    private JaxbClassList() {

    }

    public static JaxbClassList getInstance() {
        if(instance == null) {
            instance = new JaxbClassList();
        }
        return instance;
    }

    public void addClass(Class _class) {
        if(_class != null && this.classList.contains(_class) == false) {
            this.classList.add(_class);
        }
    }

    public void addClasses(Class... classes) {
        if(classes != null) {
            for(Class _class : classes) {
                this.addClass(_class);
            }
        }
    }

    public Class[] getClasses() {
        Class[] classes = new Class[this.classList.size()];
        for(int i = 0; i < this.classList.size(); i++) {
            classes[i] = this.classList.get(i);
        }
        return classes;
    }
}
