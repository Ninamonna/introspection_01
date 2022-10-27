<div class="collapse <#if note??>show</#if>" id="collapseExample">
    <h1>Редактировать запись</h1>
    <br/>
    <div class="form-group row">
        <form method="post" enctype="multipart/form-data">

            <div class="col-2">
                <label for="date" class="form-label">Дата </label>
                <input type="date" pattern="yyyy-MM-dd"
                       class="form-control ${(dateError??)?string('is-invalid','')}"
                       value="<#if note??>${note.date?date?string('yyyy-MM-dd')}</#if>"
                       name="date"/>
                <#if dateError??>
                    <div class="invalid-feedback">
                        ${dateError}
                    </div>
                </#if>
            </div>
            <br/>

            <div class="col-8">
                <label for="good" class="form-label">
                    Что Вы сделали правильно/хорошо? Как можно усилить в будущем эти преимущества?</label>
                <input type="text"
                       class="form-control
               ${(goodError??)?string('is-invalid', '')}"
                       value="<#if note??>${note.good}</#if>"
                       name="good"/>
                <#if goodError??>
                    <div class="invalid-feedback">
                        ${goodError}
                    </div>
                </#if>
            </div>
            <br/>


            <div class="col-8">
                <label for="bad" class="form-label">
                    Что Вы сделали неправильно/плохо? Как можно в дальнейшем это исправить?</label>
                <input type="text" class="form-control
            ${(badError??)?string('is-invalid', '')}"
                       value="<#if note??>${note.bad}</#if>"
                       name="bad"/>
                <#if badError??>
                    <div class="invalid-feedback">
                        ${badError}
                    </div>
                </#if>
            </div>
            <br/>

            <div class="col-8">
                <label for="another" class="form-label">
                    Что можно было сделать еще/лучше? Почему этого не было сделно?</label>
                <input type="text" class="form-control
            ${(anotherError??)?string('is-invalid', '')}"
                       value="<#if note??>${note.another}</#if>"
                       name="another"/>
                <#if anotherError??>
                    <div class="invalid-feedback">
                        ${anotherError}
                    </div>
                </#if>
            </div>
            <br/>

            <div class="col-8">
                <label for="targetApproach" class="form-label">
                    Приблизил ли текущий день к достижению моих долгосрочных целей?</label>
                <input type="text" class="form-control
            ${(targetApproachError??)?string('is-invalid', '')}"
                       value="<#if note??>${note.targetApproach}</#if>"
                       name="targetApproach"/>
                <#if targetApproachError??>
                    <div class="invalid-feedback">
                        ${targetApproachError}
                    </div>
                </#if>
            </div>
            <br/>

            <div class="col-8">
                <label for="together" class="form-label">
                    Что я буду делать завтра, чтобы усилить свои сильные стороны?</label>
                <input type="text" class="form-control
            ${(togetherError??)?string('is-invalid', '')}"
                       value="<#if note??>${note.together}</#if>"
                       name="together"/>
                <#if togetherError??>
                    <div class="invalid-feedback">
                        ${togetherError}
                    </div>
                </#if>
            </div>
            <br/>
            <br/>
            <hr class="my-4" width="67%"/>
            <div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <input type="hidden" name="id" value="<#if note??>${note.id}</#if>" />
                <button class="w-30 btn btn-primary btn-lg" type="submit">Изменить</button>
            </div>

        </form>


    </div>
</div>
<br/>




