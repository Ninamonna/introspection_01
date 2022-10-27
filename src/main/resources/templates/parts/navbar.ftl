<#include "security.ftl">
<#import "login.ftl" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-info">
    <div class="container-fluid">
        <a class="navbar-brand mr-10" href="/"><b>Дневник самоанализа</b></a>

        <button class="navbar-toggler" type="button"
                data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>


        <div class="collapse navbar-collapse me-center" id="navbarsExample02">
            <ul class="navbar-nav me-auto">
                <#if user??>
                    <li class="nav-item">
                        <a class="nav-link" href="/main">Добавить новую запись</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/userNotes">Дневник</a>
                    </li>
                <li class="nav-item">
                    <a class="nav-link" href="/user/profile">Личный кабинет <b>${name}</b></a>
                </li>
            </ul>
            <div class="navbar-text mr-3">
            <@l.logout />
            </div>
            </#if>
            <#if !user??>
                <li class="nav-item">
                    <a class="nav-link" href="/login">Войти</a>
                </li>
            </#if>

        </div>
        </div>
</nav>