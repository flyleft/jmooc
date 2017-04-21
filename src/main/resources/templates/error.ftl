<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head >
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Your request cannot be processed</title>
    <link rel="stylesheet" th:href="@{/css/error.css}">
</head>
<body>
<div class="container">
    <h1>${status}<span>:(</span></h1>

    <p>${title}</p>

    <span>${error}</span>
    <br/>
</div>
</body>
</html>