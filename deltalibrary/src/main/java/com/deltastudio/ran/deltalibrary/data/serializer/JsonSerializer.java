/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.deltastudio.ran.deltalibrary.data.serializer;

import com.google.gson.Gson;

public class JsonSerializer {

    private final Gson gson = new Gson();

    public JsonSerializer() {
    }

    public String serialize(Object entity) {
        String jsonString = gson.toJson(entity, entity.getClass());
        return jsonString;
    }

    public Object deserialize(String jsonString, Class cls) {
        Object userEntity = gson.fromJson(jsonString, cls);
        return userEntity;
    }
}
