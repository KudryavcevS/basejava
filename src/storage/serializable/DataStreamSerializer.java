package storage.serializable;

import model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializable {
    @Override
    public void doWrite(Resume resume, OutputStream outputStream) throws IOException {
        try (DataOutputStream writer = new DataOutputStream(outputStream)) {
            writer.writeUTF(resume.getUuid());
            writer.writeUTF(resume.getFullName());

            writer.writeInt(resume.getContacts().size());
            for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                writer.writeUTF(entry.getKey().name());
                writer.writeUTF(entry.getValue());
            }

            writer.writeInt(resume.getSections().size());
            for (Map.Entry<SectionType, Section> entry : resume.getSections().entrySet()) {
                writer.writeUTF(entry.getKey().name());

                Section section = entry.getValue();
                String sectionClassName = section.getClass().getSimpleName();
                writer.writeUTF(sectionClassName);

                if (sectionClassName.equals("SectionList")) {
                    SectionList sectionList = (SectionList) section;
                    writer.writeInt(sectionList.getItems().size());
                    for (String string : sectionList.getItems()) writer.writeUTF(string);
                }
                if (sectionClassName.equals("SectionText")) {
                    SectionText sectionText = (SectionText) section;
                    writer.writeUTF(sectionText.getContent());
                }
                if (sectionClassName.equals("SectionOrg")) {
                    SectionOrg sectionOrg = (SectionOrg) section;

                    writer.writeInt(sectionOrg.getOrganizations().size());
                    for (Organization organization : sectionOrg.getOrganizations()) {
                        writer.writeUTF(organization.getOrgHomePage().getName());
                        writer.writeUTF(organization.getOrgHomePage().getUrl());

                        writer.writeInt(organization.getPeriods().size());
                        for (Organization.Period period : organization.getPeriods()) {
                            writer.writeUTF(period.getStartDate().toString());
                            writer.writeUTF(period.getEndDate().toString());
                            writer.writeUTF(period.getTitle());
                            writer.writeUTF(period.getDescription());
                        }
                    }
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream inputStream) throws IOException {
        try (DataInputStream reader = new DataInputStream(inputStream)) {
            String uuid = reader.readUTF();
            String fullName = reader.readUTF();
            Resume result = new Resume(uuid, fullName);

            int contactsSize = reader.readInt();
            for (int i = 0; i < contactsSize; i++) {
                result.addContact(ContactType.valueOf(reader.readUTF()), reader.readUTF());
            }

            int sectionsSize = reader.readInt();
            for (int i = 0; i < sectionsSize; i++) {
                SectionType sectionType = SectionType.valueOf(reader.readUTF());

                String sectionClassName = reader.readUTF();

                if (sectionClassName.equals("SectionList")) {
                    int itemsListSize = reader.readInt();
                    List<String> items = new ArrayList<>();
                    for (int j = 0; j < itemsListSize; j++) items.add(reader.readUTF());
                    result.addSections(sectionType, new SectionList(items));
                }
                if (sectionClassName.equals("SectionText")) {
                    result.addSections(sectionType, new SectionText(reader.readUTF()));
                }
                if (sectionClassName.equals("SectionOrg")) {
                    int orgListSize = reader.readInt();
                    List<Organization> organizations = new ArrayList<>();
                    for (int k = 0; k < orgListSize; k++) {
                        String name = reader.readUTF();
                        String url = reader.readUTF();

                        int periodsListSize = reader.readInt();
                        List<Organization.Period> periods = new ArrayList<>();
                        for (int l = 0; l < periodsListSize; l++) {
                            LocalDate startDate = LocalDate.parse(reader.readUTF());
                            LocalDate endDate = LocalDate.parse(reader.readUTF());
                            String title = reader.readUTF();
                            String description = reader.readUTF();
                            periods.add(new Organization.Period(startDate, endDate, title, description));
                        }
                        organizations.add(new Organization(name, url, periods));
                    }
                    result.addSections(sectionType,new SectionOrg(organizations));
                }
            }
            return result;
        }
    }
}
