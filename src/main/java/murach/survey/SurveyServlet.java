package murach.survey;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** New survey module: original EmailListServlet and web.xml stay untouched. */
@WebServlet(name = "SurveyServlet", urlPatterns = {"/survey", "/week2"})
public class SurveyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Pattern EMAIL =
            Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
    private static final List<String> SOURCES = Arrays.asList(
            "Search engine", "Word of mouth", "Social Media", "Other");
    private static final List<String> CONTACT_METHODS = Arrays.asList(
            "Email or postal mail", "Email only", "Postal mail only");
    private static final DateTimeFormatter US_DATE =
            DateTimeFormatter.ofPattern("M/d/uuuu").withResolverStyle(ResolverStyle.STRICT);
    private static final DateTimeFormatter ISO_DATE = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        setFormValues(request, new Survey("", "", "", "", SOURCES.get(0),
                false, false, CONTACT_METHODS.get(0)));
        request.getRequestDispatcher("/WEB-INF/views/survey.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        Survey survey = new Survey(
                clean(request.getParameter("firstName")),
                clean(request.getParameter("lastName")),
                clean(request.getParameter("email")),
                clean(request.getParameter("dateOfBirth")),
                clean(request.getParameter("heardFrom")),
                "yes".equals(request.getParameter("announcements")),
                "yes".equals(request.getParameter("specialOffers")),
                clean(request.getParameter("contactMethod")));
        setFormValues(request, survey);

        String error = validate(survey);
        if (error != null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("errorMessage", error); // server-owned text only
            request.getRequestDispatcher("/WEB-INF/views/survey.jsp").forward(request, response);
            return;
        }

        // The result view receives only HTML-escaped text, never raw user input.
        request.setAttribute("dateOfBirthDisplay", survey.getDateOfBirth().isEmpty()
                ? "Not provided" : html(survey.getDateOfBirth()));
        request.setAttribute("heardFromDisplay", html(survey.getHeardFrom()));
        request.setAttribute("contactDisplay", html(survey.getContactMethod()));
        request.getRequestDispatcher("/WEB-INF/views/survey-result.jsp")
                .forward(request, response);
    }

    private static String validate(Survey survey) {
        if (survey.getFirstName().isEmpty() || survey.getLastName().isEmpty()
                || survey.getEmail().isEmpty()) {
            return "Please enter your first name, last name, and email address.";
        }
        if (survey.getFirstName().length() > 80 || survey.getLastName().length() > 80
                || survey.getEmail().length() > 160 || survey.getDateOfBirth().length() > 10) {
            return "One of the fields is too long. Please shorten your answer.";
        }
        if (!EMAIL.matcher(survey.getEmail()).matches()) {
            return "Please enter a valid email address.";
        }
        if (!survey.getDateOfBirth().isEmpty()) {
            try {
                LocalDate birthDate;
                try {
                    birthDate = LocalDate.parse(survey.getDateOfBirth(), US_DATE);
                } catch (DateTimeParseException ignored) {
                    birthDate = LocalDate.parse(survey.getDateOfBirth(), ISO_DATE);
                }
                if (birthDate.isAfter(LocalDate.now())) {
                    return "Date of birth cannot be in the future.";
                }
            } catch (DateTimeParseException ex) {
                return "Date of birth must be MM/DD/YYYY or YYYY-MM-DD.";
            }
        }
        if (!SOURCES.contains(survey.getHeardFrom())
                || !CONTACT_METHODS.contains(survey.getContactMethod())) {
            return "Please choose valid survey options.";
        }
        return null;
    }

    private static String clean(String value) {
        return value == null ? "" : value.trim();
    }

    private static void setFormValues(HttpServletRequest request, Survey survey) {
        request.setAttribute("firstName", html(survey.getFirstName()));
        request.setAttribute("lastName", html(survey.getLastName()));
        request.setAttribute("email", html(survey.getEmail()));
        request.setAttribute("dateOfBirth", html(survey.getDateOfBirth()));
        request.setAttribute("heardFrom", survey.getHeardFrom());
        request.setAttribute("announcements", survey.isAnnouncements());
        request.setAttribute("specialOffers", survey.isSpecialOffers());
        request.setAttribute("contactMethod", survey.getContactMethod());
    }

    /** Escape every untrusted value before JSP EL writes it into an HTML element. */
    private static String html(String value) {
        return value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }
}
