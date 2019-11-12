<a  class="btn btn-primary" data-toggle="collapse"
    href="#collapseExample" role="button" aria-expanded="false"
    aria-controls="collapseExample">
    Message editor
</a>
<div class="collapse <#if message??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input  class="form-control ${(textError??)?string('is-invalid', '')}"
                        type="text" name="text" value="<#if message??>${message.text}</#if>"
                        placeholder="Input message"/>
                <#if textError??>
                    <div class="invalid-feedback">
                        ${textError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <input  class="form-control"
                        type="text" name="tag" value="<#if message??>${message.tag}</#if>"
                        placeholder="Tag"/>
                <#if tagError??>
                    <div class="invalid-feedback">
                        ${tagError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile" />
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>
            <input  class="form-control" type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input  class="form-control" type="hidden" name="id"
                    value="<#if message??>${message.id}</#if>" =/>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Safe message</button>
            </div>
        </form>
    </div>
</div>