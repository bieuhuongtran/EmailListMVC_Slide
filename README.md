# Email List MVC + Survey (Murach Java Servlets & JSP)

The existing **Email List MVC** exercise is preserved. A new **Survey** exercise recreates the provided screenshot and uses the same Maven WAR / Tomcat 9 setup.

## Pages

- **Email List:** `/email-list-mvc/` (existing page) and `/email-list-mvc/emailList` (existing servlet)
- **Survey:** `/email-list-mvc/survey` (new `SurveyServlet`, JSP form, CSS, and receipt)
- On the Email List page, click **Take our Survey**.

## Run on Windows with Java 17+ / Maven / Tomcat 9

```powershell
mvn clean package
# Copy target\email-list-mvc.war to your Tomcat 9 webapps folder.
# Start Tomcat using bin\startup.bat or bin\catalina.bat run.
```

Open: **http://localhost:8080/email-list-mvc/survey**

**Important:** Requires Tomcat **9** (`javax.servlet`), not Tomcat 10+ (`jakarta.servlet`). Maven compiles at `--release 17`, so JDK 17, 21 or 27 can run the build. If you already deployed the original app, replace its WAR and restart/redeploy Tomcat.

## Docker (optional)

```bash
docker build -t email-list-survey .
docker run --rm -p 8080:8080 email-list-survey
```

Docker uses `ROOT.war`, so Survey runs at **http://localhost:8080/survey**.

## New files

- `src/main/java/murach/survey/Survey.java`: survey model
- `src/main/java/murach/survey/SurveyServlet.java`: validation/controller; `@WebServlet("/survey")`
- `src/main/webapp/WEB-INF/views/survey.jsp`: survey form
- `src/main/webapp/WEB-INF/views/survey-result.jsp`: confirmation page
- `src/main/webapp/styles/survey.css`: page styling, responsive and separate from existing `main.css`
- `src/main/webapp/images/murach-mark.svg`: simple hand-drawn logo approximation

The only change to the original exercise is a link to `/survey` in `src/main/webapp/index.html`. The original `web.xml`, controller, model, `thanks.jsp`, and `main.css` stay intact.

## Survey behavior

- Text fields: first name, last name, email and optional date of birth (MM/DD/YYYY or YYYY-MM-DD)
- Radio selection: search engine / word of mouth / social media / other
- Two separate opt-in checkboxes
- Select: email or postal mail / email only / postal mail only
- Browser + server validation, preserves entered values on server-side errors, and displays a result page after successful submission
- Input escaped before display to reduce risk of reflected HTML injection
- **Demonstration only:** survey submissions are not persisted to database or sent as emails.

## Push to the supplied GitHub repository

The archive is a full project snapshot. If you have the repo cloned locally, copy the new files and the updated `index.html` into your clone; then:

```powershell
git add src/main/java/murach/survey src/main/webapp/WEB-INF/views src/main/webapp/styles/survey.css src/main/webapp/images/murach-mark.svg src/main/webapp/index.html README.md
git commit -m "feat: add Murach survey MVC page"
git push origin main
```

Or, for a full snapshot extracted to a new folder, initialize it and push after confirming `origin` and permissions:

```powershell
git init
git branch -M main
git remote add origin https://github.com/bieuhuongtran/EmailListMVC_Slide.git
# Before pushing, reconcile with the existing history; do not force-push.
```

Avoid force-pushing a snapshot over the existing repository history. Use the first approach when possible.

## One-command helpers on Windows

From the extracted project folder:

```powershell
# Build and run on local Tomcat 9 (adjust path if needed)
.\run-tomcat.ps1

# Push only the new Survey files into the real existing GitHub history
.\push-to-github.ps1
```

`push-to-github.ps1` uses an ordinary clone/commit/push; it **never force-pushes** or destroys the existing repo files. GitHub credentials with write permission are needed. PowerShell execution policy may require `powershell -ExecutionPolicy Bypass -File .\push-to-github.ps1` on trusted local files.
