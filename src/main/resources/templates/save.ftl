<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>
<@c.page>
    <style>
        .panel {
            background: #31d2ee; /* Цвет фона */
            color: white; /* Цвет текста */
            padding: 20px; /* Поля вокруг текста */
            text-align: justify; /* Выравнивание по ширине */
            text-decoration-width: auto;
        }
    </style class="panel">
    <div>
        <h2>Запись сохранена!</h2>
        <img src="https://static.tildacdn.com/tild3236-3032-4564-a362-336436313765/check02.gif"
             class="rounded mx-auto d-block"
             data-image-width="500" data-image-height="500">
        <br>
        <#if user??>
        <a href="/main">Новая запись</a>
        <br>
        <a href="/user-notes/${currentUserId}">Перейти к дневнику</a>
        </#if>
    </div>
</@c.page>