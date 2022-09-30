package com.myapp;

import java.io.Serializable;
import java.util.Comparator;
import org.apache.commons.lang3.builder.CompareToBuilder;


public class OperationDifficultyComparator implements
        Comparator<Operation>, Serializable {
    @Override
    public int compare(final Operation o1, final Operation o2) {
        return new CompareToBuilder().append(o1.getPredictedBlock(), o2.getPredictedBlock())
                .toComparison();
    }
}
