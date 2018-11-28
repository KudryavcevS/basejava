package model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {

    private final Link orgHomePage;
    private List<Period> periods = new ArrayList<>();    

    public Organization(String orgName, String url, Period... periods) {
        this.orgHomePage = new Link(orgName, url);
        this.periods = Arrays.asList(periods);
    }
    
    public Organization(String orgName, String url, List<Period> periods) {
        this.orgHomePage = new Link(orgName, url);
        this.periods = periods;
    }
   
    public static class Period {
        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String title;
        private final String description;
        
        public Period(LocalDate startDate, LocalDate endDate, String title, String description){
            Objects.requireNonNull(startDate, "start date is null");
            Objects.requireNonNull(title, "title is null");
            Objects.requireNonNull(endDate, "endDate is null")
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
            this.description = description;
        }
        
        public Period(LocalDate startDate, String title, String description){
            this(startDate, NOW, title, description);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!orgHomePage.equals(that.orgHomePage)) return false;
        if (!startDate.equals(that.startDate)) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (!title.equals(that.title)) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = orgHomePage.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "orgHomePage=" + orgHomePage +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
