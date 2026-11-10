package murach.survey;

import java.io.Serializable;

/** A simple model for one survey submission (not stored in a database). */
public final class Survey implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String dateOfBirth;
    private final String heardFrom;
    private final boolean announcements;
    private final boolean specialOffers;
    private final String contactMethod;

    public Survey(String firstName, String lastName, String email, String dateOfBirth,
                  String heardFrom, boolean announcements, boolean specialOffers,
                  String contactMethod) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.heardFrom = heardFrom;
        this.announcements = announcements;
        this.specialOffers = specialOffers;
        this.contactMethod = contactMethod;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getDateOfBirth() { return dateOfBirth; }
    public String getHeardFrom() { return heardFrom; }
    public boolean isAnnouncements() { return announcements; }
    public boolean isSpecialOffers() { return specialOffers; }
    public String getContactMethod() { return contactMethod; }
}
