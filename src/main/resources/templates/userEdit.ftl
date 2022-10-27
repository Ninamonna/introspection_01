<#import "parts/common.ftl" as c>
<@c.page>
    <h1>Изменить роль</h1>
    <form method="post" action ="/user"> <!-- action направляет на страницy-->
        <input type="text" name="username" value="${user.username}">
        <#list roles as role>
            <div>
                <label>
                    <input
                            type="checkbox"
                            name="${role}"
                            ${user.roles?seq_contains(role)?string("checked","")}
                            <!-- пометка роль активна/невктивна
                            seq_contains проверяет роль
                            string преобразует в строку
                            ("checked","") чекбокс выбран либо ничего -->
                    />${role}
                </label>
            </div>
        </#list>
        <input type="hidden" value="${user.id}" name="userId"> <!--индефикатор пользователя отпр на сервер -->
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button type="submit">Сохранить</button>
    </form>
</@c.page>