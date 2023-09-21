<html lang="en">
<#include "base.ftl">

<#macro title>Users</#macro>

<#macro content>
    Hello,
    <br>
    <#if users?has_content>
        <#list users as u>
            ${u}
            <br>
        </#list>
    </#if>
</#macro>

</html>
