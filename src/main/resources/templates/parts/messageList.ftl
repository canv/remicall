<#include "security.ftl">

<div class="card-columns" id="message-list">
    <#list messages as message>
        <div    class="card text-white my-2 bg-secondary"
                data-id="${message.id}" style="width: 18rem;">
            <#if message.filename??>
                <img class="card-img-top" width="200" src="/img/${message.filename}">
            </#if>
            <div class="m-2">
                <#if isAdmin><b>${message.id}</b></#if>
                <h5>
                    <span>${message.text}</span>
                    <i>${message.tag}</i>
                </h5>
            </div>
            <div class="card-footer text-white-muted">
                <a  class="badge badge-pill badge-light"
                    href="/user-messages/${message.author.userId}">
                        ${message.authorName}
                </a>
                <#if message.author.userId == currentUserId>
                    <a  class="badge badge-pill badge-info"
                        href="/user-messages/${message.author.userId}?message=${message.id}">
                        Edit
                    </a>
                </#if>
            </div>
        </div>
        <#else>
            No message
    </#list>
</div>