<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>People list</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>

<div class="container">
    <div class="row justify-content-around">
        <div class="col-md-8">
            <div class="panel panel-default user_panel">
                <div class="panel-heading bg-warning" STYLE="text-align: center;">
                    <h3 class="panel-title">FAVORITES</h3>
                </div>
                <div class="panel-body">
                    <div class="table-container">
                        <table class="table-users table" border="0">
                            <tbody>
                            <#list users as user>
                                <tr onclick="location.href='/messages/${user.getId()}'" class="bg-danger">
                                    <td>
                                        <div class="avatar-img">
                                            <img class="img-circle" src="${user.getUrl()}">  
                                        </div>
                                    </td>
                                    <td class="align-middle">
                                        ${user.getName()}
                                        ${user.getSurname()}
                                    </td>
                                    <td class="align-middle">
                                        ${user.getStatus()}
                                    </td>
                                    <td class="align-middle">
                                        Last Login: ${user.getLastSeen()}
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>