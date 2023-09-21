<html lang="en">
<#include "base.ftl">

<#macro title>Login</#macro>

<#macro content1>
    <div style="font-size:150%; text-align:center">
        <b>
            Please log in
        </b>
    </div>
</#macro>
<#macro content2>
    <div style="font-size:110%; text-align: center">
        <form action="login" method="post">
            <b>
                Login:
            </b>
            <input type="text" name="login"/>
            <br>
            <b>
                Password:
            </b>
            <input type="password" name="password"/>
            <br>
            <br>
            <input type="submit" value="login"/>
        </form>
    </div>
</#macro>
</html>
