package storage.serializable;

import model.Resume;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class JSONStreamSerializer implements StreamSerializable  {

    @Override
    public void doWrite(Resume resume, OutputStream outputStream) throws IOException {
        try(Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)){
            JsonParser.write(resume, writer);
        }
    }

    @Override
    public Resume doRead(InputStream inputStream) throws IOException {
        try(Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)){
            return JsonParser.read(reader, Resume.class);
        }
    }
}
