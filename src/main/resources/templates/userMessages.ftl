<#import "parts/common.ftl" as c>
<#--<#include "parts/security.ftl">-->

    <@c.page>
<#if isCurrentUser>
    <#include "parts.messageEdit.ftl" />
</#if>
<#include "parts.messageList.ftl" />
    </@c.page>