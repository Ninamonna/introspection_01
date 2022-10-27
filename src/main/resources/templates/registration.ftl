<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <div class="mb-3"><h3>Зарегистрировать нового пользователя</h3></div>
    <div>
        ${message?ifExists}
        <@l.login "/registration" true />
        <p><font color="red">Для подтверждения регистрации Вам нужно перейти по ссылке которая придет на указанную почту</font> </p>
    </div>

</@c.page>

