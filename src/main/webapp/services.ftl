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
                        $("#service").prop("disabled", false);
                        $("#service").find(".select-option").remove();
                        $.each(response, function (index, name) {
                            $("#service").append('<option class="select-option">' + name + "</option>");
                        })
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

                $.post(
                    "${pageContext}/service", {
                        "action": "makeAppointment",
                        "phone": phone,
                        "master": master,
                        "service": service,
                        "date": date,
                        "time": time
                    }, function (response) {
                        $("#edit-status").text(response)
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
<div id="header">
    <H3>Make an appointment</H3>
</div>

<div id="container" align="center" class="content">
    <div class="form-outline mb-4">
        <label>
            Enter your phone number
            <input id="phone" class="form-control" type="text" pattern="^\+?[0-9\s\-\(\)]+$">
        </label>
    </div>

    <div class="form-outline mb-4">
        <#if masters?has_content>
            <select class="selector" id="master" name="master">
                <option selected disabled value="">Choose one of this categories</option>
                <#list masters as m>
                    <option id="master-option" class="master-option">${m.name} ${m.lastname}</option>
                </#list>
            </select>
        </#if>
    </div>

    <div class="form-outline mb-4">
        <select disabled id="service" name="service">
            <option selected disabled value="">Choose one of this categories</option>
        </select>
    </div>

    <div class="form-outline mb-4">
        <label for="date">Enter your date</label><input id="date" type="date">
    </div>
    <div class="form-outline mb-4">
        <label for="time">Enter your time</label><input id="time" type="time">
    </div>

    <button id="make-appointment" type="submit" class="btn btn-primary btn-block mb-4">Submit</button>
</div>
</body>
</html>