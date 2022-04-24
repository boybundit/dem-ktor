<#-- @ftlvariable name="books" type="kotlin.collections.MutableList<net.bundit.model.Book>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <script src="https://cdn.tailwindcss.com"></script>
    <title>My favorite books</title>
</head>
<body class="m-2">
<h1 class="mt-2 mb-5 text-3xl font-bold">My favorite books</h1>
<hr class="mt-2 mb-2">
<#list books?reverse as book>
    <div>
        <h3>
            <a href="/books/${book.id}"
               class="underline text-blue-600 hover:text-blue-800 visited:text-purple-600">${book.title}</a>
        </h3>
        <p>
            ${book.author}
        </p>
    </div>
</#list>
<hr class="mt-2 mb-2">
</body>
</html>