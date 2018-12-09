package model;

import storage.serializable.LocalDateXMLAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    private Link orgHomePage;
    private List<Period> periods = new ArrayList<>();

    public Organization(String orgName, String url, Period... periods) {
        this(orgName, url, Arrays.asList(periods));
    }

    public Organization(String orgName, String url, List<Period> periods) {
        this.orgHomePage = new Link(orgName, url);
        this.periods = periods;
    }

    public Organization() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!orgHomePage.equals(that.orgHomePage)) return false;
        return periods.equals(that.periods);
    }

    @Override
    public int hashCode() {
        int result = orgHomePage.hashCode();
        result = 31 * result + periods.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "orgHomePage=" + orgHomePage +
                ", periods=" + periods +
                '}';
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Period implements Serializable {
        @XmlJavaTypeAdapter(LocalDateXMLAdapter.class)
        private LocalDate startDate;
        @XmlJavaTypeAdapter(LocalDateXMLAdapter.class)
        private LocalDate endDate;
        private String title;
        private String description;

        public Period(LocalDate startDate, LocalDate endDate, String title, String description) {
            Objects.requireNonNull(startDate, "start date is null");
            Objects.requireNonNull(title, "title is null");
            Objects.requireNonNull(endDate, "endDate is null");
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
            this.description = description;
        }

        public Period(LocalDate startDate, String title, String description) {
            this(startDate, LocalDate.now(), title, description);
        }

        public Period() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Period period = (Period) o;

            if (!startDate.equals(period.startDate)) return false;
            if (!endDate.equals(period.endDate)) return false;
            if (!title.equals(period.title)) return false;
            return description != null ? description.equals(period.description) : period.description == null;
        }

        @Override
        public int hashCode() {
            int result = startDate.hashCode();
            result = 31 * result + endDate.hashCode();
            result = 31 * result + title.hashCode();
            result = 31 * result + (description != null ? description.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Period{" +
                    "startDate=" + startDate +
                    ", endDate=" + endDate +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }
}
