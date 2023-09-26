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
        <#if weatherAttributes?has_content>
            <b>
                Temperature:
            </b>
            ${weatherAttributes.temperature}
            <br>
            <b>
                Humidity:
            </b>
            ${weatherAttributes.humidity}
            <br>
            <b>
                Precipitation:
            </b>
            ${weatherAttributes.precipitation}
        </#if>
    </div>
</#macro>

</html>
