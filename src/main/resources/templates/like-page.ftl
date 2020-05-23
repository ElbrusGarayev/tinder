<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Like page</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body style="background-color: #f5f5f5;">
<form method="post">
    <div class="container">
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <div class="profile bg-light rounded p-2">
                    <div class="row">
                        <div class="col-md-12 text-center">
                            <img src=${image_url} alt="" class="mx-auto rounded img-fluid" style="max-height: 400px;">
                            <h5 class="mt-2 text-truncated">${status}</h5>
                            <h3 class="mt-2 text-truncated">${name} ${surname}</h3>
                            <br>
                        </div>
                        <div class="col-md-6">
                            <button type="submit" class="btn btn-outline-danger btn-block mb-2" name="button"
                                    value="dislike"><span
                                        class="fa fa-times"></span> Dislike
                            </button>
                        </div>
                        <div class="col-md-6">
                            <button type="submit" class="btn btn-outline-success btn-block mb-2" name="button"
                                    value="like"><span
                                        class="fa fa-heart"></span> Like
                            </button>
                        </div>
                        <div class="col-md-6">
                            <button type="submit" class="btn btn-outline-info btn-block mb-2" name="button"
                                    value="likes">
                                <i class="fas fa-bookmark"></i> Favorites
                            </button>
                        </div>
                        <div class="col-md-6">
                            <button type="submit" class="btn btn-outline-warning btn-block mb-2" name="button"
                                    value="logout">
                                <i class="fas fa-sign-out-alt"></i> Log out
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3"></div>
    </div>
</form>
</body>
</html>