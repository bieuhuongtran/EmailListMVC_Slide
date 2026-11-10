<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Survey - Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/survey.css">
</head>
<body>
<main class="survey-page">
    <img class="brand" src="${pageContext.request.contextPath}/images/murach-mark.svg"
         alt="Murach" width="134" height="126">

    <h1>Survey</h1>
    <p class="intro">If you have a moment, we'd appreciate if you would fill out this<br class="wide-only"> survey.</p>

    <form action="${pageContext.request.contextPath}/survey" method="post" accept-charset="UTF-8">
        <p class="error-message" role="alert" ${empty errorMessage ? 'hidden' : ''}>${errorMessage}</p>

        <fieldset class="information-section">
            <legend>Your information:</legend>
            <div class="form-grid">
                <label for="firstName">First Name</label>
                <input id="firstName" name="firstName" type="text" autocomplete="given-name"
                       maxlength="80" required value="${firstName}">

                <label for="lastName">Last Name</label>
                <input id="lastName" name="lastName" type="text" autocomplete="family-name"
                       maxlength="80" required value="${lastName}">

                <label for="email">Email</label>
                <input id="email" name="email" type="email" autocomplete="email"
                       maxlength="160" required value="${email}">

                <label for="dateOfBirth">Date of Birth</label>
                <input id="dateOfBirth" name="dateOfBirth" type="text" autocomplete="bday"
                       maxlength="10" title="MM/DD/YYYY or YYYY-MM-DD"
                       value="${dateOfBirth}">
            </div>
        </fieldset>

        <fieldset class="heard-section">
            <legend>How did you hear about us?</legend>
            <div class="radio-options">
                <label><input type="radio" name="heardFrom" value="Search engine"
                       ${heardFrom == 'Search engine' ? 'checked' : ''}> Search engine</label>
                <label><input type="radio" name="heardFrom" value="Word of mouth"
                       ${heardFrom == 'Word of mouth' ? 'checked' : ''}> Word of mouth</label>
                <label><input type="radio" name="heardFrom" value="Social Media"
                       ${heardFrom == 'Social Media' ? 'checked' : ''}> Social Media</label>
                <label><input type="radio" name="heardFrom" value="Other"
                       ${heardFrom == 'Other' ? 'checked' : ''}> Other</label>
            </div>
        </fieldset>

        <fieldset class="announcements-section">
            <legend>Would you like to receive announcements<br class="wide-only">
                about new CDs and special offers?</legend>
            <div class="check-options">
                <label><input type="checkbox" name="announcements" value="yes"
                       ${announcements ? 'checked' : ''}> YES, I'd like that.</label>
                <label><input type="checkbox" name="specialOffers" value="yes"
                       ${specialOffers ? 'checked' : ''}> YES, please send me email announcements.</label>
            </div>
        </fieldset>

        <div class="contact-row">
            <label for="contactMethod">Please contact me by:</label>
            <select name="contactMethod" id="contactMethod">
                <option value="Email or postal mail" ${contactMethod == 'Email or postal mail' ? 'selected' : ''}>Email or postal mail</option>
                <option value="Email only" ${contactMethod == 'Email only' ? 'selected' : ''}>Email only</option>
                <option value="Postal mail only" ${contactMethod == 'Postal mail only' ? 'selected' : ''}>Postal mail only</option>
            </select>
        </div>

        <button type="submit" class="submit-button">Submit</button>
    </form>
    <p class="back-link"><a href="${pageContext.request.contextPath}/index.html">Back to Email List</a></p>
</main>
</body>
</html>
