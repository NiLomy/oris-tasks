<html lang="en">
<head>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        $(function(){
            $("#master").change(function(){
                let master = $("#master").val();
                $.post(
                    "${pageContext}/service", {
                        "action": "addServices",
                        "master": master
                    }, function (response) {
                        if (response === "empty") {
                            $("#empty-input").text("Invalid input")
                        } else {
                            $("#service").prop("disabled", false).empty();
                            $.each(response, function (index, name) {
                                $("#service").append('<option class="select-option">' + name + "</option>");
                            })
                        }
                    }
                )
            });
        });

        $(document).on(
            "click", "#make-appointment", function () {
                let phone = $("#phone").val();
                let master = $("#master").val();
                let service = $("#service").val();
                let date = $("#date").val();
                let time = $("#time").val();

                if (phone === "") {
                    $("#phone").addClass("is-invalid")
                }
                if (date === "") {
                    $("#date").addClass("is-invalid")
                }
                if (time === "") {
                    $("#time").addClass("is-invalid")
                }

                $.post(
                    "${pageContext}/service", {
                        "action": "makeAppointment",
                        "phone": phone,
                        "master": master,
                        "service": service,
                        "date": date,
                        "time": time
                    }, function (response) {
                        $("#phone").removeClass("is-invalid")
                        $("#date").removeClass("is-invalid")
                        $("#time").removeClass("is-invalid")
                        if (response === "ok") {
                            alert("Everything is good!")
                            $("#empty-input").text("")
                            $("#phone").addClass("is-valid")
                            $("#date").addClass("is-valid")
                            $("#time").addClass("is-valid")
                        } else if (response === "empty") {
                            $("#empty-input").text("Invalid input")
                        } else if (response === "phoneError") {
                            $("#phone").addClass("is-invalid")
                            $("#empty-input").text("Invalid phone number")
                        } else if (response === "dateError") {
                            $("#date").addClass("is-invalid")
                            $("#empty-input").text("You can't appoint in the past...")
                        } else {
                            alert("This time is currently busy")
                            $("#empty-input").text("")
                        }
                    }
                )
            }
        )
    </script>
    <meta charset="UTF-8">
    <title>Services</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>

<body>

<header>
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <p class="navbar-brand">Plumbing services</p>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext}/service">Services</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext}/about">About us</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>

<div id="header" align="center">
    <H3>Make an appointment</H3>
</div>

<div id="container" align="center" class="content container" style="width: 50%">
    <div class="form-outline mb-4">
        <label>
            Enter your phone number
            <input id="phone" class="form-control" type="tel" pattern="^\+?[0-9\s\-\(\)]+$">
        </label>
    </div>

    <div class="form-outline mb-4">
        <#if masters?has_content>
            <select class="selector form-select" id="master" name="master">
                <option selected disabled value="">Choose one of the masters</option>
                <#list masters as m>
                    <option id="master-option" class="master-option" value="${m.id}">${m.name} ${m.lastname}</option>
                </#list>
            </select>
        </#if>
    </div>

    <div class="form-outline mb-4">
        <select disabled id="service" class="form-select" name="service">
            <option selected disabled value="">Choose one of the services</option>
        </select>
    </div>

    <div class="form-outline mb-4">
        <label for="date">Enter your date</label><input class="form-control" id="date" type="date">
    </div>
    <div class="form-outline mb-4">
        <label for="time">Enter your time</label><input id="time" class="form-control" type="time">
    </div>

    <button id="make-appointment" type="submit" class="btn btn-primary btn-block mb-4 form-control">Submit</button>
    <div id="empty-input" class="form-control"></div>
</div>
</body>
</html>
