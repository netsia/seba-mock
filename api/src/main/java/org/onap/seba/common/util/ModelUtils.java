package org.onap.seba.common.util;

import org.onap.seba.common.exception.NullParameterException;
import org.onap.seba.model.*;

public class ModelUtils {

    private ModelUtils(){}

    public static CommonEventHeader getDefaultPnfCommontEventHeader(String pnfName) throws NullParameterException {
        if(pnfName == null)
            throw new NullParameterException("PNF name can not be null");

        long timeMillis = System.currentTimeMillis();
        return CommonEventHeader.builder().eventId(generateEventId()).lastEpochMicrosec(timeMillis)
                .startEpochMicrosec(timeMillis).version(MessageConstants.VERSION_NUMBER)
                .vesEventListenerVersion(MessageConstants.VES_EVENT_LISTENER_VERSION_NUMBER).sequence(MessageConstants.SEQUENCE_NUMBER)
        .internalHeaderFields(new InternalHeaderFields(String.valueOf(timeMillis))).priority(MessageConstants.PRIORITY_NORMAL)
                .eventName(MessageConstants.DOMAIN_PNF_REGISTRATION).eventType(MessageConstants.DOMAIN_PNF_REGISTRATION)
                .domain(DomainType.PNF_REGISTRATION.getType()).sourceId(MessageConstants.DOMAIN_PNF_REGISTRATION)
                .sourceName(pnfName).reportingEntityName(MessageConstants.DOMAIN_PNF_REGISTRATION).build();
    }

    public static PnfRegistrationFields createPnfRegistration(String macAddress ,String ipV4Address,String ipV6Address)
    {
        return createPnfRegistration(macAddress,ipV4Address,ipV6Address,null,null,null,
                null,null,null, null,
                null,null);
    }

    public static PnfRegistrationFields createPnfRegistration(String macAddress ,String ipV4Address,String ipV6Address,
                                                              String serialNumber,String unitFamily,String modelNumber,
                                                              String softwareVersion,String unitType,String vendorName,
                                                              String pnfRegistrationFieldsVersion,String lastServiceDate,
                                                              String manufactureDate)
    {

        PnfRegistrationFields.PnfRegistrationFieldsBuilder pnfRegistrationFields =  PnfRegistrationFields.builder();


        if(pnfRegistrationFieldsVersion != null)
            pnfRegistrationFields.pnfRegistrationFieldsVersion(pnfRegistrationFieldsVersion);
        else
            pnfRegistrationFields.pnfRegistrationFieldsVersion(MessageConstants.PNF_REGISTRATION_FIELDS_VERSION_VALUE);


        if(lastServiceDate != null)
            pnfRegistrationFields.lastServiceDate(lastServiceDate);
        else
            pnfRegistrationFields.lastServiceDate(String.valueOf(System.currentTimeMillis()));


        if(manufactureDate != null)
            pnfRegistrationFields.manufactureDate(manufactureDate);
        else
            pnfRegistrationFields.manufactureDate(String.valueOf(System.currentTimeMillis()));


        if(ipV4Address != null)
            pnfRegistrationFields.oamV4IpAddress(ipV4Address);
        if(ipV6Address != null)
             pnfRegistrationFields.oamV6IpAddress(ipV6Address);
        if(macAddress != null)
            pnfRegistrationFields.macAddress(macAddress);
        if(serialNumber != null)
            pnfRegistrationFields.serialNumber(serialNumber);
        if(unitFamily != null)
            pnfRegistrationFields.unitFamily(unitFamily);
        if(modelNumber != null)
            pnfRegistrationFields.modelNumber(modelNumber);
        if(softwareVersion != null)
            pnfRegistrationFields.softwareVersion(softwareVersion);
        if(unitType != null)
            pnfRegistrationFields.unitType(unitType);
        if(vendorName != null)
            pnfRegistrationFields.vendorName(vendorName);

        return pnfRegistrationFields.build();
    }

    private static String generateEventId() {
        String timeAsString = String.valueOf(System.currentTimeMillis());
        return String.format("registration_%s",
                timeAsString.substring(timeAsString.length() - 11, timeAsString.length() - 3));
    }
}
