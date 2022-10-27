<#import "parts/common.ftl" as c>
<@c.page>
    <h3>Добавить новую запись</h3>
    <div>
        <form method="post" enctype="multipart/form-data">
            Дата
            <div class="col-2">
                <label>
                    <input type="date" pattern="yyyy-MM-dd"
                           class="form-control ${(dateError??)?string('is-invalid', '')}"
                           value="<#if note??>${note.date?date?string('yyyy-MM-dd')}</#if>"
                           name="date"/>
                </label>
                <#if dateError??>
                    <div class="invalid-feedback">
                        ${dateError}
                    </div>
                </#if>
            </div>
            <div>
                <br/>
            Что Вы сделали правильно/хорошо? Как можно усилить в будущем эти преимущества?
            <label class="col-8">
                    <input type="text" class="form-control
                    ${(goodError??)?string('is-invalid', '')}"
                           value="<#if note??>${note.good}</#if>"
                           name="good"/>
                </label>
                <#if goodError??>
                    <div class="invalid-feedback">
                        ${goodError}
                    </div>
                </#if>
            </div>
            <div>
                <br/>
            Что Вы сделали неправильно/плохо? Как можно в дальнейшем это исправить?
            <label class="col-8">
                    <input type="text" class="form-control
                ${(badError??)?string('is-invalid', '')}"
                           value="<#if note??>${note.bad}</#if>"
                           name="bad"/>
                </label>
                <#if badError??>
                    <div class="invalid-feedback">
                        ${badError}
                    </div>
                </#if>
            </div>
            <div>
                <br/>
            Что можно было сделать еще/лучше? Почему этого не было сделано?
                <label class="col-8">
                    <input type="text" class="form-control
                ${(anotherError??)?string('is-invalid', '')}"
                           value="<#if note??>${note.another}</#if>"
                           name="another"/>
                </label>
                <#if anotherError??>
                    <div class="invalid-feedback">
                        ${anotherError}
                    </div>
                </#if>
            </div>
            <div>
                <br/>
            Приблизил ли текущий день к достижению моих долгосрочных целей?
                <label class="col-8">
                    <input type="text" class="form-control
                ${(targetApproachError??)?string('is-invalid', '')}"
                           value="<#if note??>${note.targetApproach}</#if>"
                           name="targetApproach"/>
                </label>
                <#if targetApproachError??>
                    <div class="invalid-feedback">
                        ${targetApproachError}
                    </div>
                </#if>
            </div>
            <div>
                <br/>
            Что я буду делать завтра, чтобы усилить свои сильные стороны?
            <label class="col-8">
                    <input type="text" class="form-control
                ${(togetherError??)?string('is-invalid', '')}"
                           value="<#if note??>${note.together}</#if>"
                           name="together"/>
                </label>
                <#if togetherError??>
                    <div class="invalid-feedback">
                        ${togetherError}
                    </div>
                </#if>
            </div>
            <br/>
            <div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button class="w-30 btn btn-primary btn-lg" type="submit">Добавить запись</button>
            </div>
        </form>
    </div>


</@c.page>