<#import "parts/common.ftl" as c>

<@c.page>
User editor

<form action="/user" method="post">
    <input type="text" name="username" value="${user.username}">
    <#list roles as role>
    <div>
<#--    checks for the presence of an element in the collection (returns a boolean who is convertible to String)-->
    <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
    </div>
    </#list>
<input type="hidden" value="${user.userId}" name="userID">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button type="submit">Save</button>
</form>
</@c.page>
