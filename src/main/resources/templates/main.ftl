<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

    <@c.page>
<div class="form-row">
    <div class="form-group col-md-6">
        <form method="get" action="/main" class="form-inline">
            <input  type="text" name="filter" class="form-control"
                    value="${filter?ifExists}" placeholder="tag">
            <button type="submit" class="btn btn-primary ml-2">find by tag</button>
        </form>
    </div>
</div>

<a  class="btn btn-primary" data-toggle="collapse"
    href="#collapseExample" role="button" aria-expanded="false"
    aria-controls="collapseExample">
    Add new message
</a>
<div class="collapse <#if message??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input  class="form-control ${(textError??)?string('is-invalid', '')}"
                        type="text" name="text" value="<#if message??>${message.text}</#if>"
                        placeholder="Input message" />
                <#if textError??>
                    <div class="invalid-feedback">
                        ${textError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <input  class="form-control"
                        type="text" name="tag" value="<#if message??>${message.tag}</#if>"
                        placeholder="Tag" />
                <#if tagError??>
                    <div class="invalid-feedback">
                        ${tagError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile"/>
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>
            <input  class="form-control"type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">add</button>
            </div>
        </form>
    </div>
</div>
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
    </@c.page>