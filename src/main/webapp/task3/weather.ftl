<html lang="en">
<#include "base.ftl">

<#macro title>Weather</#macro>

<#macro content1>
    <div style="font-size:150%; text-align: center">
        <b>
            Here's your results!
        </b>
    </div>
</#macro>

<#macro content2>
    <div style="font-size:110%; text-align: center">
        <b>
            Town:
        </b>
        <#if townName?has_content>
            ${townName}
        </#if>
        <br>
        <b>
            Temperature:
        </b>
        <#if temperature?has_content>
            ${temperature}
        </#if>
        <br>
        <b>
            Humidity:
        </b>
        <#if humidity?has_content>
            ${humidity}
        </#if>
        <br>
        <b>
            Precipitation:
        </b>
        <#if precipitation?has_content>
            ${precipitation}
        </#if>
    </div>
</#macro>

</html>
