package org.onap.seba.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Event {
    CommonEventHeader commonEventHeader;
    PnfRegistrationFields pnfRegistrationFields;
}
