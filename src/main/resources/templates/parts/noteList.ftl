<#include "security.ftl">
<#import "pager.ftl" as p>
<h3>Мой дневник самоанализа:</h3>
<@p.pager url page/>
<hr class="my-4" width="67%"/>
<div class="table-responsive">

    <table class="table">
        <thead class="table-primary">
        <tr>
            <th scope="col"><b>Дата</b></th>
            <th scope="col"><b>Хорошо</b></th>
            <th scope="col"><b>Плохо</b></th>
            <th scope="col"><b>Исправить</b></th>
            <th scope="col"><b>Приближение цели</b></th>
            <th scope="col"><b>Завтра</b></th>
            <th scope="col"><b>Редактировать</b></th>
        </tr>
        </thead>
        <tbody>
        <#list page.content as note>
            <tr scope="row">
            <td><b>${note.date?string('dd.MM.yyyy')}</b></td>
            <td><i>${note.good}</i></td>
            <td><i>${note.bad}</i></td>
            <td><i>${note.another}</i></td>
            <td><i>${note.targetApproach}</i></td>
            <td><i>${note.together}</i></td>
            <td>
                <i>
                    <div class="card-footer text-muted">
                        <a class="btn btn-primary"
                           href="/user-notes/${note.author.id}?note=${note.id}">
                            Изменить</a></div>
                </i>
                <i>
                    <div class="card-footer text-muted">
                        <a class="btn btn-warning" href="/del-user-note/${note.author.id}?note=${note.id}">Delete</a>
                    </div>
                </i>

            </td>

            </tr>
        <#else>
            <tr>
            <td>Нет записей</td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
