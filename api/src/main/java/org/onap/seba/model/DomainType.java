package org.onap.seba.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum DomainType {
    FAULT("fault"),
    HEARTBEAT("heartbeat"),
    MEASUREMENT("measurement"),
    MOBILE_FLOW("mobileFlow"),
    NOTIFICATION("notification"),
    OTHER("other"),
    PNF_REGISTRATION("pnfRegistration"),
    SIP_SIGNALING("sipSignaling"),
    STATE_CHANGE("stateChange"),
    SYSLOG("syslog"),
    TRESHOLD_CROSSING_ALERT("thresholdCrossingAlert"),
    VOICE_QUALITY("voiceQuality");

    @Getter
    @Setter
    String type;
}
