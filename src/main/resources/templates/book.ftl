<#-- @ftlvariable name="book" type="net.bundit.model.Book" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <script src="https://cdn.tailwindcss.com"></script>
    <title>${book.title}</title>
</head>
<body class="m-2">
    <h1 class="mt-2 mb-5 text-3xl font-bold">${book.title}</h1>
    <p>${book.author}</p>
</body>
</html>