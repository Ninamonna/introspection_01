<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>

    <div class="mb-3"><h3>Войти в свою учетную запись</h3></div>
<#--    <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>-->
<#--        <div class="alert alert-danger" role="alert">-->
<#--            ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}-->
<#--        </div>-->
<#--    </#if>-->
    <#if message??>
        <div class="alert alert-${messageType}" role="alert">
            ${message}
        </div>
    </#if>
    <br>
    <@l.login "/login" false/>
</@c.page>




