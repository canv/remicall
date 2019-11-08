<#import "parts/common.ftl" as c>

    <@c.page>
<table class="table table-dark">
    <thread>
    <tr>
        <th scope="col">Name</th>
        <th scope="col">Role</th>
    </tr>
    </thread>
    <tbody>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td><a href="/user/${user.userId}">edit</a></td>
            </tr>
        </#list>
    </tbody>
</table>
    </@c.page>
