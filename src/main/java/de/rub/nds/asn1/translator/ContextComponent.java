package de.rub.nds.asn1.translator;

import de.rub.nds.asn1.parser.IntermediateAsn1Field;

public class ContextComponent {

    private static int MIN_SCORE = 3;

    public final String identifier;

    public final String type;

    public final ContextComponentOption<?>[] contextComponentOptions;

    public final boolean isOptional;

    public final boolean isRepetitive;

    public ContextComponent(final String identifier, final String type, final ContextComponentOption<?>[] contextComponentOptions, final boolean isOptional, final boolean isRepetitive) {
        this.identifier = identifier;
        this.type = type;
        this.contextComponentOptions = contextComponentOptions;
        this.isOptional = isOptional;
        this.isRepetitive = isRepetitive;
    }

    public boolean hasMatch(final IntermediateAsn1Field intermediateAsn1Field) {
        return this.getMatch(intermediateAsn1Field) != null;
    }

    public ContextComponentOption<?> getMatch(final IntermediateAsn1Field intermediateAsn1Field) {
        int maxScore = 0;
        ContextComponentOption bestMatch = null;
        if(isOptional) {
            //if a ContextCoponent is optional, we want a perfect Match with one of the ContextComponentOptions, otherwise it can be not detected if an optional asn1 field is not present
            MIN_SCORE = 4;
        }
        else {
            MIN_SCORE = 3;
        }
            
        for(ContextComponentOption contextComponentOption : this.contextComponentOptions) {
            int score = contextComponentOption.computeScore(intermediateAsn1Field.getTag(), intermediateAsn1Field.getTagClass(), intermediateAsn1Field.getTagConstructed(), intermediateAsn1Field.getTagNumber(), intermediateAsn1Field.containsChildren());
            if(score >= MIN_SCORE && score > maxScore) {
                maxScore = score;
                bestMatch = contextComponentOption;
            }
        }
        return bestMatch;
    }
}
