package storage.serializable;

import com.google.gson.*;

import java.lang.reflect.Type;

public class SectionJsonAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {
    @Override
    public T deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return null;
    }

    @Override
    public JsonElement serialize(T t, Type type, JsonSerializationContext jsonSerializationContext) {
        return null;
    }
}
