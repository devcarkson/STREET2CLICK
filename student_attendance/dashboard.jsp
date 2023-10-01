```jsp
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-12">
                <h2 class="text-center">Dashboard</h2>
                <div class="card">
                    <div class="card-header">
                        Welcome, ${sessionScope.user.username}!
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">Your Tasks</h5>
                        <p class="card-text">
                            <!-- Here you can add a loop to display the tasks assigned to the user -->
                        </p>
                        <a href="logout.jsp" class="btn btn-primary">Logout</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="script.js"></script>
</body>
</html>
```
