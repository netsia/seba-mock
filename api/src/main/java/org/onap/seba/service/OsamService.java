package org.onap.seba.service;

import org.onap.seba.model.AccessPod;

/**
 * Created by cemturker on 02.10.2018.
 */
public interface OsamService {
    void register(AccessPod accessPod);
    void deRegister(AccessPod accessPod);
}
