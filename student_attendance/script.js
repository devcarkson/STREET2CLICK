``
`javascript
$(document).ready(function() {
    // Login form validation
    $("form[action='UserServlet']").submit(function(e) {
        var username = $("#username").val();
        var password = $("#password").val();

        if(username.trim() === "" || password.trim() === "") {
            alert("Username and password are required!");
            e.preventDefault();
        }
    });
});
`
``