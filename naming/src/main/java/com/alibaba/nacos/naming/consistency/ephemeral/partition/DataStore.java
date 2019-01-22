/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.nacos.naming.consistency.ephemeral.partition;

import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.naming.consistency.Datum;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Store of data
 *
 * @author nkorange
 * @since 1.0.0
 */
@Component
public class DataStore {

    private Map<String, Datum> dataMap = new ConcurrentHashMap<>(1024);

    public void put(String key, Datum value) {
        dataMap.put(key, value);
    }

    public void remove(String key) {
        dataMap.remove(key);
    }

    public Set<String> keys() {
        return dataMap.keySet();
    }

    public Datum get(String key) {
        return dataMap.get(key);
    }

    public Map<String, Datum> batchGet(List<String> keys) {
        Map<String, Datum> map = new HashMap<>();
        for (String key : keys) {
            if (!dataMap.containsKey(key)) {
                continue;
            }
            map.put(key, dataMap.get(key));
        }
        return map;
    }
}