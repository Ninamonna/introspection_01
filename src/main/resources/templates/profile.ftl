<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>

<div class="mb-3" xmlns=""><h3>Редактировать свою учетную запись</h3></div>
    <form method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Введите новый Email :</label>
            <div class="col-sm-3">
                <input type="email"
                       name="email"
                       class="form-control"
                       placeholder="some@some.com"
                       value="${email!''}"/>
            </div>
        </div>
    <br>
        <div class="form-group row">
        <label class="col-sm-2 col-form-label">Введите новый Пароль :</label>
        <div class="col-sm-3">
            <input type="password"
                   name="password"
                   class="form-control"
                   placeholder="Password"/>
        </div>
        </div>
    <br>
    <div class="bd-callout" >
        <strong>Предупреждение.</strong> Для подтверждения изменений Вам нужно перейти по ссылке которая придет на указанную почту
    </div>


    <br>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn btn-primary" type="submit">Изменить</button>
    <br>
    <br>
    <br>
    <#if isAdmin>
        <div class="mb-3"><h3><a href="/user">Меню администратора</a></h3></div>
    </#if>
        <style type="text/css">
            .bd-callout {
                padding: 1.25rem;
                margin-top: 1.25rem;
                margin-bottom: 1.25rem;
                background-color: rgba(232, 58, 66, 0.42);
                border-left: 0.25rem;
            }
        </style>

</@c.page>

