<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Survey Submitted - Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/survey.css">
</head>
<body>
<main class="survey-page result-page">
    <img class="brand" src="${pageContext.request.contextPath}/images/murach-mark.svg"
         alt="Murach" width="134" height="126">
    <h1>Thank you for completing our survey!</h1>
    <p>Here is the information you submitted:</p>
    <dl class="response-list">
        <dt>First Name</dt><dd>${firstName}</dd>
        <dt>Last Name</dt><dd>${lastName}</dd>
        <dt>Email</dt><dd>${email}</dd>
        <dt>Date of Birth</dt><dd>${dateOfBirthDisplay}</dd>
        <dt>How you heard about us</dt><dd>${heardFromDisplay}</dd>
        <dt>Announcements</dt><dd>${announcements ? 'Yes' : 'No'}</dd>
        <dt>Email announcements</dt><dd>${specialOffers ? 'Yes' : 'No'}</dd>
        <dt>Contact preference</dt><dd>${contactDisplay}</dd>
    </dl>
    <p><a class="return-link" href="${pageContext.request.contextPath}/week2">Return to Survey</a></p>
    <p><a href="${pageContext.request.contextPath}/index.html">Back to Email List</a></p>
</main>
</body>
</html>
