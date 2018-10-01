package org.onap.seba.model;

import lombok.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class CommonEventHeader {
    private long startEpochMicrosec;
    private String sourceId;
    private String eventId;
    private String nfcNamingCode;
    private String nfVendorName;
    private String reportingEntityId;
    private String eventType;
    private String priority;
    private String domain;
    private long lastEpochMicrosec;
    private String eventName;
    private String sourceName;
    private String nfNamingCode;
    private String version;
    private String reportingEntityName;
    private int sequence;
    private InternalHeaderFields internalHeaderFields;
    private String timeZoneOffset;
    private String vesEventListenerVersion;

    private void createDefaultData(String source)
    {
        startEpochMicrosec = new Date().getTime();
        sourceId = "Progran";
        eventId = "Progran-20";
        nfcNamingCode ="progran";
        reportingEntityId="No UUID available";
        internalHeaderFields = new InternalHeaderFields();
        DateFormat dateFormat = new SimpleDateFormat("EEE, MM DD YYYY HH:mm:ss zzz");
        Date date = new Date();
        String dateStr = dateFormat.format(date);
        internalHeaderFields.setCollectorTimeStamp(dateStr);
        eventType = "Progran profile rate";
        priority = ProcessPriority.HIGH.getType();
        version = "4.0.1";
        reportingEntityName = source;
        sequence = 3;
        domain = DomainType.PNF_REGISTRATION.getType();
        eventName = "enodbMeasurement";
        sourceName = "PROGRAN";
        nfNamingCode = "vPROGRAN";
        vesEventListenerVersion = "7.0.1";
    }
}
