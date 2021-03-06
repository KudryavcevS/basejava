package com.webapp.storage.serializable;

import com.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface StreamSerializable {

    void doWrite(Resume r, OutputStream outputStream) throws IOException;

    Resume doRead(InputStream inputStream) throws IOException;
}
