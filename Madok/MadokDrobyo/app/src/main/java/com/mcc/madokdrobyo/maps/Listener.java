package com.mcc.madokdrobyo.maps;

import java.util.HashMap;
import java.util.List;

/**
 * Created by nitul on 2/12/17.
 */

public interface Listener {

    public void onSuccessfullRouteFetch(final List<List<HashMap<String, String>>> result);
    public void onFail();
}
