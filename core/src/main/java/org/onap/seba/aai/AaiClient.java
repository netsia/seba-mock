package org.onap.seba.aai;

import org.onap.seba.aai.model.PNF;

/**
 * Created by cemturker on 26.09.2018.
 */
public interface AaiClient {
    PNF queryPnf(String name);
    PNF putPnf(PNF pnf);
}
