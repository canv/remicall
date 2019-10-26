<#import "parts/common.ftl" as c>

    <@c.page>
List of users

<table>
    <thread>
    <tr>
        <th>Name</th>
        <th>Role</th>
        <th></th>
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