package org.onap.seba.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum ProcessPriority {
    HIGH("High"),
    MEDIUM("Medium"),
    NORMAL("Normal"),
    LOW("Low");

    @Getter
    @Setter
    String type;
}
