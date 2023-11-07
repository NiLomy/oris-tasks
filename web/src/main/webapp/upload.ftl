<html lang="en">
<#include "base.ftl">

<#macro title>Upload file</#macro>

<#macro content>
    <p>Upload file</p>
    <form action="upload" method="post">
        <input type="file" name="file">
        <br>
        <input type="submit" value="upload">
    </form>
</#macro>

</html>
