<#macro login path isRegisterForm>
<form action="${path}" method="post">
    <div class="form-group row col-sm-4">
        <input  class="form-control ${(usernameError??)?string('is-invalid', '')}"
                type="text" name="username" placeholder="User name"
                value="<#if user??>${user.username}</#if>"/>
        <#if usernameError??>
            <div class="invalid-feedback">
                ${usernameError}
            </div>
        </#if>
    </div>
    <div class="form-group row col-sm-4">
        <input  class="form-control ${(passwordError??)?string('is-invalid', '')}"
                type="password" name="password" placeholder="Password"/>
        <#if passwordError??>
            <div class="invalid-feedback">
                ${passwordError}
            </div>
        </#if>
    </div>
    <#if isRegisterForm>
        <div class="form-group row col-sm-4">
            <input  class="form-control ${(passwordError??)?string('is-invalid', '')}"
                    type="password" name="password2" placeholder="Retype password"/>
            <#if password2Error??>
                <div class="invalid-feedback">
                    ${password2Error}
                </div>
            </#if>
        </div>
        <div class="form-group row col-sm-4">
            <input  class="form-control ${(emailError??)?string('is-invalid', '')}"
                    type="email" name="email" placeholder="ex@mp.le"/
                    value="<#if user??>${user.email}</#if>">
            <#if emailError??>
                <div class="invalid-feedback">
                    ${emailError}
                </div>
            </#if>
        </div>
        <div class="col-sm-4">
            <div class="g-recaptcha"
                 data-sitekey="6LeVusAUAAAAALDJgrn0B389zsxHDHxmLJuhVzwZ"></div>
            <#if captchaError??>
                <div class="alert alert-danger row" role="alert">
                    ${captchaError}
                </div>
            </#if>
        </div>
    </#if>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button type="submit" class="btn btn-dark">
        <#if isRegisterForm>
            Create <#else> Login!
        </#if>
    </button>
    <#if !isRegisterForm>
        <label class="form-control-sm-2">
            <a href="/registration">Add new user</a>
        </label>
    </#if>
</form>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button type="submit" class="btn btn-primary btn-sm">Sign out</button>
</form>
</#macro>