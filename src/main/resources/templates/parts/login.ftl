<#macro login path isRegisterForm>

    <form action="${path}" method="post">

        <div class="form-group row">
            <label class="col-sm-1 col-form-label">Имя :</label>
            <div class="col-sm-3">
                <input type="text" name="username" value="<#if user??>${user.username}</#if>"
                       class="form-control ${(usernameError??)?string('is-invalid', '')}"
                       placeholder="Иван Иванов " />
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>
        <br/>

        <div class="form-group row">
            <label class="col-sm-1 col-form-label">Пароль :</label>
            <div class="col-sm-3">
                <input type="password" name="password"
                       class="form-control ${(passwordError??)?string('is-invalid', '')}"
                       placeholder="пароль" />
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>
        <br/>

        <#if isRegisterForm>

            <div class="form-group row">
                <label class="col-sm-1 col-form-label">повторить Пароль :</label>
                <div class="col-sm-3">
                    <input type="password" name="password2"
                           class="form-control ${(password2Error??)?string('is-invalid', '')}"
                           placeholder="повторите пароль" />
                    <#if password2Error??>
                        <div class="invalid-feedback">
                            ${password2Error}
                        </div>
                    </#if>

                </div>
            </div>
            <br/>


        <div class="form-group row">
            <label class="col-sm-1 col-form-label">Email :</label>
            <div class="col-sm-3">
                <input type="email" name="email"
                       value="<#if email??>${user.email}</#if>"
                       class="form-control ${(emailError??)?string('is-invalid', '')}"
                       placeholder="some@some.com" />
                <#if emailError??>
                    <div class="invalid-feedback">
                        ${emailError}
                    </div>
                </#if>
            </div>
        </div>
            <br/>
            <div class="col-sm-3">
                <div class="g-recaptcha" data-sitekey="6LeYYV0hAAAAABbr-Y7pXk4jeu5xb6JgW4DeOs91"></div>
                <#if captchaError??>
                    <div class="alert alert-danger" role="alert">
                        ${captchaError}
                    </div>
                </#if>
            </div>
        </#if>
        <br/>
        <br/>

        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit"><#if isRegisterForm>Создать<#else>Войти</#if></button>
        <br/>
        <br/>

        <#if !isRegisterForm><a href="/registration">Добавить нового пользователя</a></#if>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit">Выйти</button>
    </form>
</#macro>