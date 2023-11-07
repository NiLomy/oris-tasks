<html lang="en">
<#include "base.ftl">

<#macro content0></#macro>
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
            <table align="center">
                <tr>
                    <td>
                        <b>
                            Login:
                        </b>
                    </td>
                    <td>
                        <input type="text" name="login"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <b>
                            Password:
                        </b>
                    </td>
                    <td>
                        <input type="password" name="password"/>
                    </td>
                </tr>
            </table>
            <br>
            <input type="submit" value="login"/>
        </form>
    </div>
</#macro>
</html>
