<div class=card-columns>
    <#list messages as message>
    <div class="card text-white my-2 bg-secondary" style="width: 18rem;">
        <#if message.filename??>
            <img class="card-img-top" width="200" src="/img/${message.filename}">
        </#if>
        <div class="m-2">
            <#if isAdmin><b>${message.id}</b></#if>
            <span>${message.text}</span>
            <i>${message.tag}</i>
        </div>
        <div class="card-footer text-white-muted">
            ${message.authorName}
        </div>
    </div>
    <#else>
        No message
    </#list>
</div>