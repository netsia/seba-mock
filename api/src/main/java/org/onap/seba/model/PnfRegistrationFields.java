package org.onap.seba.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class PnfRegistrationFields {
    private String lastServiceDate;
    private String macAddress;
    private String manufactureDate;
    private String modelNumber;
    private String oamV4IpAddress;
    private String oamV6IpAddress;
    private String pnfRegistrationFieldsVersion;
    private String serialNumber;
    private String softwareVersion;
    private String unitFamily;
    private String unitType ;
    private String vendorName;
}
