package lyq.com.livedatabusdemo;

import android.arch.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sunshiny
 * @date 2019/1/30.
 * @desc
 */
public class LiveDataBusBeta {

    private final Map<String,MutableLiveData<Object>>  bus;

    public LiveDataBusBeta() {
        bus = new HashMap<>();
    }

    private static class SingelHolder{
        private static final LiveDataBusBeta DATA_BUS = new LiveDataBusBeta();
    }

    public static LiveDataBusBeta get(){
        return SingelHolder.DATA_BUS;
    }

    public <T> MutableLiveData<T> with(String target,Class<T> type){
        if (!bus.containsKey(target)){
            bus.put(target,new MutableLiveData<Object>());
        }

        return (MutableLiveData<T>) bus.get(target);
    }

    public MutableLiveData<Object> with(String target){
        return with(target,Object.class);
    }
}
