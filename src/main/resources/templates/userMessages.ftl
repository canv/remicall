<#import "parts/common.ftl" as c>

    <@c.page>
<h3>${userChannel.username}</h3>
<#if !isCurrentUser>
    <#if isSubscriber>
        <a class="btn btn-info" href="/user/unsubscribe/${userChannel.userId}">
            Unsubscribe
        </a>
    <#else>
        <a class="btn btn-info" href="/user/subscribe/${userChannel.userId}">
            Subscribe
        </a>
    </#if>
</#if>
<div class="container my-3">
    <div class="row">
        <div class="col">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-text">Subscriptions
                        <a  class="badge badge-primary"
                            href="/user/subscriptions/${userChannel.userId}/list">
                            ${subscriptionsCount}
                        </a>
                    </h4>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-text">Subscribers
                        <a  class="badge badge-primary"
                            href="/user/subscribers/${userChannel.userId}/list">
                            ${subscribersCount}
                        </a>
                    </h4>
                </div>
            </div>
        </div>
    </div>
</div>
<#if isCurrentUser>
    <#include "parts/messageEdit.ftl" />
</#if>
<#include "parts/messageList.ftl" />
    </@c.page>