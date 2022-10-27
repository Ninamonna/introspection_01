<#import "parts/common.ftl" as c>
<@c.page>
    <h1>Список пользователей</h1>
    <table>
        <thead>
            <tr>
                <th>Имя</th>
                <th>Роль</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <#list users as user>
                <tr>
                    <td>${user.username}</td>
                    <td> <#list user.roles as role>${role}<#sep>, <!--сепаратор разделяет запятой --></#list> </td>
                    <td><a href="/user/${user.id}">Редактировать</a> </td>
                </tr>
            </#list>
        </tbody>

    </table>

</@c.page>
