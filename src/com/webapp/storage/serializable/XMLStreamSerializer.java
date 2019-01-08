package com.webapp.storage.serializable;

import com.webapp.model.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XMLStreamSerializer implements StreamSerializable {
    private XmlParser xmlParser;

    public XMLStreamSerializer() {
        xmlParser = new XmlParser(Resume.class, Organization.class,ContactType.class, Link.class,
                SectionList.class, SectionOrg.class, SectionText.class, SectionType.class, Organization.Period.class);
    }

    @Override
    public void doWrite(Resume resume, OutputStream outputStream) throws IOException {
        try(Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)){
            xmlParser.marshall(resume, writer);
        }
    }

    @Override
    public Resume doRead(InputStream inputStream) throws IOException {
        try(Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)){
            return xmlParser.unmarshall(reader);
        }
    }
}
