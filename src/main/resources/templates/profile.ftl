<#import "parts/common.ftl" as c>

    <@c.page>
<h5>${username}</h5>
${message?ifExists}
<form method="post">
    <div class="form-group row col-sm-4">
        <input type="password" name="password" class="form-control" placeholder="Password" />
    </div>
    <div class="form-group row col-sm-4">
        <input  type="email" name="email" class="form-control"
                placeholder="ex@mp.le" value="${email!''}" />
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button type="submit" class="btn btn-primary">Save changes</button>
</form>
    </@c.page>