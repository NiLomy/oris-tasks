<html lang="en">
<#include "base.ftl">

<#macro title>Town</#macro>

<#macro content0>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        $(document).on(
            "click", "#ajax-button", function () {
                let town = $("#ajax-input").val();

                $.get("/weather?town=" + town, function (response) {
                    $("#ajax-response").text(response);
                })
            }
        )
    </script>
</#macro>

<#macro content1>
    <div style="font-size:150%; text-align:center">
        Enter the town whose whether you want to know
    </div>
</#macro>

<#macro content2>
    <div align="center">
        <form>
            <b>Town:</b>
            <br>
            <br>
            <input type="text" id="ajax-input">
            <br>
            <br>
            <input type="button" id="ajax-button" value="Get message">
        </form>
        <br>
        <div id="ajax-response">
        </div>
    </div>
</#macro>
</html>
